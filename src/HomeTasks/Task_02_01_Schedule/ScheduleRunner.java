package HomeTasks.Task_02_01_Schedule;
/*
1. Есть список поездов, представленный с виде XML. Вывести на экран информацию о тех поездах, которые отправляются
сегодня с 15:00 до 19:00.
    <?xml version="1.0" encoding="UTF-8"?>
    <trains>
        <train id=“1”>
            <from>Kyiv</from>
            <to>Donetsk</to>
            <date>19.12.2013</date>
            <departure>15:05</departure>
        </train>
        <train id=“2”>
            <from>Lviv</from>
            <to>Donetsk</to>
            <date>19.12.2013</date>
            <departure>19:05</departure>
        </train>
    </trains>
Написать код для добавления новых поездов в существующий XML.
*/

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by JIeIIIa on 18.11.2016.
 */
public class ScheduleRunner {
    public static void main(String[] args) throws ParseException {
        File file = new File("D:\\Java\\JavaPRO\\src\\HomeTasks\\Task_02_01_Schedule\\trains.xml");


        Schedule schedule = read(file);
        System.out.println("--= Read from file =--");
        System.out.println(schedule);
        System.out.println();



        System.out.println("Find all train between 01.01.2013 01:23 AND 19.12.2013 18:23\nResults: ");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        System.out.println(find(schedule, dateFormat.parse("01.01.2013 01:23"), dateFormat.parse("19.12.2013 18:23")));
        System.out.println();

        schedule = addTrain(schedule);
        write(file, schedule);
        System.out.println("Add train in file");
        System.out.println(schedule);
    }

    public static void write(File file, Schedule schedule) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Schedule.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(schedule, file);
        } catch (JAXBException e) {
            System.err.println("!!!     WRITE ERROR     !!!");
            e.printStackTrace();
        }
    }

    public static Schedule read(File file) {
        Schedule schedule = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Schedule.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            schedule = (Schedule) unmarshaller.unmarshal(file);

        } catch (JAXBException e) {
            System.err.println("!!!     READ ERROR     !!!");
            e.printStackTrace();
        }
        return schedule;
    }
    
    public static Schedule find(Schedule schedule, Date low, Date up) {
        Schedule result = new Schedule();

        for (Train train : schedule) {
            if (low.before(train.getDateTime().getTime()) && up.after(train.getDateTime().getTime())) {
                result.add(train);
            }
        }
        return result;
    }

    public static Schedule addTrain(Schedule schedule) {
        Random rnd = new Random();
        Train train = new Train(Integer.toString(rnd.nextInt(100000)), "", "", new Date(), new Time(rnd.nextInt(86400000)));

        if (rnd.nextInt() % 2 == 0) {
            train.setFrom("Kiev");
            train.setTo("Lviv");
        } else {
            train.setFrom("Lviv");
            train.setTo("Kiev");
        }

        schedule.add(train);
        return schedule;

    }
}
