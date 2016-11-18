package HomeTasks.Task_02_01_Schedule;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by JIeIIIa on 18.11.2016.
 */
public class TimeAdapter extends XmlAdapter<String, Time> {
    /**
     * Thread safe {@link DateFormat}.
     */
    private static final ThreadLocal<DateFormat> TIME_FORMAT_TL = new ThreadLocal<DateFormat>() {

        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("HH:mm");
        }

    };

    @Override
    public Time unmarshal(String v) throws Exception {
        return new Time(TIME_FORMAT_TL.get().parse(v).getTime());
    }

    @Override
    public String marshal(Time v) throws Exception {
        return TIME_FORMAT_TL.get().format(v);
    }
}
