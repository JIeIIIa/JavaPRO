package HomeTasks.Task_01_03_SerializatorRunner;

/**
 * Created by JIeIIIa on 18.11.2016.
 */
public class SimpleClass {
    @Save
    private int first;

    public int second;

    public SimpleClass() {}

    public SimpleClass(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return "SimpleClass{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}
