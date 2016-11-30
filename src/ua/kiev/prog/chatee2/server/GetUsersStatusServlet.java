package ua.kiev.prog.chatee2.server;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class GetUsersStatusServlet extends HttpServlet {
    private UserSet userSet = UserSet.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = userSet.toJSON();

        if (json != null) {
            OutputStream out = resp.getOutputStream();
            byte[] buf = json.getBytes(StandardCharsets.UTF_8);
            out.write(buf);
        }
    }
}
