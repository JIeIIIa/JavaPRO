package HomeTasks.Task_02_03_YahooFinanceXml;

import javax.print.attribute.standard.MediaSize;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by JIeIIIa on 18.11.2016.
 */
@XmlRootElement(name = "rate")
public class Rate {
    @XmlAttribute
    public String id;
    @XmlElement
    public String Name;
    @XmlElement
    public double Rate;
    @XmlElement
    public String Date;
    @XmlElement
    public String Time;
    @XmlElement
    public String Ask;
    @XmlElement
    public String Bid;

    @Override
    public String toString() {
        return "Rate{" +
                "id='" + id + '\'' +
                ", Name='" + Name + '\'' +
                ", Rate=" + Rate +
                ", Date='" + Date + '\'' +
                ", Time='" + Time + '\'' +
                ", Ask='" + Ask + '\'' +
                ", Bid='" + Bid + '\'' +
                '}';
    }

    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(Name)
                .append("\n\t")
                .append("Date: ")
                .append(Date)
                .append(" ")
                .append(Time)
                .append("\n\t")
                .append("Rate = ")
                .append(Rate)
                .append("\n\t")
                .append("Ask = ")
                .append(Ask)
                .append("\n\t")
                .append("Bid = ")
                .append(Bid)
                .append("\n");
        return sb.toString();
    }
}
