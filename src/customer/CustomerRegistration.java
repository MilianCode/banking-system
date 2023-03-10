package customer;

import databaseconnector.DatabaseConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class CustomerRegistration extends Customer{

    public static void singUp() {
        Scanner in = new Scanner(System.in);

        int customerId = ThreadLocalRandom.current().nextInt(10000, 99999);

        if (checkCustomerId(customerId)) {
            singUp();
        } else {
            System.out.println("Customer Id: " + customerId);

            System.out.println("Enter customer name: ");
            String name = in.nextLine();

            System.out.println("Enter lastname: ");
            String lastName = in.nextLine();

            System.out.println("Enter customer address: ");
            String address = in.nextLine();

            System.out.println("Enter phone: ");
            String phone = in.nextLine();

            System.out.println("Create pincode: ");
            int pincode = in.nextInt();

            new Customer(customerId, name, lastName, address, phone, pincode);
            registration(customerId);
            System.out.println("Your customer id is | " + customerId + " | Please, make sure you remember it. You will need it in future to login into your account");
            System.out.println("Your customer account created.");
        }
    }

    public static boolean registration(int customerId) {
        setCustomerId(customerId);
        try {
            Connection connection = DatabaseConnector.getConnection();

            String sql = "INSERT INTO customer VALUES ("+ getCustomerId() + ", '" + getPincode() + "', '" + getName() + "', " +
                    "'" + getLastName() + "', '" + getAddress() + "' , '" + getPhoneNumber() + "', '" + getBalance() + "')";
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sql);

            stmt.close();
            connection.close();

            System.out.println("Account created successfully");
            System.out.println("Customer Id(you need to remember it): " + customerId);
            System.out.println("Pincode: " + getPincode());
            return true;
        } catch (SQLException e) {
            System.out.println("CustomerReg.registration() problem");
            e.printStackTrace();
        }
        return false;
    }
}