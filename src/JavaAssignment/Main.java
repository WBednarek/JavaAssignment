package JavaAssignment;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * Application calculates LU Decomposition and Inverse matrix of selected input.
 * Main class. It initializes application GUI.
 * Application uses JavaFX software platform.
 * @author Wiktor Bednarek
 */

public class Main extends Application {
/*
TO DO
 - Documentation!
 - Save and load functions
 - Non number error in matrix first row
 - isMatrixSingular validation
 - Add nice .css
 - Refactor code
 */

    /**
     * Main function. Launch program with command line arguments.
     *
     * @param args String array of program arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Starting application and loading its design located in file Design.fxml.
     * @param primaryStage Stage to display
     * @throws Exception Exception handling incorrect data loading
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Design.fxml"));

        primaryStage.setTitle("Java assignment Wiktor Bednarek");
        primaryStage.setScene(new Scene(root, 800, 700));
        primaryStage.show();

    }
}

