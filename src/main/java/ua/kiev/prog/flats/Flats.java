package ua.kiev.prog.flats;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Flats {

    public static void main(String[] args) {
        DbProperties properties = new DbProperties();

        try(Connection conn = DriverManager.getConnection(properties.getUrl(), properties.getUser(), properties.getPassword())) {
            System.out.println("Connecion to " + properties.getUrl() + "\t\t\tOk!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean initDb(Connection conn) throws SQLException {
        Statement statement = conn.createStatement();
        try {
            statement.execute("");
        } finally {
            statement.close();
        }

        return true;
    }


}
