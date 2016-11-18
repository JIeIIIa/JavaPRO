package HomeTasks.Task_01_01_TestAnnotation;

public class MyClass {
    @Test(a = 123, b = 321)
    public void test(int a, int b)
    {
        System.out.println("a = " + a + "\tb = " + b);

    }
}
