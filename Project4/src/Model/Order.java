package Model;

import java.util.ArrayList;

public class Order implements Customizable {
    public static int lineNumber = 0;
    private ArrayList<OrderLine> orderlines;

    if (orderlines.isEmpty()) {
        lineNumber = 0;
    }

    @Override
    public boolean add(Object obj) {
        orderlines.add((OrderLine) obj);
        return false;
    }

    @Override
    public boolean remove(Object obj) {
        orderlines.remove((OrderLine) obj);
        return false;
    }
}
