import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.*;

public class BankCloneApp 
{
    public static void main(String[] args) 
    {
        ArrayList<BankAccount> bankObj = new ArrayList<>();  // to store objects of bank account class
        Scanner sc = new Scanner(System.in);
        BankAccount bankInfo = new BankAccount();
        UserView userObj = new UserView();
        NetBanking netBankingObj = new NetBanking();
        int choice;
        boolean flag = true;
        
        do 
        {   
            System.out.println();
            System.out.println("-----------------------------------------------------------------------------------------------");
            System.out.println();
            System.out.println("1: Admin\n2: User\n3: Net Banking\n4: Exit\nSelect one option:");
            try 
            {
                choice = sc.nextInt();
                sc.nextLine();  // Clear buffer
            } catch (InputMismatchException e) 
            {
                System.out.println("Enter digits only.");
                sc.nextLine();  // Clear buffer after invalid input
                continue;
            }

            switch (choice) 
            {
                case 1:
                    boolean adminFlag = true;
                    do 
                    {
                        System.out.println();
                        System.out.println("-----------------------------------------------------------------------------------------------");
                        System.out.println();
                        System.out.println("1: Create Account | 2: Display all Account details"
                                            + "3: Delete Account \n4: Search Account"
                                            + " | 5: Deposit | 6: Withdraw"
                                            + "\n7: Transfer | 8: Show Transaction History"
                                            + "9: Apply for Net Banking  \n10: Update details | 11: Exit");

                        try 
                        {
                            choice = sc.nextInt();
                            sc.nextLine();  // Clear buffer
                        } catch (InputMismatchException e) 
                        {
                            System.out.println("Enter digits only.");
                            sc.nextLine();  // Clear buffer after invalid input
                            continue;
                        }

                        switch (choice) 
                        {
                            case 1:
                                System.out.println("Enter Name:");
                                String name = sc.nextLine();

                                long aadharNo = returnAadhaar(sc);

                                String dateString = returnDate(sc);

                                System.out.println("Enter Address:");
                                String address = sc.nextLine();

                                long phNo = returnPhone(sc);
                                
                                String emailId = returnEmailId(sc);

                                long accBalance = returnBalance(sc);

                                BankAccount createAcc = new BankAccount();
                                createAcc.addNewAccount(aadharNo, name, dateString, address, phNo, emailId, accBalance);
                                bankObj.add(createAcc);
                                break;

                            case 2:
                                bankInfo.displayAllAccountDetails(bankObj);
                                break;

                            case 3:
                                try 
                                {
                                    System.out.println("Enter Account No and Aadhar No to delete account:");
                                    long delAccNo = sc.nextLong();
                                    long delAadharNo = sc.nextLong();
                                    sc.nextLine();
                                    bankInfo.deleteAccount(delAccNo, delAadharNo, bankObj); 
                                } 
                                catch (InputMismatchException e) 
                                {
                                    System.out.println("Invalid inputs.");
                                    sc.nextLine();
                                }
                                
                                break;

                            case 4:
                                boolean searchFlag = true;
                                while(searchFlag)
                                {
                                    System.out.println();
                                    System.out.println("-----------------------------------------------------------------------------------------------");
                                    System.out.println();
                                    System.out.println("1: By Name | 2: By Account No | 3: By Aadhar No | 4: By Phone No | 5: Exit");
                                    try {
                                        choice = sc.nextInt();
                                        sc.nextLine();
                                    } catch (InputMismatchException e) {
                                        System.out.println("Enter digits only.");
                                        sc.nextLine();
                                        continue;
                                    }

                                    switch (choice) 
                                    {
                                        case 1:
                                            System.out.println("Enter Name to search:");
                                            String searchName = sc.nextLine();
                                            bankInfo.searchByName(searchName, bankObj);
                                            break;

                                        case 2:
                                            System.out.println("Enter Account No to search:");
                                            try {
                                                long searchAccNo = sc.nextLong();
                                                sc.nextLine();
                                                bankInfo.searchAccNo(searchAccNo, bankObj);
                                            } catch (InputMismatchException e) {
                                                System.out.println("Enter a valid account number.");
                                                sc.nextLine();
                                            }
                                            break;

                                        case 3:
                                            System.out.println("Enter Aadhar No to search:");
                                            try {
                                                long searchAadharNo = sc.nextLong();
                                                sc.nextLine();
                                                bankInfo.searchByAadhar(searchAadharNo, bankObj);
                                            } catch (InputMismatchException e) {
                                                System.out.println("Enter a valid Aadhar number.");
                                                sc.nextLine();
                                            }
                                            break;

                                        case 4:
                                            System.out.println("Enter Phone No to search:");
                                            try {
                                                long searchPhNo = sc.nextLong();
                                                sc.nextLine();
                                                bankInfo.searchByPhone(searchPhNo, bankObj);
                                            } catch (InputMismatchException e) {
                                                System.out.println("Enter a valid phone number.");
                                                sc.nextLine();
                                            }
                                            break;

                                        case 5:
                                            searchFlag = false;
                                            break;

                                        default:
                                            System.out.println("Invalid choice.");
                                    }
                                }
                                
                                break;

                            case 5 :
                                System.out.println("Enter Account no and  deposit amount :");
                                try {
                                    long depAccNo = sc.nextLong();
                                    long depAmount = sc.nextLong();
                                    sc.nextLine();
                                    bankInfo.depositAmount(depAccNo, depAmount, bankObj);
                                } catch (InputMismatchException e) {
                                    System.out.println("Enter a valid Account number and deposit amount(ie. in digit).");
                                    sc.nextLine();
                                }
                                break;

                            case 6 :
                                System.out.println("Enter Account no and  withdraw amount :");
                                try {
                                    long withAccNo = sc.nextLong();
                                    long withAmount = sc.nextLong();
                                    sc.nextLine();
                                    bankInfo.withdrawAmount(withAccNo, withAmount, bankObj);
                                } catch (InputMismatchException e) {
                                    System.out.println("Enter a valid Account number and Withdraw amount(ie. in digit).");
                                    sc.nextLine();
                                }
                                break;

                            case 7 :
                                System.out.println("Enter source account :");
                                try 
                                {
                                    long sourceAccNo = sc.nextLong();
                                    System.out.println("Enter target account :");
                                    long targetAccNo = sc.nextLong();
                                    System.out.println("Enter transfer amount :");
                                    long transferAmount = sc.nextLong();
                                    sc.nextLine();
                                    bankInfo.transferAmount(sourceAccNo, targetAccNo, transferAmount, bankObj);
                                } 
                                catch (InputMismatchException e) 
                                {
                                    System.out.println("Enter a valid inputs(ie. in digit).");
                                    sc.nextLine();
                                }
                                break;

                            case 8 :
                                System.out.println("Enter Account no to get transaction history :");
                                try {
                                    long searchAccNo = sc.nextLong();
                                    sc.nextLine();
                                    bankInfo.getTransactionHistory(searchAccNo, bankObj);
                                } catch (InputMismatchException e) {
                                    System.out.println("Enter a valid Account number(ie. in digit).");
                                    sc.nextLine();
                                }
                                break;

                            case 9 :
                                System.out.println("Enter Account no and Aadhaar No to apply for net banking :");
                                try {
                                    long searchAccNo = sc.nextLong();
                                    long searchAadharNo = sc.nextLong();
                                    sc.nextLine();
                                    bankInfo.applyForNetBanking(searchAccNo, searchAadharNo, bankObj);
                                } catch (InputMismatchException e) {
                                    System.out.println("Enter a valid Account number and Withdraw amount(ie. digit).");
                                    sc.nextLine();
                                }
                                break;

                            case 10 :
                                System.out.println();
                                System.out.println("-----------------------------------------------------------------------------------------------");
                                System.out.println();
                                System.out.println("1: Name | 2: Aadhaar No | 3: Email Id | 4: Phone No | 5: Address "
                                                    +"6: Date of Birth | 7: ATM Pin | 8: Net Banking Password");
                                try {
                                    choice = sc.nextInt();
                                    sc.nextLine();
                                } catch (InputMismatchException e) {
                                    System.out.println("Enter digit only.");
                                    sc.nextLine();
                                }
                                
                                switch (choice) 
                                {
                                    case 1:
                                        try 
                                        {
                                            System.out.println("Enter correct Account no : ");
                                            long searchAccNo = sc.nextLong();
                                            System.out.println("Enter correct Aadhaar no : ");
                                            long searchAadhaarNo = sc.nextLong();
                                            sc.nextLine();
                                            System.out.println("Enter new name : ");
                                            String newName = sc.nextLine();
                                            bankInfo.updateName(searchAccNo, searchAadhaarNo, newName, bankObj);
                                        } 
                                        catch (InputMismatchException e) 
                                        {
                                            System.out.println("Enter valid inputs");
                                        }  
                                        break;

                                    case 2:
                                        try 
                                        {
                                            System.out.println("Enter correct Account no : ");
                                            long searchAccNo = sc.nextLong();
                                            System.out.println("Enter correct Phone no : ");
                                            long searchPhNo = sc.nextLong();
                                            System.out.println("Enter new Aadhaar No : ");
                                            long newAadharNo = returnAadhaar(sc);
                                            bankInfo.updateAadhaarNo(searchAccNo, searchPhNo, newAadharNo, bankObj);
                                        } 
                                        catch (InputMismatchException e) 
                                        {
                                            System.out.println("Enter valid inputs");
                                            sc.nextLine();
                                        }  
                                        break;

                                    case 3:
                                        try 
                                        {
                                            System.out.println("Enter correct Account no : ");
                                            long searchAccNo = sc.nextLong();
                                            System.out.println("Enter correct Aadhaar no : ");
                                            long searchAadhaarNo = sc.nextLong();
                                            sc.nextLine();
                                            String newEmailId = returnEmailId(sc);
                                            bankInfo.updateEmailId(searchAccNo, searchAadhaarNo, newEmailId, bankObj);
                                        } 
                                        catch (InputMismatchException e) 
                                        {
                                            System.out.println("Enter valid inputs");
                                            sc.nextLine();
                                        }  
                                        break;

                                    case 4:
                                        try 
                                        {
                                            System.out.println("Enter correct Account no : ");
                                            long searchAccNo = sc.nextLong();
                                            System.out.println("Enter correct Aadhaar no : ");
                                            long searchAadhaarNo = sc.nextLong();

                                            long newPhoneNo = returnPhone(sc);
                                            bankInfo.updatePhoneNo(searchAccNo, searchAadhaarNo, newPhoneNo, bankObj);
                                        } 
                                        catch (InputMismatchException e) 
                                        {
                                            System.out.println("Enter valid inputs");
                                            sc.nextLine();
                                        }  
                                        break;

                                    case 5:
                                        try 
                                        {
                                            System.out.println("Enter correct Account no : ");
                                            long searchAccNo = sc.nextLong();
                                            System.out.println("Enter correct Aadhaar no : ");
                                            long searchAadhaarNo = sc.nextLong();
                                            sc.nextLine();
                                            System.out.println("Enter new Address : ");
                                            String newAddress = sc.nextLine();
                                            bankInfo.updateAddress(searchAccNo, searchAadhaarNo, newAddress, bankObj);
                                        } 
                                        catch (InputMismatchException e) 
                                        {
                                            System.out.println("Enter valid inputs");
                                            sc.nextLine();
                                        }  
                                        break;

                                    case 6:
                                        try 
                                        {
                                            System.out.println("Enter correct Account no : ");
                                            long searchAccNo = sc.nextLong();
                                            System.out.println("Enter correct Aadhaar no : ");
                                            long searchAadhaarNo = sc.nextLong();
                                            sc.nextLine();
                                            String newDateString = returnDate(sc);
                                    
                                            bankInfo.updateDOB(searchAccNo, searchAadhaarNo, newDateString, bankObj);
                                        } 
                                        catch (InputMismatchException e) 
                                        {
                                            System.out.println("Enter valid inputs");
                                            sc.nextLine();
                                        }  
                                        break;

                                    case 7:
                                        try 
                                        {
                                            System.out.println("Enter correct Account no : ");
                                            long searchAccNo = sc.nextLong();
                                            System.out.println("Enter correct Aadhaar no : ");
                                            long searchAadhaarNo = sc.nextLong();
                                            System.out.println("Enter new ATM Pin : ");
                                            int newPin = sc.nextInt();
                                            sc.nextLine();
                                            bankInfo.updateAtmPassword(searchAccNo, searchAadhaarNo, newPin, bankObj);
                                        } 
                                        catch (InputMismatchException e) 
                                        {
                                            System.out.println("Enter valid inputs");
                                            sc.nextLine();
                                        }  
                                        break;

                                    case 8:
                                        try 
                                        {
                                            System.out.println("Enter correct Account no : ");
                                            long searchAccNo = sc.nextLong();
                                            System.out.println("Enter correct Aadhaar no : ");
                                            long searchAadhaarNo = sc.nextLong();
                                            sc.nextLine();
                                            System.out.println("Enter new Net Banking Password : ");
                                            String newPass = sc.nextLine();
                                            bankInfo.updateNetBankingPass(searchAccNo, searchAadhaarNo, newPass, bankObj);
                                        } 
                                        catch (InputMismatchException e) 
                                        {
                                            System.out.println("Enter valid inputs");
                                        }  
                                        break;
                                    
                                    default:
                                        System.out.println("Invalid choice");
                                }

                                break;

                            case 11:
                                adminFlag = false;
                                System.out.println("Exited successfully.");
                                break;

                            default:
                                System.out.println("Invalid choice.");
                        }
                    } while (adminFlag);
                    break;
                
                    case 2:
                        boolean userFlag = true;
                        while(userFlag)
                        {
                            System.out.println();
                            System.out.println("-----------------------------------------------------------------------------------------------");
                            System.out.println();
                            System.out.println("1: Create Account | 2: Log in Account | 3: Exit");
                            try {
                                choice = sc.nextInt();
                                sc.nextLine();
                            } catch (InputMismatchException e) {
                                System.out.println("Enter digits only.");
                                sc.nextLine();
                                continue;
                            }

                            switch (choice) 
                            {
                                case 1:
                                    System.out.println("Enter Name:");
                                    String name = sc.nextLine();

                                    long aadharNo = returnAadhaar(sc);

                                    String dateString = returnDate(sc);

                                    System.out.println("Enter Address:");
                                    String address = sc.nextLine();

                                    long phNo = returnPhone(sc);
                                    
                                    String emailId = returnEmailId(sc);

                                    long accBalance = returnBalance(sc);

                                    userObj.createAccount(aadharNo, name, dateString, address, phNo, emailId, accBalance, bankObj);
                                    break;

                                case 2:
                                        try 
                                        {
                                            System.out.println("Enter correct Account no : ");
                                            long searchAccNo = sc.nextLong();
                                            System.out.println("Enter correct Aadhaar no : ");
                                            long searchAadhaarNo = sc.nextLong();
                                            sc.nextLine();
                                            userObj.loginAccount(searchAccNo, searchAadhaarNo, bankObj);
                                        } 
                                        catch (InputMismatchException e) 
                                        {
                                            System.out.println("Enter valid inputs");
                                            sc.nextLine();
                                        }
                                        choice = 0;
                                        UserView.AuthenticatedActions actions = userObj.getAuthenticatedActions();
                                        if(actions != null)
                                        {
                                            boolean logInActionsFlag = true;
                                            while(logInActionsFlag)
                                            {
                                                System.out.println();
                                                System.out.println("-----------------------------------------------------------------------------------------------");
                                                System.out.println();
                                                System.out.println("1: Display account info | 2: Apply for net Banking | 3: Update details | 4: Log out");
                                                try 
                                                {
                                                    choice = sc.nextInt();
                                                    sc.nextLine();
                                                } catch (InputMismatchException e) 
                                                {
                                                    System.out.println("Enter valid input");
                                                    sc.nextLine();
                                                }

                                                switch (choice) 
                                                {
                                                    case 1:
                                                        actions.displayAccountInfo();
                                                        break;

                                                    case 2:
                                                        actions.applyForNetBanking(bankObj);
                                                        break;

                                                    case 3:
                                                        choice = 0;
                                                        System.out.println();
                                                        System.out.println("-----------------------------------------------------------------------------------------------");
                                                        System.out.println();
                                                        System.out.println("1: Name | 2: Phone No | 3: Aadhaar No | 4: Email Id "
                                                                            +"\n5: Date of Birth  | 6: Address | 7: Atm Pin");

                                                        try 
                                                        {
                                                            choice = sc.nextInt();  
                                                            sc.nextLine(); 
                                                        } catch (InputMismatchException e) {
                                                            System.out.println("Enter valid inputs.");
                                                            sc.nextLine();
                                                        }

                                                        switch (choice) 
                                                        {
                                                            case 1:
                                                                System.out.println("Enter new name : ");
                                                                String newName = sc.nextLine();
                                                                actions.updateName(newName);
                                                                break;

                                                            case 2:
                                                                long newphNo = returnPhone(sc);
                                                                actions.updatePhoneNo(newphNo);
                                                                break;

                                                            case 3:
                                                                long newAadharNo = returnAadhaar(sc);
                                                                actions.updateAadhaarNo(newAadharNo);
                                                                break;

                                                            case 4:
                                                                String newEmailId = returnEmailId(sc);
                                                                actions.updateName(newEmailId);
                                                                break;

                                                            case 5:
                                                                String newDate = returnDate(sc);
                                                                actions.updateDOB(newDate);
                                                                break;

                                                            case 6:
                                                                System.out.println("Enter new Address :");
                                                                String newAddress = sc.nextLine();
                                                                actions.updateAddress(newAddress);
                                                                break;

                                                            case 7:
                                                                try 
                                                                {
                                                                    System.out.println("Enter new ATM Pin : ");
                                                                    int newPin = sc.nextInt();
                                                                    sc.nextLine();
                                                                    actions.updateAtmPIN(newPin);
                                                                } 
                                                                catch (InputMismatchException e) 
                                                                {
                                                                    System.out.println("Enter valid inputs");
                                                                    sc.nextLine();
                                                                } 
                                                                break;
                                                                

                                                            default:
                                                                System.out.println("Invalid choice.");
                                                        }
                                                        break;

                                                    case 4: 
                                                        logInActionsFlag = false;
                                                        break;
                                                    default:
                                                        System.out.println("Enter valid choice");
                                                }
                                            }
                                               
                                        }
                                        break;

                                    case 3:
                                        System.out.println("Exited Successfully.");
                                        userFlag = false;
                                        break;
                                    
                                    


                                default:
                                    System.out.println("Enter valid choice.");
                            }
                        }
                        break;

                    case 3:
                        try 
                        {
                            System.out.println("Enter accurate Net Banking Id And Password :");
                            long searchNetId = sc.nextLong();
                            sc.nextLine();
                            String searchNetPass = sc.nextLine();
                            netBankingObj.loginAccount(searchNetId, searchNetPass, bankObj);
                        } 
                        catch (InputMismatchException e) 
                        {
                            System.out.println("Enter valid input.");
                            sc.nextLine();
                        }
                        
                        NetBanking.AuthenticatedActions actions = netBankingObj.getAuthenticatedActions();
                        if (actions != null) // available if you have loged in successfully
                        {
                            boolean netBankingFlag = true;
                            while(netBankingFlag)
                            {
                                try 
                                {
                                    System.out.println();
                                    System.out.println("-----------------------------------------------------------------------------------------------");
                                    System.out.println();
                                    System.out.println("1: Display Account Info | 2: Deposit Amount | 3: Withdraw Amount \n4: Transfer Amount | 5: Transaction History | 6: Log out ");
                                    choice = sc.nextInt();
                                    sc.nextLine();   
                                }
                                catch (InputMismatchException e) 
                                {
                                    System.out.println("Enter valid inputs.");
                                    sc.nextLine();
                                }

                                switch (choice) 
                                {
                                    case 1:
                                        actions.displayAccountInfo();
                                        break;

                                    case 2:
                                        try 
                                        {
                                            System.out.println("Enter amount to deposit");
                                            long depAmount = sc.nextLong();
                                            sc.nextLine();
                                            actions.depositAmount(depAmount);
                                        } catch (InputMismatchException e) 
                                        {
                                            System.out.println("Enter valid amount");
                                        }
                                        break;

                                    case 3:
                                        try 
                                        {
                                            System.out.println("Enter amount to withdraw");
                                            long withAmount = sc.nextLong();
                                            sc.nextLine();
                                            actions.withdrawAmount(withAmount);
                                        } catch (InputMismatchException e) 
                                        {
                                            System.out.println("Enter valid amount");
                                        }
                                        break;

                                    case 4:
                                        try 
                                        {
                                            System.out.println("Enter target account :");
                                            long targetAcc = sc.nextLong();
                                            System.out.println("Enter transfer Amount :");
                                            long transferAmount = sc.nextLong();
                                            sc.nextLine();
                                            actions.transferAmount(targetAcc, transferAmount);
                                        } catch (InputMismatchException e) 
                                        {
                                            System.out.println("Enter valid amount and account");
                                        }
                                        break;
                                    
                                    case 5:
                                        actions.getTransactionHistory();
                                        break;

                                    case 6:
                                        netBankingFlag = false;
                                        break;
                                    default:
                                        System.out.println("Enter valid choice.");
                                }
                                

                            }
                        }
                        break;

                    case 4:
                        System.out.println("Exited Successfully.");
                        flag = false;
                        break;

                    default:
                        System.out.println("Invalid choice.");

                }
            } while (flag);
        sc.close();
    }

    public static boolean checkPhone(long ph)
    {
        return String.valueOf(ph).length() == 10;
    }

    public static String returnDate(Scanner sc )
    {
        int day, month, year;
        boolean validDate = false;
        String dateString = "";  // To store the valid date as a string
                        
        while (!validDate) 
        {
            try 
            {
                System.out.print("Enter day (1-31): ");
                day = sc.nextInt();
                if (day < 1 || day > 31) 
                    throw new IllegalArgumentException("Invalid day! Please enter a day between 1 and 31.");
                            
                System.out.print("Enter month (1-12): ");
                month = sc.nextInt();
                if (month < 1 || month > 12) 
                    throw new IllegalArgumentException("Invalid month! Please enter a month between 1 and 12.");
                                            
                System.out.print("Enter year (e.g., 1990): ");
                year = sc.nextInt();
                if (year < 1900 || year > LocalDate.now().getYear()) 
                    throw new IllegalArgumentException("Invalid year! Please enter a realistic year.");
                    
                LocalDate date = LocalDate.of(year, month, day);
                dateString = date.toString();  // Store the date as a string in "YYYY-MM-DD" format
                
                validDate = true;
            } 
            catch (IllegalArgumentException e) 
            {
                System.out.println(e.getMessage());  // Handle invalid day/month/year ranges
                
            } 
            catch (DateTimeException e) 
            {
                System.out.println("Invalid date! This day does not exist in this month/year. Please try again.");
            }
            sc.nextLine();  // Clear the scanner buffer
        }
        return dateString;                        
    }

    public static long returnAadhaar(Scanner sc)
    {
        long aadharNo = 0;
        boolean aadharFlag = true;
        do 
        {
            System.out.println("Enter AadharNo:");
            try 
            {
                aadharNo = sc.nextLong();
                sc.nextLine();
                if (UniqueInfo.aadharSet.contains(aadharNo)) 
                    System.out.println("Aadhar card already exists. Enter again.");
                else if(String.valueOf(aadharNo).length() != 12)
                    System.out.println("Enter valid Aadhar (i.e 12 digit)");
                else
                    aadharFlag = false;
            } 
            catch (InputMismatchException e) 
            {
                System.out.println("Enter a valid Aadhar number.");
                sc.nextLine();  // Clear buffer
            }
        } while (aadharFlag);
        return aadharNo;
    }

    public static long returnPhone(Scanner sc)
    {
        boolean phFlag = false;
        long phNo = 0;
        while(!phFlag)
        {
            try 
            {
                System.out.println("Enter Phone No.:");
                phNo = sc.nextLong();
                sc.nextLine();
                int digitCount = String.valueOf(phNo).length();
                if(digitCount == 10)
                    phFlag = true; 
                else 
                    System.out.println("Enter 10 digit no."); 
            } catch (Exception e) 
            {
                System.out.println("Enter digit only.");
                sc.nextLine();
            }
        }
        return phNo;
    }

    public static String returnEmailId(Scanner sc)
    {
        boolean flag = true;
        String email = "";

        while(flag)
        {
            System.out.println("Enter Email Id : ");
            email = sc.nextLine();

            if(email.endsWith("@gmail.com"))
                flag = false;
            else
                System.out.println("Enter valid Email Id (i.e. ends with .co)");
        }
        return email;
    }

    public static long returnBalance(Scanner sc)
    {
        boolean balanceFlag = false;
        long accBalance = 0;
        while(!balanceFlag)
        {
            try 
            {
                System.out.println("Enter initial balance:");
                accBalance = sc.nextLong();
                sc.nextLine();
                balanceFlag = true;
            } catch (Exception e) 
            {
                System.out.println("Enter digit only.");
                sc.nextLine();
            }
        }
        return accBalance;
    }
}
