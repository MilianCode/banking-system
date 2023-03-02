package customer;

import databaseconnector.DatabaseConnector;
import transaction.Transaction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

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

    public static void deposit(){
        Scanner in = new Scanner(System.in);
        int dep;

        System.out.println("Enter amount of deposit: ");
        dep = in.nextInt();
        if(CustomerBalanceManaging.deposit(dep)){
            System.out.println("Successfully deposited " + dep + " $");
        }else{
            System.out.println("Error while depositing");
        }
    }

    public static void withdrawal(){
        Scanner in = new Scanner(System.in);
        int amount;

        if (getBalance() == 0){
            System.out.println("You don't have money on your bank account");
            return;
        }

        System.out.println("Enter the withdrawal amount: ");
        amount = in.nextInt();
        if(CustomerBalanceManaging.withdraw(amount)){
            System.out.println("Successfully withdrawn of " + amount + " $");
            System.out.println("Current balance: " + getBalance());
            new Transaction(amount, getCustomerId());
        }else{
            System.out.println("Error while withdrawning");
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