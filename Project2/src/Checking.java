/**
 * @author Clarissa Hwang, Zain Ali
 */
public class Checking extends Account {
    int MIN_BALANCE = 1500;
    int MONTHLY_FEE = 25;
    double ANNUAL_INTEREST_RATE = 0.0005;
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