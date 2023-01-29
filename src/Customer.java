import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Customer {
    private int customerId;
    private String password;
    private String name;
    private String address;
    private String phoneNumber;
    private ArrayList<Account> accounts;

    Scanner in = new Scanner(System.in);

    public Customer(int customerId, String name, String address, String phoneNumber, String password) {
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.password = password;
        accounts = new ArrayList<Account>();
    }



    private boolean checkNewPassword(String password){
        if (password.length() < 8){
            System.out.println("Password must be at least 8 symbols");
            password = in.nextLine();
            checkNewPassword(password);
        }
        return true;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }
}
