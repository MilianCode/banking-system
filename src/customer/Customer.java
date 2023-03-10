package customer;

import com.sun.tools.javac.Main;
import databaseconnector.DatabaseConnector;
import main.MainWork;

import java.util.Scanner;
import java.sql.*;


public class Customer {
    private static int customerId;
    private static int pincode;
    private static String name;
    private static String lastName;
    private static String address;
    private static String phoneNumber;
    private static double balance = 0;

    public Customer(){

    }

    public Customer(int customerId, String name, String lastName,String address, String phoneNumber, int pincode) {
        Customer.setCustomerId(customerId);
        Customer.setName(name);
        Customer.setLastName(lastName);
        Customer.setAddress(address);
        Customer.setPhoneNumber(phoneNumber);
        Customer.setPincode(pincode);
    }

//  Method that checks if customer entered correct customer id
//  I decided to create this function, because I need to check this instance more than once
    public static boolean checkCustomerId(int customerId){
        int bufId;

        try {
            Connection connection = DatabaseConnector.getConnection();

            String sql  = "Select customerId FROM customer";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                bufId = rs.getInt(1);
                if (customerId == bufId){
                    rs.close();
                    stmt.close();
                    connection.close();
                    return true;
                }
            }

        }catch (SQLException e){
            System.out.println("Customer.checkCustomerId() problem");
            e.printStackTrace();
        }

        return false;
    }

//  Method that checks if customer have enought money to make transaction or withdrawal
//  I decided to create this function, because I need to check this instance more than once
    protected static boolean checkIfCanWithdraw(double amount){
        return amount < balance;
    }

    public static int getCustomerId() {
        return customerId;
    }

    public static int getPincode(){ return pincode; }

    public static String getName() {
        return name;
    }

    public static String getLastName(){
        return lastName;
    }

    public static String getAddress() {
        return address;
    }

    public static String getPhoneNumber() {
        return phoneNumber;
    }

    public static double getBalance(){
        return balance;
    }

    public static void setCustomerId(int customerId){ Customer.customerId = customerId; }

    public static void setPincode(int pincode) {
        Customer.pincode = pincode;
    }

    public static void setName(String name) {
        Customer.name = name;
    }

    public static void setLastName(String lastName) {
        Customer.lastName = lastName;
    }

    public static void setAddress(String address) {
        Customer.address = address;
    }

    public static void setPhoneNumber(String phoneNumber) {
        Customer.phoneNumber = phoneNumber;
    }

    public static void setBalance(double balance) {
        Customer.balance = balance;
    }
}