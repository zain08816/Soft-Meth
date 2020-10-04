/**
 * Account class for Checking
 * @author Clarissa Hwang, Zain Ali
 */
public class Checking extends Account {
    private final int MIN_BALANCE = 1500;
    private final int MONTHLY_FEE = 25;
    private final double ANNUAL_INTEREST_RATE = 0.0005;
    private final double MONTHS_IN_YEAR = 12;
    private boolean directDeposit;


    /**
     * Checking Constructor
     * @param holder Profile holder of the account
     * @param dateOpen Date account was opened
     * @param balance double initial balance
     * @param directDeposit boolean if customer is has direct deposit enabled
     */
    public Checking(Profile holder, Date dateOpen, double balance, boolean directDeposit) {
        super(holder, dateOpen, balance);
        this.directDeposit = directDeposit;
    }

    /**
     * returns String with class information
     * @return returns formatted string with account info
     */
    @Override
    public String toString() {
        String special = directDeposit ? "*direct deposit account*" : "";
        return String.format("*Checking*%s* $%,.2f*%s%s", this.getName(),
                this.getBalance(), this.getDateOpen().toString(), special);
    }

    /**
     * Compare two Account objects to see if they are equal.
     * @param obj object you that needs to be compared to Checking
     * @return true if the objects are the same, false if not
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Checking checkingObject = (Checking) obj;
        return checkingObject.getName().equals(this.getName());
    }

    /**
     * Calculate monthly interest on account
     * @return monthly interest for account
     */
    @Override
    public double monthlyInterest() {
        return getBalance()*(ANNUAL_INTEREST_RATE/MONTHS_IN_YEAR);
    }

    /**
     * Calculate fee needed to be payed on account
     * @return monthly fee for account;
     */
    @Override
    public double monthlyFee() {
        if (getBalance() >= MIN_BALANCE) return 0;
        else if (directDeposit) return 0;
        return MONTHLY_FEE;
    }
}