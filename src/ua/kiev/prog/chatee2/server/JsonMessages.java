package ua.kiev.prog.chatee2.server;

import java.util.*;

public class JsonMessages {
    private final List<Message> list;


    public JsonMessages(String login, String roomName, long from) {
        Date fromDate = new Date(from);
        List<Message> tmpList = new LinkedList<>();
        tmpList = addPrivateMessage(tmpList, login, fromDate);
        Collections.sort(addMessageFromRoom(tmpList, roomName, fromDate));
        list = tmpList;
    }

    private List<Message> addPrivateMessage(List<Message> list, String login, Date fromDate) {
        Iterator<Message> iterator = ChatRooms.getInstance().getRoom("Private").descendingIterator();

        while (iterator.hasNext()) {
            Message msg = iterator.next();
            if (msg.getDate().after(fromDate)) {
                if (msg.getTo().equals(login) || msg.getFrom().equals(login)) {
                    list.add(0, msg);
                }
            } else {
                break;
            }
        }
        return list;
    }

    private List<Message> addMessageFromRoom(List<Message> list, String roomName, Date fromDate) {
        if (!ChatRooms.getInstance().isRoomExist(roomName)) {
            return list;
        }
        Iterator<Message> iterator = ChatRooms.getInstance().getRoom(roomName).descendingIterator();

        while (iterator.hasNext()) {
            Message msg = iterator.next();
            if (msg.getDate().after(fromDate)) {
                list.add(0, msg);
            } else {
                break;
            }
        }
        return list;
    }


}
