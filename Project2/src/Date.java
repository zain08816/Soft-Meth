/**
 * @author Clarissa Hwang, Zain Ali
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    /**
     * Constructor for Date
     * @param day int day of the month
     * @param month int month of the year
     * @param year int year
     */
    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * Compare dates to eachother
     * @param date date you want to compare to
     * @return return 0 if equal, 1 if greater than, or -1 is lessthan
     */
    @Override
    public int compareTo(Date date) {
        return 0;
    }

    /**
     * Converts data in class to a string
     * @return returns string in the format mm/dd/yyyy
     */
    @Override
    public String toString() {
        return "";
    }

    /**
     * Check if date is a real date.
     * @return return true if date is valid, false if not valid
     */
    public boolean isValid() {
        return false;
    }
}