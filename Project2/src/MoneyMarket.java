import java.util.Objects;

/**
 * @author Clarissa Hwang, Zain Ali
 */
public class MoneyMarket extends Account {
    private final int MIN_BALANCE = 2500;
    private final int MONTHLY_FEE = 12;
    private final double ANNUAL_INTEREST_RATE = 0.0065;
    private final int  MAX_WITHDRAWAL_WITHOUT_FEE = 6;
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
     * increase the balance by amount
     * @param amount amount that you want to add
     */
    @Override
    public void credit(double amount) {
        super.credit(amount);
        withdrawals += 1;
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
     *
     * @return
     */
    @Override
    public double monthlyInterest() {
        return 0.0;
    }

    /**
     *
     * @return
     */
    @Override
    public double monthlyFee() {
        return 0.0;
    }
}