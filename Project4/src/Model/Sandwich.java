package Model;

import Model.Extras.Extra;

import java.util.ArrayList;

public abstract class Sandwich implements Customizable {
    static final int MAX_EXTRAS = 6;
    static final double PER_EXTRA = 1.99;
    protected ArrayList<Extra> extras;

    public Sandwich() {
        extras = new ArrayList<Extra>();
    }

    /**
     * Makes the sandwich into a string
     * @return Sandwich as a string
     */
    @Override
    public String toString() {
        if (extras.isEmpty()) return "";
        String buffer = "Extra: ";
        for (Extra extra : extras) {
            buffer += extra.getName() + ", ";
        }
        return buffer;
    }

    /**
     * Add an extra to the sandwich
     * @param obj Extra that is being added
     * @return true if success, false if fail
     */
    @Override
    public boolean add(Object obj) {
        if (extras.size() >= MAX_EXTRAS) return false;
        Extra addExtra = (Extra) obj;
        if (extras.contains(addExtra)) return false;
        extras.add(addExtra);
        return true;
    }

    /**
     * remove an extra to the sandwich
     * @param obj Extra that is being removed
     * @return true if success, false if fail
     */
    @Override
    public boolean remove(Object obj) {
        if (extras.isEmpty()) return false;
        Extra removeExtra = (Extra) obj;
        if (!extras.contains(removeExtra)) return false;
        extras.remove(obj);
        return false;
    };

    /**
     * The price of the sandwich
     * @return Double the price of the sandwich
     */
    public abstract double price();

    /**
     * Gives the number of extras that are on the sandwich
     * @return the number of extras that are on the sandwich
     */
    public int numExtras() {
        return extras.size();
    }
}
