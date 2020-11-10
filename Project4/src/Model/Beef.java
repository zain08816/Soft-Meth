package Model;

public class Beef extends Sandwich {
    Ingredient roastBeef;
    Ingredient provoloneCheese;
    Ingredient mustard;

    @Override
    public double price() {
        return 0;
    }

    @Override
    public boolean add(Object obj) {
        return false;
    }

    @Override
    public boolean remove(Object obj) {
        return false;
    }
}
