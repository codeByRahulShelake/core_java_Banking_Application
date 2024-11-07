import java.util.*;

class UserView 
{
    private boolean valid = false;
    private BankAccount info = new BankAccount();
    private AuthenticatedActions authenticatedActions;

    // Method to create a new account
    public void createAccount(long aadharNo, String name, String dob, String address, long phNo, String emailId, long accBalance, ArrayList<BankAccount> bankObj) 
    {
        info.addNewAccount(aadharNo, name, dob, address, phNo, emailId, accBalance);
        bankObj.add(info);
    }

    // Method to login to account
    public boolean  loginAccount(long accNo, long aadharNo, ArrayList<BankAccount> accounts) 
    {
        for (BankAccount account : accounts) 
        {
            if (account.getAadharNo() == aadharNo && account.getAccNo() == accNo) 
            {
                valid = true;
                info = account;
                authenticatedActions = new AuthenticatedActions(); // Enable access to restricted methods
                System.out.println("\nLogin successful");
            } 
        }
        if(!valid)
            System.out.println("Login failed, Invalid Account no or password");

        return valid;
    }

    // Inner class to hold methods that are only accessible after login
    class AuthenticatedActions {
        private AuthenticatedActions() {} // default constructor as private so that it can be instatiated by only his parent class

        // Method to display account info (restricted)
        public void displayAccountInfo() 
        {
            info.displayAccountDetails();
        }

        public void applyForNetBanking(ArrayList<BankAccount> accounts)
        {
            info.applyForNetBanking(info.getAccNo(), info.getAadharNo(), accounts);
        }

        public void updateName( String newName) 
        {
            info.setName(newName);
            System.out.println("Name updated");
        }

        public void updatePhoneNo(long newPhNo) 
        {
           info.setPhNo(newPhNo);
           System.out.println("Phone no updated");
        }

        public void updateAadhaarNo(long newAadhaarNo) 
        {
            info.setAadharNo(newAadhaarNo);
            System.out.println("Aadhaar No updated");
        }

        public void updateEmailId(String newEmailId) 
        {
            info.setEmailId(newEmailId);
            System.out.println("Email Id updated");
        }

        public void updateDOB(String newDOB) 
        {
            info.setDob(newDOB);
            System.out.println("Date of Birth updated");
        }

        public void updateAddress(String newAdress) 
        {
            info.setAddress(newAdress);
            System.out.println("Address updated");
        }

        public void updateAtmPIN(int newAtmPass) 
        {
            info.setAtmPass(newAtmPass);
            System.out.println("ATM pin updated");
        }

        public void logOut()
        {
            valid = false;
            System.out.println("Log out successfull");
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