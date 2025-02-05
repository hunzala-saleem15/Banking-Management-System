import java.util.*;
import java.io.*;
public class Menu{
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in); 
        Account_List accountList = new Account_List();
        Queue<Application> applicationQueue = new LinkedList<>();
        int choice;
        boolean flag=true;
        Queue<String> systemlogs = new LinkedList<>();

        while(flag){
            System.out.println("----------- Welcome To Our Bank Management System -----------");
            System.out.println("0. Exit");
            System.out.println("1. Admin");
            System.out.println("2. User");
            System.out.print("Enter Choice: ");
            
            choice = scanner.nextInt();

            if(choice == 0){
                flag=false;
            }else if(choice == 1){
                Admin admin = new Admin(accountList);
                choice =  1;

                if (admin.admin_login() == false) {
                    choice =  0;
                }
                while (choice !=  0) {
                    System.out.println("--------------- Admin Menu --------------------");
                    System.out.println("0. Go Back");
                    System.out.println("1. Create New User Account");
                    System.out.println("2. Close User Account");
                    //System.out.println("3. Freeze/Unfreeze User Account");
                    System.out.println("4. Search for an user account.");
                    System.out.println("5. Update User Account Details");
                    System.out.println("6. Display All User Accounts");
                    System.out.println("7. Balance Enquiry");                           //check balance in user account
                    //System.out.println("8. View Transaction History"); 
                    //System.out.println("9. Generate Account Statement");
                    System.out.println("10. Manage Application/Requests.");
                    System.out.println("11. View System Logs");
                    System.out.println("12. Update Admin Password");
                    //System.out.println("13. User Account Lock.");
                    System.out.println("-----------------------------------------------");
                    System.out.print("Enter Your Choice: ");
                                
                    choice = scanner.nextInt();

                    if(choice == 0){
                        break;
                    }else if(choice == 1){
                        System.out.println("Creating the account... Loading");                                                  
                        admin.createNewAccount();
                    }else if(choice == 2){
                        System.out.println("Closing the account... Loading");
                        admin.closeAccount();
                    }else if(choice == 3){
                        System.out.println("Freezing The Account.. Loading");
                        //method call
                    }else if(choice == 4){
                        System.out.println("Searching the Account....");
                        admin.findAccountById();
                    }else if(choice == 5){
                        System.out.println("Updating The Account Details.... Loading");
                        admin.updateExistingAccount();

                    }else if(choice == 6){
                        System.out.println("Displaying All User Accounts.... Loading");
                        admin.displayAllUserAccounts();
                    }else if(choice == 7){
                        System.out.println("Balance Enquiry.... Loading");
                        admin.enquiryBalance();

                    }else if(choice == 8){
                        System.out.println("View Transaction History.... Loading");
                        scanner.nextLine(); // Consume the newline character left in the buffer
                        System.out.print("Enter the username to view transaction history: ");
                        String usernameToViewHistory = scanner.nextLine();
                        admin.viewTransactionHistory(usernameToViewHistory);
                    }else if(choice == 9){
                        System.out.println("Generate Account Statement.... Loading");
                        admin.generate_account_statement();

                    }else if(choice == 10){
                        System.out.println("Manage Application/Requests..... Loading");
                        admin.processApplication(applicationQueue);
                    }else if(choice == 11){
                        System.out.println("Viewing System Logs:");
                        if (systemlogs.isEmpty()) {
                            System.out.println("No system logs available.");
                        } else {
                            for (String log : systemlogs) {
                                System.out.println(log);
                            }
                        }
                    }else if(choice == 12){
                        System.out.println("Update Admin Password.......Loading");
                        admin.changeAdminPassword();
                    }else if(choice == 13){
                        System.out.println("User Account Lock........Loading");
                        System.out.println("Enter id: ");
                        int account_id=scanner.nextInt();
                        admin.setAccountLocked(account_id, false);
                    }else{
                        System.out.println("Invalid Choice.");
                    }
                }
            }else if(choice == 2){
                User user = new User(accountList);
                choice =  1;
                
                if(user.user_login()==false){
                    choice =  0;
                }
                while (choice !=  0) {
                    systemlogs.add(user.getUsername());
                    System.out.println(user.getUsername());
                    System.out.println("--------- User Menu ---------");
                    System.out.println("0. Go Back");
                    System.out.println("1. Withdraw Money");
                    System.out.println("2. Deposit Money");
                    System.out.println("3. Check Balance");
                    System.out.println("4. Send Money to Another Account");
                    System.out.println("5. Change Password");
                    System.out.println("6. My Account Details");
                    System.out.println("7. View Transaction History");
                    System.out.println("8. Currency Exchange");            
                    System.out.println("9. Application Request");
                    System.out.println("10. Logout");
                    System.out.println();
                    System.out.println("-----------------------------");
                    System.out.print("Enter Your Choice: ");

                    choice = scanner.nextInt();

                    if(choice == 0){
                        break;
                    }else if(choice == 1){
                        System.out.println("Withdrawing the money... Loading");
                        System.out.print("Enter the amount to withdraw: ");
                        double amount = scanner.nextDouble();
                        user.withdraw(amount);
                       
                    }else if(choice == 2){
                        System.out.println("Deposit.......Loading");
                        System.out.print("Enter the amount to deposit: ");
                        double amount = scanner.nextDouble();
                        user.deposit(amount);
                        
                    }else if(choice == 3){
                        System.out.println("Check Balance......Loading");
                        user.checkBalance();
                    }else if(choice == 4){
                        System.out.println("Send Money to Another Account......Loading");
                        //method call
                    }else if(choice == 5){
                        System.out.println("Change Password......Loading");
                        //method call
                    }else if(choice == 6){
                        System.out.println("My Account Details... Loading");
                        user.findAccountById();
                    }else if(choice == 7){
                        System.out.println("View Transaction History... Loading");
                        //user.viewTransactionHistory();
                    }else if(choice == 8){
                        System.out.println("Currency Exchange... Loading");
                        user.currency_exchange(user);
                    }else if(choice == 9){
                        System.out.println("Application:");
                        System.out.println("0. Go Back");
                        System.out.println("1. Apply for Safety Deposit Box/Locker");
                        System.out.println("2. Debit/Credit Card");
                        System.out.println("3. Cheque Book request");
                        System.out.println("4. Apply For Loan");

                        choice = scanner.nextInt();
                        if(choice == 0){
                            break;
                        }else if(choice == 1){
                            System.out.println("Locker... Loading");
                            user.applyForLocker(applicationQueue);
                        }else if(choice == 2){
                            System.out.println("Debit/Credit Card.. Loading");
                            user.applyForCard(applicationQueue);
                        }else if(choice == 3){
                            System.out.println("Cheque Book.... Loading");
                            user.applyForChequeBook(applicationQueue);
                        }else if(choice == 4){
                            System.out.println("5. Apply For Loan....Loading");
                            user.applyForLoan(applicationQueue);
                        }else{
                            System.out.println("\n!Invalid Choice, try again");
                        }
                    }else if(choice == 10){
                        System.out.println("Log Out... Loading");
                        break;
                    }else{
                        System.out.println("Invalid Choice");
                    }
                }
            }else{
                System.out.println("Invalid Choice! Try again");
            }
        }
        System.out.println("Goodbye...");
    }
    public void viewSystemLogs() {
        
    }
}
