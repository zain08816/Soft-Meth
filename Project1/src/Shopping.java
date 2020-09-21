import java.util.Scanner;

public class Shopping {
    private ShoppingBag bag = new ShoppingBag(); 

    private void add(String name, double price, boolean taxable) {
        


        GroceryItem new_item = new GroceryItem(name, price, taxable);
        bag.add(new_item);
    }

    private void remove(String name, double price, boolean taxable ){
        GroceryItem remove = new GroceryItem(name, price, taxable);
        Boolean check = bag.remove(remove);
        if (check == true) {
            String remove_statement = String.format("%s %.2f removed.", name, price);
            System.out.println(remove_statement);
        } else {
            System.out.println("Unable to remove, this item is not in the bag.");
        }
    }

    private void display() {
        if (bag.getSize() != 0) {
            String item = bag.getSize() == 1 ? "item" : "items";
            String result = String.format("**You have %d %s in the bag.", bag.getSize(), item);
            System.out.println(result);
            bag.print();
            System.out.println("**End of list");
        } else {
            System.out.println("The bag is empty!");
        }
    }

    private void check_out() {
        if (bag.getSize() == 0) {
            System.out.println("Unable to check out, the bag is empty!");
        } else {
            String item = bag.getSize() == 1 ? "item" : "items";
            String checkOut = String.format("**Checking out %d %s.", bag.getSize(), item);
            System.out.println(checkOut);
            bag.print();
            System.out.println(String.format("*Sales total: $%.2f", bag.salesPrice()));
            System.out.println(String.format("*Sales tax: $%.2f", bag.salesTax()));
            System.out.println(String.format("*Total amount paid: $%.2f", bag.salesPrice() + bag.salesTax()));

        }


    }

    private void quit() {

    }

    public void run() {
        Scanner reader = new Scanner(System.in);

        while (reader.hasNextLine()) {
            String line = reader.nextLine();
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

                    break;

                case 'Q':
                    if (bag.getSize() == 0) {
                        System.out.println();
                    }
                    break;
                
                default:
                    System.out.println();
            }
             
        }
        reader.close();

    }

}
