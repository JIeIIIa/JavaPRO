package HomeTasks.Task_01_02_SaveByAnnotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Saver {
    private TextContainer textContainer;

    public Saver(TextContainer cls) {
        this.textContainer = cls;
    }

    public boolean save() {
        Class<?> cls = textContainer.getClass();
        if (!cls.isAnnotationPresent(SaveTo.class)) {
            System.out.println("Class not annotated!");
            return false;
        }
        SaveTo saveTo = cls.getAnnotation(SaveTo.class);
        Method[] methods = cls.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Save.class)) {
                try {
                    method.invoke(textContainer, saveTo.path());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    return false;
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }

        return true;
    }
}
