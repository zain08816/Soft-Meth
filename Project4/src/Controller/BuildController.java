package Controller;

import Model.*;
import Model.Extras.*;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BuildController {

    @FXML
    VBox sandwich;

    Stage currentStage;
    Order order = new Order();


    public void start(Stage primaryStage) {
        currentStage = primaryStage;
    }

    @FXML
    public void initialize(){
        System.out.println("Initialized");
        Chicken test = new Chicken();
        Beef test2 = new Beef();
        Fish test3 = new Fish();



        order.add(new OrderLine(test));
        order.add(new OrderLine(test2));
        order.add(new OrderLine(test3));

        order.printLines();

        order.remove(new OrderLine(2));

        order.printLines();
    }

    public void add(){
        Image smonk = new Image("Images/smonk.png", 50, 50, false, false);
        int size = sandwich.getChildren().size();
        sandwich.getChildren().add(new ImageView(smonk));
    }

    public void remove(){
        int size = sandwich.getChildren().size();
        sandwich.getChildren().remove(size-1);
    }
}
