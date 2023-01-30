import java.io.*;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class MainWork {

    static HashMap<Integer, Customer> customers = new HashMap<Integer, Customer>();
    static Bank bank;
    public static void main(String[] args) {
//        try {
//            FileInputStream fileIn = new FileInputStream("bank.txt");
//            ObjectInputStream in = new ObjectInputStream(fileIn);
//            bank = (Bank) in.readObject();
//            in.close();
//            fileIn.close();
//        } catch (IOException i) {
//            i.printStackTrace();
//            return;
//        } catch (ClassNotFoundException c) {
//            System.out.println("Employee class not found");
//            c.printStackTrace();
//            return;
//        }
        //customers = bank.getCustomers();
        //customers.put(2, new Customer(66666, "admin", "admin", "999888777","maksym1"));
        bank = new Bank("Santander");
        ATM atm = new ATM(bank, 100000);
        Scanner in = new Scanner(System.in);

        int menu = 0;
        System.out.println("====Choose option====");
        System.out.println("1.Log in\n2.Create account");
        menu = in.nextInt();
        if (menu == 1){
            int accountNumber, pincode;
            System.out.println("Enter account number: ");
            accountNumber = in.nextInt();
            bank.checkAccountNumber(accountNumber);
            System.out.println("Enter PIN: ");
            pincode = in.nextInt();
            bank.checkAccountPin(pincode, accountNumber);
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
            System.out.println("Enter customer address: ");
            String address = in.nextLine();
            System.out.println("Enter phone: ");
            String phone = in.nextLine();
            System.out.println("Enter password: ");
            String password = in.nextLine();

            Customer customer = new Customer(customerId, name, address, phone, password);
            customers.put(customerId, customer);
            bank.addCustomer(customerId, customer);
            System.out.println("Your customer id is | " + customerId + " | Please, make sure you remember it. You will need it in future to login into your account");
            System.out.println("Your customer account created.");

            try {
                FileOutputStream fileOut = new FileOutputStream("bank.txt");
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                //out.writeObject(customers);
                out.writeObject(bank);
                out.close();
                fileOut.close();
                System.out.println("Serialized data is saved in bank.txt");
            } catch (IOException i) {
                i.printStackTrace();
            }
        }


    }

    private static void logIn(){
        Scanner in = new Scanner(System.in);

    }



}

