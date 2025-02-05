import java.util.Date;

    public class Transaction {
    private String date;
    private String description;
    private int credit;
    private int debit;
    private int balance;
    private String transaction_type;


    public Transaction(String date, String description, int credit, int debit, int balance) {
        this.date = date;
        this.description = description;
        this.credit = credit;
        this.debit = debit;
        this.balance = balance;
    }

    // Getter methods
    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public int getCredit() {
        return credit;
    }

    public int getDebit() {
        return debit;
    }

    public int getBalance() {
        return balance;
    }

    // Setter methods
    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public void setDebit(int debit) {
        this.debit = debit;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    } 
    public void setTransactionType(String transactionType){
        this.transaction_type = transactionType;
    }
    public String getTransactionType(){
        return this.transaction_type;
    }
}
