public class GroceryItem {
    private String name;
    private double price;
    private boolean taxable;

    /**
     * @param name
     * @param price
     * @param taxable
     */
    public GroceryItem (String name, double price, boolean taxable) {
        this.name = name;
        this.price = price;
        this.taxable = taxable;
    }

    /**
     *
     * @return
     */
    public double price () {
        return this.price;
    }

    /**
     *
     * @return
     */
    public boolean isTaxable () {
        return this.taxable;
    }

    /**
     *
     * @param obj
     * @return
     */
    public boolean equals (Object obj) {
        if (this == obj) {
            return true;
        }

        GroceryItem compare = (GroceryItem) obj;

        if (this.name.equals(compare.name) && this.price == compare.price && this.taxable == compare.taxable) {
            return true;
        }

        return false;
    }

    public String toString () {
        String tax = taxable ? "is taxable" : "tax free";
        return String.format("%s: $%.2f : %s", name, price, tax);
    }

}
