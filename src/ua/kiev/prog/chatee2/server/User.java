package ua.kiev.prog.chatee2.server;

import java.util.Date;

public class User implements Comparable<User> {
    private final long frequency = 1000000;
    private String login;
    private String password;
    private Date lastReadMessage;
    private boolean authorized;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastReadMessage() {
        return lastReadMessage;
    }

    public void setLastReadMessage(Date lastReadMessage) {
        this.lastReadMessage = lastReadMessage;
    }

    public boolean isAuthorized() {
        return authorized;
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }

    public UserStatus getStatus() {
        if (authorized && lastReadMessage == null) {
            return UserStatus.ONLINE;
        } else if (lastReadMessage == null || lastReadMessage.getTime() + frequency < new Date().getTime()) {
            authorized = false;
            lastReadMessage = null;
            return UserStatus.OFFLINE;
        } else {
            return UserStatus.ONLINE;
        }
    }

    @Override
    public int compareTo(User o) {
        if (o == null) return -1;
        return login.compareTo(o.getLogin());

    }
}
