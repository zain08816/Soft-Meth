package Model;

public class Chicken extends Sandwich {
    Ingredient friedChicken;
    Ingredient spicySauce;
    Ingredient pickles;

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
