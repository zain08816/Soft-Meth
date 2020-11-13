package Model;

public class OrderLine {
    private int lineNumber;
    private Sandwich sandwich;
    private double price;


    /**
     * Constructor for OrderLine
     * @param sandwich Sandwich that was built
     */
    public OrderLine(Sandwich sandwich) {
        this.sandwich = sandwich;
        this.price = sandwich.price();
    }

    /**
     * Parameterized Constructor for OrderLine
     * @param lineNumber line number
     */
    public OrderLine(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    /**
     * Get the price
     * @return price of sandwich
     */
    public double getPrice() {
        return price;
    }

    /**
     * Get current line number
     * @return line number
     */
    public int getLineNumber() {
        return lineNumber;
    }

    /**
     * Set lineNumber
     * @param lineNumber number that you want to be the new line number
     */
    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    /**
     * Turns the object into a string
     * @return the object as a string
     */
    @Override
    public String toString() {
        return String.format("%d %s", lineNumber, sandwich.toString());
    }
}
