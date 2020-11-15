package Controller;

import Model.Order;
import Model.OrderLine;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.PrintWriter;

/**
 * Controller for adding, removing, and printing order
 * @author Clarissa Hwang, Zain Ali
 */
public class OrderController {
    @FXML
    ListView<OrderLine> orderlines;
    @FXML
    TextField orderPrice;

    Stage currentStage;
    Order order;
    double price;

    /**
     * Get stage that we are at
     * @param primaryStage stage that we were on before
     */
    public void start(Stage primaryStage) {
        currentStage = primaryStage;
    }

    /**
     * Sets Order object from parent function.
     * @param order order that was made
     */
    public void setOrder(Order order) {
        this.order = order;
        orderlines.getItems().clear();
        orderlines.getItems().addAll(order.getOrders());
        price = order.orderTotal();
        orderPrice.setText(String.format("$%.2f", price));
    }

    /**
     * Duplicate selected orderline
     */
    public void duplicate() {
        OrderLine toDuplicate = orderlines.getSelectionModel().getSelectedItem();
        if (toDuplicate == null) return;
        order.add(toDuplicate);
        setOrder(order);
    }

    /**
     * Remove selected orderline
     */
    public void delete() {
        if (order.getOrders().isEmpty()) return;
        OrderLine toRemove = orderlines.getSelectionModel().getSelectedItem();
        if (toRemove == null) return;
        order.remove(toRemove);
        setOrder(order);
    }

    /**
     * Clears order
     */
    public void clearOrder() {
        order.clear();
        setOrder(order);
    }

    /**
     * Saves order as a text file
     */
    public void exportOrder() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Order");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );
        File selectedFile = fileChooser.showSaveDialog(currentStage);
        if (selectedFile == null) {
            return;
        }
        try {
            PrintWriter writer = new PrintWriter(selectedFile);
            writer.println(order.exportString());
            writer.close();
        } catch (Exception exception) {
            return;
        }
        return;
    }

    /**
     * Closes window
     */
    public void closeWindow() {
        currentStage.close();
    }

}
