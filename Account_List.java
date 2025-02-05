import java.io.*;
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

public class Account_List {
    private Node head;
    private Queue<String> systemLogs;

    public Account_List() {
        this.head = null;
        this.systemLogs = new LinkedList<>();

    }
    public Node getHead(){
        return head;
    } 
    public void addAccount(Account account) throws Exception{
        Node newNode = new Node(account);
        newNode.setNextNode(head);
        head = newNode;
        saveToFile(account);
    }
    public void saveToFile(Account account) throws Exception {
        Node current = head;

        FileWriter file = new FileWriter("User_Accounts.txt", true);
            file.write(account.getId() + "\n");
            file.write(account.getName() + "\n");
            file.write(account.getPassword() + "\n");
            file.write(account.getDateOfBirth() + "\n");
            file.write(account.getAge() + "\n");
            file.write(account.getGender() + "\n");
            file.write(account.getOccupation() + "\n");
            file.write(account.getAddress() + "\n");
            file.write(account.getCity() + "\n");
            file.write(account.getContactNo() + "\n");
            file.write(account.getTotalMoney() + "\n");

            if (account.getAccountType().equals("Saving Account")) {
                Saving_Account savingAccount = (Saving_Account) account;
                file.write("Saving Account"+"\n");
                file.write("Interest Rate: " + String.valueOf((savingAccount.getInterestRate())) + "\n");
            }
            if (account.getAccountType().equals("Joint Account")) {
                Joint_Account jointAccount = (Joint_Account) account;
                file.write("Joint Account"+"\n");
                file.write("Co Holder Name: " + jointAccount.getuser2_name() + "\n");
            }else{
                Current_Account currentAccount = (Current_Account) account;
                file.write("Current Account"+"\n");
                file.write("Over Draft Limit : " + String.valueOf((currentAccount.getOverdraftLimit())) + "\n");
                
            }


            file.write("\n");

        file.close();
    }
    public Account findAccountByUsername(String username) {
    Node current = head;
    while (current != null) {
        Account account = current.getAccount();
        System.out.println("Searching for: " + username + ", found: " + account.getName()); // Debugging statement
        if (account.getName().equalsIgnoreCase(username)) {
            return account;
        }
        current = current.getNextNode();
    }
    return null;
}
    public void addSystemLog(String log) {
        systemLogs.offer(log);
    }

     public void viewSystemLogs() {
        System.out.println("View System Logs:");
        if (systemLogs.isEmpty()) {
            System.out.println("No system logs available.");
            return;
        }
        for (String log : systemLogs) {
            System.out.println(log);
        }
    }

       public Node findAccountById(int accountId) {
        Node current = head;
        while (current != null) {
            if (current.getAccount().getId() == accountId) {
                return current;
            }
            current = current.getNextNode();
        }
        return null; // Account not found
    }

}
