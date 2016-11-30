package ua.kiev.prog.chatee2.server;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationServlet extends HttpServlet {

    private UserSet userSet = UserSet.getInstance();

    private boolean checkUser(String login, String password) {
        return userSet.checkPassword(login, password);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String logout = req.getParameter("logout");
        checkUser(logout, "");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (!checkUser(login, password)) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

    }
}
