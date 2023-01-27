import java.util.ArrayList;

public class Bank {
    private ArrayList<Account> accounts;
    private String bankName;

    public Bank(String bankName) {
        this.bankName = bankName;
        accounts = new ArrayList<Account>();
    }

    public String getBankName() {
        return bankName;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public Account getAccount(int accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }

    public void addCustomer(Customer customer) {

    }
}
