import java.util.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class User{
    private boolean isUserLoginSuccessfully;
    private Account_List list;
    private Node head;
    private int userId;
    private String username;
    private List<Transaction> transactionHistory = new ArrayList<>();
    private Queue<Application> applicationsQueue;
    
    

    public User(Account_List list){
        this.list=list;
        this.isUserLoginSuccessfully = false;
        this.head = list.getHead();
        this.applicationsQueue = new LinkedList<>();

        
    }
    public boolean user_login() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("----- User Login ---- "); 
        System.out.print("Username: ");
        this.username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        try {
            if (login(username, password)) {
                isUserLoginSuccessfully = true;
                System.out.println("Successfully logged in "+username);
                
                return true;
            } else {
                isUserLoginSuccessfully = false;
                System.out.println("Login Failed!");
                return false;
            }
        } catch (Exception e) {
            System.out.println("An error occurred???????????????????: " + e.getMessage());
            return false;
        }
    }

    private boolean login(String username, String password) throws Exception {
        File read = new File("User_Accounts.txt");
        Scanner scanner = new Scanner(read);
        
            while (scanner.hasNextLine()) {
                String idStr = scanner.nextLine();

                try {
                    int id = Integer.parseInt(idStr);
                    this.userId = Integer.parseInt(idStr);

                } catch (NumberFormatException e) {
                    continue;  // Skip to the next iteration
                    }
                String storedUsername = scanner.nextLine();
                String storedPassword = scanner.nextLine();

                if (storedUsername.equals(username) && storedPassword.equals(password)) {
                    return true;
                }
            }
        return false;
    }
    public void applyForLocker(Queue<Application> admin_applicationsQueue) {
        Application application = new Application(this.userId, "Safety Deposit Box/Locker");
        this.applicationsQueue.add(application);
        admin_applicationsQueue.add(application);
        System.out.println("Waiting for approval");
    }
    public void applyForCard(Queue<Application> admin_applicationsQueue) {
        Application application = new Application(this.userId, "Debit/Credit Card");
        this.applicationsQueue.add(application);
        admin_applicationsQueue.add(application);
        System.out.println("Waiting for approval");
        
    }
    public void applyForChequeBook(Queue<Application> admin_applicationsQueue) {
        Application application = new Application(this.userId, "Cheque Book");
        this.applicationsQueue.add(application);
        admin_applicationsQueue.add(application);
               System.out.println("Waiting for approval");

    }
    public void applyForLoan(Queue<Application> admin_applicationsQueue) {
        Application application = new Application(this.userId, "Loan");
        this.applicationsQueue.add(application);
        admin_applicationsQueue.add(application);
        System.out.println("Waiting for approval");

    }


    public void currency_exchange(User user) throws Exception{
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the amount to exchange: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        double user_amount = getTotalAmount(user);

        if (user_amount < amount) {
            System.out.println("Insufficient funds. Exiting currency exchange.");
        }

        System.out.println("Select Source Currency:");
        System.out.println("1. USD");
        System.out.println("2. EUR");
        System.out.println("3. GBP");
        System.out.println("4. JPY");
        System.out.println("5. AUD");
        System.out.println("6. CAD");
        System.out.println("7. INR");
        System.out.println("8. CNY");
        System.out.print("Enter your choice (1-8): ");
        int sourceCurrencyChoice = scanner.nextInt();
        scanner.nextLine();  

        
        while (sourceCurrencyChoice < 1 || sourceCurrencyChoice > 8) {
            System.out.println("Invalid choice. Exiting currency exchange.");
            sourceCurrencyChoice = scanner.nextInt();
        }

        System.out.println("Select Target Currency:");
        System.out.println("1. USD");
        System.out.println("2. EUR");
        System.out.println("3. GBP");
        System.out.println("4. JPY");
        System.out.println("5. AUD");
        System.out.println("6. CAD");
        System.out.println("7. INR");
        System.out.println("8. CNY");
        System.out.print("Enter your choice (1-8): ");
        int targetCurrencyChoice = scanner.nextInt();
        scanner.nextLine();

        while (targetCurrencyChoice < 1 || targetCurrencyChoice > 8) {
            System.out.println("Invalid choice. Exiting currency exchange.");
            targetCurrencyChoice = scanner.nextInt();
        }
        String[] currencies = {"USD", "EUR", "GBP", "JPY", "AUD", "CAD", "INR", "CNY"};

        String from_currency = currencies[sourceCurrencyChoice];
        String to_currency = currencies[targetCurrencyChoice];

        double rate = currency_rates(from_currency, to_currency);

        double result = rate * amount;
        System.out.println("Total amount that you got: "+result);
        System.out.println("Your amount has successfully been exchanged.");

        double newTotalAmount = result - user_amount;

        System.out.println("Your amount has successfully been exchanged.");

    }

    public double getTotalAmount(User user) throws FileNotFoundException {
    Scanner scanner = new Scanner(System.in);
    System.out.println(userId);
    File file = new File("User_Accounts.txt");
    Scanner read = new Scanner(file);

    while (read.hasNextLine()) {
        String currentUserIdStr = read.nextLine();

        try {
            int currentUserId = Integer.parseInt(currentUserIdStr.trim());

            if (currentUserId == this.userId) {
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
            continue;
        }
    }
    return -1;
}


    public double currency_rates(String from, String to){

    if (from.equals("USD") && to.equals("EUR")) {
        return 0.85; 
    } else if (from.equals("USD") && to.equals("GBP")) {
        return 0.73; 
    } else if (from.equals("USD") && to.equals("JPY")) {
        return 110.25; 
    } else if (from.equals("USD") && to.equals("AUD")) {
        return 1.37; 
    } else if (from.equals("USD") && to.equals("CAD")) {
        return 1.25; 
    } else if (from.equals("USD") && to.equals("INR")) {
        return 74.63; 
    } else if (from.equals("USD") && to.equals("CNY")) {
        return 6.36; 
    } else if (from.equals("EUR") && to.equals("USD")) {
        return 1.18; 
    } else if (from.equals("EUR") && to.equals("GBP")) {
        return 0.87; 
    } else if (from.equals("EUR") && to.equals("JPY")) {
        return 130.12;
    } else if (from.equals("EUR") && to.equals("AUD")) {
        return 1.57; 
    } else if (from.equals("EUR") && to.equals("CAD")) {
        return 1.43; 
    } else if (from.equals("EUR") && to.equals("INR")) {
        return 85.23; 
    } else if (from.equals("EUR") && to.equals("CNY")) {
        return 7.22; 
    } else if (from.equals("GBP") && to.equals("USD")) {
        return 1.37; 
    } else if (from.equals("GBP") && to.equals("EUR")) {
        return 1.15; 
    } else if (from.equals("GBP") && to.equals("JPY")) {
        return 150.62; 
    } else if (from.equals("GBP") && to.equals("AUD")) {
        return 1.81; 
    } else if (from.equals("GBP") && to.equals("CAD")) {
        return 1.65; // Example exchange rate: 1 GBP = 1.65 CAD
    } else if (from.equals("GBP") && to.equals("INR")) {
        return 98.16; // Example exchange rate: 1 GBP = 98.16 INR
    } else if (from.equals("GBP") && to.equals("CNY")) {
        return 8.32; 
    } else if (from.equals("JPY") && to.equals("USD")) {
        return 0.0091; 
    } else if (from.equals("JPY") && to.equals("EUR")) {
        return 0.0077; 
    } else if (from.equals("JPY") && to.equals("GBP")) {
        return 0.0066;
    } else if (from.equals("JPY") && to.equals("AUD")) {
        return 0.012;
    } else if (from.equals("JPY") && to.equals("CAD")) {
        return 0.011; 
    } else if (from.equals("JPY") && to.equals("INR")) {
        return 0.65;
    } else if (from.equals("JPY") && to.equals("CNY")) {
        return 0.055;
    } else if (from.equals("AUD") && to.equals("USD")) {
        return 0.73; 
    } else if (from.equals("AUD") && to.equals("EUR")) {
        return 0.64;
    } else if (from.equals("AUD") && to.equals("GBP")) {
        return 0.55;
    } else if (from.equals("AUD") && to.equals("JPY")) {
        return 82.83; 
    } else if (from.equals("AUD") && to.equals("CAD")) {
        return 0.91; 
    } else if (from.equals("AUD") && to.equals("INR")) {
        return 54.29; 
    } else if (from.equals("AUD") && to.equals("CNY")) {
        return 4.6; 
    } else if (from.equals("CAD") && to.equals("USD")) {
        return 0.8; 
    } else if (from.equals("CAD") && to.equals("EUR")) {
        return 0.7; 
    } else if (from.equals("CAD") && to.equals("GBP")) {
        return 0.6; 
    } else if (from.equals("CAD") && to.equals("JPY")) {
        return 90.23; 
    } else if (from.equals("CAD") && to.equals("AUD")) {
        return 1.1; 
    } else if (from.equals("CAD") && to.equals("INR")) {
        return 59.87; 
    } else if (from.equals("CAD") && to.equals("CNY")) {
        return 5.08; 
    } else if (from.equals("INR") && to.equals("USD")) {
        return 0.013; 
    } else if (from.equals("INR") && to.equals("EUR")) {
        return 0.012;
    } else if (from.equals("INR") && to.equals("GBP")) {
        return 0.01;
    } else if (from.equals("INR") && to.equals("JPY")) {
        return 1.54; 
    } else if (from.equals("INR") && to.equals("AUD")) {
        return 0.018; 
    } else if (from.equals("INR") && to.equals("CAD")) {
        return 0.017; 
    } else if (from.equals("INR") && to.equals("CNY")) {
        return 0.15; 
    } else if (from.equals("CNY") && to.equals("USD")) {
        return 0.16; 
    } else if (from.equals("CNY") && to.equals("EUR")) {
        return 0.14; 
    } else if (from.equals("CNY") && to.equals("GBP")) {
        return 0.12; 
    } else if (from.equals("CNY") && to.equals("JPY")) {
        return 18.18; 
    } else if (from.equals("CNY") && to.equals("AUD")) {
        return 0.22; 
    } else if (from.equals("CNY") && to.equals("CAD")) {
        return 0.2; 
    } else if (from.equals("CNY") && to.equals("INR")) {
        return 6.73;
    }else{
        return -1;
    }
}

    public void findAccountById() {
    System.out.println("Finding account by ID...");
    if (isUserLoginSuccessfully) {
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
                fileScanner.nextLine(); 
                if (id == accountId) {
                    
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
                    break; 
                } else {
                    
                    for (int i = 0; i < 8; i++) { 
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
}
    public String getUsername() {
        return this.username;
    }
    public void checkBalance() {
        Account account = list.findAccountByUsername(this.username);

        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.println("Current Balance for " + getUsername() + ": $" + account.getTotalMoney());
    }
    public Account getUserAccount() {
        try (Scanner scanner = new Scanner(new File("User_Accounts.txt"))) {
            while (scanner.hasNextLine()) {
                String storedUsername = scanner.nextLine();
                String storedPassword = scanner.nextLine();
                if (storedUsername.equals(username)) {
                    int id = Integer.parseInt(scanner.nextLine());
                    String name = scanner.nextLine();
                    String dob = scanner.nextLine();
                    int age = Integer.parseInt(scanner.nextLine());
                    char gender = scanner.nextLine().charAt(0);
                    String occupation = scanner.nextLine();
                    String address = scanner.nextLine();
                    String city = scanner.nextLine();
                    String contactNo = scanner.nextLine();
                    double totalMoney = Double.parseDouble(scanner.nextLine());
                    String accountType = scanner.nextLine();
                    boolean accountLocked = scanner.nextBoolean();


                    
                    Account account = new Account(id, name, dob, age, gender, occupation, address, city, storedPassword, contactNo, totalMoney, accountType);
                    
                    return account;
                } else {
                    for (int i = 0; i < 9; i++) {
                        scanner.nextLine();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found - " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("Username not found.");
        return null;
    }

//      public void sendMoneyToAnotherAccount() {
//     if (isUserLoginSuccessfully) {
//         Scanner scanner = new Scanner(System.in);
//         System.out.print("Enter recipient's account number: ");
//         String recipientAccountNumber = scanner.nextLine();

//         // Assuming the recipient's account number is valid and exists in the system
//         Account recipientAccount = list.findAccountByAccountNumber(recipientAccountNumber);

//         if (recipientAccount != null) {
//             System.out.print("Enter amount to send: ");
//             double amount = scanner.nextDouble();
//             scanner.nextLine(); // Consume newline character

//             Account senderAccount = list.findAccountByUserId(this.userId);

//             if (senderAccount != null) {
//                 if (senderAccount.getTotalMoney() >= amount) {
//                     // Deduct amount from sender's account
//                     senderAccount.setTotalMoney(senderAccount.getTotalMoney() - amount);
//                     // Add amount to recipient's account
//                     recipientAccount.setTotalMoney(recipientAccount.getTotalMoney() + amount);

//                     // Update accounts in the text file
//                     updateAccountInFile(senderAccount);
//                     updateAccountInFile(recipientAccount);

//                     System.out.println("Amount of $" + amount + " has been successfully sent to account number " + recipientAccountNumber);
//                 } else {
//                     System.out.println("Insufficient balance to send money.");
//                 }
//             } else {
//                 System.out.println("Sender account not found.");
//             }
//         } else {
//             System.out.println("Recipient account not found.");
//         }
//     } else {
//         System.out.println("Please Login! First.");
//     }
// }
    //  public void payBills() {
    //     if (isUserLoginSuccessfully) {
    //         System.out.print("Enter account ID to pay bills: ");
    //         Scanner scanner = new Scanner(System.in);
    //         int accountId = scanner.nextInt();
    //         System.out.print("Enter amount to pay: ");
    //         double amountToPay = scanner.nextDouble();
    //         scanner.nextLine();

    //         File billsFile = new File("Bills.txt");

    //         try {
    //             Scanner billsScanner = new Scanner(billsFile);
    //             Queue<String> billsQueue = new LinkedList<>();

    //             while (billsScanner.hasNextLine()) {
    //                 billsQueue.offer(billsScanner.nextLine());
    //             }
    //             billsScanner.close();

    //             File userAccountsFile = new File("User_Accounts.txt");
    //             Scanner userAccountsScanner = new Scanner(userAccountsFile);
    //             StringBuilder updatedUserAccountsData = new StringBuilder();

    //             while (userAccountsScanner.hasNextLine()) {
    //                 String line = userAccountsScanner.nextLine();
    //                 String[] array = line.split(": ");
    //                 int id = Integer.parseInt(array[1].trim()); 
    //                 if (id == accountId) {
    //                     double balance = Double.parseDouble(array[11].trim()); 
    //                     if (balance >= amountToPay && !billsQueue.isEmpty()) {
    //                         String billToPay = billsQueue.poll();
    //                         System.out.println("Paid bill: " + billToPay);

    //                         double newBalance = balance - amountToPay;
    //                         array[11] = "Total Money: " + newBalance; 
    //                         System.out.println("Remaining balance: " + newBalance);
    //                     } else {
    //                         System.out.println("Insufficient balance or no bills to pay.");
    //                     }
    //                 }

    //                 updatedUserAccountsData.append(String.join(": ", array)).append("\n");
    //             }
    //             userAccountsScanner.close();

    //             PrintWriter userAccountsWriter = new PrintWriter(userAccountsFile);
    //             userAccountsWriter.print(updatedUserAccountsData.toString());
    //             userAccountsWriter.close();

    //         } catch (FileNotFoundException e) {
    //             System.out.println("File not found: " + e.getMessage());
    //         } catch (NumberFormatException e) {
    //             System.out.println("Error parsing numbers: " + e.getMessage());
    //         }
    //     } else {
    //         System.out.println("Please Login! First.");
    //     }
    // }
 public void withdraw(double amount) {
    if (isUserLoginSuccessfully) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter account ID to withdraw: ");
            int accountId;
            try {
                accountId = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid account ID format. Please enter a valid numeric ID.");
                return;
            }

            File file = new File("User_Accounts.txt");
            File tempFile = new File("temp_User_Accounts.txt");

            try (Scanner fileScanner = new Scanner(file);
                 PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {

                boolean accountFound = false;

                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    int id = Integer.parseInt(line.trim());

                    if (id == accountId) {
                        accountFound = true;
                        writer.println(id);

                        // Initialize accountDetails array with appropriate length
                        String[] accountDetails = new String[13];
                        for (int i = 0; i < accountDetails.length; i++) {
                            if (fileScanner.hasNextLine()) {
                                accountDetails[i] = fileScanner.nextLine();
                            } else {
                                throw new NoSuchElementException("No line found");
                            }
                        }

                        // Update balance
                        double currentBalance = Double.parseDouble(accountDetails[9].split(": ")[1]);
                        if (currentBalance >= amount) {
                            double newBalance = currentBalance - amount;
                            accountDetails[9] = "Total Money: " + newBalance;
                            System.out.println("Withdrawal of $" + amount + " successful. New balance: $" + newBalance);
                        } else {
                            System.out.println("Insufficient balance to withdraw $" + amount);
                        }

                        // Write updated details to file
                        for (String detail : accountDetails) {
                            writer.println(detail);
                        }

                        writer.println(); // Add a blank line to separate accounts
                    } else {
                        writer.println(line);
                    }
                }

                if (!accountFound) {
                    System.out.println("No account found with the given ID.");
                }
            } catch (IOException e) {
                System.out.println("Error updating account: " + e.getMessage());
            } catch (NoSuchElementException e) {
                System.out.println("Error updating account: " + e.getMessage());
            }
        }
    } else {
        System.out.println("Please Login! First.");
    }
}

   public void deposit(double amount) {
    if (isUserLoginSuccessfully) {
        try (Scanner scanner = new Scanner(System.in)) {
            int accountId;

            // Prompt the user to enter a valid numeric ID
            while (true) {
                System.out.print("Enter account ID to deposit: ");
                try {
                    accountId = Integer.parseInt(scanner.nextLine());
                    break; // Exit loop if input is a valid integer
                } catch (NumberFormatException e) {
                    System.out.println("Invalid account ID format. Please enter a valid numeric ID.");
                }
            }

            File file = new File("User_Accounts.txt");
            File tempFile = new File("temp_User_Accounts.txt");

            try (Scanner fileScanner = new Scanner(file);
                 PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {

                boolean accountFound = false;

                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    int id = Integer.parseInt(line.trim());

                    if (id == accountId) {
                        accountFound = true;
                        writer.println(id);

                        // Initialize accountDetails array with appropriate length
                        String[] accountDetails = new String[13];
                        for (int i = 0; i < accountDetails.length; i++) {
                            if (fileScanner.hasNextLine()) {
                                accountDetails[i] = fileScanner.nextLine();
                            } else {
                                throw new NoSuchElementException("No line found");
                            }
                        }

                        // Update balance
                        double currentBalance = Double.parseDouble(accountDetails[9].split(": ")[1]);
                        double newBalance = currentBalance + amount;
                        accountDetails[9] = "Total Money: " + newBalance;

                        // Write updated details to file
                        for (String detail : accountDetails) {
                            writer.println(detail);
                        }

                        writer.println(); // Add a blank line to separate accounts
                    } else {
                        writer.println(line);
                    }
                }

                if (!accountFound) {
                    System.out.println("No account found with the given ID.");
                } else {
                    System.out.println("Deposit successful!");
                }
            } catch (IOException e) {
                System.out.println("Error updating account: " + e.getMessage());
            } catch (NoSuchElementException e) {
                System.out.println("Error updating account: " + e.getMessage());
            }
        }
    } else {
        System.out.println("Please Login! First.");
    }
}
}