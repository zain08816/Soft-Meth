package Controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BuildController {

    @FXML
    VBox sandwich;

    Stage currentStage;

    public void start(Stage primaryStage) {
        currentStage = primaryStage;
    }

    @FXML
    public void initialize(){
        System.out.println("Initialized");
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
