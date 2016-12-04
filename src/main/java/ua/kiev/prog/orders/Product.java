package ua.kiev.prog.orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class Product {
    public static void add(Connection conn, String title, int cost) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO Products(title, cost) VALUES (?, ?)");
        ps.setString(1, title);
        ps.setInt(2, cost);
        ps.executeUpdate();
    }

    public static void updateById(Connection conn, String title, int cost, int id) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("UPDATE Products SET title = ?, cost = ? WHERE id = ?)");
        ps.setString(1, title);
        ps.setInt(2, cost);
        ps.setInt(3, id);
        ps.executeUpdate();
    }

    public static void deleteById(Connection conn, int id) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("DELETE FROM Products WHERE id = ?)");
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    public static void addRandom(Connection conn, int count) throws SQLException {
        Random rnd = new Random();
        for (int i = 0; i < count; i++) {
            add(conn, "Product_" + i, rnd.nextInt(100000));
        }
    }

    public static void show(Connection conn) throws SQLException {
        String query = "SELECT * FROM Products";
        DbUtils.showQuery(conn, query);
    }
}