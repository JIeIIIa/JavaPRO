package ua.kiev.prog.orders;

import java.sql.*;

public class DbUtils {

    public static void initDb(Connection conn) throws SQLException {
        Statement statement = conn.createStatement();
        try {
            statement.execute("DROP TABLE IF EXISTS Orders;;");
            statement.execute("DROP TABLE IF EXISTS Clients;");
            statement.execute("DROP TABLE IF EXISTS Products;");


            String query = "CREATE TABLE IF NOT EXISTS Products ( " +
                                "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                                "title VARCHAR(30) NOT NULL, " +
                                "cost INT NOT NULL " +
                            ");";
            statement.execute(query);

            query = "CREATE TABLE IF NOT EXISTS Clients( " +
                            "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                            "fullName VARCHAR(60) NOT NULL, " +
                            "age INT NOT NULL, " +
                            "tel CHAR(11) " +
                    ");";
            statement.execute(query);


            query = "CREATE TABLE IF NOT EXISTS Orders( " +
                             "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                            "productID INT NOT NULL, " +
                            "clientID INT NOT NULL, " +
                            "orderDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                            "FOREIGN KEY FKproductIDtoProducts(productID) REFERENCES Products(id), " +
                            "FOREIGN KEY FKclientIDtoClientss(clientID) REFERENCES Clients(id) " +
                            ");";
            statement.execute(query);

        } finally {
            statement.close();
        }
    }

    public static void showResultSet(ResultSet rs) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();
        int[] widths = new int[]{15, 25, 25, 7, 7, 10};

        for (int i = 0; i < md.getColumnCount(); i++) {
            System.out.print(String.format("%" + md.getColumnDisplaySize(i+1) + "s |",md.getColumnName(i+1)));
        }

        System.out.println();
        while (rs.next()) {
            for (int i = 0; i < md.getColumnCount(); i++) {
                System.out.print(String.format("%" + md.getColumnDisplaySize(i+1)  + "s |",rs.getString(i+1)));
            }
            System.out.println();
        }
    }

    public static void showQuery(Connection conn, String query) throws SQLException {

        try (Statement st =conn.createStatement()){
            ResultSet rs = st.executeQuery(query);
            showResultSet(rs);
        }

    }

}
