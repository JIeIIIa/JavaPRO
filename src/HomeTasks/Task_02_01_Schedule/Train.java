package HomeTasks.Task_02_01_Schedule;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by JIeIIIa on 18.11.2016.
 */

@XmlRootElement(name = "train")
public class Train {
    private static SimpleDateFormat formatDate = new SimpleDateFormat("dd.MM.yyyy");
    private static SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm");


    private String id;
    private String from;
    private String to;
    private Date date;
    private Time time;

    public Train() {
    }

    public Train(String id, String from, String to, Date date, Time time) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.date = date;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    @XmlAttribute
    public void setId(String id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    @XmlElement
    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    @XmlElement
    public void setTo(String to) {
        this.to = to;
    }

    @XmlJavaTypeAdapter(DateAdapter.class)
    public Date getDate() {
        return date;
    }


    public void setDate(Date date) {
        this.date = date;
    }

    @XmlElement(name = "departure")
    @XmlJavaTypeAdapter(TimeAdapter.class)
    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Calendar getDateTime() {
        Calendar dateTime = Calendar.getInstance();
        dateTime.setTime(date);
        Calendar time = Calendar.getInstance();
        time.setTime(this.time);

        dateTime.add(Calendar.HOUR_OF_DAY, time.get(Calendar.HOUR_OF_DAY));
        dateTime.add(Calendar.MINUTE, time.get(Calendar.MINUTE));

        return dateTime;
    }
    @Override
    public String toString() {
        getDateTime();
        return "\n\tTrain{" +
                "\t\tid=" + id +
                ",\n\t\tfrom='" + from + '\'' +
                ", to='" + to + '\'' +
                ",\n\t\tdate=" + formatDate.format(date) +
                ", time=" + formatTime.format(time) +
                "\n\t}\n";
    }
}
