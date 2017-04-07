package JavaAssignment;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

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
        mat = new Matrix(2, 2);

        mat.setMatrixElement(1, 1, 232.0);
        mat.setMatrixElement(0, 0, 12.0);
        mat.setMatrixElement(1, 0, 3412.0);
        mat.display();
        System.out.println("1 1 is: " + mat.getMatrixElement(1, 1));

        Matrix matrix1 = new Matrix(mat);

        ArrayList<Double> LUrowi = new ArrayList<Double>();

        LUrowi = matrix1.getRow(0);
        System.out.println("MAT 1 is: " + LUrowi);
        matrix1.display();


        LUDeconposition LUDec = new LUDeconposition(mat);
        System.out.println("L:");
        LUDec.getL().display();


    }



    }

