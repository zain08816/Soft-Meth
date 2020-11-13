package Model;

import java.util.ArrayList;

public class Order implements Customizable {
    public static int lineNumber;
    private ArrayList<OrderLine> orderlines;

    /**
     * Constructor for order
     */
    public Order() {
        orderlines = new ArrayList<OrderLine>();
        lineNumber = 0;
    }

    /**
     * Add orderline to Order
     * @param obj orderline to add
     * @return true if added, false if not
     */
    @Override
    public boolean add(Object obj) {
        OrderLine line = (OrderLine) obj;
        line.setLineNumber(orderlines.size()+1);
        orderlines.add(line);
        return true;
    }

    /**
     * remove orderline from number
     * @param obj orderline to remove
     * @return true if removed, false if not
     */
    @Override
    public boolean remove(Object obj) {
        if (orderlines.isEmpty()) return false;
        OrderLine line = (OrderLine) obj;
        int toRemove = line.getLineNumber();
        if (toRemove-1 > orderlines.size()) return false;
        if (orderlines.size() == 1) {
            orderlines.remove(0);
            return true;
        }
        for (int i = toRemove; i < orderlines.size(); i += 1) {
            orderlines.get(i).setLineNumber(i);
        }
        orderlines.remove(toRemove - 1);
        return true;
    }

    /**
     * Get all the orderlines
     * @return an Arraylist of orderlines
     */
    public ArrayList<OrderLine> getOrders() {
        return orderlines;
    }
}
