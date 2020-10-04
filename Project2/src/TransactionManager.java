import java.util.Scanner;

/**
 * Runs the project and manages input/output
 * @author Clarissa Hwang, Zain Ali
 */
public class TransactionManager {
    private final String ACCOUNT_TYPES= "CSM";
    private final String PRINT_TYPES = "ADN";
    private AccountDatabase database;

    /**
     * Constructor for TransactionManager
     */
    public TransactionManager () {
        database = new AccountDatabase();
    }

    /**
     * Open an account under a specified name and amount
     * @param type Type of account you want to open
     * @param name Profile name of account opener
     * @param amount Double initial amount to be in account
     * @param dateOpen Date account is opened
     * @param special special flag for Direct deposit and special savings
     */
    private void open(char type, Profile name, double amount, Date dateOpen, boolean special) {
        boolean check;
        switch (type) {
            case 'C':
                Checking checking = new Checking(name, dateOpen, amount, special);
                check = database.add(checking);
                if (check) System.out.println("Account opened and added to the database.");
                else System.out.println("Account is already in the database.");
                break;

            case 'S':
                Savings savings = new Savings(name, dateOpen, amount, special);
                check = database.add(savings);
                if (check) System.out.println("Account opened and added to the database.");
                else System.out.println("Account is already in the database.");
                break;

            case 'M':
                MoneyMarket market = new MoneyMarket(name, dateOpen, amount);
                check = database.add(market);
                if (check) System.out.println("Account opened and added to the database.");
                else System.out.println("Account is already in the database.");
                break;
        }

    }

    /**
     * Close an account under a name
     * @param type type of account being closed
     * @param name Profile name of account holder being closed
     */
    private void close(char type, Profile name) {
        boolean check;
        switch (type) {
            case 'C':
                Checking checking = new Checking(name, new Date("0/0/0"), 0.0, false);
                check = database.remove(checking);
                if (check) System.out.println("Account closed and removed from the database.");
                else System.out.println("Account does not exist.");
                break;

            case 'S':
                Savings savings = new Savings(name, new Date("0/0/0"), 0.0, false);
                check = database.remove(savings);
                if (check) System.out.println("Account closed and removed from the database.");
                else System.out.println("Account does not exist.");
                break;

            case 'M':
                MoneyMarket market = new MoneyMarket(name, new Date("0/0/0"), 0.0);
                check = database.remove(market);
                if (check) System.out.println("Account closed and removed from the database.");
                else System.out.println("Account does not exist.");
                break;
        }
    }

    /**
     * Deposit money under a specific account
     * @param type type of account being deposited into
     * @param name name of account holder being deposited into
     * @param amount amount being deposited
     */
    private void deposit(char type, Profile name, double amount) {
        boolean check;
        switch (type) {
            case 'C':
                Checking checking = new Checking(name, new Date("0/0/0"), 0.0, false);
                check = database.deposit(checking, amount);
                if (check) System.out.printf("%.2f deposited to account.%n", amount);
                else System.out.println("Account does not exist.");
                break;

            case 'S':
                Savings savings = new Savings(name, new Date("0/0/0"), 0.0, false);
                check = database.deposit(savings, amount);
                if (check) System.out.printf("%.2f deposited to account.%n", amount);
                else System.out.println("Account does not exist.");
                break;

            case 'M':
                MoneyMarket market = new MoneyMarket(name, new Date("0/0/0"), 0.0);
                check = database.deposit(market, amount);
                if (check) System.out.printf("%.2f deposited to account.%n", amount);
                else System.out.println("Account does not exist.");
                break;
        }
    }

    /**
     * Withdraw money from a specific account
     * @param type type of account
     * @param name Profile name of account holder
     * @param amount amount being withdrawn
     */
    private void withdraw(char type, Profile name, double amount) {
        int check;
        switch (type) {
            case 'C':
                Checking checking = new Checking(name, new Date("0/0/0"), 0.0, false);
                check = database.withdrawal(checking, amount);
                if (check == 0) System.out.printf("%.2f withdrawn from account.%n", amount);
                else if (check == 1) System.out.println("Insufficient funds.");
                else if (check == -1) System.out.println("Account does not exist.");
                break;

            case 'S':
                Savings savings = new Savings(name, new Date("0/0/0"), 0.0, false);
                check = database.withdrawal(savings, amount);
                if (check == 0) System.out.printf("%.2f withdrawn from account.%n", amount);
                else if (check == 1) System.out.println("Insufficient funds.");
                else if (check == -1) System.out.println("Account does not exist.");
                break;

            case 'M':
                MoneyMarket market = new MoneyMarket(name, new Date("0/0/0"), 0.0);
                check = database.withdrawal(market, amount);
                if (check == 0) System.out.printf("%.2f withdrawn from account.%n", amount);
                else if (check == 1) System.out.println("Insufficient funds.");
                else if (check == -1) System.out.println("Account does not exist.");
                break;
        }
    }

    /**
     * Print all accounts based on specified sorting
     * @param type order that accounts are printed
     */
    private void print(char type) {

        if (database.getSize() == 0) {
            System.out.println("Database is empty");
            return;
        }

        switch (type) {
            case 'A':
                System.out.println("--Listing accounts in the database--");
                database.printAccounts();
                System.out.println("--end of listing--");
                break;

            case 'D':
                System.out.println(" ");
                System.out.println("--Printing accounts by date opened--");
                database.printByDateOpen();
                System.out.println("--end of printing--");
                break;

            case 'N':
                System.out.println(" ");
                System.out.println("--Printing accounts by last name--");
                database.printByLastName();
                System.out.println("--end of printing--");
                break;
        }


    }

    /**
     * Quit program
     */
    private void quit() {
        System.out.println("Transaction processing completed.");
        System.exit(0);
        return;
    }

    /**
     * Read though inputs and drive functions
     */
    public void run() {
        System.out.println("Transaction processing starts.....");
        Scanner reader = new Scanner(System.in);



        while(reader.hasNextLine()){

            String line = reader.nextLine();
            if (line.equals("")) continue;
            Scanner lineReader = new Scanner(line).useDelimiter(" ");

            String command = lineReader.next();
            char commandSegment = command.charAt(0);
            if (commandSegment == 'Q') quit();

            char type;
            Profile profile;
            double amount;
            Date date;
            boolean special = false;

            try {
                type = command.charAt(1);
            } catch (Exception exception) {
                System.out.printf("Command '%s' is not supported!%n", command);
                continue;
            }

            if (commandSegment == 'P') {
                if (PRINT_TYPES.indexOf(type) == -1) {
                    System.out.printf("Command '%s' is not supported!%n", command);
                    continue;
                }
            } else {
                if (ACCOUNT_TYPES.indexOf(type) == -1) {
                    System.out.printf("Command '%s' is not supported!%n", command);
                    continue;
                }
            }


            switch (commandSegment) {

                case 'O': //Open Account
                    //Handle inputs
                    try {
                        profile = new Profile(lineReader.next(), lineReader.next());
                        amount = lineReader.nextDouble();
                        date = new Date(lineReader.next());
                        if (!date.isValid()){
                            System.out.printf("%s is not a valid date!%n", date.toString());
                            break;
                        }
                        if (type != 'M') special = lineReader.nextBoolean();
                        open(type, profile, amount, date, special);
                    } catch (Exception exception) {
                        System.out.println("Input data type mismatch.");
                    }

                    break;

                case 'C': //Close Account
                    //Handle inputs
                    try {
                        profile = new Profile(lineReader.next(), lineReader.next());
                        close(type, profile);
                    } catch (Exception exception) {
                        System.out.println("Input data type mismatch.");
                    }

                    break;

                case 'D': //Deposit
                    //Handle inputs
                    try {
                        profile = new Profile(lineReader.next(), lineReader.next());
                        amount = lineReader.nextDouble();
                        deposit(type, profile, amount);
                    } catch (Exception exception) {
                        System.out.println("Input data type mismatch.");
                    }

                    break;

                case 'W': // Withdraw
                    //Handle inputs
                    try {
                        profile = new Profile(lineReader.next(), lineReader.next());
                        amount = lineReader.nextDouble();
                        withdraw(type, profile, amount);
                    } catch (Exception exception) {
                        System.out.println("Input data type mismatch.");
                    }

                    break;

                case 'P':
                    print(type);
                    break;


                default:
                    System.out.printf("Command '%s' is not supported!%n", command);
                    break;

            }
        }

    }
}
