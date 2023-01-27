public class MainTest {
    public static void main(String[] args) {
        Bank bank = new Bank("Santander");
        Customer customer1 = new Customer(1, "John Doe", "123 Main St.", "555-555-5555");
        Account account1 = new Account(1, customer1.getName(), 1000, 3456);
        customer1.addAccount(account1);
        bank.addAccount(account1);

        Customer customer2 = new Customer(2, "Jane Smith", "456 Park Ave.", "555-555-5556");
        Account account2 = new Account(2, customer2.getName(), 2000, 1234);
        Account account3 = new Account(3, customer2.getName(), 3000, 2345);
        customer2.addAccount(account2);
        customer2.addAccount(account3);
        bank.addAccount(account2);
        bank.addAccount(account3);

        ATM atm = new ATM(bank, 100000);
        double balance = atm.checkBalance(1);
        System.out.println("Balance of account 1: " + balance);
        double withdrawal = atm.withdrawCash(1, 500);
        System.out.println("Withdrawal of account 1: " + withdrawal);
        balance = atm.checkBalance(1);
        System.out.println("Balance of account 1: " + balance);
        double deposit = atm.depositCash(1, 1000);
        System.out.println("Deposit of account 1: " + deposit);
        balance = atm.checkBalance(1);
        System.out.println("Balance of account 1: " + balance);
    }
}

