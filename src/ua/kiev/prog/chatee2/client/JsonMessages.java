package ua.kiev.prog.chatee2.client;

import java.util.*;

public class JsonMessages {
    public List<Message> getList() {
        return Collections.unmodifiableList(list);
    }

    private final List<Message> list;

    public JsonMessages(TreeSet<Message> sourceSet, long from) {
        Date fromDate = new Date(from);
        this.list = new LinkedList<>();
        Iterator<Message> iterator = sourceSet.descendingIterator();
        while (iterator.hasNext()) {
            Message msg = iterator.next();
            if (msg.getDate().after(fromDate)) {
                list.add(0, msg);
            } else {
                break;
            }
        }
    }
}
