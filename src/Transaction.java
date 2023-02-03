import java.util.Date;

public class Transaction {
    private double amount;
    private Customer fromCustomer;
    private Customer toCustomer;
    private Date date;
    private String type;

    public Transaction(double amount, Customer fromCustomer, Customer toCustomer, String type) {
        this.amount = amount;
        this.fromCustomer = fromCustomer;
        this.toCustomer = toCustomer;
        this.type = type;
        date = new Date();
    }

    public double getAmount() {
        return amount;
    }

    public Customer getFromAccount() {
        return fromCustomer;
    }

    public Customer getToAccount() {
        return toCustomer;
    }

    public String getType() {
        return type;
    }

    public Date getDate() {
        return date;
    }
}
