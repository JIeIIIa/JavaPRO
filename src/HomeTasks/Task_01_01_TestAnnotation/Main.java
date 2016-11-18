package HomeTasks.Task_01_01_TestAnnotation;
/*
1. Создать аннотацию, которая принимает параметры для метода. Написать код, который вызовет метод, помеченный
этой аннотацией, и передаст параметры аннотации в вызываемый метод.

    @Test(a=2, b=5)
    public void test(int a, int b) {…}
*/

import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
        MyClass obj = new MyClass();

        Class<?> cls = MyClass.class;

        try {
            Method findMethod = cls.getMethod("test", new Class<?>[]{int.class, int.class});
            if (!findMethod.isAnnotationPresent(Test.class)) {
                System.out.println("Method not annotated");
            } else {
                Test an = findMethod.getAnnotation(Test.class);
                obj.test(an.a(), an.b());
            }

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
