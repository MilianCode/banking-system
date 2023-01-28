import java.util.Scanner;

public class Account {
    private int accountNumber;
    private String accountHolderName;
    private double balance;
    private int pincode;

    public Account(int accountNumber, String accountHolderName, double balance, int pincode) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.pincode = pincode;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public boolean logIn() {
        int pincodeLog = 0;
        if (pincodeLog > 9999 || pincodeLog < 0000){
            System.out.println("Sorry, pincode must contain 4 characters\nTry again: ");
            Scanner in = new Scanner(System.in);
            pincode = in.nextInt();
            logIn();
        }

        if (this.pincode == pincode){
            return true;
        }else {
            return false;
        }
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            return false;
        }
        balance -= amount;
        return true;
    }
}
