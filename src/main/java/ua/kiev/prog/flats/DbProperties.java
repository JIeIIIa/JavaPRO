package ua.kiev.prog.flats;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DbProperties {
    private String url;
    private String user;
    private String password;

    public DbProperties() {
        InputStream is = getClass().getClassLoader().getResourceAsStream("db.properties");

        Properties properties = new Properties();

        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        url = properties.getProperty("db.url", "jdbc:mysql://localhost:3306/FlatsDB");
        user = properties.getProperty("db.user", "root");
        password = properties.getProperty("db.password", "password");
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
