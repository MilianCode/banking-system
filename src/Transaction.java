import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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
        try {
            Connection connection;
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "root");
            System.out.println("Connection succesful: trans");
            String sql = "INSERT INTO transaction VALUES ( NULL, "+ amount +", "+ fromCustomer.getCustomerId() + ", "+ toCustomer.getCustomerId() +", '"+ date.toString() +"', '"+ type +"')";
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sql);

            connection.close();
            stmt.close();

        }catch (Exception e){
            e.printStackTrace();
        }
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
