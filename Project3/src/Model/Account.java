package Model;
/**
 * Abstract class for Accounts
 * @author Clarissa Hwang, Zain Ali
 */
public abstract class Account {
    private Profile holder;
    private double balance;
    private Date dateOpen;

    /**
     * Account default constructor
     * @param holder Profile of the account
     * @param dateOpen Date account was opened
     * @param balance initial account balance
     */
    public Account(Profile holder, Date dateOpen, double balance) {
        this.holder = holder;
        this.dateOpen = dateOpen;
        this.balance = balance;
    }

    /**
     * decrease the balance by amount
     * @param amount amount that you want to remove
     */
    public void debit(double amount) {
        balance -= amount;
    }

    /**
     * increase the balance by amount
     * @param amount amount that you want to add
     */
    public void credit(double amount) {
        balance += amount;
    }

    /**
     * returns String with class information
     * @return returns formatted string with account info
     */
    @Override
    public String toString() {
        return String.format("%s* $%,.2f*%s", getName(), getBalance(), dateOpen.toString());
    }

    /**
     * Returns full name of account holder
     * @return returns full name String of account holder.
     */
    public String getName() {
        return holder.getFname() + " " + holder.getLname();
    }

    /**
     * Gets the last name of account holder
     * @return returns the last name of the account holder
     */
    public String getLastName() {
        return holder.getLname();
    }

    /**
     * Returns the balance of the account
     * @return returns the double balance of the account
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Return the open date of the account
     * @return return Date of account open
     */
    public Date getDateOpen() {
        return dateOpen;
    }

    /**
     * function used for Money Market when the Database needs to debit an account.
     * This debit should not count as a withdrawal to the account.
     * 1 withdraw is removed from their account.
     * If the Account does not Override this function, it does nothing.
     */
    public void freeWithdraw() {
        return;
    }

    /**
     * abstract function to return Monthly Interest
     * @return returns Monthly Interest
     */
    public abstract double monthlyInterest();

    /**
     * abstract function to return Monthly Fee
     * @return returns Monthly Fee
     */
    public abstract double monthlyFee();

    /**
     * Gets the exportable string for an account
     * @return String that contains readable data of account
     */
    public String exportString(){
        return String.format("%s,%s,%.2f,%s", holder.getFname(), holder.getLname(), balance, dateOpen.toString());
    }


}
