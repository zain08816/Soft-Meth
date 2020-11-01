package Model;
/**
 * Account class for Savings
 * @author Clarissa Hwang, Zain Ali
 */
public class Savings extends Account {
    private final int MIN_BALANCE = 300;
    private final int MONTHLY_FEE = 5;
    private final double ANNUAL_INTEREST_RATE = 0.0025;
    private final double PROMOTIONAL_INTEREST_RATE = 0.0035;
    private final double MONTHS_IN_YEAR = 12;
    private boolean isLoyal;


    /**
     * Savings Constructor
     * @param holder Profile holder of the account
     * @param dateOpen Date account was opened
     * @param balance double initial balance
     * @param isLoyal boolean if customer is loyal
     */
    public Savings(Profile holder, Date dateOpen, double balance, boolean isLoyal) {
        super(holder, dateOpen, balance);
        this.isLoyal = isLoyal;
    }


    /**
     * returns String with class information
     * @return returns formatted string with account info
     */
    @Override
    public String toString() {
        String special = isLoyal ? "*special Savings account*" : "";
        return String.format("*Savings*%s%s", super.toString(), special);
    }

    /**
     * Compare two Account objects to see if they are equal.
     * @param obj object you that needs to be compared to Savings
     * @return true if the objects are the same, false if not
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Savings savingsObject = (Savings) obj;
        return savingsObject.getName().equals(this.getName());
    }

    /**
     * Calculate monthly interest on account
     * @return monthly interest for account
     */
    @Override
    public double monthlyInterest() {
        if (isLoyal) return getBalance()*(PROMOTIONAL_INTEREST_RATE/MONTHS_IN_YEAR);
        else return getBalance()*(ANNUAL_INTEREST_RATE/MONTHS_IN_YEAR);
    }

    /**
     * Calculate fee needed to be payed on account
     * @return monthly fee for account;
     */
    @Override
    public double monthlyFee() {
        if (getBalance() >= MIN_BALANCE) return 0;
        else return MONTHLY_FEE;
    }

    /**
     * Gets the exportable string for an account
     * @return String that contains readable data of account
     */
    @Override
    public String exportString() {
        return String.format("S,%s,%b\n", super.exportString(), isLoyal);
    }

}