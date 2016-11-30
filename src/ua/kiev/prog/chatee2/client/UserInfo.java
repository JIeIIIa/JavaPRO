package ua.kiev.prog.chatee2.client;

public class UserInfo {
    private String login;
    private UserStatus status;

    public UserInfo(String login, UserStatus status) {
        this.login = login;
        this.status = status;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return login + "\t\t\t" + status;
    }
}
