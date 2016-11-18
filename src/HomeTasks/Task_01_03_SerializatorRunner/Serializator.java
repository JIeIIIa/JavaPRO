package HomeTasks.Task_01_03_SerializatorRunner;

import java.io.*;
import java.lang.reflect.Field;
import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by JIeIIIa on 18.11.2016.
 */
public class Serializator {
    public static void serialization(Object obj, File file) throws IOException, IllegalAccessException {
        StringBuilder sb = new StringBuilder();

        try (FileWriter out = new FileWriter(file)) {
            sb = makeString(obj, sb, null);
            out.write(sb.toString());
        }

    }

    private static StringBuilder makeString(Object obj, StringBuilder sb, String name) throws IllegalAccessException {
        if(name != null) {
            sb.append("->")
                    .append("\n")
                    .append(name)
                    .append("\n");
        }

        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Save.class)) {
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }

                if (int.class == field.getType()) {
                    sb.append(field.getName()).append(" = ");
                    sb.append(field.getInt(obj)).append("\n");
                } else if (String.class == field.getType()) {
                    sb.append(field.getName()).append(" = ");
                    sb.append(field.get(obj).toString()).append("\n");
                } else {
                    sb = makeString(field.get(obj), sb, field.getName());
                }
              }
        }
        if(name != null) {
            sb.append("<-")
                    .append("\n");
        }

        return sb;
    }

    public static <T> T deserialization(File file, Class<T> cls) throws ClassNotFoundException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        List<String> list = new LinkedList<>();
        T res = null;
        try (FileReader fr = new FileReader(file)) {
            int c;
            StringBuilder sb = new StringBuilder();
            while ((c = fr.read()) > 0) {
                sb.append((char)c);
            }
            list.addAll(Arrays.asList(sb.toString().split("\n")));
            res = parseString(list, cls);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;

    }

    public static <T> T parseString(List<String> list, Class<T> cls) throws IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchFieldException {
        T obj = (T) cls.newInstance();
        String line;
        while (list.size() > 0) {
            line = list.remove(0);
            if(line.equals("->")) {
                line = list.remove(0);
                Field field = cls.getDeclaredField(line);
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                setField(field, obj, parseString(list, field.getType()));
            } else if (line.equals("<-")) {
                return obj;
            } else {
                String[] entity = line.split(" = ");
                if (entity.length < 2) {
                    throw new InvalidParameterException(line);
                }
                Field field = cls.getDeclaredField(entity[0]);
                if(field.getType() == int.class) {
                    setField(field, obj, Integer.parseInt(entity[1]));
                } else if (field.getType() == String.class) {
                    setField(field, obj, entity[1]);
                }
            }
        }
        return obj;
    }

    public static <T> void  setField(Field field, T obj, Object value) throws NoSuchFieldException, IllegalAccessException {

        if (!field.isAccessible()) {
            field.setAccessible(true);
        }
        if (field.isAnnotationPresent(Save.class)) {
            field.set(obj, value);
        }
    }
}
