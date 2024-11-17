# Core Java Banking Application

## Overview
The **Core Java Banking Application** is a simple, console-based banking system developed using **core Java**. It simulates basic banking operations such as account creation, login, transaction management, and net banking services. This project is designed to manage user accounts, transactions, and balances in a secure and efficient manner.

## Features
1. **Account Creation:** Allows users to create bank accounts by providing personal information like phone number, email, Aadhaar, and balance.
2. **Login System:** Users can log in to their accounts using their credentials (e.g., user ID and password).
3. **Deposit & Withdraw Funds:** Enables deposit and withdrawal of funds from user accounts.
4. **Fund Transfer:** Allows users to transfer funds between accounts.
5. **Transaction History:** Keeps track of all transactions made by the user, including deposits, withdrawals, and transfers.
6. **Net Banking:** A simulated net banking interface that offers features like displaying account info, making deposits and withdrawals, and transferring funds.
7. **Secure Data Handling:** The application securely stores and manages user data using custom logic for validation.

## Project Structure

### 1. **BankAccount.java**
- Contains the **BankAccount** class, which holds the details of each user account, including balance, transactions, and account-related operations.
- **Methods:**
  - `createAccount()`: Creates a new user account.
  - `depositAmount()`: Deposits money into the account.
  - `withdrawAmount()`: Withdraws money from the account.
  - `transferAmount()`: Transfers money to another account.
  - `getAccountInfo()`: Displays account details.

### 2. **NetBanking.java**
- Handles **Net Banking** functionality such as login and transaction management.
- **Methods:**
  - `loginAccount()`: Authenticates a user using net banking credentials.
  - `displayAccountInfo()`: Displays account information via net banking.
  - `depositAmount()`, `withdrawAmount()`, `transferAmount()`: Perform respective actions for transactions.

### 3. **Transaction.java**
- Defines the **Transaction** class, which stores details about individual transactions (e.g., type, amount, date).
- **Methods:**
  - Constructor to initialize transaction details (type, amount, date).
  - `toString()`: Returns a string representation of the transaction.

### 4. **UserView.java**
- Manages the user interface and interactions for account login and transaction processing.
- **Methods:**
  - `startApplication()`: Starts the application and manages the menu system.
  - `logIn()`: Handles user login.
  - `displayMenu()`: Displays the menu for different user actions (deposit, withdraw, transfer).

### 5. **UniqueInfo.java**
- Contains classes and methods to store unique information for each user (e.g., phone number, Aadhaar number).
- **Methods:**
  - `checkPhone()`: Validates the phone number format.
  - `returnAadhaar()`: Returns a valid Aadhaar number for the user.
  - `returnPhone()`: Returns a valid phone number for the user.
  - `returnEmailId()`: Returns a valid email ID for the user.
  - `returnBalance()`: Returns the balance entered by the user.

## How It Works
1. **Account Creation:**
   - When a new user is created, they provide essential details such as name, phone number, email, Aadhaar, and initial balance.
   - These details are stored in the **BankAccount** class.
   
2. **Login System:**
   - The system prompts the user for their credentials (phone number or email) to log in.
   - Once logged in, users can access features like depositing funds, withdrawing funds, or viewing transaction history.

3. **Transaction Management:**
   - Users can deposit or withdraw funds from their accounts.
   - Each transaction is logged as an instance of the **Transaction** class, which records the type of transaction, amount, and date.

4. **Net Banking Services:**
   - Users can log in to their net banking portal, view account information, and perform transactions like deposit, withdrawal, and transfers.

## Dependencies
- **JDK**: Java Development Kit (Version 8 or later).
- **Database (Optional)**: If you want to extend the functionality to store user data in a database, JDBC can be used.

## Usage Instructions

### 1. Clone the Repository
To clone the repository, run the following command:
```bash
git clone https://github.com/codeByRahulShelake/core_java_Banking_Application.git

2. Run the Application
To run the banking application, navigate to the project directory and execute the BankCloneApp class using your Java IDE or the command line:

bash
Copy code
java BankCloneApp
3. Interact with the Application
Follow the prompts on the console to create an account, log in, and manage your account and transactions.

Contributions
Feel free to fork this repository, make improvements, and create pull requests. Contributions are welcome!

License
This project is open source and licensed under the MIT License
