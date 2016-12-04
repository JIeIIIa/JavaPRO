package ua.kiev.prog.orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.Random;

public class Order {
    public static void add(Connection conn, int productId, int clientId, Date orderDate) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO Orders(productId, clientId, orderDate) VALUES (?, ?, ?)");
        ps.setInt(1, productId);
        ps.setInt(2, clientId);
        ps.setDate(3, new java.sql.Date(orderDate.getTime()));
        ps.executeUpdate();
    }

    public static void updateById(Connection conn, int productId, int clientId, Date orderDate, int id) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("UPDATE Orders SET productId = ?, clientId = ?, orderDate = ? WHERE id = ?)");
        ps.setInt(1, productId);
        ps.setInt(2, clientId);
        ps.setDate(3, new java.sql.Date(orderDate.getTime()));
        ps.setInt(4, id);
        ps.executeUpdate();
    }

    public static void deleteById(Connection conn, int id) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("DELETE FROM Orders WHERE id = ?)");
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    public static void addRandom(Connection conn, int count, int maxClient, int maxProduct) throws SQLException {
        Random rnd = new Random();
        for (int i = 0; i < count; i++) {
            add(conn, rnd.nextInt(maxProduct)+1, rnd.nextInt(maxClient)+1, new Date());
        }
    }

    public static void show(Connection conn) throws SQLException {
        String query = "SELECT P.title, C.fullName, O.orderDate FROM Orders AS O, Products AS P, Clients AS C " +
                "WHERE O.productId = P.id AND O.clientId = C.id " +
                "ORDER BY O.orderDate";
        DbUtils.showQuery(conn, query);
    }
}