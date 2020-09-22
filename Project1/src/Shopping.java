/**
 * Runs the project and manages input/output
 * @author Clarissa Hwang, Zain Ali
 */
import java.util.Scanner;

public class Shopping {
    private ShoppingBag bag = new ShoppingBag();

    /**
     * Adds specified item to the ShoppingBag
     * @param name String name of item being added
     * @param price double price of item being added
     * @param taxable boolean if item being added is taxable
     */
    private void add(String name, double price, boolean taxable) {
        GroceryItem new_item = new GroceryItem(name, price, taxable);
        bag.add(new_item);
        System.out.printf("%s added to the bag.%n", name);
    }

    /**
     * Removes specified item to the ShoppingBag
     * @param name String name of item being removed
     * @param price double price of item being removed
     * @param taxable boolean if item being removed is taxable
     */
    private void remove(String name, double price, boolean taxable ){
        GroceryItem remove = new GroceryItem(name, price, taxable);
        boolean removed = bag.remove(remove);

        if (removed) {
            System.out.printf("%s %.2f removed.%n", name, price);
        } else {
            System.out.println("Unable to remove, this item is not in the bag.");
        }
    }

    /**
     * Prints out the contents of the ShoppingBag
     */
    private void display() {
        if (bag.getSize() != 0) {
            String item = bag.getSize() == 1 ? "item" : "items";
            System.out.printf("**You have %d %s in the bag.%n", bag.getSize(), item);
            bag.print();
            System.out.println("**End of list");
        } else {
            System.out.println("The bag is empty!");
        }
    }

    /**
     * Prints out the contents of the ShoppingBag and clears the bag.
     * Sales total, Sales tax, and total amount payed is also shown.
     */
    private void checkOut() {
        if (bag.getSize() == 0) {
            System.out.println("Unable to check out, the bag is empty!");
        } else {
            String item = bag.getSize() == 1 ? "item" : "items";
            System.out.printf("**Checking out %d %s.%n", bag.getSize(), item);
            bag.print();
            System.out.printf("*Sales total: $%.2f%n", bag.salesPrice());
            System.out.printf("*Sales tax: $%.2f%n", bag.salesTax());
            System.out.printf("*Total amount paid: $%.2f%n", bag.salesPrice() + bag.salesTax());
            bag = new ShoppingBag();
        }
    }


    /**
     * Runs the program and handles input/output
     */
    public void run() {
        System.out.println("Let's start shopping!");
        Scanner reader = new Scanner(System.in);

        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            if (line.equals("")) continue;
            char command = line.charAt(0);
            String[] segments;

            switch(command) {
                case 'A':
                    segments = line.split("\\s+");
                    add(segments[1], Double.parseDouble(segments[2]), Boolean.parseBoolean(segments[3]));
                    break;

                case 'R':
                    segments = line.split("\\s+");
                    remove(segments[1], Double.parseDouble(segments[2]), Boolean.parseBoolean(segments[3]));
                    break;

                case 'P':
                    display();
                    break;

                case 'C':
                    checkOut();
                    break;

                case 'Q':
                    if (bag.getSize() != 0) {
                        checkOut();
                    }
                    System.out.println("Thanks for shopping with us!");
                    reader.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid command!");
            }
        }

        reader.close();
        System.exit(0);
    }

}
