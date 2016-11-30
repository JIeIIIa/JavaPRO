package ua.kiev.prog.chatee2.server;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Utils {

    public static int checkAuthorized(HttpServletRequest req, HttpServletResponse resp, String login) {
        if (!UserSet.getInstance().userStatus(login)) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return 1;
        }
        return 0;
    }

    public static byte[] requestBodyToArray(HttpServletRequest req) throws IOException {
        InputStream is = req.getInputStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[10240];
        int r;

        do {
            r = is.read(buf);
            if (r > 0) bos.write(buf, 0, r);
        } while (r != -1);

        return bos.toByteArray();
    }
}
