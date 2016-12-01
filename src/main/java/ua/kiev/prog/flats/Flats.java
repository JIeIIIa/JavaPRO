package ua.kiev.prog.flats;

import java.sql.*;
import java.util.Scanner;

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

            System.out.println();
            flatByDistrict(conn);

            System.out.println();
            moreStorey(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void flatByDistrict(Connection conn) throws SQLException {
        System.out.println("Enter substring of District name:");
        Scanner scanner = new Scanner(System.in);
        String substr = scanner.nextLine();
        String query = DbUtils.query + "WHERE D.District LIKE ?;";

        try (PreparedStatement ps = conn.prepareStatement(query)){
            ps.setString(1, "%"+substr+"%");
            ps.execute();

            DbUtils.showResultSet(ps.getResultSet());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void moreStorey(Connection conn) throws SQLException {
        System.out.println("Enter min storey count:");
        Scanner scanner = new Scanner(System.in);
        String storey = scanner.nextLine();
        String query = DbUtils.query + "WHERE F.Storey > ?;";

        try (PreparedStatement ps = conn.prepareStatement(query)){
            ps.setInt(1, Integer.parseInt(storey));
            ps.execute();

            DbUtils.showResultSet(ps.getResultSet());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





}
