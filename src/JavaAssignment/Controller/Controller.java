package JavaAssignment.Controller;

import JavaAssignment.Displayer;
import JavaAssignment.LUDecomposition;
import JavaAssignment.Matrix;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

    @FXML
    protected Button LUButton;

    private String initialInfo;

    private Matrix inputMatrix;


    public Controller() {
        initialInfo = "Hello! Input or load matrix and vector to perform calculations.";


    }


    public void disableButton(Button button) {
        if (!button.isDisabled()) ;
        {
            button.setDisable(true);
        }
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
            String[] row;
            for (int i = 0; i < vectorLength; ++i) {
                row = rows[i].trim().split("\\s+");
                for (int j = 0; j < vectorLength; ++j) {
                    inputMatrixToGet.setMatrixElement(i, j, Double.valueOf(row[j]));
                }
            }
            inputMatrixToGet.display();
        }
        return inputMatrixToGet;
    }

    public String[] getVector() {
        String textVector = vectorArea.getText();
        String[] rows = textVector.trim().split("\n");
        String[] row = new String[textVector.length()];
        int isVector = rows.length;
        if (isVector == 1) {

            row = rows[0].trim().split("\\s+");
        } else {
            infoArea.setText("Is not a vector");
        }
        return row;

    }


    public ArrayList<Double> getVectorArray() {
        String textVector = vectorArea.getText();
        String[] rows = textVector.trim().split("\n");
        String[] row = new String[textVector.length()];
        int isVector = rows.length;
        ArrayList<Double> vector = new ArrayList<Double>(textVector.length());
        if (isVector == 1) {

            row = rows[0].trim().split("\\s+");
            for (int i = 0; i < row.length; ++i) {
                vector.add(i, Double.valueOf(row[i]));
            }
        } else {
            infoArea.setText("Is not a vector");
        }
        return vector;

    }

    public void calculateLU() {

        LUDecomposition LUDec = new LUDecomposition(getMatrix());
        Displayer displayer = new Displayer(getMatrix(), getVector(), getVectorArray(), LUDec);
        resultsArea.setText(displayer.displayLUDecomposition(1));

    }

    /**
     * Called when Inverse button pressed. Calculating inverse matrix form TextArea.
     */
    public void calculateIverseMatrix() {
        LUDecomposition LUDec = new LUDecomposition(getMatrix());
        Displayer displayer = new Displayer(getMatrix(), getVector(), getVectorArray(), LUDec);
        resultsArea.setText(displayer.displayLUDecomposition(2));
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

