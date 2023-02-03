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
            Connection c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:3306:");

            Statement stmt = c.createStatement();

            String sql = "INSERT INTO customer VALUES (customerId, '" + pincode + "', '" + name + "', '" + lastName + "', '" + address + "' , '" + phoneNumber + "', '" + balance + "')";

            stmt.executeUpdate(sql);

            c.close();


            System.out.println("Account created successfully");

            System.out.println("Card number: " + customerId);

            System.out.println("Pincode: " + pincode);


            return true;
        } catch (Exception e) {
            System.out.println(e);
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
