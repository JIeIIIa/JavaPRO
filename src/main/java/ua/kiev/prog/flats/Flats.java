package ua.kiev.prog.flats;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static ua.kiev.prog.flats.DbUtils.fillDb;
import static ua.kiev.prog.flats.DbUtils.initDb;
import static ua.kiev.prog.flats.DbUtils.showDb;

public class Flats {

    public static void main(String[] args) {
        DbProperties properties = new DbProperties();

        try(Connection conn = DriverManager.getConnection(properties.getUrl(), properties.getUser(), properties.getPassword())) {
            System.out.println("Connecion to " + properties.getUrl() + "\t\t\tOk!");

            initDb(conn);
            System.out.println("Initializing DataBase \t\t\t\t\tOK!\n" );

            fillDb(conn);
            showDb(conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





}
