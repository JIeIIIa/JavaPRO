package ua.kiev.prog.chatee2.server;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ManageChatRoomServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String roomName = req.getParameter("RoomName");
        String login = req.getParameter("login");
        if (Utils.checkAuthorized(req, resp, login) != 0) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        String option = req.getParameter("option").toLowerCase();
        switch (option) {
            case "new":
                if (!ChatRooms.getInstance().isRoomExist(roomName)) {
                    ChatRooms.getInstance().addRoom(roomName);
                }
                break;
            case "change":
                if (!ChatRooms.getInstance().isRoomExist(roomName)) {
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    return;
                }
                break;
        }
    }
}
