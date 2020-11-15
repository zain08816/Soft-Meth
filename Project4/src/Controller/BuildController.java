package Controller;

import Model.*;
import Model.Extras.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for building a sandwich and adding it to an order
 * @author Clarissa Hwang, Zain Ali
 */
public class BuildController {
    private final String[] BASIC_CHICKEN = {"Fried Chicken", "Spicy Sauce", "Pickles"};
    private final String[] BASIC_BEEF = {"Roast Beef", "Provolone Cheese", "Mustard"};
    private final String[] BASIC_FISH = {"Grilled Snapper", "Cilantro", "Lime"};
    private final Image TOP_BUN = new Image("Images/topBun.png", 150, 50, false, false);
    private final Image BOTTOM_BUN = new Image("Images/bottomBun.png", 150, 50, false, false);
    static final int MAX_EXTRAS = 6;
    private double price;


    @FXML
    VBox builtSandwich;
    @FXML
    ComboBox<String> sandwichTypes;
    @FXML
    ListView<Extra> unaddedExtras;
    @FXML
    ListView<Extra> addedExtras;
    @FXML
    TextField sandwichPrice;
    @FXML
    ListView<String> basicIngredients;
    @FXML
    TextArea output;

    Stage currentStage;
    public Order order = new Order();


    /**
     * Function to pass the current stage around
     * @param primaryStage stage that created for this view
     */
    public void start(Stage primaryStage) {
        currentStage = primaryStage;
    }

    /**
     * Function that is run at the start of program
     */
    @FXML
    public void initialize(){
        basicIngredients.setDisable(true);
        sandwichTypes.getItems().add("Chicken");
        sandwichTypes.getItems().add("Beef");
        sandwichTypes.getItems().add("Fish");
        sandwichTypes.getSelectionModel().selectFirst();
        setChicken();
    }

    /**
     * Set Extras back to original state
     */
    public void setExtras() {
        addedExtras.getItems().clear();
        unaddedExtras.getItems().clear();
        Extra[] extras = {
                new Avocado(),
                new Cheese(),
                new Egg(),
                new Lettuce(),
                new Mushroom(),
                new Olive(),
                new Onion(),
                new Pepper(),
                new Pickle(),
                new Tomato()
        };
        unaddedExtras.getItems().addAll(extras);
        builtSandwich.getChildren().clear();
        builtSandwich.getChildren().add(new ImageView(TOP_BUN));
        builtSandwich.getChildren().add(new ImageView(BOTTOM_BUN));
    }

    /**
     * updates the type of sandwich
     */
    public void newType() {
        String type = sandwichTypes.getValue();
        switch (type) {
            case "Chicken":
                setChicken();
                break;
            case "Beef":
                setBeef();
                break;
            case "Fish":
                setFish();
                break;
        }
    }

    /**
     * Change type to sandwich type to Chicken
     */
    private void setChicken(){
        Chicken data = new Chicken();
        price = data.price();
        sandwichPrice.setText(String.format("$%.2f", price));
        basicIngredients.getItems().clear();
        for (String s : BASIC_CHICKEN) {
            basicIngredients.getItems().add(s);
        }
        setExtras();
        builtSandwich.getChildren().add(1, new ImageView(data.getImage()));
    }

    /**
     * Change type to sandwich type to Beef
     */
    private void setBeef(){
        Beef data = new Beef();
        price = data.price();
        sandwichPrice.setText(String.format("$%.2f", price));
        basicIngredients.getItems().clear();
        for (String s : BASIC_BEEF) {
            basicIngredients.getItems().add(s);
        }
        setExtras();
        builtSandwich.getChildren().add(1, new ImageView(data.getImage()));
    }

    /**
     * Change type to sandwich type to Fish
     */
    private void setFish(){
        Fish data = new Fish();
        price = data.price();
        sandwichPrice.setText(String.format("$%.2f", price));
        basicIngredients.getItems().clear();
        for (String s : BASIC_FISH) {
            basicIngredients.getItems().add(s);
        }
        setExtras();
        builtSandwich.getChildren().add(1, new ImageView(data.getImage()));
    }

    /**
     * Add Extra to sandwich
     */
     public void add() {
         if (addedExtras.getItems().size() >= MAX_EXTRAS) {
             output.appendText("You are only allowed to have 6 extras.\n");
             return;
         }
         Extra toAdd = unaddedExtras.getSelectionModel().getSelectedItem();
         if (toAdd == null) {
             output.appendText("Please select an extra to add.\n");
             return;
         }
         price += toAdd.getPrice();
         sandwichPrice.setText(String.format("$%.2f", price));
         addedExtras.getItems().add(toAdd);
         unaddedExtras.getItems().remove(toAdd);

         builtSandwich.getChildren().add(1, new ImageView(toAdd.getImage()));
    }

    /**
     * Remove extra from sandwich
     */
    public void remove(){
        if (addedExtras.getItems().isEmpty()) {
            output.appendText("Please add extras to remove.\n");
            return;
        }
        Extra toRemove = addedExtras.getSelectionModel().getSelectedItem();
        int removeIndex = addedExtras.getSelectionModel().getSelectedIndex();
        if (toRemove == null) {
            output.appendText("Please select an extra to remove.\n");
            return;
        }
        price -= toRemove.getPrice();
        sandwichPrice.setText(String.format("$%.2f", price));
        unaddedExtras.getItems().add(toRemove);
        addedExtras.getItems().remove(toRemove);

        builtSandwich.getChildren().remove(builtSandwich.getChildren().size() - removeIndex - 3);
    }

    /**
     * Creates sandwich given order specifications
     */
    public void createSandwich(){
        String type = sandwichTypes.getValue();
        switch (type) {
            case "Chicken":
                Chicken addChicken = new Chicken();
                for (Extra extra : addedExtras.getItems()) {
                    addChicken.add(extra);
                }
                for (Extra extra : unaddedExtras.getItems()) {
                    addChicken.remove(extra);
                }
                order.add(new OrderLine(addChicken));
                setChicken();
                break;
            case "Beef":
                Beef addBeef = new Beef();
                for (Extra extra : addedExtras.getItems()) {
                    addBeef.add(extra);
                }
                for (Extra extra : unaddedExtras.getItems()) {
                    addBeef.remove(extra);
                }
                order.add(new OrderLine(addBeef));
                setBeef();
                break;
            case "Fish":
                Fish addFish = new Fish();
                for (Extra extra : addedExtras.getItems()) {
                    addFish.add(extra);
                }
                for (Extra extra : unaddedExtras.getItems()) {
                    addFish.remove(extra);
                }
                order.add(new OrderLine(addFish));
                setFish();
                break;
        }
        output.appendText("Sandwich added to order!\n");
    }

    /**
     * Open Order window
     */
    public void openOrder() {

        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("View/OrderView.fxml"));
            root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Order Details");
            stage.setScene(new Scene(root, 820, 500));
            stage.setResizable(false);

            OrderController secondaryController = loader.getController();
            secondaryController.start(stage);
            secondaryController.setOrder(order);

            stage.show();
        }
        catch (IOException exception) {
            output.appendText("An error occurred when showing order details\n");
        }
    }
}
