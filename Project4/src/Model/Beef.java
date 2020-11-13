package Model;

public class Beef extends Sandwich {
    private final double BEEF_PRICE = 10.99;

    /**
     * Constructor for a Beef sandwich
     */
    public Beef() {
        super();
    }

    /**
     * Calculates the price of the sandwich
     * @return the price of the sandwich
     */
    @Override
    public double price() {
        return BEEF_PRICE+(super.numExtras() * PER_EXTRA);
    }

    /**
     * Makes the sandwich into a string
     * @return the sandwich as a string
     */
    @Override
    public String toString() {
        return String.format("Beef Sandwich: Roast Beef, Provolone Cheese, Mustard, %sPrice: $%.2f", super.toString(), price());
    }
}
