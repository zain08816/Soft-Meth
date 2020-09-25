/**
 * Holds all the items that are put into the bag.
 * It is the interface to manipulate the bag.
 * @author Clarissa Hwang, Zain Ali
 */
public class ShoppingBag {
    final int STARTING_BAG_SIZE = 5;
    final double SALES_TAX = 0.06625;
    private GroceryItem[] bag;
    private int size;

    /**
     * Constructor for ShoppingBag Class
     */
    public ShoppingBag() {
        size = 0;
        bag = new GroceryItem[STARTING_BAG_SIZE];
    }

    /**
     * Returns number of items in the bag
     * @return returns number of items in bag
     */
    public int getSize() {
        return size;
    }

    /**
     * Helper function used to find the index of a specified GroceryItem
     * Finds first instance of the specified GroceryItem in the ShoppingBag.
     * @param item GroceryItem that you want the index of
     * @return returns index of found item. returns -1 if not found
     */
    private int find (GroceryItem item) {
        for (int i = 0; i < size; i += 1) {
            if (item.equals(bag[i])) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Doubles the size of the current bag
     */
    private void grow() {
        int new_size = bag.length * 2;
        GroceryItem[] newBag = new GroceryItem[new_size];

        for (int i = 0; i < bag.length; i += 1) {
            newBag[i] = bag[i];
        }

        bag = newBag;
    }

    /**
     * Adds an element into the ShoppingBag. Grows the ShoppingBag if
     * there is not enough room to add.
     * @param item GroceryItem that you want to add to the ShoppingBag
     */
    public void add(GroceryItem item) {
        if (size >= bag.length) {
            grow();
        }
        bag[size] = item;
        size += 1;
    }

    /**
     *
     * @param item GroceryItem that you want to remove from ShoppingBag
     * @return returns true if successfully removed, returns false if not in bag
     */
    public boolean remove(GroceryItem item) {
        int foundItem = find(item);

        if (foundItem != -1) {
            bag[foundItem] = bag[size - 1];
            bag[size - 1] = null;
            size -= 1;
            return true;
        }

        return false;
    }

    /**
     * Sums the prices of items in the ShoppingBag
     * @return double with the total of the prices of the items in the ShoppingBag
     */
    public double salesPrice() {
        double priceTotal = 0;

        for (int i = 0; i < size; i += 1) {
            priceTotal += bag[i].price();
        }

        return priceTotal;
    }

    /**
     * Calculates the total of tax needed to be payed for all
     * taxable items in the ShoppingBag
     * @return double with the total of all sales tax needed to be payed on item in ShoppingBag
     */
    public double salesTax() {
        double taxTotal = 0;

        for (int i = 0; i < size; i += 1) {
            if (bag[i].isTaxable()) {
                taxTotal += bag[i].price() * SALES_TAX;
            }
        }

        return taxTotal;
    }

    /**
     * Prints out the contents of the shopping bag.
     * One item per line
     */
    public void print() {
        for (int i = 0; i < size; i += 1) {
            System.out.println("\u2022"+bag[i]);
        }
    }


    /**
     * Testbed main
     */
    public static void main(String[] args) {
        ShoppingBag testBag = new ShoppingBag();

        GroceryItem item1 = new GroceryItem("test1", 1, true);
        GroceryItem item2 = new GroceryItem("test2", 2, true);
        GroceryItem item3 = new GroceryItem("test3", 3, false);
        GroceryItem item4 = new GroceryItem("test4", 4, false);
        GroceryItem item5 = new GroceryItem("test5", 1, true);
        GroceryItem item6 = new GroceryItem("test6", 2, true);


        System.out.println("Test Case 1: (Adding item1)");
        System.out.println("Expected:");
        System.out.println("test1: $1.00 : is taxable");
        System.out.println("Result:");
        testBag.add(item1);
        testBag.print();

        System.out.println("Test Case 2: (Adding item2)");
        System.out.println("Expected:");
        System.out.println("test1: $1.00 : is taxable");
        System.out.println("test2: $2.00 : is taxable");
        System.out.println("Result:");
        testBag.add(item2);
        testBag.print();

        System.out.println("Test Case 3: (Growing bag)");
        System.out.println("Expected:");
        System.out.println("Current bag length: 5 | Current items in bag: 2");
        System.out.println("Current bag length: 10 | Current items in bag: 6");
        System.out.println("Result:");
        System.out.printf("Current bag length: %d | Current items in bag: %d%n", testBag.bag.length, testBag.getSize());
        testBag.add(item3);
        testBag.add(item4);
        testBag.add(item5);
        testBag.add(item6);
        System.out.printf("Current bag length: %d | Current items in bag: %d%n", testBag.bag.length, testBag.getSize());


        System.out.println("Test Case 4: (Removing item3 (middle item))");
        System.out.println("Expected:");
        System.out.printf("test1: $1.00 : is taxable %ntest2: $2.00 : is taxable %ntest6: $2.00 : is taxable %ntest4: $4.00 : tax free %ntest5: $1.00 : is taxable%n");
        System.out.println("Result:");
        testBag.remove(item3);
        testBag.print();

        System.out.println("Test Case 5: (Removing item5 (last item))");
        System.out.println("Expected:");
        System.out.printf("test1: $1.00 : is taxable %ntest2: $2.00 : is taxable %ntest6: $2.00 : is taxable %ntest4: $4.00 : tax free %n");
        System.out.println("Result:");
        testBag.remove(item5);
        testBag.print();

        System.out.println("Test Case 6: (Calculating Sales Tax)");
        System.out.println("Expected:");
        System.out.printf("Sales tax $%.2f%n", 0.33);
        System.out.println("Result:");
        System.out.printf("Sales tax $%.2f%n", testBag.salesTax());

    }
}
