package Project3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The Main class is used to set the stage and run the Payroll Processor GUI.
 * @author Srija Gottiparthi, Catherine Nguyen
 */

public class Main extends Application {

    /**
     * Method that starts the Payroll Processor GUI.
     * @param primaryStage the stage of the application
     * @throws Exception if there are runtime errors
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Payroll Processing System");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
