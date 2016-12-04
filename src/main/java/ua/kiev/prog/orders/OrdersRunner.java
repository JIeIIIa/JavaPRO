package ua.kiev.prog.orders;
/*
Создать проект «База данных заказов». Создать таблицы «Товары» , «Клиенты» и «Заказы». Написать код для добавления
новых клиентов, товаров и оформления заказов.
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import static ua.kiev.prog.orders.DbUtils.initDb;

public class OrdersRunner {
    public static void main(String[] args) {
        DbProperties properties = new DbProperties();

        try (Connection conn = DriverManager.getConnection(properties.getUrl(), properties.getUser(), properties.getPassword())) {
            System.out.println("Connection to " + properties.getUrl() + "\t\t\tOk!");

            initDb(conn);
            System.out.println("Initializing DataBase \t\t\t\t\tOK!\n");

            System.out.println("All clients: ");
            int clientCount = 4;
            Client.addRandom(conn, clientCount);
            Client.show(conn);

            System.out.println("All products: ");
            int productCount = 5;
            Product.addRandom(conn, productCount);
            Product.show(conn);

            System.out.println("All orders: ");
            Order.addRandom(conn, 5, clientCount, productCount);
            Order.show(conn);

            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("1 - Add client");
                System.out.println("2 - Update client");
                System.out.println("3 - Delete client");
                System.out.println("4 - Show client");
                System.out.println("-------------------");
                System.out.println("5 - Add product");
                System.out.println("6 - Update product");
                System.out.println("7 - Delete product");
                System.out.println("8 - Show product");
                System.out.println("-------------------");
                System.out.println("9 - Add order");
                System.out.println("10 - Update order");
                System.out.println("11 - Delete order");
                System.out.println("12 - Show order");
                System.out.println("-------------------");

                System.out.print(">>");
                String s = sc.nextLine();
                switch (s) {
                    case "1":
                        addClient(conn, sc);
                        break;
                    case "2":
                        changeClient(conn, sc);
                        break;
                    case "3":
                        deleteClient(conn, sc);
                        break;
                    case "4":
                        Client.show(conn);
                        break;
                    case "5":
                        addProduct(conn, sc);
                        break;
                    case "6":
                        changeProduct(conn, sc);
                        break;
                    case "7":
                        deleteProduct(conn, sc);
                        break;
                    case "8":
                        Product.show(conn);
                        break;
                    case "9":
                        addOrder(conn, sc);
                        break;
                    case "10":
                        changeOrder(conn, sc);
                        break;
                    case "11":
                        deleteOrder(conn, sc);
                        break;
                    case "12":
                        Order.show(conn);
                        break;
                    default:
                        return;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void addClient(Connection conn, Scanner sc) throws SQLException {
        System.out.println("Fullname: ");
        String fullName = sc.nextLine();
        System.out.println("Age: ");
        int age = sc.nextInt();
        String tel = null;
        sc.nextLine();
        while (tel == null || tel.length() > 11) {
            System.out.println("Telephone: ");
            tel = sc.nextLine();
        }
        Client.add(conn, fullName, age, tel);
    }

    public static void changeClient(Connection conn, Scanner sc) throws SQLException {
        System.out.println("Client id: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("Fullname: ");
        String fullName = sc.nextLine();
        System.out.println("Age: ");
        int age = sc.nextInt();
        sc.nextLine();
        String tel = null;
        while (tel == null || tel.length() > 11) {
            System.out.println("Telephone: ");
            tel = sc.nextLine();
        }
        Client.updateById(conn, fullName, age, tel, id);
    }

    public static void deleteClient(Connection conn, Scanner sc) throws SQLException {
        System.out.println("Client id: ");
        int id = sc.nextInt();
        sc.nextLine();

        Client.deleteById(conn, id);
    }

    public static void addProduct(Connection conn, Scanner sc) throws SQLException {
        System.out.println("Title: ");
        String title = sc.nextLine();
        System.out.println("Cost: ");
        int cost = sc.nextInt();
        sc.nextLine();

        Product.add(conn, title, cost);
    }

    public static void changeProduct(Connection conn, Scanner sc) throws SQLException {
        System.out.println("Product id: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("Title: ");
        String title = sc.nextLine();
        System.out.println("Cost: ");
        int cost = sc.nextInt();
        sc.nextLine();

        Product.updateById(conn, title, cost, id);
    }

    public static void deleteProduct(Connection conn, Scanner sc) throws SQLException {
        System.out.println("Product id: ");
        int id = sc.nextInt();
        sc.nextLine();

        Product.deleteById(conn, id);
    }

    public static void addOrder(Connection conn, Scanner sc) throws SQLException, ParseException {
        System.out.println("Client id: ");
        int clientId = sc.nextInt();
        sc.nextLine();
        System.out.println("Product id: ");
        int productId = sc.nextInt();
        sc.nextLine();
        System.out.println("Order date: ");
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        Date orderDate = sdf.parse(sc.nextLine());

        Order.add(conn, productId, clientId, orderDate);
    }

    public static void changeOrder(Connection conn, Scanner sc) throws SQLException, ParseException {
        System.out.println("Order id: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("Client id: ");
        int clientId = sc.nextInt();
        sc.nextLine();
        System.out.println("Product id: ");
        int productId = sc.nextInt();
        sc.nextLine();
        System.out.println("Order date: ");
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        Date orderDate = sdf.parse(sc.nextLine());

        Order.updateById(conn, productId, clientId, orderDate, id);
    }

    public static void deleteOrder(Connection conn, Scanner sc) throws SQLException {
        System.out.println("Order id: ");
        int id = sc.nextInt();
        sc.nextLine();

        Order.deleteById(conn, id);
    }


}
