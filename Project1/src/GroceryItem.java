/**
 * Item that contains an item's name, price, and whether it is taxable.
 * This is used to be added to the ShoppingBag
 * @author Clarissa Hwang, Zain Ali
 */
public class GroceryItem {
    private String name;
    private double price;
    private boolean taxable;

    /**
     * Constructor for GroceryItem Class.
     * @param name string name of GroceryItem
     * @param price double price of GroceryItem
     * @param taxable true if item is taxable, otherwise false
     */
    public GroceryItem (String name, double price, boolean taxable) {
        this.name = name;
        this.price = price;
        this.taxable = taxable;
    }

    /**
     * Returns the price variable.
     * @return returns price of item
     */
    public double price () {
        return this.price;
    }

    /**
     * Returns the taxable variable.
     * @return returns true if item is taxable, false if not
     */
    public boolean isTaxable () {
        return this.taxable;
    }

    /**
     * Compare two GroceryItem objects to see if they are equal.
     * @param obj object you that needs to be compared to GroceryItem
     * @return true if the objects are the same, false if not
     */
    public boolean equals (Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof GroceryItem) {
            GroceryItem compare = (GroceryItem) obj;

            return this.name.equals(compare.name) &&
                    this.price == compare.price &&
                    this.taxable == compare.taxable;
        }

        return false;
    }

    /**
     * Assembles GroceryItem into a string.
     * @return returns string containing the information in the GroceryItem
     */
    public String toString () {
        String tax = taxable ? "is taxable" : "tax free";
        return String.format("%s: $%.2f : %s", name, price, tax);
    }

}
