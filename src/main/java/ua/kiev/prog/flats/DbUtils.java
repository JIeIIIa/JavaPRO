package ua.kiev.prog.flats;

import java.sql.*;

public class DbUtils {
    public static final String query =
            "SELECT F.id AS 'ID', D.District AS 'District', D.address AS 'Address', " +
            "F.storey AS Storey, F.Squar AS Squar, F.Price AS Price " +
            "FROM Flats AS F " +
            "LEFT JOIN (SELECT A.id AS id, A.address AS address, Districts.Title AS District " +
            "FROM Addresses AS A " +
            "LEFT JOIN Districts ON A.districtID = Districts.id " +
            ") AS D ON F.addressID = D.id ";

    public static void initDb(Connection conn) throws SQLException {
        Statement statement = conn.createStatement();
        try {
            statement.execute("DROP TABLE IF EXISTS Flats;");
            statement.execute("DROP TABLE IF EXISTS Addresses;");
            statement.execute("DROP TABLE IF EXISTS Districts;");


            String query = "CREATE TABLE IF NOT EXISTS Districts (" +
                                    "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                                    "Title VARCHAR(30) NOT NULL" +
                            ");";
            statement.execute(query);

            query = "CREATE TABLE IF NOT EXISTS Addresses (" +
                                    "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                                    "address VARCHAR(60) NOT NULL," +
                                    "districtID INT NOT NULL," +
                                    "FOREIGN KEY FKdistrictIDinAddresses(districtID) REFERENCES Districts(id)" +
                    ");";
            statement.execute(query);


            query = "CREATE TABLE IF NOT EXISTS Flats (" +
                                    "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                                    "addressID INT NOT NULL," +
                                    "storey INT NOT NULL," +
                                    "squar INT NOT NULL," +
                                    "price INT NOT NULL," +
                                    "FOREIGN KEY FKaddressIDinFlats(addressID) REFERENCES Addresses(id)" +
                    ");";
            statement.execute(query);

        } finally {
            statement.close();
        }
    }

    public static void fillDb(Connection conn) throws SQLException {
        conn.setAutoCommit(false);
        String query = "INSERT INTO Districts (Title) VALUES (?)";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, "Main district");
            ps.executeUpdate();

            ps.setString(1, "Perfectvile");
            ps.executeUpdate();

            ps.setString(1, "Reachvile");
            ps.executeUpdate();
            conn.commit();
        } finally {
            conn.rollback();
            conn.setAutoCommit(true);
        }

        conn.setAutoCommit(false);
        query = "INSERT INTO Addresses (address, districtID) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, "First avenue");
            ps.setInt(2, 1);
            ps.executeUpdate();

            ps.setString(1, "Sun street");
            ps.setInt(2, 1);
            ps.executeUpdate();

            ps.setString(1, "Apple street");
            ps.setInt(2, 1);
            ps.executeUpdate();

            ps.setString(1, "Yellow avenue");
            ps.setInt(2, 2);
            ps.executeUpdate();

            ps.setString(1, "Shadow street");
            ps.setInt(2, 3);
            ps.executeUpdate();
            conn.commit();
        } finally {
            conn.rollback();
            conn.setAutoCommit(true);
        }

        conn.setAutoCommit(false);
        query = "INSERT INTO Flats (addressID, storey, squar, price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, 1);
            ps.setInt(2, 1);
            ps.setInt(3, 120);
            ps.setInt(4, 100000);
            ps.executeUpdate();

            ps.setInt(1, 2);
            ps.setInt(2, 3);
            ps.setInt(3, 123);
            ps.setInt(4, 123321);
            ps.executeUpdate();

            ps.setInt(1, 3);
            ps.setInt(2, 2);
            ps.setInt(3, 25);
            ps.setInt(4, 55555);
            ps.executeUpdate();

            ps.setInt(1, 4);
            ps.setInt(2, 16);
            ps.setInt(3, 315);
            ps.setInt(4, 456456);
            ps.executeUpdate();

            conn.commit();
        } finally {
            conn.rollback();
            conn.setAutoCommit(true);
        }
    }

    public static void showDb(Connection conn) throws SQLException {
        try (Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery(query);
            try {
                showResultSet(rs);
            } finally {
                rs.close();
            }
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


}
