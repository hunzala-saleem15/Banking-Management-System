import java.util.*;
import java.io.*;
public class Joint_Account extends Account {
    private String user2_name;

    public Joint_Account(int id, String name, String date_of_birth, int age, char gender, String occupation, String address, String city, String password, String contactNo, double totalMoney, String user2_name) throws Exception {
        super(id, name, date_of_birth, age, gender, occupation, address, city, password, contactNo, totalMoney, "Joint Account");
        this.user2_name = user2_name;
    }

    public String getuser2_name() {
        return user2_name;
    }

    public void setuser2_name(String user2_name) {
        this.user2_name = user2_name;
    }
}
