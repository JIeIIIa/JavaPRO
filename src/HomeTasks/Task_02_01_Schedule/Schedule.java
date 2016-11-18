package HomeTasks.Task_02_01_Schedule;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by JIeIIIa on 18.11.2016.
 */

@XmlRootElement(name = "trains")
public class Schedule implements Iterable<Train>{
    @XmlElement(name = "train")
    private List<Train> schedule = new ArrayList<>();

    public void add(Train train) {
        schedule.add(train);
    }

    @Override
    public String toString() {
        return Arrays.deepToString(schedule.toArray());
    }

    @Override
    public Iterator<Train> iterator() {
        return schedule.iterator();
    }


}
