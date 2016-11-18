package HomeTasks.Task_01_03_SerializatorRunner;

/**
 * Created by JIeIIIa on 18.11.2016.
 */
public class TripleClass {
    CompositeClass a = new CompositeClass(new SimpleClass(0, 0), 0);
    @Save
    CompositeClass b = new CompositeClass(new SimpleClass(0, 0), 0);
    SimpleClass c = new SimpleClass(0, 0);
    @Save
    private int d;
    @Save
    String s;

    TripleClass() {
    }

    public TripleClass(CompositeClass d, CompositeClass b, SimpleClass c, int D, String s) {
        this.a = d;
        this.b = b;
        this.c = c;
        this.d = D;
        this.s = s;
    }

    @Override
    public String toString() {
        return "TripleClass{" +
                "\n\ta=" + a +
                ",\n\tb=" + b +
                ",\n\tc=" + c +
                ",\n\td=" + d +
                ",\n\ts=" + s +
                "\n}";
    }
}
