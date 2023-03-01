package transaction;

import customer.Customer;
import databaseconnector.DatabaseConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Transaction {
    private final Date date;

    public Transaction(double amount, int fromCustomerId, int toCustomerId, String type) {

        date = new Date();

        try {
            Connection connection = DatabaseConnector.getConnection();

            System.out.println("Connection succesful: trans");
            String sql = "INSERT INTO transaction VALUES ( NULL, "+ -amount +", "+ fromCustomerId + ", "+ toCustomerId +", '"+ date.toString() +"', '"+ type +"')";
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sql);

            connection.close();
            stmt.close();

        }catch (SQLException sqle){
            sqle.printStackTrace();
        }

    }

    public Transaction(double amount, int fromCustomerId) {

        date = new Date();

        try {
            Connection connection = DatabaseConnector.getConnection();

            System.out.println("Connection succesful: trans");
            String sql = "INSERT INTO transaction VALUES ( NULL, "+ -amount +", "+ fromCustomerId + ", "+ fromCustomerId +", '"+ date.toString() +"', 'Withdawal')";
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sql);

            connection.close();
            stmt.close();

        }catch (SQLException sqle){
            sqle.printStackTrace();
        }

    }

    public Transaction(double amount, int toCustomerId, String type) {

        date = new Date();

        try {
            Connection connection = DatabaseConnector.getConnection();

            System.out.println("Connection succesful: trans");
            String sql = "INSERT INTO transaction VALUES ( NULL, "+ amount +", "+ toCustomerId + ", "+ toCustomerId +", '"+ date.toString() +"', 'Deposit')";
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sql);

            connection.close();
            stmt.close();

        }catch (SQLException sqle){
            sqle.printStackTrace();
        }

    }

}
