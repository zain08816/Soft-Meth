/**
 * @author Clarissa Hwang, Zain Ali
 */
public class AccountDatabase {
    private Account[] accounts;
    private int size;

    /**
     *
     * @param account
     * @return
     */
    private int find(Account account) {
        return 0;
    }

    /**
     *
     */
    private void grow() {
    }

    /**
     *
     * @param account
     * @return
     */
    public boolean add(Account account) {
        return false;
    }

    /**
     * return false if account doesn’t exist
     * @param account
     * @return
     */
    public boolean remove(Account account) {
        return false;
    }

    /**
     *
     * @param account
     * @param amount
     * @return
     */
    public boolean deposit(Account account, double amount) {
        return false;
    }

    /**
     * return 0: withdrawal successful, 1: insufficient funds, -1 account doesn’t exist
     * @param account
     * @param amount
     * @return
     */
    public int withdrawal(Account account, double amount) {
        return 0;
    }

    /**
     * sort in ascending order
     */
    private void sortByDateOpen() {
    }

    /**
     * sort in ascending order
     */
    private void sortByLastName() {
    }

    /**
     *
     */
    public void printByDateOpen() {
    }

    /**
     *
     */
    public void printByLastName() {
    }

    /**
     *
     */
    public void printAccounts() {
    }

}
