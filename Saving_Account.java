import java.io.*;
public class Saving_Account extends Account {
    private double interestRate;
    private int id;

    public Saving_Account(int id,String name, String date_of_birth, int age, char gender, String occupation, String address, String city, String password, String contactNo, double totalMoney) throws Exception {
        super(id, name, date_of_birth, age, gender, occupation, address, city, password, contactNo, totalMoney, "Saving Account");
        this.interestRate = 0.03;
        
    }
    public double getInterestRate() {
        return this.interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}
