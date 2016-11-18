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
    private int count;
    private String created;
    private String lang;
    private Results results;

    public int getCount() {
        return count;
    }
    @XmlAttribute(namespace = "http://www.yahooapis.com/v1/base.rng")
    public void setCount(int count) {
        this.count = count;
    }

    public String getCreated() {
        return created;
    }
    @XmlAttribute(namespace = "http://www.yahooapis.com/v1/base.rng")
    public void setCreated(String created) {
        this.created = created;
    }

    public String getLang() {
        return lang;
    }
    @XmlAttribute(namespace = "http://www.yahooapis.com/v1/base.rng")
    public void setLang(String lang) {
        this.lang = lang;
    }

    public Results getResults() {
        return results;
    }
    @XmlElement(name = "results")
    public void setResults(Results results) {
        this.results = results;
    }

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
