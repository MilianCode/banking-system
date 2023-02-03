public class ATM {
    private Bank bank;
    private double cashInMachine;

    public ATM(Bank bank, double cashInMachine) {
        this.bank = bank;
        this.cashInMachine = cashInMachine;
    }

//    public double checkBalance(int accountNumber) {
//        Account account = bank.getAccount(accountNumber);
//        if (account != null) {
//            return account.getBalance();
//        } else {
//            return -1;
//        }
//    }
//
//    public double withdrawCash(int accountNumber, double amount) {
//        Customer customer = bank.getCustomers(accountNumber);
//        if (account != null) {
//            if (account.getBalance() >= amount && cashInMachine >= amount) {
//                account.withdraw(amount);
//                cashInMachine -= amount;
//                return amount;
//            } else {
//                return -1;
//            }
//        } else {
//            return -1;
//        }
//    }
//
//    public double depositCash(int accountNumber, double amount) {
//        Account account = bank.getAccount(accountNumber);
//        if (account != null) {
//            account.deposit(amount);
//            cashInMachine += amount;
//            return amount;
//        } else {
//            return -1;
//        }
//    }

    public double getCashInMachine() {
        return cashInMachine;
    }
}
