package HomeTasks.Task_01_03_SerializatorRunner;

/*
3. Написать код, который сериализирует и десериализирует в/из файла все значения полей класса, которые отмечены аннотацией @Save
*/

import java.io.File;
import java.io.IOException;

/**
 * Created by JIeIIIa on 18.11.2016.
 */
public class SerializatorRunner {


    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, IOException {
        TripleClass tripleClass = new TripleClass(new CompositeClass(new SimpleClass(1, 2), 3),
                                                    new CompositeClass(new SimpleClass(4, 5), 6),
                                                    new SimpleClass(7, 8),
                                                    9,
                                                    "test");
        File file = new File("D:\\Java\\JavaPRO\\src\\HomeTasks\\Task_01_03_SerializatorRunner\\simpleClass.tmp");

        Serializator.serialization(tripleClass, file);
        tripleClass = Serializator.deserialization(file, TripleClass.class);

        System.out.println(tripleClass);
    }
}
