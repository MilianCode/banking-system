package customer;

import databaseconnector.DatabaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerTransactionManaging extends Customer{

    //   Method that takes money from account that makes transaction
    public static boolean transferFrom(double amount) {

        if (!checkIfCanWithdraw(amount)) return false;

        setBalance((getBalance() - amount));

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
            System.out.println("customer.Customer.transferFrom() problem");
            e.printStackTrace();
        }
        return false;
    }

    //   Method that adding money to customer, who is transaction reciever
    public static boolean transferTo(double amount, int customerReciever){
        try {
            Connection connection = DatabaseConnector.getConnection();

            String sql = "UPDATE customer SET balance = balance + "+ amount +" WHERE customerId = " + customerReciever;
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sql);

            sql = "Select name FROM customer WHERE customerId = " + getCustomerId();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                setName(rs.getString(1));
            }

            sql = "Select surname FROM customer WHERE customerId = " + getCustomerId();
            rs = stmt.executeQuery(sql);

            while (rs.next()){
                setLastName(rs.getString(1));
            }

            rs.close();
            connection.close();
            stmt.close();

            return true;

        }catch (SQLException e){
            System.out.println("customer.Customer.transferTo() problem");
            e.printStackTrace();
        }

        return false;
    }

}
