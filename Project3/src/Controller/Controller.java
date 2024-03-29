/**
 * @author Clarissa Hwang, Zain Ali
 */
package Controller;
import Model.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Class for controlling MainView.fxml
 * @author Clarissa Hwang, Zain Ali
 */
public class Controller {
    /**
     * FXML Declarations
     */
    // ------- OPEN/CLOSE ACCOUNT ------
    @FXML
    TextField firstAccount;
    @FXML
    TextField lastAccount;
    @FXML
    TextField month;
    @FXML
    TextField day;
    @FXML
    TextField year;
    @FXML
    TextField balance;
    @FXML
    RadioButton checkingAccount;
    @FXML
    RadioButton savingsAccount;
    @FXML
    RadioButton moneyMarketAccount;
    @FXML
    TextArea output;
    @FXML
    CheckBox loyal;
    @FXML
    CheckBox direct;

    // ------- DEPOSIT/WITHDRAW FUNDS -----
    @FXML
    TextField firstFunds;
    @FXML
    TextField lastFunds;
    @FXML
    TextField amount;
    @FXML
    RadioButton checkingFunds;
    @FXML
    RadioButton savingsFunds;
    @FXML
    RadioButton moneyMarketFunds;

    // ----------- OTHER GLOBALS -------

    ToggleGroup tgAccount;
    ToggleGroup tgFunds;
    AccountDatabase database;
    Stage currentStage;

    /**
     * Function run at the start of loading the controller
     */
    @FXML
    public void initialize() {

        database = new AccountDatabase();

        tgAccount = new ToggleGroup();
        checkingAccount.setToggleGroup(tgAccount);
        savingsAccount.setToggleGroup(tgAccount);
        moneyMarketAccount.setToggleGroup(tgAccount);

        tgFunds = new ToggleGroup();
        checkingFunds.setToggleGroup(tgFunds);
        savingsFunds.setToggleGroup(tgFunds);
        moneyMarketFunds.setToggleGroup(tgFunds);

        // Radio button listener
        tgAccount.selectedToggleProperty().addListener(new ChangeListener<Toggle>()
        {
            public void changed(ObservableValue<? extends Toggle> ob, Toggle o, Toggle n) {
                RadioButton rb = (RadioButton)tgAccount.getSelectedToggle();
                if (rb != null) {
                    String type = rb.getText();
                    switch (type) {
                        case "Checking":
                            loyal.setDisable(true);
                            direct.setDisable(false);
                            loyal.setSelected(false);
                            break;
                        case "Savings":
                            loyal.setDisable(false);
                            direct.setDisable(true);
                            direct.setSelected(false);
                            break;
                        case "Money Market":
                            loyal.setDisable(true);
                            direct.setDisable(true);
                            loyal.setSelected(false);
                            direct.setSelected(false);
                            break;
                    }
                }
            }
        });

    }

    public void start(Stage primaryStage) {
        currentStage = primaryStage;
    }


    //-------------------------------OPEN/CLOSE TAB------------------------------------------

    /**
     * Open an account
     */
    public void open() {

        //Check if inputs are all fill out
        if (!validateAccountInput()) return;

        //Get inputs
        String firstName = firstAccount.getText();
        String lastName = lastAccount.getText();
        String monthString = month.getText();
        String dayString = day.getText();
        String yearString = year.getText();
        String balanceString = balance.getText();
        String type = ((RadioButton) tgAccount.getSelectedToggle()).getText();

        //Check is date is valid
        if (!validateDate(monthString, dayString, yearString)) return;

        //Create open function input
        Profile person = new Profile(firstName, lastName);
        Date dateOpen = new Date(Integer.parseInt(monthString), Integer.parseInt(dayString), Integer.parseInt(yearString));
        double amount = Double.parseDouble(balanceString);


        boolean check;
        switch (type) {
            case "Checking":
                Checking checking = new Checking(person, dateOpen, amount, direct.isSelected());
                check = database.add(checking);
                if (check) output.appendText("Account opened and added to the database.\n");
                else output.appendText("Account is already in the database.\n");
                break;

            case "Savings":
                Savings savings = new Savings(person, dateOpen, amount, loyal.isSelected());
                check = database.add(savings);
                if (check) output.appendText("Account opened and added to the database.\n");
                else output.appendText("Account is already in the database.\n");
                break;

            case "Money Market":
                MoneyMarket market = new MoneyMarket(person, dateOpen, amount);
                check = database.add(market);
                if (check) output.appendText("Account opened and added to the database.\n");
                else output.appendText("Account is already in the database.\n");
                break;

            default:
                output.appendText("Please fill out all fields :)\n");
        }

    }

    /**
     * Close an account
     */
    public void close() {
        //Get inputs
        String firstName = firstAccount.getText();
        String lastName = lastAccount.getText();

        if (firstName.length() == 0 || lastName.length() == 0){
            output.appendText("Please provide a first and last name.\n");
            return;
        }

        RadioButton rb = (RadioButton)tgAccount.getSelectedToggle();
        String type;
        if (rb != null) {
            type = rb.getText();
        } else {
            output.appendText("Please select an account type\n");
            return;
        }


        //Create Profile object
        Profile person = new Profile(firstName, lastName);

        boolean check;
        switch (type) {
            case "Checking":
                Checking checking = new Checking(person, new Date(0,0,0), 0.0, false);;
                check = database.remove(checking);
                if (check) output.appendText("Account closed and removed from the database.\n");
                else output.appendText("Account does not exist.\n");
                break;

            case "Savings":
                Savings savings = new Savings(person, new Date(0,0,0), 0.0, false);;
                check = database.remove(savings);
                if (check) output.appendText("Account closed and removed from the database.\n");
                else output.appendText("Account does not exist.\n");
                break;

            case "Money Market":
                MoneyMarket market = new MoneyMarket(person, new Date(0,0,0), 0.0);;
                check = database.remove(market);
                if (check) output.appendText("Account closed and removed from the database.\n");
                else output.appendText("Account does not exist.\n");
                break;

            default:
                output.appendText("Please fill out all fields :)\n");
        }

    }



    //-------------------------------DEPOSIT/WITHDRAW TAB ------------------------------------

    /**
     * Deposit amount into account
     */
    public void deposit() {
        //Validate inputs
        if (!validateFundsInput()) return;

        // Get inputs
        String firstName = firstFunds.getText();
        String lastName = lastFunds.getText();
        String balanceString = amount.getText();
        String type = ((RadioButton) tgFunds.getSelectedToggle()).getText();

        //Create Profile object
        Profile person = new Profile(firstName, lastName);
        double depositAmount = Double.parseDouble(balanceString);

        boolean check;
        switch (type) {
            case "Checking":
                Checking checking = new Checking(person, new Date(0,0,0), 0.0, false);
                check = database.deposit(checking, depositAmount);
                if (check) output.appendText( String.format("%.2f deposited to account.\n", depositAmount));
                else output.appendText("Account does not exist.\n");
                break;

            case "Savings":
                Savings savings = new Savings(person, new Date(0,0,0), 0.0, false);
                check = database.deposit(savings, depositAmount);
                if (check) output.appendText( String.format("%.2f deposited to account.\n", depositAmount));
                else output.appendText("Account does not exist.\n");
                break;

            case "Money Market":
                MoneyMarket market = new MoneyMarket(person, new Date(0,0,0), 0.0);
                check = database.deposit(market, depositAmount);
                if (check) output.appendText( String.format("%.2f deposited to account.\n", depositAmount));
                else output.appendText("Account does not exist.\n");
                break;
        }


    }

    public void withdraw() {
        if (!validateFundsInput()) return;

        //Get inputs
        String firstName = firstFunds.getText();
        String lastName = lastFunds.getText();
        String balanceString = amount.getText();
        String type = ((RadioButton) tgFunds.getSelectedToggle()).getText();

        //Create Profile object
        Profile person = new Profile(firstName, lastName);
        double withdrawAmount = Double.parseDouble(balanceString);

        int check;
        switch (type) {
            case "Checking":
                Checking checking = new Checking(person, new Date(0,0,0), 0.0, false);
                check = database.withdrawal(checking, withdrawAmount);
                if (check == 0) output.appendText( String.format("%.2f withdrawn from account.\n", withdrawAmount));
                else if (check == 1) output.appendText("Insufficient funds.\n");
                else if (check == -1) output.appendText("Account does not exist.\n");
                break;

            case "Savings":
                Savings savings = new Savings(person, new Date(0,0,0), 0.0, false);
                check = database.withdrawal(savings, withdrawAmount);
                if (check == 0) output.appendText( String.format("%.2f withdrawn from account.\n", withdrawAmount));
                else if (check == 1) output.appendText("Insufficient funds.\n");
                else if (check == -1) output.appendText("Account does not exist.\n");
                break;

            case "Money Market":
                MoneyMarket market = new MoneyMarket(person, new Date(0,0,0), 0.0);
                check = database.withdrawal(market, withdrawAmount);
                if (check == 0) output.appendText( String.format("%.2f withdrawn from account.\n", withdrawAmount));
                else if (check == 1) output.appendText("Insufficient funds.\n");
                else if (check == -1) output.appendText("Account does not exist.\n");
                break;
        }

    }

    //-------------------------------IMPORT/EXPORT TAB ---------------------------------------

    /**
     * Import database.txt file into the database
     */
    public void importFile() {

        FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("TXT Files", "database.txt")
        );
        File selectedFile = fileChooser.showOpenDialog(currentStage);
        if (selectedFile == null){
            output.appendText("Please select a database.txt\n");
            return;
        }

        try {
            Scanner reader = new Scanner(selectedFile);
            while(reader.hasNextLine()) {
                String line = reader.nextLine();
                if (line.equals("")) {
                    continue;
                }
                addAccount(line);
            }
            output.appendText("Database imported\n");
        } catch(Exception exception) {
            output.appendText("Please select a valid format database.txt\n");
        }
    }

    /**
     * Prints database in no specific order
     */
    public void printAll() {
        if (database.getSize() == 0) {
            output.appendText("Database is empty\n");
            return;
        }
        output.appendText("--Listing accounts in the database--\n");
        output.appendText(database.printAccounts());
        output.appendText("--end of listing--\n");
    }

    /**
     * Prints statements by last name
     */
    public void printLast() {
        if (database.getSize() == 0) {
            output.appendText("Database is empty\n");
            return;
        }
        output.appendText("--Printing statements by date opened--\n");
        output.appendText(database.printByLastName());
        output.appendText("--end of listing--\n");
    }

    /**
     * Prints statements by Date
     */
    public void printDate() {
        if (database.getSize() == 0) {
            output.appendText("Database is empty\n");
            return;
        }
        output.appendText("--Printing statements by last name--\n");
        output.appendText(database.printByDateOpen());
        output.appendText("--end of printing--\n");
    }

    /**
     * Exports database in no specific order
     */
    public void exportAll() {
        if (database.getSize() == 0) {
            output.appendText("Cannot export, Database is empty\n");
            return;
        }
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("TXT Files", "*.txt")
        );
        File selectedFile = fileChooser.showSaveDialog(currentStage);
        if (selectedFile == null){
            output.appendText("Please select a valid save location\n");
            return;
        }
        try {
            PrintWriter writer = new PrintWriter(selectedFile);
            writer.println(database.printAccounts());
            writer.close();
        } catch(Exception exception) {
            output.appendText("Please select a valid save location\n");
        }
        output.appendText("File exported\n");
    }

    /**
     * Exports statement by last name
     */
    public void exportLast() {
        if (database.getSize() == 0) {
            output.appendText("Cannot export, Database is empty\n");
            return;
        }
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("TXT Files", "*.txt")
        );
        File selectedFile = fileChooser.showSaveDialog(currentStage);
        if (selectedFile == null){
            output.appendText("Please select a valid save location\n");
            return;
        }
        try {
            PrintWriter writer = new PrintWriter(selectedFile);
            writer.println(database.printByLastName());
            writer.close();
        } catch(Exception exception) {
            output.appendText("Please select a valid save location\n");
        }
        output.appendText("File exported\n");
    }

    /**
     * Exports statement by date opened
     */
    public void exportDate() {
        if (database.getSize() == 0) {
            output.appendText("Cannot export, Database is empty\n");
            return;
        }
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("TXT Files", "*.txt")
        );
        File selectedFile = fileChooser.showSaveDialog(currentStage);
        if (selectedFile == null){
            output.appendText("Please select a valid save location\n");
            return;
        }
        try {
            PrintWriter writer = new PrintWriter(selectedFile);
            writer.println(database.printByDateOpen());
            writer.close();
        } catch(Exception exception) {
            output.appendText("Please select a valid save location");
        }
        output.appendText("File exported\n");
    }

    /**
     * Exports importable file
     */
    public void exportImportable() {
        if (database.getSize() == 0) {
            output.appendText("Cannot export, Database is empty\n");
            return;
        }
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("TXT Files", "*.txt")
        );
        File selectedFile = fileChooser.showSaveDialog(currentStage);
        if (selectedFile == null){
            output.appendText("Please select a valid save location\n");
            return;
        }
        try {
            PrintWriter writer = new PrintWriter(selectedFile);
            writer.println(database.printExportable());
            writer.close();
        } catch(Exception exception) {
            output.appendText("Please select a valid save location\n");
        }

        output.appendText("File exported\n");
    }

    //-------------------------------CLEARING LOGIC-------------------------------------------

    /**
     * Clear input fields
     */
    public void clearFields() {
        firstAccount.setText("");
        lastAccount.setText("");
        firstFunds.setText("");
        lastFunds.setText("");
        month.setText("");
        day.setText("");
        year.setText("");
        balance.setText("");
        amount.setText("");
        checkingAccount.setSelected(false);
        savingsAccount.setSelected(false);
        moneyMarketAccount.setSelected(false);
        checkingFunds.setSelected(false);
        savingsFunds.setSelected(false);
        moneyMarketFunds.setSelected(false);
        loyal.setSelected(false);
        direct.setSelected(false);
    }

    /**
     * Clear Output
     */
    public void clearOutput() {
        output.setText("");
    }

    /**
     * Clear both fields and output
     */
    public void clearAll() {
        clearOutput();
        clearFields();
    }



    //---------------------------------HELPER FUNCTIONS---------------------------------------

    /**
     * check to see if a given date is valid
     * @return true if valid date, false if not
     */
    private boolean validateDate(String monthString, String dayString, String yearString){
        try {
            int mm = Integer.parseInt(monthString);
            int dd = Integer.parseInt(dayString);
            int yyyy = Integer.parseInt(yearString);
            if (new Date(mm, dd, yyyy).isValid()) {
                return true;
            } else {
                output.appendText("Please enter a valid date.\n");
                return false;
            }

        } catch(Exception exception) {
            output.appendText("Please enter a valid date.\n");
            return false;
        }
    }

    /**
     * Validate to see if inputs are filled out and correctly
     * @return true if valid inputs, false if not
     */
    private boolean validateAccountInput() {
        String firstName = firstAccount.getText();
        String lastName = lastAccount.getText();
        String monthString = month.getText();
        String dayString = day.getText();
        String yearString = year.getText();
        String amount = balance.getText();
        RadioButton rb = (RadioButton)tgAccount.getSelectedToggle();
        String type;
        if (rb != null) {
            type = rb.getText();
        } else {
            output.appendText("Please select an account type\n");
            return false;
        }

        try {
            Double check = Double.parseDouble(amount);
            if (check < 0 ) {
                output.appendText("Please enter a non negative amount.\n");
                return false;
            }
        } catch (Exception e) {
            output.appendText("Please enter a valid amount\n");
            return false;
        }

        if (firstName.length() == 0 ||
                lastName.length() == 0 ||
                monthString.length() == 0 ||
                dayString.length() == 0 ||
                yearString.length() == 0 ||
                type.length() == 0) {
            output.appendText("Please fill out all fields :)\n");
            return false;
        } else return true;
    }

    /**
     * Validate inputs on withdraw/deposit tab
     * @return true if valid inputs, false if not
     */
    private boolean validateFundsInput() {
        String firstName = firstFunds.getText();
        String lastName = lastFunds.getText();
        String fundAmount = amount.getText();

        if (firstName.length() == 0 || lastName.length() == 0 || fundAmount.length() == 0){
            output.appendText("Please provide a first name, last name, and amount.\n");
            return false;
        }

        try {
            double check = Double.parseDouble(fundAmount);
            if (check < 0 ) {
                output.appendText("Please enter a non negative amount.\n");
                return false;
            }
        }catch(Exception exception) {
            output.appendText("Please enter a valid amount.\n");
            return false;
        }

        RadioButton rb = (RadioButton)tgFunds.getSelectedToggle();
        if (rb == null) {
            output.appendText("Please select an account type\n");
            return false;
        }

        return true;
    }

    /**
     * Add account function for importable Strings
     * @param account importable String
     */
    private void addAccount(String account) {
            String[] parts = account.split(",");
            String[] dateParts = parts[4].split("/");
            boolean special = false;
            int withdrawals = 0;

            String type = parts[0];
            Profile person = new Profile(parts[1], parts[2]);
            double initialBalance = Double.parseDouble(parts[3]);
            Date dateOpen = new Date(Integer.parseInt(dateParts[0]),
                    Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2]));
            if (!type.equals("M")) special = Boolean.parseBoolean(parts[5]);
            else withdrawals = Integer.parseInt(parts[5]);

            boolean check;
            switch (type) {
                case "C":
                    Checking checking = new Checking(person, dateOpen, initialBalance, special);
                    check = database.add(checking);
                    if (check) output.appendText("Account opened and added to the database.\n");
                    else output.appendText("Account is already in the database.\n");
                    break;

                case "S":
                    Savings savings = new Savings(person, dateOpen, initialBalance, special);
                    check = database.add(savings);
                    if (check) output.appendText("Account opened and added to the database.\n");
                    else output.appendText("Account is already in the database.\n");
                    break;

                case "M":
                    MoneyMarket market = new MoneyMarket(person, dateOpen, initialBalance);
                    check = database.add(market);
                    if (check) {
                        database.addWithdrawals(market, withdrawals);
                        output.appendText("Account opened and added to the database.\n");
                    }
                    else output.appendText("Account is already in the database.\n");
                    break;

                default:
                    output.appendText("Please fill out all fields :)\n");
            }

    }



}
