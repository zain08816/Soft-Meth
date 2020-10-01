/**
 * @author Clarissa Hwang, Zain Ali
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    /**
     * Constructor for Date
     * @param date String in the format of mm/dd/yyyy
     */
    public Date(String date) {
        String[] parts = date.split("/");
        this.month = Integer.parseInt(parts[0]);
        this.day = Integer.parseInt(parts[1]);
        this.year = Integer.parseInt(parts[2]);
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
        return month + "/" + day + "/" + year;
    }

    /**
     * Check if date is a real date.
     * @return return true if date is valid, false if not valid
     */
    public boolean isValid() {
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Date("02/09/2020"));
    }
}