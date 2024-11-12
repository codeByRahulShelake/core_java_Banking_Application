import java.util.*;

class BankAccount // class to maintain actual bank system
{
    Scanner sc = new Scanner(System.in);
    private long aadharNo;
    private String name;
    private String dob;
    private String address;
    private long phNo;
    private String emailId;
    private long accNo;
    private long accBalance;
    private int atmPass;
    private long netBankingId;
    private String netBankingPass;
    private LinkedList<Transaction> accTransactionHistory = new LinkedList<>();  // linked list to store transation history one after another 
    Random random = new Random(); // for random no.

    // method to add new account
    public void addNewAccount(long aadharNo, String name, String dob, String address, long phNo, String emailId, long accBalance)
    {
        this.aadharNo = aadharNo;
        UniqueInfo.aadharSet.add(aadharNo); // store aadhaar no in set for uniqueness
        this.name = name;
        this.dob = dob;
        this.address = address;
        this.phNo = phNo;
        this.emailId = emailId;
        this.accBalance = accBalance;

        
        if(UniqueInfo.accNoSet.isEmpty()) // if there is no account present create a first account no
        {
            long newAccNo = 1245400001;
            UniqueInfo.accNoSet.add(newAccNo);
            this.accNo = newAccNo;
        }
        else
        {
            long lastAccNo = 0;
            for (long element : UniqueInfo.accNoSet) 
            {
                lastAccNo = element; 
            }
            UniqueInfo.accNoSet.add(lastAccNo + 1);
            this.accNo = lastAccNo + 1;
        }
        this.atmPass = 1000 + random.nextInt(9000);
        System.out.println("Your Account No : " +this.accNo);
        System.out.println("Your ATM Password : " +this.atmPass);
    }

    // display all account details
    public void displayAllAccountDetails(ArrayList<BankAccount> accounts) 
    {
        if(accounts.isEmpty())
        {
            System.out.println("No accounts found.");
            return;
        }
        int count = 0;
    
        for (int i = 0; i < accounts.size(); i++) {
            BankAccount account = accounts.get(i);
            count++;
            System.out.println("Account No : " + account.accNo 
                + " | Name : " + account.name 
                + " | Balance : " + account.accBalance 
                + " | Aadhar No : " + account.aadharNo 
                + " | Date of Birth : " + account.dob 
                + " | Address : " + account.address 
                + " | Phone No : " + account.phNo 
                + " | Email ID : " + account.emailId
                + " | ATM Password : " + account.atmPass
                + " | Net Banking ID : " + account.netBankingId
                + " | Net Banking Password : " + account.netBankingPass);
            System.out.println();
    
            if (count >= 10 && i < accounts.size() - 1) 
            { // Check if there are more accounts to display
                System.out.print("Enter yes for next accounts, or any other key to exit: ");
                String userInput = sc.nextLine();
                if (!userInput.equalsIgnoreCase("yes")) 
                    break; // Exit loop if user does not enter y
                
                count = 0; // Reset count for the next batch of accounts
            }
        }
    }
    

    // delete account by checking account no and aadhaar card no if present
    public void deleteAccount(long delAccNo, long delAadhaarNo, ArrayList<BankAccount> accounts) {
        boolean found = false;
        Iterator<BankAccount> iterator = accounts.iterator();
        
        while (iterator.hasNext()) 
        {
            BankAccount account = iterator.next();
            if (account.accNo == delAccNo && account.aadharNo == delAadhaarNo) 
            {
                iterator.remove(); // Remove the account from the ArrayList
                UniqueInfo.aadharSet.remove(account.aadharNo); // Also remove from unique sets
                UniqueInfo.accNoSet.remove(account.accNo);
                UniqueInfo.netBankingIdSet.remove(account.netBankingId);
                found = true;
                System.out.println("Account deleted successfully for Account No: " + delAccNo);
                break;
            }
        }     
        if (!found)
            System.out.println("No account found with Account No: " + delAccNo);
    }
   
    // serach account by account no
    public void searchAccNo(long searchAccNo, ArrayList<BankAccount> accounts) 
    {
        boolean found = false; // Flag to track that account is found or not.
        
        for (BankAccount account : accounts) 
        {
            if (account.accNo == searchAccNo) // Check if the account number matches
            {
                account.displayAccountDetails();
                found = true;  // account found
            }
        }

        if (!found) // if not found
            System.out.println("Invalid Account No.");
    }

    //search account by aadhaar no
    public void searchByAadhar(long searchAadhar, ArrayList<BankAccount> accounts) 
    {
        boolean found = false;
        for (BankAccount account : accounts) 
        {
            if (account.aadharNo == searchAadhar) 
            {
                account.displayAccountDetails(); // display info if account found
                found = true;
            }
        }
        if (!found) 
            System.out.println("No account found with Aadhar No: " + searchAadhar);
    }

    // search by name
    public void searchByName(String searchName, ArrayList<BankAccount> accounts) 
    {
        boolean found = false;
        for (BankAccount account : accounts) 
        {
            if (account.name.equalsIgnoreCase(searchName)) 
            {
                account.displayAccountDetails(); // display info if account found
                found = true;
            }
        }
        if (!found) 
            System.out.println("No account found with Name: " + searchName);
    }

    // search by phone no
    public void searchByPhone(long searchPhone, ArrayList<BankAccount> accounts) 
    {
        boolean found = false;
        for (BankAccount account : accounts) 
        {
            if (account.phNo == searchPhone) 
            {
                account.displayAccountDetails(); // display info if account found
                found = true;
            }
        }
        if (!found) 
            System.out.println("No account found with Phone No: " + searchPhone);
    }

    // display details of particular account
    public void displayAccountDetails() 
    {
        System.out.println("Account No : " + this.accNo 
            + " | Name : " + this.name 
            + " | Balance : " + this.accBalance 
            + " | Aadhar No : " + this.aadharNo 
            + " | Date of Birth : " + this.dob 
            + " | Address : " + this.address 
            + " | Phone No : " + this.phNo 
            + " | Email ID : " + this.emailId
            + " | ATM Password : " + this.atmPass
            + " | Net Banking ID : " + this.netBankingId
            + " | Net Banking Password : " + this.netBankingPass);
        System.out.println();
    }

    // deposit amount 
    public void depositAmount(long depNo, long depAmount, ArrayList<BankAccount> accounts) 
    {
        if(depAmount < 0) // check if the deposit amount is negative or not
        {
            System.out.println("Deposit Amount should not be negative");
            return;
        }
        boolean found = false; // Flag to track if the account is found
        for (BankAccount obj : accounts) 
        {
            if (obj.accNo == depNo) // Check if the account number matches
            {
                found = true; // Account found
                obj.accBalance += depAmount; // Update the balance
                obj.accTransactionHistory.add(new Transaction("Deposit", depAmount)); // add in transaction history
                System.out.println("Amount deposited successfully.");
                System.out.println("Account No: " + obj.accNo);
                System.out.println("Name: " + obj.name);
                System.out.println("New Balance: " + obj.accBalance);
                System.out.println();
            }
        }

        if (!found) // If account was not found
            System.out.println("Invalid account no.");
    }

    // withdraw amount
    public void withdrawAmount(long accNo, long withdrawAmount, ArrayList<BankAccount> accounts) 
    {
        if(withdrawAmount < 0) // check amount is negative or not
        {
            System.out.println("Withdraw Amount should not be negative");
            return;
        }
        boolean found = false; // Flag to track if the account is found
        for (BankAccount obj : accounts) 
        {
            if (obj.accNo == accNo) // Check if the account number matches
            {
                found = true; // Account found
                if (obj.accBalance < withdrawAmount) // Check for sufficient balance
                {
                    System.out.println("Insufficient Balance");
                } 
                else 
                {
                    obj.accBalance -= withdrawAmount; // Update the balance
                    obj.accTransactionHistory.add(new Transaction("Withdraw", withdrawAmount)); // add in transaction history
                    System.out.println("Amount withdrawn successfully.");
                    System.out.println("Account No: " + obj.accNo);
                    System.out.println("Name: " + obj.name);
                    System.out.println("Remaining Balance: " + obj.accBalance);
                }
            }
        }

        if (!found) // If account was not found
            System.out.println("Invalid account no.");
    }

    // transfer amount from one account to another
    public void transferAmount(long sourceAccNo, long targetAccNo, long transferAmount, ArrayList<BankAccount> accounts) 
    {
        if(transferAmount < 0)
        {
            System.out.println("Transfer Amount should not be negative");
            return;
        }
        boolean sourceFound = false; // Track if the source account is found
        boolean targetFound = false; // Track if the target account is found
        BankAccount sourceAccount = null;
        BankAccount targetAccount = null;

        // Search for source and target accounts
        for (BankAccount obj : accounts) 
        {
            if (obj.accNo == sourceAccNo) 
            {
                sourceAccount = obj;
                sourceFound = true;
            } 
            else if (obj.accNo == targetAccNo) 
            {
                targetAccount = obj;
                targetFound = true;
            }
        }

        if (!sourceFound) // check source found or not
        {
            System.out.println("Invalid source account no.");
            return;
        }

        if (!targetFound) // check target found or not
        {
            System.out.println("Invalid target account no. Transfer failed.");
            return;
        }

        // Check if the source account has sufficient balance
        if (sourceAccount.accBalance < transferAmount) 
        {
            System.out.println("Insufficient balance in source account. Transfer failed.");
        } 
        else 
        {
            // Transfered from source account to target account
            sourceAccount.accBalance -= transferAmount;
            targetAccount.accBalance += transferAmount;
            sourceAccount.accTransactionHistory.add(new Transaction("Withdraw", transferAmount)); // add in transaction history
            targetAccount.accTransactionHistory.add(new Transaction("Deposit", transferAmount));

            // Successful transfer
            System.out.println("Amount transferred successfully.");
            System.out.println("Source Account No: " + sourceAccount.accNo);
            System.out.println("Source Account Remaining Balance: " + sourceAccount.accBalance);
        }
    }

    // display transaction history
    public void getTransactionHistory(long accNo, ArrayList<BankAccount> accounts)
    {
        int count = 0;
        boolean found = false; // Flag to track if the account is found
        for (BankAccount account : accounts) 
        {
            if (account.accNo == accNo) // Check if the account number matches
            {
                found = true; // Account found
                if(account.accTransactionHistory.isEmpty())
                {
                    System.out.println("No transaction history");
                    return;
                }
                for (Transaction transaction : account.accTransactionHistory) 
                {
                    count++;
                    System.out.println(transaction); // Print each transaction

                    if(count >=10)
                    {
                        System.out.print("Enter yes for next accounts, or any other key to exit: ");
                        String userInput = sc.nextLine();
                        if (!userInput.equalsIgnoreCase("yes")) 
                        {
                            break; // Exit loop if user does not enter y
                        }
                        count = 0; // Reset count for the next batch of transaction    
                    }
                }
            }
        }
        if (!found) // If account was not found
        {
            System.out.println("Invalid account no.");
            return;
        }
        System.out.println("Wants to sort history if yes enter 'yes' : ");
        String sortChoice = sc.nextLine();
        if(sortChoice.equalsIgnoreCase("yes"))
        {
            boolean sortFlag = true;
            String sortType = null;
            do 
            {
                try 
                {
                    System.out.println("Enter how you want sort \n1 : Deposit | 2  : Withdraw | 3 : Exit");
                    int choice = sc.nextInt();
                    sc.nextLine();
                    switch (choice) 
                    {
                        case 1:
                            sortType = "Deposit";
                            break;
                        case 2:
                            sortType = "Withdraw";
                            break;
                        case 3:
                            sortFlag = false;
                            break;
                        default:
                            System.out.println("Enter valid choice");
                    }
                    getSortedTransactionHistory(accNo, sortType, accounts);
                }
                catch (InputMismatchException e) 
                {
                    System.out.println("Enter valid inputs (i.e in digit.)");
                }
            } while (sortFlag);
        }
    }

    private void getSortedTransactionHistory(long accNo, String type, ArrayList<BankAccount> accounts) {
        boolean accountFound = false;
        boolean transactionFound = false;
    
        for (BankAccount account : accounts) 
        {
            if (account.accNo == accNo) 
            {
                accountFound = true;
                int count = 0; // Reset count for each account
                for (Transaction transaction : account.accTransactionHistory) {
                    if (transaction.getType().equalsIgnoreCase(type)) 
                    {
                        transactionFound = true;
                        System.out.println(transaction);
                        count++;
                        
                        if (count >= 10) 
                        { // Prompt every 10 transactions
                            System.out.print("Enter 'y' for more transactions, or any other key to exit: ");
                            String userInput = sc.nextLine();
                            if (!userInput.equalsIgnoreCase("y")) 
                                return; // Exit method if user does not want more transactions
                            count = 0; // Reset count for the next batch
                        }
                    }
                }
                break; // Exit outer loop once the account is found and processed
            }
        }
    
        if (!accountFound) 
            System.out.println("Invalid account number.");
        else if (!transactionFound) 
            System.out.println("No transactions of type '" + type + "' found.");
    }

    public void applyForNetBanking(long accNo, long aadharNo ,ArrayList<BankAccount> accounts)
    {
        boolean found = false;
        for (BankAccount account : accounts) 
        {
            if(account.accNo == accNo && account.aadharNo == aadharNo)
            {
                found = true;
                if(account.netBankingId != 0)
                {
                    System.out.println("You already have Id and Password");
                    return;
                }
                boolean netFlag = true;
                while(netFlag)
                {
                    account.netBankingId = 100000 + random.nextLong(900000);
                    if(!UniqueInfo.netBankingIdSet.contains(account.netBankingId))
                        netFlag = false;
                    
                }
                account.netBankingPass = account.name.substring(0, account.name.length()/2)+1000 + random.nextInt(9000);
                System.out.println("Net Banking Id : "+account.getNetBankingId() + " | Password : "+account.getNetBankingPass());
            }
        }
        if(!found)
            System.out.println("Account not found.");     
    }
    
    public void updateName(long accNo, long aadharNo, String newName, ArrayList<BankAccount> accounts) 
    {
        boolean found = false;
        for (BankAccount account : accounts) 
        {
            if(account.accNo == accNo && account.aadharNo == aadharNo)
            {
                found = true;
                account.name = newName;
                System.out.println("Name updated : "+newName);
            }
        }
        if(!found)
            System.out.println("Account not found");
    }

    public void updatePhoneNo(long accNo, long aadharNo, long newPhNo, ArrayList<BankAccount> accounts) 
    {
        boolean found = false;
        for (BankAccount account : accounts) 
        {
            if(account.accNo == accNo && account.aadharNo == aadharNo)
            {
                found = true;
                account.phNo = newPhNo;
                System.out.println("Phone No updated : "+newPhNo);
            }
        }
        if(!found)
            System.out.println("Account not found");
    }

    public void updateAadhaarNo(long accNo, long phNo, long newAadhaarNo, ArrayList<BankAccount> accounts) 
    {
        boolean found = false;
        for (BankAccount account : accounts) 
        {
            if(account.accNo == accNo && account.phNo == phNo)
            {
                found = true;
                account.aadharNo = newAadhaarNo;
                System.out.println("Aadhaar No updated : "+newAadhaarNo);
            }
        }
        if(!found)
            System.out.println("Account not found");
    }

    public void updateEmailId(long accNo, long aadharNo, String newEmailId, ArrayList<BankAccount> accounts) 
    {
        boolean found = false;
        for (BankAccount account : accounts) 
        {
            if(account.accNo == accNo && account.aadharNo == aadharNo)
            {
                found = true;
                account.emailId = newEmailId;
                System.out.println("Email Id updated : "+newEmailId);
            }
        }
        if(!found)
            System.out.println("Account not found");
    }

    public void updateDOB(long accNo, long aadharNo, String newDOB, ArrayList<BankAccount> accounts) 
    {
        boolean found = false;
        for (BankAccount account : accounts) 
        {
            if(account.accNo == accNo && account.aadharNo == aadharNo)
            {
                found = true;
                account.dob = newDOB;
                System.out.println("Date of Birth updated : "+newDOB);
            }
        }
        if(!found)
            System.out.println("Account not found");
    }

    public void updateAddress(long accNo, long aadharNo, String newAdress, ArrayList<BankAccount> accounts) 
    {
        boolean found = false;
        for (BankAccount account : accounts) 
        {
            if(account.accNo == accNo && account.aadharNo == aadharNo)
            {
                found = true;
                account.address = newAdress;
                System.out.println("Address updated : "+newAdress);
            }
        }
        if(!found)
            System.out.println("Account not found");
    }

    public void updateAtmPassword(long accNo, long aadharNo, int newAtmPass, ArrayList<BankAccount> accounts) 
    {
        boolean found = false;
        for (BankAccount account : accounts) 
        {
            if(account.accNo == accNo && account.aadharNo == aadharNo)
            {
                found = true;
                account.atmPass = newAtmPass;
                System.out.println("PIN updated : "+newAtmPass);
            }
        }
        if(!found)
            System.out.println("Account not found");
    }

    public void updateNetBankingPass(long accNo, long aadharNo, String newPass, ArrayList<BankAccount> accounts) 
    {
        boolean found = false;
        for (BankAccount account : accounts) 
        {
            if(account.accNo == accNo && account.aadharNo == aadharNo)
            {
                found = true;
                account.netBankingPass = newPass;
                System.out.println("Net Banking Password updated : "+newPass);
            }
        }
        if(!found)
            System.out.println("Account not found");
    }

    

    public long getBalance() {
        return this.accBalance;
    }

    public long getAadharNo() {
        return aadharNo;
    }

    public String getName() {
        return name;
    }

    public String getDob() {
        return dob;
    }

    public String getAddress() {
        return address;
    }

    public long getPhNo() {
        return phNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public long getAccNo() {
        return accNo;
    }

    public int getAtmPass() {
        return atmPass;
    }

    public long getNetBankingId() {
        return netBankingId;
    }

    public String getNetBankingPass() {
        return netBankingPass;
    }

    public void setAadharNo(long aadharNo) {
        this.aadharNo = aadharNo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhNo(long phNo) {
        this.phNo = phNo;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setAtmPass(int atmPass) {
        this.atmPass = atmPass;
    }

    public void setAccBalance(long accBalance) {
        this.accBalance = accBalance;
    }
        
    public void setNetBankingPass(String netBankingPass) {
        this.netBankingPass = netBankingPass;
    }

    public LinkedList<Transaction> getAccTransactionHistory() {
        return accTransactionHistory;
    }

    
}
