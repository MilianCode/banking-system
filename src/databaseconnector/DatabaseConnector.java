package databaseconnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DatabaseConnector {

    private static String url = "jdbc:mysql://localhost:3306/banking";
    private static String driverName = "com.mysql.cj.jdbc.Driver";
    private static String username = "root";
    private static String password = "root";
    private static Connection con;

    public static Connection getConnection() {

        try {
            Class.forName(driverName).newInstance();
            con = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver not found");
        } catch (SQLException e) {
            System.out.println("Failed to create the database connection");
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return con;
    }

}