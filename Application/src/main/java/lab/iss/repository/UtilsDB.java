package lab.iss.repository;

import java.sql.*;

public class UtilsDB {

    private String URL;

    private String username;

    private String password;

    public UtilsDB(String URL, String username, String password) {
        this.URL = URL;
        this.username = username;
        this.password = password;
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, username, password);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
