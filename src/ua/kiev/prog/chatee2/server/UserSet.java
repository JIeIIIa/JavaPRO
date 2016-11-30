package ua.kiev.prog.chatee2.server;

import com.google.gson.Gson;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

public class UserSet {
    private static UserSet userSet = new UserSet();

    private final Gson gson;
    private final Set<User> users = new TreeSet<>();

    public static UserSet getInstance() {
        return userSet;
    }

    private UserSet() {
        gson = new Gson();

        users.add(new User("user", "user"));
        users.add(new User("java", "java"));
        users.add(new User("tester", "123"));
    }

    public synchronized void add(User user) {
        users.add(user);
    }

    public synchronized String toJSON() {
        return gson.toJson(new JsonUsers(users));
    }

    public synchronized boolean checkUser(String login) {
        for (User user : users) {
            if (login != null && user.getLogin().equals(login)) {
                return true;
            }
        }
        return false;
    }

    public synchronized boolean checkPassword(String login, String password) {
        if (password == null || login == null) {
            return false;
        }
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                boolean authorized = password.equals(user.getPassword());
                user.setAuthorized(authorized);
                return authorized;
            }
        }
        return false;
    }

    public synchronized boolean userStatus(String login) {
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                return user.getStatus() == UserStatus.ONLINE;
            }
        }
        return false;
    }

    public synchronized void updateLastReadDate(String login, Date date) {
        if (login == null) {
            return;
        }

        for (User user : users) {
            if (login.equals(user.getLogin())) {
                user.setLastReadMessage(date);
            }
        }
    }
}
