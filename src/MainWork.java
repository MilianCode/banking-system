import java.io.*;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class MainWork {

    static HashMap<Integer, Customer> customers = new HashMap<Integer, Customer>();
    static Bank bank;
    static ATM atm;
    public static void main(String[] args) {

        //bank = new Bank("Santander");
        atm = new ATM(bank, 100000);
        Scanner in = new Scanner(System.in);

        boolean log = false;
        int menu = 0;
        System.out.println("====Choose option====");
        System.out.println("1.Log in\n2.Create account");
        menu = in.nextInt();
        if (menu == 1){
            logIn();
        }else if(menu == 2){
            singUp();
        }
        System.out.println("End of program");
    }

    private static void singUp() {
        Scanner in = new Scanner(System.in);

        int customerId = ThreadLocalRandom.current().nextInt(10000, 99999);

        System.out.println("cust: " + customerId);

        if (!customers.isEmpty() && customers.containsKey(customerId)) {
            singUp();
        } else {
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

            Customer customer = new Customer(customerId, name, lastName, address, phone, pincode);
            customer.registration();
            customer.setCustomerId(customerId);
            System.out.println("Your customer id is | " + customerId + " | Please, make sure you remember it. You will need it in future to login into your account");
            System.out.println("Your customer account created.");
        }


    }

    private static boolean logIn(){
        Scanner in = new Scanner(System.in);
        Customer customer = new Customer();
        int customerId, pincode;

        System.out.println("Enter your customerId: ");
        customerId = in.nextInt();
        if(customer.checkCustomerId(customerId)){
            System.out.println("Enter pincode: ");
            pincode = in.nextInt();
            customer.login(customerId, pincode);
            System.out.println("Login successful");
            return true;
        }
        return false;
    }

}

