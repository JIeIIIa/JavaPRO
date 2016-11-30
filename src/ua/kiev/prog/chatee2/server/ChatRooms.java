package ua.kiev.prog.chatee2.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class ChatRooms {
    private static final ChatRooms CHAT_ROOMS = new ChatRooms();
    private static final int LIMIT = 1000;
    private final Gson GSON;
    private final Map<String, TreeSet<Message>> rooms;

    private ChatRooms() {
        GSON = new GsonBuilder().create();
        rooms = new HashMap<>();
        rooms.put("Main", new TreeSet<Message>());
        rooms.put("Private", new TreeSet<Message>());
    }

    public static ChatRooms getInstance() {
        return CHAT_ROOMS;
    }

    public void addRoom(String title) {
        rooms.put(title, new TreeSet<Message>());
    }

    public TreeSet<Message> getRoom(String title) {
        return rooms.get(title);
    }

    public boolean isRoomExist(String title) {
        return rooms.get(title) != null;
    }

    public synchronized String toJSON(String login, String roomName, long n) {
        return GSON.toJson(new JsonMessages(login, roomName, n));
    }

    public synchronized int addMessage(String roomName, Message m) {
        TreeSet<Message> room = rooms.get(roomName);
        if (room == null) {
            return 1;
        }
        if (room.size() + 1 == LIMIT) {
            room.remove(room.first());
        }
        room.add(m);
        return 0;
    }


}
