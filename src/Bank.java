import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class Bank implements java.io.Serializable{
    private ArrayList<Account> accounts;
    private HashMap<Integer, Customer> customers = new HashMap<Integer, Customer>();
    private String bankName;

    public Bank(String bankName) {
        this.bankName = bankName;
        accounts = new ArrayList<Account>();
    }

    public String getBankName() {
        return bankName;
    }
    public HashMap<Integer, Customer> getCustomers(){
        return customers;
    }
    public void addCustomer(int customerId, Customer customer){
        customers.put(customerId, customer);
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

    public boolean checkAccountNumber(int accountNumber){
        if (getAccount(accountNumber) != null){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkAccountPin(int pincode, int accountNumber){
        Account account =  new Account();
        account = getAccount(accountNumber);
        return account.checkPin(pincode);
    }

//    private void writeObject(ObjectOutputStream oos)
//            throws IOException {
//        oos.defaultWriteObject();
//        oos.writeObject(customers);
//    }

//    private void readObject(ObjectInputStream ois)
//            throws ClassNotFoundException, IOException {
//        ois.defaultReadObject();
//        Integer houseNumber = (Integer) ois.readObject();
//        Address a = new Address();
//        a.setHouseNumber(houseNumber);
//        this.setAddress(a);
//    }
}
