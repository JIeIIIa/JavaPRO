package ua.kiev.prog.orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class Client {
    public static void add(Connection conn, String fullName, int age, String tel) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO Clients(fullName, age, tel) VALUES (?, ?, ?)");
        ps.setString(1, fullName);
        ps.setInt(2, age);
        ps.setString(3, tel);
        ps.executeUpdate();
    }

    public static void updateById(Connection conn, String fullName, int age, String tel, int id) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("UPDATE Clients SET fullName = ?, age = ?, tel = ? WHERE id = ?)");
        ps.setString(1, fullName);
        ps.setInt(2, age);
        ps.setString(3, tel);
        ps.setInt(4, id);
        ps.executeUpdate();
    }

    public static void deleteById(Connection conn, int id) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("DELETE FROM Clients WHERE id = ?)");
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    public static void addRandom(Connection conn, int count) throws SQLException {
        Random rnd = new Random();
        for (int i = 0; i < count; i++) {
            add(conn, "Name_" + i, 25 + rnd.nextInt(30), "tel:" + rnd.nextInt(10000000));
        }
    }

    public static void show(Connection conn) throws SQLException {
        String query = "SELECT * FROM Clients";
        DbUtils.showQuery(conn, query);
    }
}