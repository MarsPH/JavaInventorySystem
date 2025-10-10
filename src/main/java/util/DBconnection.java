package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
    private static final String URL = "jdbc:mysql://localhost:3306/rpg_shop";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    private DBconnection() {} // block instantiation

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
