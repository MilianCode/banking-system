import java.util.Scanner;

public class Account implements java.io.Serializable{
    private int accountNumber;
    private String accountHolderName;
    private String accountName;
    private double balance;
    private int pincode;

    public Account(){

    }
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

    public boolean checkId(int accountNumber){
        if (this.accountNumber == accountNumber){
            return true;
        }else {
            return false;
        }
    }

    public boolean checkPin(int pincode) {
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
