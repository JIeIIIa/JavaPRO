package HomeTasks.Task_01_03_SerializatorRunner;

/**
 * Created by JIeIIIa on 18.11.2016.
 */
public class CompositeClass {
    @Save
    private SimpleClass myClass = new SimpleClass(0, 0);
    @Save
    int x = 0;

    public CompositeClass() {
    }


    public CompositeClass(SimpleClass myClass, int x) {
        this.myClass = myClass;
        this.x = x;
    }

    @Override
    public String toString() {
        return "CompositeClass{" +
                "myClass =  {first=" + myClass.getFirst() + ", second=" + myClass.getSecond() + "}" +
                ",   x=" + x +
                '}';
    }
}
