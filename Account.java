import java.util.Scanner;
import java.io.*;

public class Account {
    private int id;
    private String name;
    private String date_of_birth;
    private int age;
    private char gender;
    private String occupation;
    private String address;
    private String city;
    private String password;
    private String contactNo;
    private double totalMoney;
    private String account_type;

    public Account(int id, String name, String date_of_birth, int age, char gender, String occupation, String address, String city, String password, String contactNo, double totalMoney, String account_type) {
        this.id = id;
        this.name = name;
        this.date_of_birth = date_of_birth;
        this.age = age;
        this.gender = gender;
        this.occupation = occupation;
        this.address = address;
        this.city = city;
        this.password = password;
        this.contactNo = contactNo;
        this.totalMoney = totalMoney;
        this.account_type = account_type;
    }

    // Getter and Setter methods for all fields
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return date_of_birth;
    }

    public void setDateOfBirth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getAccountType() {
        return account_type;
    }

    public void setAccountType(String account_type) {
        this.account_type = account_type;
    }
    public void display() throws Exception{
        FileWriter file = new FileWriter("User_Accounts.txt", true);
            
        file.write("ID: " + getId() + "\n");
        file.write("Name: " + getName() + "\n");
        file.write("Password: " + getPassword() + "\n");
        file.write("Date of Birth: " + getDateOfBirth() + "\n");
        file.write("Age: " + getAge() + "\n");
        file.write("Gender: " + getGender() + "\n");
        file.write("Occupation: " + getOccupation() + "\n");
        file.write("Address: " + getAddress() + "\n");
        file.write("City: " + getCity() + "\n");
        file.write("Account Type: Saving Account\n");
        file.write("Contact Number: " + getContactNo() + "\n");
        file.write("Total Money: " + getTotalMoney() + "\n");
        if (getAccountType().equals("Saving Account")) {
            // Assuming you have a Saving_Account field in Account class
            ((Saving_Account) this).display();
        }
        file.write("\n");
        
    }
    public void deposit(double amount) throws IOException {
        if (amount > 0) {
            totalMoney += amount;
            updateAccountFile();
            System.out.println("Deposit successful. New balance: " + totalMoney);
        } else {
            System.out.println("Invalid amount. Deposit amount must be greater than zero.");
        }
    }

    
    public void withdraw(double amount) throws IOException {
        if (amount > 0 && amount <= totalMoney) {
            totalMoney -= amount;
            updateAccountFile(); 
            System.out.println("Withdrawal successful. New balance: " + totalMoney);
        } else {
            System.out.println("Invalid amount or insufficient balance.");
        }
    }

    
    private void updateAccountFile() throws IOException {
        try (FileWriter file = new FileWriter("User_Accounts.txt", false)) {
            file.write("ID: " + getId() + "\n");
            file.write("Name: " + getName() + "\n");
            file.write("Password: " + getPassword() + "\n");
            file.write("Date of Birth: " + getDateOfBirth() + "\n");
            file.write("Age: " + getAge() + "\n");
            file.write("Gender: " + getGender() + "\n");
            file.write("Occupation: " + getOccupation() + "\n");
            file.write("Address: " + getAddress() + "\n");
            file.write("City: " + getCity() + "\n");
            file.write("Account Type: Saving Account\n");
            file.write("Contact Number: " + getContactNo() + "\n");
            file.write("Total Money: " + getTotalMoney() + "\n");
            file.write("\n");
        }
    }

}
