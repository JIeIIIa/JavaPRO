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

    private String id;
    private String Name;
    private double Rate;
    private String Date;
    private String Time;
    private String Ask;
    private String Bid;

    public String getId() {
        return id;
    }
    @XmlAttribute(name="id")
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }
    @XmlAttribute(name="Name")
    public void setName(String name) {
        Name = name;
    }

    public double getRate() {
        return Rate;
    }
    @XmlAttribute(name="Rate")
    public void setRate(double rate) {
        Rate = rate;
    }

    public String getDate() {
        return Date;
    }
    @XmlAttribute(name="Date")
    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }
    @XmlAttribute(name="Time")
    public void setTime(String time) {
        Time = time;
    }

    public String getAsk() {
        return Ask;
    }
    @XmlAttribute(name="Ask")
    public void setAsk(String ask) {
        Ask = ask;
    }

    public String getBid() {
        return Bid;
    }
    @XmlAttribute(name="Bid")
    public void setBid(String bid) {
        Bid = bid;
    }

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
