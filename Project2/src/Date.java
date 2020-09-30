/**
 * @author Clarissa Hwang, Zain Ali
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    /**
     * return 0, 1, or -1
     * @param date
     * @return
     */
    @Override
    public int compareTo(Date date) {
        return 0;
    }

    /**
     * in the format mm/dd/yyyy
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
    public boolean isValid() {
        return false;
    }
}