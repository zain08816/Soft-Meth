/**
 * @author Clarissa Hwang, Zain Ali
 */
public class Savings extends Account {
    int MIN_BALANCE = 300;
    int MONTHLY_FEE = 5;
    double ANNUAL_INTEREST_RATE = 0.0025;
    double PROMOTIONAL_INTEREST_RATE = 0.0035;
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