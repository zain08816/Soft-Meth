/**
 * @author Clarissa Hwang, Zain Ali
 */
public class MoneyMarket extends Account {
    int MIN_BALANCE = 2500;
    int MONTHLY_FEE = 12;
    double ANNUAL_INTEREST_RATE = 0.0065;
    int  MAX_WITHDRAWAL_WITHOUT_FEE = 6;
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