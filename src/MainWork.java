import java.io.*;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class MainWork {

    static Customer customer = new Customer();
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
            if(!logIn())return;
        }else if(menu == 2){
            singUp();
            return;
        }else{
            System.out.println();
        }

        System.out.println("====Choose option====");
        System.out.println("1.Check balance\n2.Deposit\n3.Withdrawal\n4.Transfer\n5.Exit");
        menu = in.nextInt();
        switch (menu){
            case 1:
                System.out.println("\nYour balance: " + customer.getBalance() +" $\n");
                return;
            case 2:
                deposit();
                return;
            case 3:
                withdrawal();
                return;
            case 4:

            case 5:
                System.out.println("End of program");
                return;
        }
        System.out.println("End of program");
    }

    private static void singUp() {
        Scanner in = new Scanner(System.in);

        int customerId = ThreadLocalRandom.current().nextInt(10000, 99999);

        System.out.println("cust: " + customerId);

        if (customer.checkCustomerId(customerId)) {
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
        int customerId, pincode;

        System.out.println("Enter your customerId: ");
        customerId = in.nextInt();
        if(customer.checkCustomerId(customerId)){
            System.out.println("Enter pincode: ");
            pincode = in.nextInt();
            if(customer.login(customerId, pincode)){
                System.out.println("Login successful");
                return true;
            }else{
                System.out.println("Login failed");
                return false;
            }

        }
        return false;
    }


    private static void deposit(){
        Scanner in = new Scanner(System.in);
        int dep;

        System.out.println("Enter amount of deposit: ");
        dep = in.nextInt();
        customer.deposit(dep);
    }

    private static void withdrawal(){
        Scanner in = new Scanner(System.in);
        int amount;

        System.out.println("Enter the withdrawal amount: ");
        amount = in.nextInt();
        customer.withdraw(amount);
    }
}

