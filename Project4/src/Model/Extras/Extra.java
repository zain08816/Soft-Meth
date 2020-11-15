package Model.Extras;

import javafx.scene.image.Image;

/**
 * Extra Class
 * @author Clarissa Hwang, Zain Ali
 */
public abstract class Extra {
    private double price;
    private final double EXTRA_PRICE = 1.99;

    /**
     * Constructor for extra
     */
    public Extra() {
        price = EXTRA_PRICE;
    }

    /**
     * Gets the price of extra
     * @return price of extra
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets the name of the extra
     * @return name of extra
     */
    public abstract String getName();

    /**
     * Gets the image of the extra
     * @return image of extra
     */
    public abstract Image getImage();


}
