package ua.kiev.prog.chatee2.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    private static final String ENDS = "<<";
    private static String login = "";
    private static String roomName = "Main";
    private static Thread roomTh = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            String text;
            while (true) {
                if (login.isEmpty()) {
                    logining();
                }
                text = scanner.nextLine();
                if (text.isEmpty()){ break;}

                String words[] = text.split(ENDS);
                if (text.trim().toLowerCase().equals("/logout")) {
                    logout();
                } else if (text.trim().toLowerCase().equals("/users")) {
                    getUsersStatus();
                } else if (words.length > 2 && words[0].trim().toLowerCase().equals("pr")) {
                    if (sendMessage(new Message(login, words[2], words[1])) == -1) {
                        return;
                    }
                } else if (words.length > 1 && words[0].trim().toLowerCase().equals("mkroom")) {
                    addRoom(words[1]);
                } else if (words.length > 1 && words[0].trim().toLowerCase().equals("chroom")) {
                    if (changeRoom(words[1]) == 0) {
                        while (roomTh != null && !roomTh.isInterrupted()) {
                            roomTh.interrupt();
                        }
                        roomName = words[1];
                        roomTh = new Thread(new GetRoomThread(login, roomName));
                        roomTh.setDaemon(true);
                        roomTh.start();
                    }
                } else if (sendMessage(new Message(login, text)) == -1) {
                    return;
                }
                Thread.sleep(700);
            }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    public static int sendMessage(Message m) throws IOException {
        int res = m.send(Utils.getURL() + "/add?RoomName=" + roomName, roomName);

        if (res == 203) {
            System.out.println("An incorrect receiver of a message");
        } else if (res == 204) {
            System.out.println("Chat room " + roomName + " not found. Welcome to Main chat.");
            roomName = "Main";

        } else if (res != 200) { // 200 OK
            System.out.println("HTTP error occured: " + res);
            return -1;
        }
        return 0;
    }


    public static int changeRoom(String title) {
        try {
            URL url = new URL(Utils.getURL() + "/manageroom?login=" + login + "&RoomName=" + title + "&option=change");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();

            if (responseCode != 200) {
                System.out.println("Can't change room");
                return 1;
            }
        } catch (Exception e) {
            System.out.println("Cat't switch room");
            return 2;
        }
        return 0;
    }

    public static void addRoom(String title) {
        try {
            URL url = new URL(Utils.getURL() + "/manageroom?login=" + login + "&RoomName=" + title + "&option=new");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();

            if (responseCode != 200) {
                System.out.println("Cat't create room " + title);
                return;
            }

        } catch (Exception e) {
            System.out.println("Error during creating room " + title);
            return;
        }
        System.out.println("Room " + title + " was created.");
    }

    public static int logout() {
        login = "";
        roomTh.interrupt();

        try {
            URL url = new URL(Utils.getURL() + "/authorization?logout=true");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();

            if (responseCode != 401) {
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 0;
    }

    public static void logining() {
        while (!authorization()) {
            System.out.println("Wrong login/password. Try again.\n");
        }
        roomName = "Main";
        roomTh = new Thread(new GetRoomThread(login, roomName));
        roomTh.setDaemon(true);
        roomTh.start();

        System.out.println("Hello " + login + ") Enter your message: ");
    }

    public static void getUsersStatus() {

        Gson gson = new GsonBuilder().create();

        try {

            URL url = new URL(Utils.getURL() + "/status");
            HttpURLConnection http = (HttpURLConnection) url.openConnection();

            InputStream is = http.getInputStream();
            try {

                byte[] buf = Utils.requestBodyToArray(is);
                String strBuf = new String(buf, StandardCharsets.UTF_8);

                JsonUsers list = gson.fromJson(strBuf, JsonUsers.class);
                if (list != null) {
                    for (UserInfo userInfo : list.getList()) {
                        System.out.println(userInfo);
                    }
                }

            } finally {
                is.close();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static boolean authorization() {
        Main.login = "";
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your login: ");
        String login = scanner.nextLine();

        System.out.println("Enter your password: ");
        String password = scanner.nextLine();

        if (login.isEmpty()) System.exit(0);


        try {
            URL url = new URL(Utils.getURL() + "/authorization");
            String parameters = "login=" + login + "&password=" + password;
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            conn.setRequestProperty("Content-Length", "" + Integer.toString(parameters.getBytes().length));
            OutputStream os = conn.getOutputStream();
            byte[] data = parameters.getBytes("UTF-8");
            os.write(data);

            conn.connect();
            int responseCode = conn.getResponseCode();

            if (responseCode != 200) {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        Main.login = login;
        return true;
    }
}
