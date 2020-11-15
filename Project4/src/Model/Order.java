package Model;

import java.util.ArrayList;

/**
 * Order Class
 * @author Clarissa Hwang, Zain Ali
 */
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
        OrderLine line = new OrderLine((OrderLine) obj);
        line.setLineNumber(lineNumber+1);
        orderlines.add(line);
        lineNumber += 1;
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
        orderlines.remove(lineNumber - 1);
        lineNumber -= 1;
        return true;
    }

    /**
     * Get all the orderlines
     * @return an Arraylist of orderlines
     */
    public ArrayList<OrderLine> getOrders() {
        return orderlines;
    }

    public String exportString() {
        String buffer = "~~~~~~~~~~========== ORDER DETAILS ==========~~~~~~~~~~\n";
        if (orderlines.isEmpty()) {
            buffer += "Please order something... :)\n";
            buffer += "~~~~~~~~~~===================================~~~~~~~~~~\n";
            buffer += String.format("PRICE: $%.2f\n", orderTotal());
            buffer += "TAX: WAIVED\n";
            buffer += "TIP: $0.00\n";
            return buffer;
        } else {
            for (OrderLine order: orderlines) {
                buffer += order.toString() + "\n";
            }
        }
        buffer += "~~~~~~~~~~===================================~~~~~~~~~~\n";
        buffer += String.format("PRICE: $%.2f\n", orderTotal());
        buffer += "TAX: WAIVED\n";
        buffer += "TIP: $0.00\n";
        return buffer;
    }

    /**
     * Get total price
     * @return total order price
     */
    public double orderTotal() {
        double total = 0;
        for (OrderLine order: orderlines) {
            total += order.getPrice();
        }
        return total;
    }

    /**
     * Deletes everything in the order
     */
    public void clear() {
        orderlines.clear();
        lineNumber = 0;
    }
}
