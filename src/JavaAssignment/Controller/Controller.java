package JavaAssignment.Controller;

import JavaAssignment.Displayer;
import JavaAssignment.LUDecomposition;
import JavaAssignment.Matrix;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.util.ArrayList;

public class Controller {


    /**
     * Object used to make possible operations in matrix input text field.
     * It binds Graphical JavaFX TextArea with that object.
     */
    @FXML
    protected TextArea matrixArea;
    /**
     * Object used to make possible operations in vector input text field.
     * It binds Graphical JavaFX TextArea with that object.
     */
    @FXML
    protected TextArea vectorArea;
    /**
     * Object used to display results in results TextArea.
     * It binds Graphical JavaFX TextArea with that object.
     */
    @FXML
    protected TextArea resultsArea;

    /**
     * Object used to display current program status in information area.
     * It binds Graphical JavaFX TextArea with that object.
     */
    @FXML
    protected TextArea infoArea;

    private String initialInfo;

    private Matrix inputMatrix;

    public Controller() {
        initialInfo = "Hello! Input or load matrix and vector to perform calculations.";
    }


    public void pressButon(ActionEvent event) {
        System.out.println("Java assignment");
    }


    public boolean inputIsOnlyOneNumber() {

        if (matrixArea.getLength() == 1) {
            System.out.println("Input is only one number");
            infoArea.setText("Your input is just one number");
        } else {
            infoArea.clear();
        }

        return true;
    }


    public Matrix getMatrix() {
        inputIsOnlyOneNumber();
        String textMatrix = matrixArea.getText();
        String[] rows = textMatrix.trim().split("\n");
        int vectorLength = this.readSingleVector(rows).size();
        Matrix inputMatrixToGet = new Matrix(vectorLength, vectorLength);
        if (rows.length > 1) {

            for (int i = 0; i < vectorLength; ++i) {
                String[] row = rows[i].trim().split("\\s+");
                for (int j = 0; j < vectorLength; ++j) {
                    inputMatrixToGet.setMatrixElement(i, j, Double.valueOf(row[j]));
                }
            }
            inputMatrixToGet.display();
        }
        return inputMatrixToGet;
    }

    public void calculateLU() {
        System.out.println("GET MATRIX: ");
        getMatrix().display();
        LUDecomposition LUDec = new LUDecomposition(getMatrix());

        Displayer displayer = new Displayer(getMatrix(), LUDec);
        resultsArea.setText(displayer.displayLUDecomposition());




       /* LUDec.getL().display();

        System.out.println("U:");
        LUDec.getU().display();

        System.out.println("Determinant:");
        System.out.println(LUDec.det());

        ArrayList<Double> solution = new ArrayList<Double>();
        solution.add(4.0);
        solution.add(5.0);
        *//*Matrix sol = new Matrix(solution);
        System.out.println("Solution: ");
        sol.display();*//*

        System.out.println("Original arraylist is: " + solution);
        System.out.print("Solution: ");
        LUDec.solve(solution).display();
        System.out.print("Inverse matrix: ");
        LUDec.inverseMatrix().display();*/
    }


    public ArrayList<Double> readSingleVector(String[] rows) {

        String[] oneRow = rows[0].trim().split("\\s+");
        ArrayList<Double> singleVector = new ArrayList<Double>();
        for (int i = 0; i < oneRow.length; ++i) {
            singleVector.add(i, Double.valueOf(oneRow[i]));
        }

        return singleVector;
    }

    public void evaluateIsMatrixSquared() {
        System.out.println("Input in");
    }


    public void clearButton(ActionEvent event) {

        matrixArea.clear();
        vectorArea.clear();
        resultsArea.clear();
        infoArea.setText(initialInfo);
    }


}

