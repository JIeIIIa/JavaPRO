package ua.kiev.prog.chatee2.server;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class GetListChatRoomServlet extends HttpServlet {

    private final ChatRooms chatRooms = ChatRooms.getInstance();
    private UserSet usrSet = UserSet.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String login = req.getParameter("login");
        if (Utils.checkAuthorized(req, resp, login) != 0) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String roomName = req.getParameter("RoomName");
        if (!chatRooms.isRoomExist(roomName)) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        usrSet.updateLastReadDate(login, new Date());
        String fromStr = req.getParameter("from");

        long from;
        try {
            from = Long.parseLong(fromStr);
        } catch (Exception ex) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String json = chatRooms.toJSON(login, roomName, from);
        if (json != null) {
            OutputStream os = resp.getOutputStream();
            byte[] buf = json.getBytes(StandardCharsets.UTF_8);
            os.write(buf);
        }
    }
}
