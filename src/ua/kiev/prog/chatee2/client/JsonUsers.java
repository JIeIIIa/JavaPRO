package ua.kiev.prog.chatee2.client;

import java.util.Collections;
import java.util.List;

public class JsonUsers {
    private List<UserInfo> usersList;

    public List<UserInfo> getList() {
        return Collections.unmodifiableList(usersList);
    }

}
