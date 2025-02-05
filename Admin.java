import java.util.*;
import java.io.*;
public class Admin {
    private boolean isAdminLoginSuccessfully;
    private Account_List accountList;
    private int nodeIndex;
    private Queue<String> systemLogs;
    Node head;
    private String admin_name;
    private String admin_pass;
    private User user;

    public void setName(String name){
        this.admin_name = name;
    }
    public String getName(){
        return this.admin_name;
    }
    public void setPass(String pass){
        this.admin_pass = pass;
    }
    public String getPass(){
        return this.admin_pass;
    }
    public Admin(Account_List accountList) {
        isAdminLoginSuccessfully = false;
        this.accountList = accountList;
        nodeIndex =  0;
        head = new Node(null); 
        systemLogs = new LinkedList<>();

        this.admin_pass = "Admin12345";
        this.admin_name = "Admin";

    }
    public Admin(){
        this.accountList=accountList;
        nodeIndex=0;
        head=new Node(null);
        systemLogs = new LinkedList<>();
        this.user = user;
    }
    public void createNewAccount() throws Exception {
        Scanner scanner = new Scanner(System.in);
        Account newAccount = null;
        if(isAdminLoginSuccessfully){
            System.out.println("Enter some details:");
            System.out.println("Enter ID: ");
                int id = scanner.nextInt();
                scanner.nextLine();
            System.out.print("Enter your Full Name: ");
                String name = scanner.nextLine();
            System.out.print("Enter your Date of Birth (YYYY-MM-DD): ");
                String date_of_birth = scanner.nextLine();
            System.out.print("Enter your Age: ");        //condition
                int age = scanner.nextInt();
                scanner.nextLine();
            if(age<18){
                System.out.println("You are not eligible to craete an account in bank.");
            }else{
                System.out.print("Enter your Gender: ");
                    char gender = scanner.next().charAt(0);
                    scanner.nextLine();
                System.out.print("Enter your contact number: ");
                    String contactNo = scanner.nextLine();
                System.out.print("Enter your Occupation: ");
                    String occupation = scanner.nextLine();
                System.out.print("Enter your Address: ");
                    String address = scanner.nextLine();
                System.out.print("Enter your city name: ");
                    String city = scanner.nextLine();
                System.out.print("Enter the initial amount of money: ");
                    double totalMoney = scanner.nextDouble();
                while(totalMoney<2000){
                    System.out.println("Initial Money should be greater than 2000.");
                    totalMoney = scanner.nextDouble();
                }
                
                System.out.print("Set account password: ");   
                    String password = scanner.nextLine();
                while (!isPasswordValid(password)) {
                    System.out.print("Password should be of length 15 and atleast contain numbers and letters.!");
                    password = scanner.nextLine();
                }
                
                System.out.println("Enter Account Type: ");
                System.out.println("1. Joint Account");
                System.out.println("2. Saving Account");
                System.out.println("3. Current Account");
                System.out.println("Choose an Option: ");
                int choice = scanner.nextInt();

                while(choice <1 || choice>3){
                    System.out.println("Invalid Choice.");
                    choice = scanner.nextInt();
                }
                if(choice == 1){
                    scanner.nextLine();
                    System.out.print("Enter your CoAccount Holder Name: ");
                    String user2_name = scanner.nextLine();
                    newAccount = new Joint_Account(id, name, date_of_birth, age, gender , occupation, address, city, password, contactNo, totalMoney, user2_name);
                }else if(choice == 2){
                    newAccount = new Saving_Account(id, name, date_of_birth, age, gender , occupation, address, city, password, contactNo, totalMoney);
                }else{
                    newAccount = new Current_Account(id, name, date_of_birth, age, gender , occupation, address, city, password, contactNo, totalMoney);
                }

                try {
                    accountList.addAccount(newAccount);
                    System.out.println("New account created successfully.");
                } catch (Exception e) {
                    System.out.println("Error adding account: " + e.getMessage());
                }
            }
        }else{
            System.out.println("Please Login! First.");
            admin_login();
        }
    }
    private boolean isPasswordValid(String password) {
        // Define password requirements
        int minLength = 9;  // Minimum length
        int maxLength = 20; // Maximum length
        int minUpperCase = 1;  // Minimum uppercase letters
        int minLowerCase = 1;  // Minimum lowercase letters
        int minDigits = 1;  // Minimum digits
        int minSpecialChars = 1;  // Minimum special characters

        // Password length check
        if (password.length() < minLength || password.length() > maxLength) {
            return false;
        }

        // Counters for character types
        int upperCaseCount = 0;
        int lowerCaseCount = 0;
        int digitCount = 0;
        int specialCharCount = 0;

        // Check each character in the password
        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                upperCaseCount++;
            } else if (Character.isLowerCase(ch)) {
                lowerCaseCount++;
            } else if (Character.isDigit(ch)) {
                digitCount++;
            } else {
                // Consider any character that is not a letter or digit as a special character
                specialCharCount++;
            }
        }

        // Password requirements check
        return upperCaseCount >= minUpperCase &&
                lowerCaseCount >= minLowerCase &&
                digitCount >= minDigits &&
                specialCharCount >= minSpecialChars;
    }
    public boolean admin_login(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("----- Admin Login ---- ");
        System.out.print("Adminname: ");
        String adminname = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        if(adminname.equals(admin_name) && password.equals(admin_pass)){
            isAdminLoginSuccessfully = true;
            System.out.println("Successfully logged in");
            return true;
        }else{
            isAdminLoginSuccessfully = false;
            System.out.println("Login Failed!");
            return false;
        }
    }
    public void processApplication(Queue<Application> applicationQueue) throws Exception{
        System.out.println("Processing Applications...");

        while (!applicationQueue.isEmpty()) {
            Application application = applicationQueue.poll();

            // Process the application based on type
            String applicationType = application.getApplicationType();

            if (applicationType.equals("Safety Deposit Box/Locker")) {
                boolean accept = processLockerApplication(application);
                System.out.println(accept);
            } else if (applicationType.equals("Debit/Credit Card")) {
                boolean accept = processCardApplication(application);
                System.out.println(accept);
            } else if (applicationType.equals("Cheque Book")) {
                boolean accept = processChequeBookApplication(application);
                System.out.println(accept);
            } else if (applicationType.equals("Loan")) {
                boolean accept = processLoanApplication(application);
                System.out.println(accept);
            } else {
                System.out.println("Unknown application type. Application rejected for User ID " + application.getUserId());
            }
        }
        applicationQueue.clear();
        System.out.println("All applications processed.");
    }
    private boolean processLockerApplication(Application application) {
        try {
            File file = new File("User_Accounts.txt");
            Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String currentUserIdStr = scanner.nextLine();

            try {
                int currentUserId = Integer.parseInt(currentUserIdStr.trim());

                if (currentUserId == application.getUserId()) {
                    for (int i = 0; i < 10; i++) {
                        scanner.nextLine();
                    }

                    // Read and check the account type
                    String accountType = scanner.nextLine().trim();
                    if (accountType.equals("Joint Account")) {
                        return false;
                    } else {
                        return true;
                    }
                } else {
                    for (int i = 0; i < 10; i++) {
                        scanner.nextLine();
                    }
                }
            } catch (NumberFormatException e) {
                // Handle the case where the current user ID cannot be parsed
                continue;
            }
        }
    } catch (FileNotFoundException e) {
        System.out.println("Error reading user accounts file: " + e.getMessage());
    }

    return false;
    }
    private boolean processCardApplication(Application application) {
        try {
            File file = new File("User_Accounts.txt");
            Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String currentUserIdStr = scanner.nextLine();

            try {
                int currentUserId = Integer.parseInt(currentUserIdStr.trim());

                if (currentUserId == application.getUserId()) {
                    for (int i = 0; i < 10; i++) {
                        scanner.nextLine();
                    }

                    // Read and check the account type
                    String accountType = scanner.nextLine().trim();
                    if (accountType.equals("Saving Account")) {
                        return false;
                    } else {
                        return true;
                    }
                } else {
                    for (int i = 0; i < 10; i++) {
                        scanner.nextLine();
                    }
                }
            } catch (NumberFormatException e) {
                // Handle the case where the current user ID cannot be parsed
                continue;
            }
        }
    } catch (FileNotFoundException e) {
        System.out.println("Error reading user accounts file: " + e.getMessage());
    }

    return false;
    }
    private boolean processChequeBookApplication(Application application) {
        return true;
    }
    private boolean processLoanApplication(Application application) throws Exception{
        Scanner scanner = new Scanner(System.in);
        double money = getTotalAmount(application);
        System.out.println("How much you want: ");
        double loan = scanner.nextDouble();
        if(loan > money){
            return true;
        }else{
            return false;
        }
    }
    
    public void generate_account_statement() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter user's credentials: ");
        System.out.println("Enter the id of the user: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        boolean exist = false;

        try {
            File file = new File("User_Accounts.txt");
            Scanner read = new Scanner(file);

            while (read.hasNextLine()) {
                String storedUserID = read.nextLine();

                // Assuming storedUserID is an integer
                int storedID = Integer.parseInt(storedUserID);

                if (storedID == id) {
                    exist = true;
                    break; // No need to continue searching if the user is found
                }
            }
            read.close();

            if (exist) {
                read = new Scanner(file);

                while (read.hasNextLine()) {
                    String line = read.nextLine();
                    System.out.println(line); // Print each line to the command line
                }
                read.close();
            } else {
                System.out.println("User not found.");
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }
    public double getTotalAmount(Application application) throws FileNotFoundException {
    Scanner scanner = new Scanner(System.in);
    File file = new File("User_Accounts.txt");
    Scanner read = new Scanner(file);

    while (read.hasNextLine()) {
        String currentUserIdStr = read.nextLine();

        try {
            int currentUserId = Integer.parseInt(currentUserIdStr.trim());

            if (currentUserId == application.getUserId()) {
                for (int i = 0; i < 9; i++) {
                    read.nextLine();
                }

                return Double.parseDouble(read.nextLine().trim());
            } else {
                for (int i = 0; i < 10; i++) {
                    read.nextLine();
                }
            }
        } catch (NumberFormatException e) {
            // Handle the case where the current user ID cannot be parsed
            continue;
        }
    }
    return -1;
}
    public void findAccountById() {
    System.out.println("Finding account by ID...");
    if (isAdminLoginSuccessfully) {
        System.out.print("Enter account ID to find: ");
        Scanner scanner = new Scanner(System.in);
        int accountId = scanner.nextInt();
        scanner.nextLine();

        try {
            File file = new File("User_Accounts.txt");
            Scanner fileScanner = new Scanner(file);
            boolean accountFound = false;

            while (fileScanner.hasNextLine()) {
                int id = fileScanner.nextInt();
                fileScanner.nextLine(); // Consume the leftover newline character after reading the ID
                if (id == accountId) {
                    // Account found, display its details
                    System.out.println("Account Details:");
                    System.out.println("ID: " + id);
                    System.out.println("Name: " + fileScanner.nextLine()); // Now correctly reads the name
                    System.out.println("Password: " + fileScanner.nextLine());
                    System.out.println("Gender: " + fileScanner.nextLine());
                    System.out.println("Occupation: " + fileScanner.nextLine());
                    System.out.println("Address: " + fileScanner.nextLine());
                    System.out.println("Phone Number: " + fileScanner.nextLine());
                    System.out.println("Balance: " + fileScanner.nextLine());
                    System.out.println("Account Type: " + fileScanner.nextLine());
                    accountFound = true;
                    break; // Exit the loop once the account is found and processed
                } else {
                    // Skip the rest of the account details if the ID does not match
                    for (int i = 0; i < 8; i++) { // Assuming 8 lines per account
                        if (fileScanner.hasNextLine()) {
                            fileScanner.nextLine();
                        } else {
                            break; // Break if no more lines
                        }
                    }
                }
            }
            if (!accountFound) {
                System.out.println("No account found with the given ID.");
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error reading user data file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    } else {
        System.out.println("Please Login! First.");
    }
}public void updateExistingAccount() {
    if (isAdminLoginSuccessfully) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter account ID to update: ");
        String input = scanner.nextLine();

        int accountId;
        try {
            accountId = Integer.parseInt(input.replaceAll("\\D", ""));
        } catch (NumberFormatException e) {
            System.out.println("Invalid account ID format. Please enter a valid numeric ID.");
            return;
        }

        try {
            File file = new File("User_Accounts.txt");
            File tempFile = new File("temp_User_Accounts.txt");

            try (Scanner fileScanner = new Scanner(file);
                 PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {

                boolean accountFound = false;

                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();

                    if (!accountFound) {
                        int id = Integer.parseInt(line.trim());

                        if (id == accountId) {
                            accountFound = true;

                            // Write account ID
                            writer.println(id);
                        
                            // Read existing account details
                            String[] accountDetails = new String[11];
                            for (int i = 0; i < accountDetails.length; i++) {
                                if (fileScanner.hasNextLine()) {
                                    accountDetails[i] = fileScanner.nextLine();
                                } else {
                                    throw new NoSuchElementException("No line found");
                                }
                            }

                            // Prompt user for new details
                            System.out.print("Enter new contact number: ");
                            String newContactNo = scanner.nextLine();
                            System.out.print("Enter new total money: ");
                            String newTotalMoney = scanner.nextLine(); 
                            System.out.print("Enter new Address: ");
                            String newAddress = scanner.nextLine();
                            System.out.print("Enter new City: ");
                            String newCity = scanner.nextLine();

                            // Update specific fields
                            accountDetails[8] = newContactNo;
                            accountDetails[9] = newTotalMoney;
                            accountDetails[6] = newAddress;
                            accountDetails[7] = newCity;

                            // Write updated details to file
                            for (String detail : accountDetails) {
                                writer.println(detail);
                            }

                            // Add a blank line to separate accounts
                            writer.println();
                        } else {
                            writer.println(line);
                        }
                    } else {
                       // Skip the rest of the account details if the ID does not match
                    for (int i = 0; i < 12; i++) { // Assuming 13 lines per account
                        if (fileScanner.hasNextLine()) {
                            fileScanner.nextLine();
                        } else {
                            break; // Break if no more lines
                        }
                    }
                    }
                }

                if (!accountFound) {
                    System.out.println("No account found with the given ID.");
                }
            }

            if (file.delete()) {
                tempFile.renameTo(file);
            } else {
                System.out.println("Error updating account: Could not delete the original file.");
            }

        } catch (IOException e) {
            System.out.println("Error updating account: " + e.getMessage());
        } catch (NoSuchElementException e) {
            System.out.println("Error updating account: " + e.getMessage());
        }
    } else {
        System.out.println("Please Login! First.");
    }
}
 public void closeAccount() {
    if (isAdminLoginSuccessfully) {
        System.out.print("Enter account ID to close: ");
        Scanner scanner = new Scanner(System.in);

        int accountId;
        try {
            accountId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid account ID format. Please enter a valid account ID.");
            return;
        }

        try {
            File file = new File("User_Accounts.txt");
            File tempFile = new File("temp_User_Accounts.txt");

            try (Scanner fileScanner = new Scanner(file);
                 PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {

                boolean accountFound = false;
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();

                    // Skip empty lines
                    if (line.trim().isEmpty()) {
                        continue;
                    }

                    // Parse account ID from the first line of each account entry
                    int id;
                    try {
                        id = Integer.parseInt(line);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Invalid account ID format. Skipping line: " + line);
                        continue;
                    }
                    System.out.println(id);
                    // If the account ID matches, skip all lines until the next empty line
                    if (id == accountId) {
                        accountFound = true;
                        while (fileScanner.hasNextLine()) {
                            String nextLine = fileScanner.nextLine();
                            if (nextLine.trim().isEmpty()) {
                                break; // End of account entry
                            }
                        }
                        continue; // Skip writing this account's details to the temporary file
                    }

                    // Write the line to the temporary file if it's not part of the account being closed
                    writer.println(line);
                }

                if (accountFound && accountId != 0) {
                    System.out.println("Account closed successfully.");
                } else {
                    System.out.println("Account not found.");
                }
            }

            // Delete original file and rename temporary file to original file name
            if (file.exists()) {
                file.delete();
            }
            tempFile.renameTo(file);

        } catch (IOException e) {
            System.out.println("Error closing account: " + e.getMessage());
        }
    } else {
        System.out.println("Please Login! First.");
    }
}
    public void enquiryBalance() {
        System.out.println("Enquiring balance...");
        if (isAdminLoginSuccessfully) {
            System.out.print("Enter account ID to enquire balance: ");
            Scanner scanner = new Scanner(System.in);
            int accountId = scanner.nextInt();
            scanner.nextLine(); // Consume the leftover newline character

            try {
                File file = new File("User_Accounts.txt");
                Scanner fileScanner = new Scanner(file);
                boolean accountFound = false;

                Stack<String> accountDetailsStack = new Stack<>();

                while (fileScanner.hasNextLine()) {
                    int id = fileScanner.nextInt();
                    fileScanner.nextLine(); // Consume the leftover newline character after reading the ID

                    if (id == accountId) {
                        // Account found, push the balance to the stack
                        for (int i = 1; i <= 10; i++) { // Assuming 10 lines per account
                            if (fileScanner.hasNextLine()) {
                                String line = fileScanner.nextLine();
                                accountDetailsStack.push(line);
                            } else {
                                System.out.println("Invalid file format.");
                                return;
                            }
                        }
                        accountFound = true;
                        break; // Exit the loop once the account is found and processed
                    } else {
                        // Skip the rest of the account details if the ID does not match
                        for (int i = 0; i < 9; i++) { // Assuming 10 lines per account
                            if (fileScanner.hasNextLine()) {
                                fileScanner.nextLine();
                            } else {
                                break; // Break if no more lines
                            }
                        }
                    }
                }

                // Display only the balance from the stack
                if (accountFound) {
                    System.out.println("Balance: " + accountDetailsStack.pop());
                } else {
                    System.out.println("No account found with the given ID.");
                }

                fileScanner.close();
            } catch (FileNotFoundException e) {
                System.out.println("Error reading user data file: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        } else {
            System.out.println("Please Login! First.");
        }
    }

    public void searchUserByUsername(String username) {
    if (isAdminLoginSuccessfully) {
        try {
            File file = new File("User_Accounts.txt"); // Ensure this is the correct file name
            Scanner fileScanner = new Scanner(file);

            boolean userFound = false;

            while (fileScanner.hasNextLine()) {
                String id = fileScanner.nextLine();
                String name = fileScanner.nextLine();
                String password = fileScanner.nextLine();
                String dob = fileScanner.nextLine();

                String age = fileScanner.nextLine();

                String gender = fileScanner.nextLine();
                String occupation = fileScanner.nextLine();
                String address = fileScanner.nextLine();
                String city = fileScanner.nextLine();
                String phoneNumber = fileScanner.nextLine();
                
                String totalMoney = fileScanner.nextLine();
                String accountType = fileScanner.nextLine();

                if (name.equalsIgnoreCase(username)) {
                    userFound = true;
                    System.out.println("ID: " + id);
                    System.out.println("Name: " + name);
                } else {
                    // Skip the remaining lines for this user
                    for (int i = 0; i < 9; i++) { // Assuming each user's details are spread over 10 lines
                        if (fileScanner.hasNextLine()) {
                            fileScanner.nextLine();
                        } else {
                            System.out.println("Incomplete user details.");
                            break;
                        }
                    }
                }
            }

            if (!userFound) {
                System.out.println("User not found.");
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    } else {
        System.out.println("Please login first.");
        admin_login();
    }
}
public void viewTransactionHistory(String username) {
    if (isAdminLoginSuccessfully) {
        try {
            File file = new File("User_Accounts.txt"); // Ensure this is the correct file name
            Scanner fileScanner = new Scanner(file);

            boolean transactionHistoryFound = false;

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();

                // Assuming the transaction history starts with a line that contains the username
                if (line.equalsIgnoreCase(username + " Transaction History")) {
                    transactionHistoryFound = true;
                    System.out.println("Transaction History for " + username + ":");

                    // Print the transaction history
                    while (fileScanner.hasNextLine()) {
                        String transactionLine = fileScanner.nextLine().trim();
                        // Assuming the transaction history ends with a blank line or a specific end marker
                        if (transactionLine.isEmpty() || transactionLine.equals("End of Transaction History")) {
                            break;
                        } else {
                            System.out.println(transactionLine);
                        }
                    }
                    break;
                }
            }

            if (!transactionHistoryFound) {
                System.out.println("Transaction history not found for " + username + ".");
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    } else {
        System.out.println("Please login first.");
        admin_login();
    }
}
public void displayAllUserAccounts() {
    if (isAdminLoginSuccessfully) {
        try {
            File file = new File("User_Accounts.txt"); // Ensure this is the correct file name
            Scanner fileScanner = new Scanner(file);
            System.out.println("Account Details:");
            while (fileScanner.hasNextLine()) {
                
                String id = fileScanner.nextLine();
                String name = fileScanner.nextLine();
                String password = fileScanner.nextLine();
                String dob = fileScanner.nextLine();

                String age = fileScanner.nextLine();

                String gender = fileScanner.nextLine();
                String occupation = fileScanner.nextLine();
                String address = fileScanner.nextLine();
                String city = fileScanner.nextLine();
                String phoneNumber = fileScanner.nextLine();
                
                String totalMoney = fileScanner.nextLine();
                String accountType = fileScanner.nextLine();
                
                System.out.println("ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("Date of Birth: " + dob);
                System.out.println("Age: " + age);
                System.out.println("Gender: " + gender);
                System.out.println("Phone Number: " + phoneNumber);
                System.out.println("Occupation: " + occupation);
                System.out.println("Address: " + address);
                System.out.println("City: " + city);
                System.out.println("Total Money: " + totalMoney);
                System.out.println("Account Type: " + accountType);

                if (accountType.equals("Joint Account")) {
                    String secondUserName = fileScanner.nextLine();
                    System.out.println(secondUserName);
                }

                // Read and print additional information based on the account type
                if (accountType.equals("Saving Account")) {
                    System.out.println(fileScanner.nextLine());
                }
                if (accountType.equals("Current Account")) {
                    System.out.println(fileScanner.nextLine());
                }
                fileScanner.nextLine();
                System.out.println(); // Add an empty line for better readability
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    } else {
        System.out.println("Please login first.");
        admin_login();
    }
}

    public void changeAdminPassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter current password: ");
        String currentPassword = scanner.nextLine();

        if (currentPassword.equals(admin_pass)) { // Change this to your current password
            System.out.print("Enter new password: ");
            String newPassword = scanner.nextLine();
            System.out.println("Password changed successfully.");
            setPass(newPassword);
        } else {
            System.out.println("Incorrect current password.");
        }
    }
    public void setAccountLocked(int accountId, boolean locked) {
        Node accountNode = accountList.findAccountById(accountId);
        if (accountNode != null) {
        //    accountNode.getAccount().setaccountLocked(accountId, locked);
            System.out.println("Account " + (locked ? "locked" : "unlocked") + " successfully.");
        } else {
            System.out.println("Account not found.");
        }
    }

    public void addSystemLog(String log) {
        systemLogs.offer(log);
    }

}
