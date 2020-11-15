package Test;

import Model.*;
import javafx.embed.swing.JFXPanel;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    private Order testOrder;

    /**
     * Setup blank Order and load javafx to avoid crash
     */
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        testOrder = new Order();
        JFXPanel startjfx = new JFXPanel();
    }

    /**
     * Test Add method
     */
    @org.junit.jupiter.api.Test
    void add() {
        assertTrue(true);
        OrderLine test1 = new OrderLine(new Chicken());
        OrderLine test2 = new OrderLine(new Beef());
        OrderLine test3 = new OrderLine(new Fish());
        assertEquals(0, testOrder.getOrders().size());
        testOrder.add(test1);
        assertEquals(1, testOrder.getOrders().size());
        testOrder.add(test2);
        assertEquals(2, testOrder.getOrders().size());
        testOrder.add(test3);
        assertEquals(3, testOrder.getOrders().size());
        assertEquals(testOrder.getOrders().toString(),"[1 Chicken Sandwich: Fried Chicken, Spicy Sauce, Pickles, Price: $8.99, 2 Beef Sandwich: Roast Beef, Provolone Cheese, Mustard, Price: $10.99, 3 Fish Sandwich: Grilled Snapper, Cilantro, Lime, Price: $12.99]" );

    }

    /**
     * Test Remove method
     */
    @org.junit.jupiter.api.Test
    void remove() {
        OrderLine test1 = new OrderLine(new Chicken());
        OrderLine test2 = new OrderLine(new Beef());
        OrderLine test3 = new OrderLine(new Fish());
        testOrder.add(test1);
        testOrder.add(test2);
        testOrder.add(test3);
        assertTrue(testOrder.remove(testOrder.getOrders().get(1)));
        assertEquals(2, testOrder.getOrders().get(1).getLineNumber());
        assertEquals("[1 Chicken Sandwich: Fried Chicken, Spicy Sauce, Pickles, Price: $8.99, 2 Fish Sandwich: Grilled Snapper, Cilantro, Lime, Price: $12.99]", testOrder.getOrders().toString() );
        testOrder.remove(testOrder.getOrders().get(0));
        testOrder.remove(testOrder.getOrders().get(0));
        assertFalse(testOrder.remove(test1));
    }

    /**
     * Test getting the ArrayList of orderlines
     */
    @org.junit.jupiter.api.Test
    void getOrders() {
        OrderLine test1 = new OrderLine(new Chicken());
        OrderLine test2 = new OrderLine(new Beef());
        OrderLine test3 = new OrderLine(new Fish());
        testOrder.add(test1);
        testOrder.add(test2);
        testOrder.add(test3);
        assertEquals(testOrder.getOrders().size(), 3);
        assertEquals(testOrder.getOrders().toString(),"[1 Chicken Sandwich: Fried Chicken, Spicy Sauce, Pickles, Price: $8.99, 2 Beef Sandwich: Roast Beef, Provolone Cheese, Mustard, Price: $10.99, 3 Fish Sandwich: Grilled Snapper, Cilantro, Lime, Price: $12.99]" );
        testOrder.remove(testOrder.getOrders().get(1));
        assertEquals("[1 Chicken Sandwich: Fried Chicken, Spicy Sauce, Pickles, Price: $8.99, 2 Fish Sandwich: Grilled Snapper, Cilantro, Lime, Price: $12.99]", testOrder.getOrders().toString() );

    }

    /**
     * Test exporting the string
     */
    @org.junit.jupiter.api.Test
    void exportString() {
        OrderLine test1 = new OrderLine(new Chicken());
        OrderLine test2 = new OrderLine(new Beef());
        OrderLine test3 = new OrderLine(new Fish());
        assertEquals("~~~~~~~~~~========== ORDER DETAILS ==========~~~~~~~~~~\n" +
                "Please order something... :)\n" +
                "~~~~~~~~~~===================================~~~~~~~~~~\n" +
                "PRICE: $0.00\n" +
                "TAX: WAIVED\n" +
                "TIP: $0.00\n", testOrder.exportString());
        testOrder.add(test1);
        testOrder.add(test2);
        testOrder.add(test3);
        assertEquals("~~~~~~~~~~========== ORDER DETAILS ==========~~~~~~~~~~\n" +
                "1 Chicken Sandwich: Fried Chicken, Spicy Sauce, Pickles, Price: $8.99\n" +
                "2 Beef Sandwich: Roast Beef, Provolone Cheese, Mustard, Price: $10.99\n" +
                "3 Fish Sandwich: Grilled Snapper, Cilantro, Lime, Price: $12.99\n" +
                "~~~~~~~~~~===================================~~~~~~~~~~\n" +
                "PRICE: $32.97\n" +
                "TAX: WAIVED\n" +
                "TIP: $0.00\n", testOrder.exportString());
    }

    /**
     * Testing the order total math
     */
    @org.junit.jupiter.api.Test
    void orderTotal() {
        OrderLine test1 = new OrderLine(new Chicken());
        OrderLine test2 = new OrderLine(new Beef());
        OrderLine test3 = new OrderLine(new Fish());
        assertEquals(0, testOrder.orderTotal());
        testOrder.add(test1);
        assertEquals(8.99, testOrder.orderTotal());
        testOrder.add(test2);
        assertEquals(19.98, testOrder.orderTotal());
        testOrder.add(test3);
        assertEquals(32.97, testOrder.orderTotal());
    }

    /**
     * Check if clearing the Order clears all values properly
     */
    @org.junit.jupiter.api.Test
    void clear() {
        assertEquals(0, testOrder.getOrders().size());
        OrderLine test1 = new OrderLine(new Chicken());
        OrderLine test2 = new OrderLine(new Beef());
        OrderLine test3 = new OrderLine(new Fish());
        testOrder.add(test1);
        testOrder.add(test2);
        testOrder.add(test3);
        assertEquals(3, testOrder.getOrders().size());
        testOrder.clear();
        assertEquals(0, testOrder.getOrders().size());
        testOrder.add(test1);
        testOrder.add(test2);
        testOrder.add(test3);
        assertEquals(1, testOrder.getOrders().get(0).getLineNumber());
        assertEquals(2, testOrder.getOrders().get(1).getLineNumber());
        assertEquals(3, testOrder.getOrders().get(2).getLineNumber());
    }
}