package HomeTasks.Task_02_03_YahooFinanceXml;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;

/**
 * Created by JIeIIIa on 18.11.2016.
 */
@XmlRootElement(name = "results")
public class Results {

    public Rate[] rate;

    @Override
    public String toString() {
        return "Results{" +
                "rate=" + Arrays.toString(rate) +
                '}';
    }

    public void print() {
        for (Rate r : Arrays.asList(rate)) {
            System.out.println(r.getInfo());
        }
    }
}
