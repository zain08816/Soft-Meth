/**
 * Account class for Money Market
 * @author Clarissa Hwang, Zain Ali
 */
public class MoneyMarket extends Account {
    private final int MIN_BALANCE = 2500;
    private final int MONTHLY_FEE = 12;
    private final double ANNUAL_INTEREST_RATE = 0.0065;
    private final int  MAX_WITHDRAWAL_WITHOUT_FEE = 6;
    private final double MONTHS_IN_YEAR = 12;
    private int withdrawals;

    /**
     * Money Market Constructor
     * @param holder Profile holder of the account
     * @param dateOpen Date account was opened
     * @param balance double initial balance
     */
    public MoneyMarket(Profile holder, Date dateOpen, double balance) {
        super(holder, dateOpen, balance);
        this.withdrawals = 0;
    }

    /**
     * Decreases the balance by amount
     * @param amount amount that you want to add
     */
    @Override
    public void debit(double amount) {
        super.debit(amount);
        withdrawals += 1;
    }

    /**
     * returns String with class information
     * @return returns formatted string with account info
     */
    @Override
    public String toString() {
        String special = withdrawals == 1
                ? String.format("%d withdrawal", withdrawals)
                : String.format("%d withdrawals", withdrawals) ;
        return String.format("*Money Market*%s* $%,.2f*%s*%s*", this.getName(),
                this.getBalance(), this.getDateOpen().toString(), special);
    }

    /**
     * Compare two Account objects to see if they are equal.
     * @param obj object you that needs to be compared to MoneyMarket
     * @return true if the objects are the same, false if not
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MoneyMarket marketObject = (MoneyMarket) obj;
        return marketObject.getName().equals(this.getName());
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
        if (getBalance() >= MIN_BALANCE){
            if (withdrawals > MAX_WITHDRAWAL_WITHOUT_FEE) return 0;
        }
        return MONTHLY_FEE;
    }
}