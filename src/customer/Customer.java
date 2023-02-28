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
                if (bufId == customerId){
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



//  Method that takes balance to customer, when he logged in. It makes easier to manage balance in program
//    public static void setBalance(){
//        try {
//            Connection connection = DatabaseConnector.getConnection();
//
//            String sql = "Select balance FROM customer WHERE customerId = " + customerId;
//            Statement stmt = connection.createStatement();
//            ResultSet rs = stmt.executeQuery(sql);
//
//            while (rs.next()) {
//                balance = rs.getInt(1);
//            }
//
//            rs.close();
//            stmt.close();
//            connection.close();
//
//        }catch (SQLException e){
//            System.out.println("customer.Customer.setBalance() problem");
//            e.printStackTrace();
//        }
//    }

//  Method that adding money to customer account
//    public boolean deposit(double amount) {
//        balance += amount;
//        try {
//            Connection connection = DatabaseConnector.getConnection();
//
//            String sql = "UPDATE customer SET balance = "+ balance +" WHERE customerId = " + customerId;
//            Statement stmt = connection.createStatement();
//            stmt.executeUpdate(sql);
//
//            connection.close();
//            stmt.close();
//
//            return true;
//
//        }catch (SQLException e){
//            balance -= amount;
//            System.out.println("customer.Customer.deposit problem");
//            e.printStackTrace();
//        }
//
//        return false;
//    }

//   Method that takes money from account, when customer makes withdrawal from ATM
//    public boolean withdraw(double amount) {
//
//        if (!checkIfCanWithdraw(amount)) return false;
//
//        balance -= amount;
//
//        try {
//            Connection connection = DatabaseConnector.getConnection();
//
//            String sql = "UPDATE customer SET balance = "+ balance +" WHERE customerId = " + customerId;
//            Statement stmt = connection.createStatement();
//            stmt.executeUpdate(sql);
//
//            connection.close();
//            stmt.close();
//
//            return true;
//
//        }catch (SQLException e){
//            balance += amount;
//            System.out.println("customer.Customer.withdraw() problem");
//            e.printStackTrace();
//        }
//
//        return false;
//    }

////   Method that takes money from account that makes transaction
//    public static boolean transferFrom(double amount) {
//
//        if (!checkIfCanWithdraw(amount)) return false;
//
//        balance -= amount;
//
//        try {
//            Connection connection = DatabaseConnector.getConnection();
//
//            String sql = "UPDATE customer SET balance = "+ balance +" WHERE customerId = " + customerId;
//            Statement stmt = connection.createStatement();
//            stmt.executeUpdate(sql);
//
//            connection.close();
//            stmt.close();
//
//            return true;
//        }catch (SQLException e){
//            balance += amount;
//            System.out.println("customer.Customer.transferFrom() problem");
//            e.printStackTrace();
//        }
//        return false;
//    }
//
////   Method that adding money to customer, who is transaction reciever
//    public static boolean transferTo(double amount){
//        try {
//            Connection connection = DatabaseConnector.getConnection();
//
//            String sql = "UPDATE customer SET balance = balance + "+ amount +" WHERE customerId = " + customerId;
//            Statement stmt = connection.createStatement();
//            stmt.executeUpdate(sql);
//
//            sql = "Select name FROM customer WHERE customerId = " + customerId;
//            ResultSet rs = stmt.executeQuery(sql);
//
//            while (rs.next()){
//                setName(rs.getString(1));
//            }
//
//            sql = "Select surname FROM customer WHERE customerId = " + customerId;
//            rs = stmt.executeQuery(sql);
//
//            while (rs.next()){
//                setLastName(rs.getString(1));
//            }
//
//            rs.close();
//            connection.close();
//            stmt.close();
//
//            return true;
//
//        }catch (SQLException e){
//            System.out.println("customer.Customer.transferTo() problem");
//            e.printStackTrace();
//        }
//
//        return false;
//    }

// Method that creating String variable that contains information about all transactions from your account
    public static String showAllTransactions(){
        try {
            Connection connection = DatabaseConnector.getConnection();

            String allTransactions = "";
            String sql = "Select amount, toId, date, type FROM transaction WHERE fromId = " + customerId;
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                for (int i = 1; i <= 4; i++) {
                    if (i == 1){

                        int amount = rs.getInt(i);
                        if (amount < 100 && amount >= 10){
                            allTransactions += rs.getString(i) + "$  | ";
                        }else if(amount < 10){
                            allTransactions += rs.getString(i) + "$   | ";
                        }else{
                            allTransactions += rs.getString(i) + "$ | ";
                        }

                    }else if(i == 4){
                        allTransactions += rs.getString(i);
                    }else{
                        allTransactions += rs.getString(i) + " | ";
                    }
                }
                allTransactions += "\n";
            }

            rs.close();
            stmt.close();
            connection.close();

            return allTransactions;
        }catch (SQLException e){
            System.out.println("customer.Customer.showAllTransactions() problem");
            e.printStackTrace();
        }
        return "Error";
    }

//  Method that checks if customer have enought money to make transaction or withdrawal
//  I decided to create this function, because I need to check this instance more than once
    protected static boolean checkIfCanWithdraw(double amount){
        if (amount > balance) {
            System.out.println("Insufficient funds, you have only " + balance + " $ on your bank account");
            return false;
        }else{
            return true;
        }
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


//    private void setCustomerId(int customerId){
//        try {
//            Connection connection;
//            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
//            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "root");
//
////            I was using this to check if this method was succesfully conected to database. It was helping me to detect problems faster.
////            System.out.println("Connection succesful: checkId");
//
//            String sql  = "UPDATE customer SET customerId = " + customerId + " WHERE phoneNumber = '" + phoneNumber + "'";
//
//            Statement stmt = connection.createStatement();
//            stmt.executeUpdate(sql);
//
//            connection.close();
//            stmt.close();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }