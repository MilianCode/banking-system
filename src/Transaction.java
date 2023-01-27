import java.util.Date;

public class Transaction {
    private double amount;
    private Account fromAccount;
    private Account toAccount;
    private Date date;
    private String type;

    public Transaction(double amount, Account fromAccount, Account toAccount, String type) {
        this.amount = amount;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.type = type;
        date = new Date();
    }

    public double getAmount() {
        return amount;
    }

    public Account getFromAccount() {
        return fromAccount;
    }

    public Account getToAccount() {
        return toAccount;
    }

    public String getType() {
        return type;
    }

    public Date getDate() {
        return date;
    }
}
