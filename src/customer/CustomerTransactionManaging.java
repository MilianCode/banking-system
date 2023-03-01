package customer;

import databaseconnector.DatabaseConnector;
import transaction.Transaction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CustomerTransactionManaging extends Customer{


    public static void transfer(){
        Scanner in = new Scanner(System.in);
        int receiveId, amount;
        String type;

        if (Customer.getBalance() == 0){
            System.out.println("You don't have money on your bank account");
            return;
        }

        System.out.println("Enter recipient customer id: ");
        receiveId = in.nextInt();

        if (!Customer.checkCustomerId(receiveId)){
            System.out.println("ERROR: This id doesn't exist");
            return;
        }

        if (Customer.getCustomerId() == receiveId){
            System.out.println("ERROR: You can't transfer money to yourself");
            return;
        }

        System.out.println("Enter amount: ");
        amount = in.nextInt();
        if (!CustomerTransactionManaging.transferFrom(amount)){
            return;
        }

        System.out.print("Enter type: ");
        in.nextLine();
        type = in.nextLine();

        CustomerTransactionManaging.transferTo(amount, receiveId);
        System.out.println("You successfully transfered " + amount +  "$ to " + receiveId);
        new Transaction(amount, Customer.getCustomerId(), receiveId, type);
    }


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

    public static String showAllTransactions(){
        try {
            Connection connection = DatabaseConnector.getConnection();

            String allTransactions = "";
            String sql = "Select amount, toId, date, type FROM transaction WHERE fromId = " + getCustomerId();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                for (int i = 1; i <= 4; i++) {
                    if (i == 1){

                        int amount = rs.getInt(i);
                        if ((amount < 100 && amount >= 10) || (amount < 0 && amount > -10)){
                            allTransactions += rs.getString(i) + "  $| ";
                        }else if(amount < 10 && amount > 0){
                            allTransactions += rs.getString(i) + "   $| ";
                        }else if(amount >= 1000 || amount < -99){
                            allTransactions += rs.getString(i) + "$| ";
                        }else{
                            allTransactions += rs.getString(i) + " $| ";
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

}
