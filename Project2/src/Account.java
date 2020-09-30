/**
 * @author Clarissa Hwang, Zain Ali
 */
public abstract class Account {
    private Profile holder;
    private double balance;
    private Date dateOpen;

    /**
     * decrease the balance by amount
     * @param amount
     */
    public void debit(double amount) {
    }

    /**
     * increase the balance by amount
     * @param amount
     */
    public void credit(double amount) { } //

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "";
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
