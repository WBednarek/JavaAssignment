package JavaAssignment;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        primaryStage.setTitle("Java assignment Wiktor Bednarek");
        primaryStage.setScene(new Scene(root, 800, 700));
        primaryStage.show();

        Matrix mat;
        mat = new Matrix(2, 4);

        mat.setMatrixRow(1, 1, 232.3);
        mat.setMatrixRow(0, 0, 12.0);
        mat.display();

    }
}
