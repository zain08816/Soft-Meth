package Model;

import javafx.scene.image.Image;

/**
 * Chicken Sandwich
 * @author Clarissa Hwang, Zain Ali
 */
public class Chicken extends Sandwich {
    private final double CHICKEN_PRICE = 8.99;
    private final Image CHICKEN_IMAGE = new Image("Images/chicken.png", 150, 50, false, false);

    /**
     * Gets the image of chicken
     * @return chicken image
     */
    public Image getImage() {
        return CHICKEN_IMAGE;
    }

    /**
     * Constructor for a Chicken Sandwich
     */
    public Chicken() {
        super();
    }

    /**
     * Calculates the price of the sandwich
     * @return the price of the sandwich
     */
    @Override
    public double price() {
        return CHICKEN_PRICE+(super.numExtras() * PER_EXTRA);
    }

    /**
     * Makes the Sandwich into a string
     * @return the Sandwich as a string
     */
    @Override
    public String toString() {
        return String.format("Chicken Sandwich: Fried Chicken, Spicy Sauce, Pickles, %sPrice: $%.2f", super.toString(), price());
    }
}
