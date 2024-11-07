import java.util.*;

class NetBanking 
{
    private boolean valid = false;
    private BankAccount info = new BankAccount();
    private AuthenticatedActions authenticatedActions;
    ArrayList<BankAccount> accounts = new ArrayList<>();


    // Method to login to account
    public void loginAccount(long netBankingId, String netBankingPass, ArrayList<BankAccount> accounts2) 
    {
        for (BankAccount account : accounts2) 
        {
            if (account.getNetBankingId() == netBankingId && account.getNetBankingPass().equalsIgnoreCase(netBankingPass)) 
            {
                valid = true;
                info = account;
                accounts = accounts2;
                authenticatedActions = new AuthenticatedActions(); // Enable access to restricted methods
                System.out.println("\nLogin successful");
            } 
        }
        if(!valid)
            System.out.println("Login failed, Invalid Account no or password");
    }

    // Inner class to hold methods that are only accessible after login
    class AuthenticatedActions 
    {
        private AuthenticatedActions() {} // default constructor as private so that it can be instatiated by only his parent class

        // Method to display account info (restricted)
        public void displayAccountInfo() 
        {
            info.displayAccountDetails();
        }

        // deposit amount 
        public void depositAmount(long depAmount) 
        {
            if(depAmount < 0) // check if the deposit amount is negative or not
            {
                System.out.println("Deposit Amount should not be negative");
                return;
            }
            
            info.setAccBalance(depAmount + info.getBalance());  // Update the balance
            info.getAccTransactionHistory().add(new Transaction("Deposit", depAmount)); // add in transaction history
            System.out.println("Amount deposited successfully.");
            System.out.println("Account No: " + info.getAccNo());
            System.out.println("Name: " + info.getName());
            System.out.println("New Balance: " + info.getBalance());
            System.out.println();
        }

        // withdraw amount
        public void withdrawAmount(long withdrawAmount) 
        {
            if(withdrawAmount < 0) // check amount is negative or not
            {
                System.out.println("Withdraw Amount should not be negative");
                return;
            }
            if (info.getBalance() < withdrawAmount) // Check for sufficient balance
            {
                System.out.println("Insufficient Balance");
            } 
            else 
            {
                info.setAccBalance(info.getBalance()- withdrawAmount); // Update the balance
                info.getAccTransactionHistory().add(new Transaction("Withdraw", withdrawAmount)); // add in transaction history
                System.out.println("Amount withdrawn successfully.");
                System.out.println("Account No: " + info.getAccNo());
                System.out.println("Name: " + info.getName());
                System.out.println("Remaining Balance: " + info.getBalance());
            }
        }

        // transfer amount from one account to another
        public void transferAmount(long targetAccNo, long transferAmount) 
        {
            if(transferAmount < 0)
            {
                System.out.println("Transfer Amount should not be negative");
                return;
            }
            boolean targetFound = false; // Track if the target account is found
            BankAccount targetAccount = null;

            // Search for source and target accounts
            for (BankAccount account : accounts) 
            {
                if (account.getAccNo() == targetAccNo) 
                {
                    targetAccount = account;
                    targetFound = true;
                }
            }

            if (!targetFound) // check target found or not
            {
                System.out.println("Invalid target account no. Transfer failed.");
                return;
            }

            // Check if the source account has sufficient balance
            if (info.getBalance() < transferAmount) 
            {
                System.out.println("Insufficient balance in source account. Transfer failed.");
            } 
            else 
            {
                // Transfered from source account to target account
                info.setAccBalance(info.getBalance() - transferAmount);
                targetAccount.setAccBalance(info.getBalance() + transferAmount);
                info.getAccTransactionHistory().add(new Transaction("Withdraw", transferAmount)); // add in transaction history
                targetAccount.getAccTransactionHistory().add(new Transaction("Deposit", transferAmount));

                // Successful transfer
                System.out.println("Amount transferred successfully.");
                System.out.println("Source Account No: " + info.getAccNo());
                System.out.println("Source Account Remaining Balance: " + info.getBalance());
                System.out.println("Target Account No: " + targetAccount.getAccNo());
                System.out.println("Target Account New Balance: " + targetAccount.getBalance());
            }
        }

        public void getTransactionHistory()
        {
            info.getTransactionHistory(info.getAccNo(), accounts);
        }

        public void logOut()
        {
            valid = false;
            System.out.println("Log out successful");
        }
 
    }

    


    // Method to get AuthenticatedActions instance if login was successful
    public AuthenticatedActions getAuthenticatedActions() {
        if (valid) {
            return authenticatedActions;
        } else {
            System.out.println("Access denied. Please login first.");
            return null;
        }
    }
}