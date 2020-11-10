package Controller;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class BuildController {

    Stage currentStage;

    public void start(Stage primaryStage) {
        currentStage = primaryStage;
    }

    @FXML
    public void initialize(){
        System.out.println("Initialized");
    }
}
