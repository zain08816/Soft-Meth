package Model.Extras;

import javafx.scene.image.Image;

public abstract class Extra {
    private double price;

    public Extra() {
        price = 1.99;
    }

    public abstract String getName();
    public abstract Image getImage();


}
