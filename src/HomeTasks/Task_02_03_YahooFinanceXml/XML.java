package HomeTasks.Task_02_03_YahooFinanceXml;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by JIeIIIa on 18.11.2016.
 */
public class XML {
    @XmlElement(name = "query")
    public Query query;

    @Override
    public String toString() {
        return query.toString();
    }

    public void print() {
        query.print();
    }
}
