package HomeTasks.Task_02_01_Schedule;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by JIeIIIa on 18.11.2016.
 */
public class DateAdapter extends XmlAdapter<String, Date> {
    /**
     * Thread safe {@link DateFormat}.
     */
    private static final ThreadLocal<DateFormat> DATE_FORMAT_TL = new ThreadLocal<DateFormat>() {

        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("dd.MM.yyyy");
        }

    };

    @Override
    public Date unmarshal(String v) throws Exception {
        return DATE_FORMAT_TL.get().parse(v);
    }

    @Override
    public String marshal(Date v) throws Exception {
        return DATE_FORMAT_TL.get().format(v);
    }
}


