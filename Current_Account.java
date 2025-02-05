import java.util.*;

public class Current_Account extends Account {
    private double overdraftLimit;
    private Stack<Transaction> transactionHistory;

    public Current_Account(int id, String name, String date_of_birth, int age, char gender, String occupation, String address, String city, String password, String contactNo, double totalMoney) throws Exception {
        super(id, name, date_of_birth, age, gender, occupation, address, city, password, contactNo, totalMoney, "Current Account");
        this.overdraftLimit = 3.56;
        this.transactionHistory = new Stack<>();
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    public Stack<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void addToTransactionHistory(Transaction transaction) {
        transactionHistory.push(transaction);
    }

    public Transaction popFromTransactionHistory() {
        return transactionHistory.pop();
    }
}
