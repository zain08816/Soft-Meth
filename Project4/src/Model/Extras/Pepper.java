package Model.Extras;

import javafx.scene.image.Image;

/**
 * Pepper Extra
 * @author Clarissa Hwang, Zain Ali
 */
public class Pepper extends Extra {
    private final Image EXTRA_IMAGE = new Image("Images/pepper.png", 150, 50, false, false);
    private final String EXTRA_NAME = "Jalapeno Pepper";

    /**
     * Constructor for Pepper
     */
    public Pepper() {
        super();
    }

    /**
     * Gets the name of the extra
     * @return name of extra
     */
    @Override
    public String getName() {
        return EXTRA_NAME;
    }

    /**
     * Gets the image of extra
     * @return image of extra
     */
    @Override
    public Image getImage() {
        return EXTRA_IMAGE;
    }

    /**
     * Check if two extras are equal
     * @param obj Extra to check
     * @return true if same, false if not
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return obj != null && getClass() == obj.getClass();
    }

    /**
     * Get the string of the extra
     * @return string of extra
     */
    @Override
    public String toString() {
        return EXTRA_NAME;
    }
}
