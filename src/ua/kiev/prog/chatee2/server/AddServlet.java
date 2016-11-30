package ua.kiev.prog.chatee2.server;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class AddServlet extends HttpServlet {

    private ChatRooms chatRooms = ChatRooms.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        byte[] buf = Utils.requestBodyToArray(req);
        String bufStr = new String(buf, StandardCharsets.UTF_8);

        Message msg = Message.fromJSON(bufStr);
        if (msg == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        if (msg.getTo() != null && !UserSet.getInstance().checkUser(msg.getTo())) {
            resp.setStatus(HttpServletResponse.SC_NON_AUTHORITATIVE_INFORMATION);
            return;
        }
        String roomName;
        if (msg.getTo() != null) {
            roomName = "Private";
        } else {
            roomName = req.getParameter("RoomName");
        }

        if (chatRooms.addMessage(roomName, msg) != 0) {
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }

    }


}
