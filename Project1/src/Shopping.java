import java.util.Scanner;

public class Shopping {
    private ShoppingBag bag = new ShoppingBag(); 

    private void add(String name, double price, boolean taxable) {
        


        GroceryItem new_item = new GroceryItem(name, price, taxable);
        bag.add(new_item);
    }

    private void remove(String name, double price, boolean taxable ){
        GroceryItem remove = new GroceryItem(name, price, taxable);
        bag.remove(remove);

    }

    private void display() {
        bag.print();
    }

    private void check_out() {
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
