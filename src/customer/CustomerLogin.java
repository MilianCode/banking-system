package customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import databaseconnector.DatabaseConnector;
import main.MainWork;

public class CustomerLogin extends Customer{

    public static boolean logIn(){
        Scanner in = new Scanner(System.in);
        int customerId, pincode;

        System.out.println("Enter your customerId: ");
        customerId = in.nextInt();
        if (MainWork.customer.checkCustomerId(customerId)) {
            System.out.println("Enter pincode: ");
            pincode = in.nextInt();
            if(login(customerId, pincode)){
                System.out.println("Login successful");
                return true;
            }else{
                System.out.println("Login failed");
                return false;
            }
        } else {
            System.out.println("This customer id doesn't exist");
        }
        return false;
    }

    public static boolean login(int customerId, int pincode){
        try {

            Connection connection = DatabaseConnector.getConnection();

            String sql  = "Select pincode FROM customer WHERE customerId = " + customerId;
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                MainWork.customer.setPincode(rs.getInt(1));
            }

            rs.close();
            stmt.close();
            connection.close();

            if (MainWork.customer.getPincode() == pincode){
                setBalance();
                return true;
            }else{
                System.out.println("Incorrect pin");
                return false;
            }

        }catch (SQLException e){
            System.out.println("customer.Customer.login() problem");
            e.printStackTrace();
        }
        return false;
    }
}
