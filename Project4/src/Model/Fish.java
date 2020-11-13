package Model;

public class Fish extends Sandwich {
    private final double FISH_PRICE = 12.99;

    /**
     * Constructor for a Fish Sandwich
     */
    public Fish() {
        super();
    }

    /**
     * Calculates the price of the sandwich
     * @return the price of the sandwich
     */
    @Override
    public double price() {
        return FISH_PRICE+(super.numExtras() * PER_EXTRA);
    }

    /**
     * Makes the Sandwich into a string
     * @return the Sandwich as a string
     */
    @Override
    public String toString() {
        return String.format("Fish Sandwich: Grilled Snapper, Cilantro, Lime, %sPrice: $%.2f", super.toString(), price());
    }
}