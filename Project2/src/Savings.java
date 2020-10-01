/**
 * @author Clarissa Hwang, Zain Ali
 */
public class Savings extends Account {
    private final int MIN_BALANCE = 300;
    private final int MONTHLY_FEE = 5;
    private final double ANNUAL_INTEREST_RATE = 0.0025;
    private final double PROMOTIONAL_INTEREST_RATE = 0.0035;
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
        String loyal = isLoyal ? "*direct deposit account" : "";
        return String.format("*Savings*%s* $%.2f*%s*%s", this.getName(),
                this.getBalance(), this.getDateOpen().toString(), loyal);
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

    public static void main(String[] args) {

    }
}