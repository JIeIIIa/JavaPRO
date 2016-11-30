package ua.kiev.prog.chatee2.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class GetRoomThread implements Runnable {
    private final Gson gson;
    private long n;
    private String login;
    private String roomName;

    public GetRoomThread(String login, String roomName) {
        this.login = login;
        this.roomName = roomName;
        gson = new GsonBuilder().create();
    }

    @Override
    public void run() {
        try {
            System.out.println(" -= Enter to " + roomName + " =-");
            while (!Thread.interrupted()) {
                URL url = new URL(Utils.getURL() + "/getroom?from=" + n + "&login=" + login + "&RoomName=" + roomName);
                HttpURLConnection http = (HttpURLConnection) url.openConnection();

                InputStream is = http.getInputStream();
                try {
                    byte[] buf = Utils.requestBodyToArray(is);
                    String strBuf = new String(buf, StandardCharsets.UTF_8);

                    JsonMessages list = gson.fromJson(strBuf, JsonMessages.class);
                    if (list != null && list.getList().size() > 0) {
                        for (Message m : list.getList()) {
                            System.out.print("   <" + login + ":");
                            if (m.getTo() == null) {
                                System.out.println(roomName + ">: " + m);
                            } else {
                                System.out.println(" =Private= >: " + m);
                            }
                            n = m.getDate().getTime();
                        }
                    }
                } finally {
                    is.close();
                }
                Thread.sleep(500);
            }
        } catch (Exception ex) {
            System.out.println(" -= Exit from " + roomName + " =-\n");
        }
    }
}
