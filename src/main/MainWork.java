package main;

import customer.Customer;
import customer.CustomerLogin;
import customer.CustomerRegistration;
import transaction.Transaction;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class MainWork {

    public static Customer customer = new Customer();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int menu = 0;
        System.out.println("====Choose option====");
        System.out.println("1.Log in\n2.Create account");
        menu = in.nextInt();
        if (menu == 1){
            if(!CustomerLogin.logIn())return;
        }else if(menu == 2){
            CustomerRegistration.singUp();
            return;
        }else{
            System.out.println("ERROR: Bad choose\nEnd of program");
            return;
        }

        System.out.println("====Choose option====");
        System.out.println("1.Check balance\n2.Deposit\n3.Withdrawal\n4.Transfer\n5.Show all transaction\n6.Exit");
        menu = in.nextInt();
        switch (menu){
            case 1:
                System.out.println("\nYour balance: " + customer.getBalance() +" $\n");
                System.out.println("End of program");
                return;
            case 2:
                deposit();
                System.out.println("End of program");
                return;
            case 3:
                withdrawal();
                System.out.println("End of program");
                return;
            case 4:
                transfer();
                System.out.println("End of program");
                return;
            case 5:
                System.out.println("Amount | To id |             Date             |   Type\n"
                        + customer.showAllTransactions());
                System.out.println("End of program");
                return;
            case 6:
                System.out.println("End of program");
                return;
        }
        System.out.println("End of program");
    }

//    private static void singUp() {
//        Scanner in = new Scanner(System.in);
//
//        int customerId = ThreadLocalRandom.current().nextInt(10000, 99999);
//
//        if (customer.checkCustomerId(customerId)) {
//            singUp();
//        } else {
//            System.out.println("customer.Customer Id: " + customerId);
//
//            System.out.println("Enter customer name: ");
//            String name = in.nextLine();
//
//            System.out.println("Enter lastname: ");
//            String lastName = in.nextLine();
//
//            System.out.println("Enter customer address: ");
//            String address = in.nextLine();
//
//            System.out.println("Enter phone: ");
//            String phone = in.nextLine();
//
//            System.out.println("Create pincode: ");
//            int pincode = in.nextInt();
//
//            Customer customer = new Customer(customerId, name, lastName, address, phone, pincode);
//            customer.registration(customerId);
//            System.out.println("Your customer id is | " + customerId + " | Please, make sure you remember it. You will need it in future to login into your account");
//            System.out.println("Your customer account created.");
//        }
//
//
//    }

//    private static boolean logIn(){
//        Scanner in = new Scanner(System.in);
//        int customerId, pincode;
//
//        System.out.println("Enter your customerId: ");
//        customerId = in.nextInt();
//        if(customer.checkCustomerId(customerId)){
//            System.out.println("Enter pincode: ");
//            pincode = in.nextInt();
//            if(customer.login(customerId, pincode)){
//                System.out.println("Login successful");
//                return true;
//            }else{
//                System.out.println("Login failed");
//                return false;
//            }
//        }else{
//            System.out.println("This customer id doesn't exist");
//        }
//        return false;
//    }


    private static void deposit(){
        Scanner in = new Scanner(System.in);
        int dep;

        System.out.println("Enter amount of deposit: ");
        dep = in.nextInt();
        if(customer.deposit(dep)){
            System.out.println("Successfully deposited " + dep + " $");
        }else{
            System.out.println("Error while depositing");
        }
    }

    private static void withdrawal(){
        Scanner in = new Scanner(System.in);
        int amount;

        if (customer.getBalance() == 0){
            System.out.println("You don't have money on your bank account");
            return;
        }

        System.out.println("Enter the withdrawal amount: ");
        amount = in.nextInt();
        if(customer.withdraw(amount)){
            System.out.println("Successfully withdrawn of " + amount + " $");
            System.out.println("Current balance: " + customer.getBalance());
        }else{
            System.out.println("Error while withdrawning");
        }
    }

    private static void transfer(){
        Scanner in = new Scanner(System.in);
        Customer customerRecieve = new Customer();
        int receiveId, amount;
        String type;

        if (customer.getBalance() == 0){
            System.out.println("You don't have money on your bank account");
            return;
        }

        System.out.println("Enter recipient customer id: ");
        receiveId = in.nextInt();

        if (!customerRecieve.checkCustomerId(receiveId)){
            System.out.println("ERROR: This id doesn't exist");
            return;
        }

        if (customer.getCustomerId() == receiveId){
            System.out.println("ERROR: You can't transfer money to yourself");
            return;
        }

        System.out.println("Enter amount: ");
        amount = in.nextInt();
        if (!customer.transferFrom(amount)){
            return;
        }

        System.out.print("Enter type: ");
        in.nextLine();
        type = in.nextLine();

        customerRecieve.transferTo(amount);
        System.out.println("You successfully transfered " + amount +  "$ to " + customerRecieve.getName() + " " + customerRecieve.getLastName());
        new Transaction(amount, customer, customerRecieve, type);
    }
}

