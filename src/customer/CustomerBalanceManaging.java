package customer;

import databaseconnector.DatabaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerBalanceManaging extends Customer{

    public static void setBalance(){
        try {
            Connection connection = DatabaseConnector.getConnection();

            String sql = "Select balance FROM customer WHERE customerId = " + Customer.getCustomerId();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Customer.setBalance(rs.getInt(1));
            }

            rs.close();
            stmt.close();
            connection.close();

        }catch (SQLException e){
            System.out.println("CustomerBalanceManaging.setBalance() problem");
            e.printStackTrace();
        }
    }

    public static boolean deposit(double amount) {
        setBalance(getBalance() + amount);
        try {
            Connection connection = DatabaseConnector.getConnection();

            String sql = "UPDATE customer SET balance = "+ getBalance() +" WHERE customerId = " + getCustomerId();
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sql);

            connection.close();
            stmt.close();

            return true;

        }catch (SQLException e){
            setBalance(getBalance() - amount);
            System.out.println("CustomerBalanceManaging.deposit problem");
            e.printStackTrace();
        }

        return false;
    }

    public static boolean withdraw(double amount) {

        if (!checkIfCanWithdraw(amount)) return false;

        setBalance(getBalance() - amount);

        try {
            Connection connection = DatabaseConnector.getConnection();

            String sql = "UPDATE customer SET balance = "+ getBalance() +" WHERE customerId = " + getCustomerId();
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sql);

            connection.close();
            stmt.close();

            return true;

        }catch (SQLException e){
            setBalance(getBalance() + amount);
            System.out.println("customer.Customer.withdraw() problem");
            e.printStackTrace();
        }

        return false;
    }

}
