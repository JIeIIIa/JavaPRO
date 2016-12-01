package ua.kiev.prog.flats;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DbUtils {
    public static boolean initDb(Connection conn) throws SQLException {
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
                                    "adress VARCHAR(60) NOT NULL," +
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

        return true;
    }



}
