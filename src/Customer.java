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
    private double balance;

    Scanner in = new Scanner(System.in);

    public Customer(int customerId, String name, String lastName,String address, String phoneNumber, int pincode) {
        this.customerId = customerId;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.pincode = pincode;
    }

    public Boolean registration() {
        try {
            Connection connection;
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "root");
            System.out.println("Connection succesful");

            String sql = "INSERT INTO customer VALUES (customerId, '" + pincode + "', '" + name + "', '" + lastName + "', '" + address + "' , '" + phoneNumber + "', '" + balance + "')";
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sql);

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

    public boolean checkPin(int pincode) {
        if (this.pincode == pincode){
            return true;
        }else {
            return false;
        }
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            return false;
        }
        balance -= amount;
        return true;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public double getBalance(){return balance;}

}
