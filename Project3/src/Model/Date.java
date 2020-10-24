package Model;
/**
 * Class for holding and validating Dates
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
     * @return return 0 if equal, 1 if greater than, or -1 is less than
     */
    @Override
    public int compareTo(Date date) {
        if (year < date.year) return -1;
        if (year > date.year) return 1;

        if (month < date.month) return -1;
        if (month > date.month) return 1;

        if (day < date.day) return -1;
        if (day > date.day) return 1;

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
     * Check if a year is a leap year
     * @return true if leap year, false if not
     */
    private boolean isLeapYear() {
        return (year % DateConsts.FOURTH_YEAR == 0)
                && (year % DateConsts.CENTENNIAL != 0)
                || (year % DateConsts.QUADRICENTENNIAL == 0);
    }

    /**
     * Check if date is a real date.
     * @return return true if date is valid, false if not valid
     */
    public boolean isValid() {
        if (year < 0) return false;
        if (month < 1 || month > DateConsts.MAX_MONTHS) return false;
        if (day < 1 || day > DateConsts.LONG_MAX_DAYS) return false;

        if (month == DateConsts.FEBRUARY) {
            if (isLeapYear()) return (day <= DateConsts.FEBRUARY_LEAP_YEAR_DAYS);
            else return (day <= DateConsts.FEBRUARY_NORMAL_DAYS);
        }

        if (month == DateConsts.APRIL
                || month == DateConsts.JUNE
                || month == DateConsts.SEPTEMBER
                || month == DateConsts.NOVEMBER) return (day <= DateConsts.SHORT_MAX_DAYS);

        return true;
    }
}