import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.sql.*;


public class Customer {
    private int customerId;
    private int pincode;
    private String name;
    private String lastName;
    private String address;
    private String phoneNumber;
    private double balance = 0;

    Scanner in = new Scanner(System.in);

    public Customer(){

    }

    public Customer(int customerId, String name, String lastName,String address, String phoneNumber, int pincode) {
        this.customerId = customerId;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.pincode = pincode;
    }

    public boolean registration() {
        try {
            Connection connection;
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "root");
            System.out.println("Connection succesful: reg");

            String sql = "INSERT INTO customer VALUES (customerId, '" + pincode + "', '" + name + "', '" + lastName + "', '" + address + "' , '" + phoneNumber + "', '" + balance + "')";
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sql);

            stmt.close();
            connection.close();

            System.out.println("Account created successfully");

            System.out.println("Card number: " + customerId);

            System.out.println("Pincode: " + pincode);


            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean login(int customerId, int pincode){
        try {
            Connection connection;
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "root");
            System.out.println("Connection succesful: login");

            String sql  = "Select pincode FROM customer WHERE customerId = " + customerId;
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                this.pincode = rs.getInt(1);
            }
            connection.close();
            rs.close();
            stmt.close();

            if (this.pincode == pincode){
                setBalance();
                return true;
            }else{
                System.out.println("Incorrect pin");
                return false;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkCustomerId(int customerId){
        try {
            Connection connection;
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "root");
            System.out.println("Connection succesful: checkId");

            String sql  = "Select customerId FROM customer";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                this.customerId = rs.getInt(1);
                if (this.customerId == customerId){
                    connection.close();
                    rs.close();
                    stmt.close();
                    return true;
                }
            }

            System.out.println("Customer id " + customerId + " doesn't exist");
            return false;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public void setCustomerId(int customerId){
        try {
            Connection connection;
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "root");
            System.out.println("Connection succesful: checkId");

            String sql  = "UPDATE customer SET customerId = " + customerId + " WHERE phoneNumber = '" + phoneNumber + "'";

            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sql);

            connection.close();
            stmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setBalance(){
        try {
            Connection connection;
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "root");
            System.out.println("Connection succesful: setBalance");
            String sql = "Select balance FROM customer WHERE customerId = " + customerId;
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                this.balance = rs.getInt(1);
            }
            connection.close();
            rs.close();
            stmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deposit(double amount) {
        balance += amount;
        try {
            Connection connection;
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "root");
            System.out.println("Connection succesful: deposit");
            String sql = "UPDATE customer SET balance = "+ balance +" WHERE customerId = " + customerId;
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sql);
            connection.close();
            stmt.close();
            System.out.println("Successfully deposited " + amount + " $");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds");
            return false;
        }
        balance -= amount;
        try {
            Connection connection;
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "root");
            System.out.println("Connection succesful: undep");
            String sql = "UPDATE customer SET balance = "+ balance +" WHERE customerId = " + customerId;
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sql);
            connection.close();
            stmt.close();
            System.out.println("Successfully withdrawn of " + amount + " $");
            System.out.println("Current balance: " + balance);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean transferFrom(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds");
            return false;
        }
        balance -= amount;
        try {
            Connection connection;
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "root");
            System.out.println("Connection succesful: undep");
            String sql = "UPDATE customer SET balance = "+ balance +" WHERE customerId = " + customerId;
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sql);
            connection.close();
            stmt.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean transferTo(double amount){
        balance += amount;
        try {
            Connection connection;
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "root");
            System.out.println("Connection succesful: undep");
            String sql = "UPDATE customer SET balance = "+ balance +" WHERE customerId = " + customerId;
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sql);

            sql = "Select name FROM customer WHERE customerId = " + customerId;
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                this.name = rs.getString(1);
            }

            sql = "Select surname FROM customer WHERE customerId = " + customerId;
            rs = stmt.executeQuery(sql);
            while (rs.next()){
                this.lastName = rs.getString(1);
            }

            rs.close();
            connection.close();
            stmt.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }


    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getLastName(){
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public double getBalance(){return balance;}

}
