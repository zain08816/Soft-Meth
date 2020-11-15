/**
 * @author Clarissa Hwang, Zain Ali
 */
import Controller.BuildController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Clarissa Hwang, Zain Ali
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("View/BuildView.fxml"));
        Parent root = loader.load();

        BuildController mainController = loader.getController();
        mainController.start(primaryStage);

        primaryStage.setTitle("Sandwich Builder");
        primaryStage.setScene(new Scene(root, 800, 700));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
