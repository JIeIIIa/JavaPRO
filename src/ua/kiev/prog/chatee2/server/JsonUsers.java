package ua.kiev.prog.chatee2.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class JsonUsers {
    private List<UserInfo> usersList;

    public JsonUsers(Set<User> users) {
        usersList = new ArrayList<>();
        for (User user : users) {
            usersList.add(new UserInfo(user.getLogin(), user.getStatus()));
        }
    }

}
