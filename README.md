# Banking-Management-System

## Overview
The **Banking Management System** is a Java-based application designed to manage banking operations, including account creation, deposit and withdrawal of funds, balance inquiry, transaction history, and administrative control. This system is built using **Object-Oriented Programming (OOP)** principles and utilizes **file handling** for data storage.

## Features
### User Features:
- User authentication (login/logout)
- Account creation (Saving, Current, Joint accounts)
- Deposit and withdrawal operations
- Balance inquiry
- Transaction history
- Fund transfer to another account
- Currency exchange
- Loan and credit card application requests

### Admin Features:
- Admin login
- Create and close user accounts
- View and update user account details
- Search user accounts
- Manage transactions and applications
- View system logs
- Generate account statements
- Lock/unlock user accounts

## Technologies Used
- **Java** (Core Programming)
- **OOP Concepts** (Encapsulation, Inheritance, Polymorphism, Abstraction)
- **File Handling** (for data persistence)
- **Data Structures** (Queues, Stacks, Linked Lists)

## Project Structure
```
- src/
  - Account.java
  - Admin.java
  - User.java
  - Menu.java
  - Joint_Account.java
  - Saving_Account.java
  - Current_Account.java
  - Account_List.java
  - Transaction.java
  - Application.java
  - Node.java
```

## How to Run the Project
1. Install **Java Development Kit (JDK)** (version 8 or later)
2. Compile the Java files using the command:
   ```
   javac *.java
   ```
3. Run the main class:
   ```
   java Menu
   ```

## File Handling
All user and transaction data are stored in text files:
- **User_Accounts.txt** → Stores user account details
- **Transactions.txt** → Stores transaction records
- **System_Logs.txt** → Stores admin and system logs

## Future Enhancements
- Implement **Database (MySQL/PostgreSQL)** for better data management
- Introduce a **GUI-based interface** for user-friendly interaction
- Enable **multi-user online banking** with real-time updates

## Contributors
- **[Hunzala Saleem]** - Developer



