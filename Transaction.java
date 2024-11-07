import java.util.*;

class Transaction 
{
    private final String type; // Deposit or Withdraw
    private final long amount;
    private final String date;

    public Transaction(String type, long amount) {
        this.type = type;
        this.amount = amount;
        this.date = new Date().toString(); // Automatically set current date and time
    }

    @Override
    public String toString() {
        return date + ": " + type + " of Rs " + amount;
    }

    public String getType() {
        return type;
    }

    public long getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }  
}