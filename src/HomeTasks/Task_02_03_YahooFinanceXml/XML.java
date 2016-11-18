package HomeTasks.Task_02_03_YahooFinanceXml;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by JIeIIIa on 18.11.2016.
 */
public class XML {

    private Query query;

    public Query getQuery() {
        return query;
    }

    @XmlElement(name = "query")
    public void setQuery(Query query) {
        this.query = query;
    }

    @Override
    public String toString() {
        return query.toString();
    }

    public void print() {
        query.print();
    }
}
