/**
 * Holds all accounts and handles account functions
 * @author Clarissa Hwang, Zain Ali
 */
package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AccountDatabase {
    private final int INITIAL_SIZE = 5;
    private Account[] accounts;
    private int size;

    public AccountDatabase() {
        accounts = new Account[INITIAL_SIZE];
        size = 0;
    }

    /**
     * Helper function used to find the index of a specified Account
     * Finds first instance of the specified Account in the AccountDatabase.
     * @param account Account that needs to be found
     * @return return's index of found item or -1 if not found
     */
    private int find(Account account) {
        for (int i = 0; i < size; i += 1) {
            if (account.equals(accounts[i])) return i;
        }
        return -1;
    }

    /**
     * Doubles the size of the current AccountDatabase
     */
    private void grow() {
        int newSize = accounts.length * 2;
        Account[] newAccounts = new Account[newSize];

        for (int i = 0; i < accounts.length; i += 1) {
            newAccounts[i] = accounts[i];
        }

        accounts = newAccounts;
    }

    /**
     *  Adds an element into the AccountDatabase. Grows the AccountDatabase if
     *  there is not enough room to add.
     * @param account GroceryItem that you want to add to the ShoppingBag
     * @return return's false if account exists, true if added.
     */
    public boolean add(Account account) {
        if (find(account) == -1) {
            if (size >= accounts.length) grow();
            accounts[size] = account;
            size += 1;
            return true;
        }
        return false;
    }

    /**
     * Removes and account from AccountDatabase
     * @param account Account that you want to remove from AccountDatabase
     * @return returns true if successfully removed, returns false if not in AccountDatabase
     */
    public boolean remove(Account account) {
        int foundAccount = find(account);

        if (foundAccount != -1) {
            accounts[foundAccount] = accounts[size - 1];
            accounts[size - 1] = null;
            size -= 1;
            return true;
        }
        return false;
    }

    /**
     * Adds money into a specified account
     * @param account account that you want to deposit into
     * @param amount amount that you want to deposit
     * @return returns true if money added successfully, false if account not found
     */
    public boolean deposit(Account account, double amount) {
        int foundAccount = find(account);

        if (foundAccount != -1) {
            accounts[foundAccount].credit(amount);
            return true;
        }
        return false;
    }

    /**
     * Withdraws money from an account
     * @param account account that you want to withdraw from
     * @param amount amount that you want to withdraw
     * @return return 0: withdrawal successful, 1: insufficient funds, -1 account doesn’t exist
     */
    public int withdrawal(Account account, double amount) {
        int foundAccount = find(account);

        if (foundAccount != -1) {
            if (accounts[foundAccount].getBalance() >= amount) {
                accounts[foundAccount].debit(amount);
                return 0;
            } else {
                return 1;
            }
        }
        return -1;
    }

    /**
     * Sort accounts by date opened in ascending order
     */
    private void sortByDateOpen() {
        for (int i = 0; i < size; i += 1) {
            for (int j = i + 1; j < size; j += 1) {
                if (accounts[i].getDateOpen().compareTo(accounts[j].getDateOpen()) >= 0) {
                    Account hold = accounts[i];
                    accounts[i] = accounts[j];
                    accounts[j] = hold;
                }
            }
        }
    }

    /**
     * Sort accounts by last name in ascending order
     */
    private void sortByLastName() {
        for (int i = 0; i < size; i += 1) {
            for (int j = i + 1; j < size; j += 1) {
                if (accounts[i].getLastName().compareTo(accounts[j].getLastName()) >= 0) {
                    Account hold = accounts[i];
                    accounts[i] = accounts[j];
                    accounts[j] = hold;
                }
            }
        }
    }

    /**
     * Print accounts and information sorted by Date Opened
     */
    public String printByDateOpen() {
        sortByDateOpen();
        String buffer = "\n";
        for (int i = 0; i < size; i += 1) {
            double credit = accounts[i].monthlyInterest();
            double debit = accounts[i].monthlyFee();

            buffer += accounts[i].toString()+"\n";
            buffer += String.format("-interest: $ %,.2f\n", credit);
            buffer += String.format("-fee: $ %,.2f\n", debit);

            accounts[i].credit(credit);
            accounts[i].debit(debit);
            accounts[i].freeWithdraw();

            buffer += String.format("-new balance: $ %,.2f\n", accounts[i].getBalance());
        }
        return buffer;
    }

    /**
     * Print accounts and information sorted by Last name
     */
    public String printByLastName() {
        sortByLastName();
        String buffer = "\n";
        for (int i = 0; i < size; i += 1) {
            double credit = accounts[i].monthlyInterest();
            double debit = accounts[i].monthlyFee();

            buffer += accounts[i].toString() + "\n";
            buffer += String.format("-interest: $ %,.2f\n", credit);
            buffer += String.format("-fee: $ %,.2f\n", debit);

            accounts[i].credit(credit);
            accounts[i].debit(debit);
            accounts[i].freeWithdraw();

            buffer += String.format("-new balance: $ %,.2f\n", accounts[i].getBalance());
        }
        return buffer;
    }

    /**
     * Print out all account in database in no specific order
     */
    public String printAccounts() {
        String buffer = "";
        for (int i = 0; i < size; i += 1) {
            buffer  += accounts[i].toString() + "\n";
        }
        return buffer;
    }

    /**
     * Gives number of accounts in the database
     * @return returns the size of account database
     */
    public int getSize() {
        return size;
    }
}
