package transaction;

import customer.Customer;
import databaseconnector.DatabaseConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Transaction {
    private double amount;
    private int fromCustomerId;
    private int toCustomerId;
    private Date date;
    private String type;

    public Transaction(double amount, int fromCustomerId, int toCustomerId, String type) {
        this.amount = amount;
        this.fromCustomerId = fromCustomerId;
        this.toCustomerId = toCustomerId;
        this.type = type;
        date = new Date();

        try {
            Connection connection = DatabaseConnector.getConnection();

            System.out.println("Connection succesful: trans");
            String sql = "INSERT INTO transaction VALUES ( NULL, "+ amount +", "+ fromCustomerId + ", "+ toCustomerId +", '"+ date.toString() +"', '"+ type +"')";
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sql);

            connection.close();
            stmt.close();

        }catch (SQLException sqle){
            sqle.printStackTrace();
        }

    }

}
