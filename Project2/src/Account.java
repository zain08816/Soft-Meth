/**
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
        return "";
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
     *
     * @return
     */
    public abstract double monthlyInterest();

    /**
     *
     * @return
     */
    public abstract double monthlyFee();

}
