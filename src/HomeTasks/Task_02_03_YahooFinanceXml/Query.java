package HomeTasks.Task_02_03_YahooFinanceXml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;

/**
 * Created by JIeIIIa on 18.11.2016.
 */
@XmlRootElement(name = "query")
public class Query {
    @XmlAttribute(namespace = "http://www.yahooapis.com/v1/base.rng")
    public int count;
    @XmlAttribute(namespace = "http://www.yahooapis.com/v1/base.rng")
    public String created;
    @XmlAttribute(namespace = "http://www.yahooapis.com/v1/base.rng")
    public String lang;
    @XmlElement(name = "results")
    public Results results;

    @Override
    public String toString() {
        return "Query{" +
                "\tcount=" + count +
                ",\tcreated='" + created + '\'' +
                ",\tlang='" + lang + '\'' +
                ",\n\tresults=" + results +
                "}\n";
    }

    public void print() {
        results.print();
    }
}
