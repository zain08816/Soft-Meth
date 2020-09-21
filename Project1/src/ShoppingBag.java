

public class ShoppingBag {
    int START_BAG_SIZE = 2;
    double SALES_TAX = 0.06625;
    private GroceryItem[] bag;
    private int size;

    /**
     * Constructor
     */
    public ShoppingBag() {
        this.size = 0;
        this.bag = new GroceryItem[START_BAG_SIZE];
    }

    public int getSize() {
        return this.size
    }

    /**
     * Helper function used to find the index of the the
     * first instance of the specified GroceryItem in the ShoppingBag.
     * @param item GroceryItem that you want the index of
     * @return returns index of found item. returns -1 if not found
     */
    private int find(GroceryItem item) {
        for (int i = 0; i < this.size; i += 1) {
            if (item.equals(this.bag[i])) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Doubles the size of the current bag
     */
    private void grow() {
        int new_size = this.bag.length * 2;
        GroceryItem[] new_bag = new GroceryItem[new_size];

        for (int i = 0; i < this.bag.length; i += 1) {
            new_bag[i] = this.bag[i];
        }

        this.bag = new_bag;
    }

    /**
     * Adds and element into the ShoppingBag. Grows the ShoppingBag if
     * there is not enough rooom to add.
     * @param item GroceryItem that you want to add to the ShoppingBag
     */
    public void add(GroceryItem item) {
        if (this.size >= this.bag.length) {
            grow();
        }
        this.bag[this.size] = item;
        this.size += 1;
    }

    /**
     *
     * @param item GroceryItem that you want to remove from ShoppingBag
     * @return returns true if successfully removed, returns false if not in bag
     */
    public boolean remove(GroceryItem item) {
        int found_item = find(item);

        if (found_item != -1) {
            this.bag[found_item] = this.bag[this.size - 1];
            this.bag[this.size - 1] = null;
            this.size -= 1;
            return true;
        }

        return false;
    }

    /**
     * Sums the prices of items in the ShoppingBag
     * @return double with the total of the prices of the items in the ShoppingBag
     */
    public double salesPrice() {
        double price_total = 0;

        for (int i = 0; i < this.size; i += 1) {
            price_total += this.bag[i].price();
        }

        return price_total;
    }

    /**
     * Calculates the total of tax needed to be payed for all
     * taxable items in the ShoppingBag
     * @return double with the total of all sales tax needed to be payed on item in ShoppingBag
     */
    public double salesTax() {
        double tax_total = 0;

        for (int i = 0; i < this.size; i += 1) {
            if (this.bag[i].isTaxable()) {
                tax_total += this.bag[i].price() * SALES_TAX;
            }
        }

        return tax_total;
    }

    /**
     * Prints out the contents of the shopping bag.
     * One item per line
     */
    public void print() {
        for (int i = 0; i < this.size; i += 1) {
            System.out.println(this.bag[i]);
        }
    }





    public static void main(String[] args) {
        ShoppingBag test_bag = new ShoppingBag();

        test_bag.print();
        System.out.println(test_bag.salesPrice());
        System.out.println(test_bag.salesTax());

        GroceryItem item1 = new GroceryItem("test1", 1, true);
        GroceryItem item2 = new GroceryItem("test2", 2, true);
        GroceryItem item3 = new GroceryItem("test3", 3, false);
        GroceryItem item4 = new GroceryItem("test4", 4, false);
        GroceryItem item5 = new GroceryItem("test5", 1, true);
        GroceryItem item6 = new GroceryItem("test6", 2, true);
        GroceryItem item7 = new GroceryItem("test7", 3, false);
        GroceryItem item8 = new GroceryItem("test8", 4, false);

        test_bag.add(item1);
        test_bag.print();
        System.out.println(test_bag.salesPrice());
        System.out.println(test_bag.salesTax());

        test_bag.add(item2);
        test_bag.add(item3);
        test_bag.add(item4);
        test_bag.add(item5);
        test_bag.add(item6);
        test_bag.add(item7);
        test_bag.add(item8);
        test_bag.print();
        System.out.println(test_bag.salesPrice());
        System.out.println(test_bag.salesTax());

        test_bag.remove(item2);
        test_bag.remove(item7);
        test_bag.print();
        System.out.println(test_bag.salesTax());



    }
}
