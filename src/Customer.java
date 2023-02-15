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

    public boolean registration(int customerId) {
        try {
            this.customerId = customerId;
            Connection connection;
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "root");

            String sql = "INSERT INTO customer VALUES (customerId, '" + pincode + "', '" + name + "', '" + lastName + "', '" + address + "' , '" + phoneNumber + "', '" + balance + "')";
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sql);

            sql  = "UPDATE customer SET customerId = " + customerId + " WHERE phoneNumber = '" + phoneNumber + "'";
            stmt.executeUpdate(sql);

            stmt.close();
            connection.close();

            System.out.println("Account created successfully");

            System.out.println("Customer Id(you need to remember it): " + customerId);

            System.out.println("Pincode: " + pincode);


            return true;
        } catch (Exception e) {
            System.out.println("Customer.registration() problem");
            e.printStackTrace();
        }

        return false;
    }

    public boolean login(int customerId, int pincode){
        try {
            Connection connection;
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "root");

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
            System.out.println("Customer.login() problem");
            e.printStackTrace();
        }
        return false;
    }


//  Method that checks if customer entered correct customer id
//  I decided to create this function, because I need to check this instance more than once
    public boolean checkCustomerId(int customerId){
        try {
            Connection connection;
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "root");

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

        }catch (Exception e){
            System.out.println("Customer.checkCustomerId() problem");
            e.printStackTrace();
        }

        return false;
    }



//  Method that takes balance to customer, when he logged in. It makes easier to manage balance in program
    public void setBalance(){
        try {
            Connection connection;
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "root");

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
            System.out.println("Customer.setBalance() problem");
            e.printStackTrace();
        }
    }

//  Method that adding money to customer account
    public boolean deposit(double amount) {
        balance += amount;
        try {
            Connection connection;
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "root");

            String sql = "UPDATE customer SET balance = "+ balance +" WHERE customerId = " + customerId;
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sql);

            connection.close();
            stmt.close();

            return true;

        }catch (Exception e){
            balance -= amount;
            System.out.println("Customer.deposit problem");
            e.printStackTrace();
        }

        return false;
    }

//   Method that takes money from account, when customer makes withdrawal from ATM
    public boolean withdraw(double amount) {

        if (!checkIfCanWithdraw(amount)) return false;

        balance -= amount;

        try {
            Connection connection;
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "root");

            String sql = "UPDATE customer SET balance = "+ balance +" WHERE customerId = " + customerId;
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sql);

            connection.close();
            stmt.close();

            return true;

        }catch (Exception e){
            balance += amount;
            System.out.println("Customer.withdraw() problem");
            e.printStackTrace();
        }

        return false;
    }

//   Method that takes money from account that makes transaction
    public boolean transferFrom(double amount) {

        if (!checkIfCanWithdraw(amount)) return false;

        balance -= amount;

        try {
            Connection connection;
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "root");

            String sql = "UPDATE customer SET balance = "+ balance +" WHERE customerId = " + customerId;
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sql);

            connection.close();
            stmt.close();

            return true;
        }catch (Exception e){
            balance += balance;
            System.out.println("Customer.transferFrom() problem");
            e.printStackTrace();
        }
        return false;
    }

//   Method that adding money to customer, who is transaction reciever
    public boolean transferTo(double amount){
        try {
            Connection connection;
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "root");

            String sql = "UPDATE customer SET balance = balance + "+ amount +" WHERE customerId = " + customerId;
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
            System.out.println("Customer.transferTo() problem");
            e.printStackTrace();
        }

        return false;
    }

// Method that creating String variable that contains information about all transactions from your account
    public String showAllTransactions(){
        try {
            Connection connection;
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "root");

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
            connection.close();
            stmt.close();

            return allTransactions;
        }catch (Exception e){
            System.out.println("Customer.showAllTransactions() problem");
            e.printStackTrace();
        }
        return "Error";
    }

//  Method that checks if customer have enought money to make transaction or withdrawal
//  I decided to create this function, because I need to check this instance more than once
    private boolean checkIfCanWithdraw(double amount){
        if (amount > balance) {
            System.out.println("Insufficient funds, you have only " + balance + " $ on your bank account");
            return false;
        }else{
            return true;
        }
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

    public double getBalance(){
        return balance;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setBalance(double balance) {
        this.balance = balance;
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