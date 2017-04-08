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
        Parent root = FXMLLoader.load(getClass().getResource("Design.fxml"));

        primaryStage.setTitle("Java assignment Wiktor Bednarek");
        primaryStage.setScene(new Scene(root, 800, 700));
        primaryStage.show();

        Matrix mat;
        mat = new Matrix(2, 2);
        /*
        mat.setMatrixElement(1, 1, 232.0);
        mat.setMatrixElement(0, 0, 12.0);
        mat.setMatrixElement(1, 0, 3412.0);


        mat.setMatrixElement(0, 0, 232.0);
        mat.setMatrixElement(0, 0, 12.0);
        mat.setMatrixElement(1, 0, 3412.0);
        mat.setMatrixElement(1, 0, 3412.0);

        String display = String.valueOf(mat.getMatrixElement(1, 1));
        System.out.println("DISPLAY: " + display);



//        StringBuilder builder = new StringBuilder();
//
//        for (int row = 0; row <= mat.getNumOfRows(); row++) {
//            for (String value : mat.getRow(row)) {
//                builder.append(value);
//            }


        mat.display();
        System.out.println("Num of columns is: " + mat.getNumOfColumns());

        Matrix matrix1 = new Matrix(mat);

        ArrayList<Double> LUrowi = new ArrayList<Double>();

        LUrowi = matrix1.getRow(0);
        System.out.println("MAT 1 is: " + LUrowi);
        matrix1.display();


        LUDecomposition LUDec = new LUDecomposition(mat);
        System.out.println("L:");
        LUDec.getLowerMatrix().display();

        System.out.println("U:");
        LUDec.getU().display();

        System.out.println("Determinant:");
        System.out.println(LUDec.det());

        ArrayList<Double> solution = new ArrayList<Double>();
        solution.add(4.0);
        solution.add(5.0);
        Matrix sol = new Matrix(solution);
        System.out.println("Solution: ");
        sol.display();

        System.out.println("Original arraylist is: " + solution);
        System.out.print("Solution: ");
        LUDec.solve(solution).display();
        System.out.print("Inverse matrix: ");
        LUDec.inverseMatrix().display();
*/

    }
    }

