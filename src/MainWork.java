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

        int menu = 0;
        System.out.println("====Choose option====");
        System.out.println("1.Log in\n2.Create account");
        menu = in.nextInt();
        if (menu == 1){
            int accountNumber, pincode;
            System.out.println("Enter account number: ");
            accountNumber = in.nextInt();
            //bank.checkAccountNumber(accountNumber);
            System.out.println("Enter PIN: ");
            pincode = in.nextInt();
            //bank.checkAccountPin(pincode, accountNumber);
        }else if(menu == 2){
            singUp();
        }
        System.out.println("End of program");
    }

    private static void singUp() {
        Scanner in = new Scanner(System.in);

        int customerId = ThreadLocalRandom.current().nextInt(10000, 99999);

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
            customers.put(customerId, customer);
            System.out.println("Your customer id is | " + customerId + " | Please, make sure you remember it. You will need it in future to login into your account");
            System.out.println("Your customer account created.");
        }


    }

    private static void logIn(){
        Scanner in = new Scanner(System.in);

    }



}

