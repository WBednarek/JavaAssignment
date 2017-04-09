package JavaAssignment.Controller;

import JavaAssignment.Displayer;
import JavaAssignment.LUDecomposition;
import JavaAssignment.Matrix;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.util.ArrayList;


/**
 * Class is responsible for calculations and input validation.
 * It determines if in input TextAreas for matrix and vector is correct, doesn't contain illegal characters.
 *
 * @author Wiktor Bednarek
 */

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

    /**
     * Object used to manipulate button behaviour. That button is responsible to start LU Decomposition calculation.
     * It binds Graphical JavaFX Button with that object.
     */
    @FXML
    protected Button LUButton;

    /**
     * Object used to manipulate Inverse button behaviour. That button is responsible to start inverse matrix function.
     * It binds Graphical JavaFX Button with that object.
     */
    @FXML
    protected Button inverseButton;

    /**
     * Object used to manipulate Save button behaviour, which allows to save current results.
     * It binds Graphical JavaFX Button with that object.
     */
    @FXML
    protected Button saveButton;

    /**
     * Object used to manipulate button behaviour, which allows to load current results.
     * It binds Graphical JavaFX Button with that object.
     */
    @FXML
    protected Button loadButton;


    /**
     * Simple initial info in information TextArea.
     */
    private String initialInfo;


    // private Matrix inputMatrix;

    /**
     * Boolean value checking if matrix in input TextArea is valid.
     * Checking if matrix doesn't contain illegal characters, is non singular, have proper dimensions (is squared) etc.
     */
    private boolean isMatrixProperlySet;

    /**
     * Boolean value checking if vector in input TextArea is valid.
     * Checking if vector doesn't contain illegal characters, is non singular, have proper dimensions (has only one row) etc.
     */
    private boolean isVectorProperlySet;


    /**
     * Size of one matrix row.
     */
    private int sizeOfMatrix;
    /**
     * Size of vector.
     */
    private int sizeOfVector;


    /**
     * Default constructor.
     * Initializing initialInfo value, and setting initial matrix and vector validator value to false.
     */
    public Controller() {
        initialInfo = "Hello! Input or load matrix and vector to perform calculations.";
        isMatrixProperlySet = false;
        isVectorProperlySet = false;


    }

    /**
     * Set buttons (LUpivot, Inverse, Save and Load) usage - click possibility .
     * @param disableButtons Parameter sets buttons to be possible or impossible to click .
     */
    public void buttonsAccess(boolean disableButtons) {
        LUButton.setDisable(disableButtons);
        inverseButton.setDisable(disableButtons);
        saveButton.setDisable(disableButtons);
        loadButton.setDisable(disableButtons);
    }


    /**
     * When key on keyboard is pressed and content of matrix TextArea is changed it validates if data are proper.
     * Validating matrix, if doesn't contain illegal characters, is non singular, have proper dimensions (has only one row) etc.
     */
    public void matrixValidator() {
        if (matrixArea.getLength() == 1) {
            System.out.println("Input is only one number");
            infoArea.setText("Your input is just one number");
        } else {
            infoArea.clear();
        }
        getMatrix();


    }

    /**
     * When key on keyboard is pressed and content of vector TextArea is changed it validates if data are proper.
     * Validating vector, if doesn't contain illegal characters, is non singular, have proper dimensions (has only one row) etc.
     */
    public void vectorValidator() {
        if (matrixArea.getLength() == 1) {
            System.out.println("Input is only one number");
            infoArea.setText("Your input is just one number");
        } else {
            infoArea.clear();
        }

        getVector();


    }

    /**
     * Returns matrix TextArea input as object of Matrix class.
     * @return Validated matrix from matrix TextArea input.
     */
    public Matrix getMatrix() {
        String textMatrix = matrixArea.getText();
        String[] rows = textMatrix.trim().split("\n");
        int vectorLength = this.readSingleVector(rows).size();
        sizeOfMatrix = vectorLength;
        String[] trimmedRow = new String[vectorLength];
        for (int i = 0; i < rows.length; ++i) {
            trimmedRow = rows[i].trim().split("\\s+");

        }

       /* System.out.println("TRIMMED ROW:" + trimmedRow.length);
        System.out.println("ROWS: length " + rows.length);
        System.out.println("Vector length: " + vectorLength);
        System.out.println();*/

        Matrix inputMatrixToGet = new Matrix(vectorLength, vectorLength);
        //Is only one row.
        if (rows.length <= 1) {
            infoArea.setText("Is not a matrix");
            buttonsAccess(true);
        }
        //Check if input in text area contains non numbers excluding whitespaces.
        else if ((!textMatrix.matches("[0-9\\s]+"))) {
            infoArea.setText("One of inputs in matrix field is non a number");
            buttonsAccess(true);
        }
        //Check if matrix is squared.
        else if ((trimmedRow.length != rows.length) || (trimmedRow.length != vectorLength)) {
            infoArea.setText("Matrix is not squared");
            buttonsAccess(true);
        }
        //After all cases assume matrix is valid and can be used for calculations.
        else {
            //Matrix is valid
            isMatrixProperlySet = true;
            if (isVectorProperlySet && sizeOfMatrix == sizeOfVector) {
                buttonsAccess(false);
                String[] row;
                for (int i = 0; i < vectorLength; ++i) {
                    row = rows[i].trim().split("\\s+");
                    for (int j = 0; j < vectorLength; ++j) {
                        inputMatrixToGet.setMatrixElement(i, j, Double.valueOf(row[j]));
                    }
                }
                inputMatrixToGet.display();
            } else {
                infoArea.setText("Please also input proper vector.\nRemember dimensions of matrix and vector must be the same");
            }


        }

        return inputMatrixToGet;
    }


    /**
     * Returns vector TextArea input as array of Strings.
     * @return Validated vector from vector TextArea input.
     */
    public String[] getVector() {
        String textVector = vectorArea.getText();
        String[] rows = textVector.trim().split("\n");
        String[] row = new String[textVector.length()];

        String[] trimmedVector = new String[rows.length];

        trimmedVector = rows[0].trim().split("\\s+");

        //Set class field size of vector. It will be used for validation purposed, comparing size of matrix and vector.
        sizeOfVector = trimmedVector.length;

        int isVector = rows.length;
        if (isVector != 1) {
            infoArea.setText("Is not a vector");
            buttonsAccess(true);
        }
        //Check if input in text area contains non numbers excluding whitespaces.
        else if ((!textVector.matches("[0-9\\s]+"))) {
            infoArea.setText("One of inputs in vector field is non a number");
            buttonsAccess(true);
        } else {
            // Vector is valid.
            isVectorProperlySet = true;
            if (isMatrixProperlySet && sizeOfMatrix == sizeOfVector) {
                buttonsAccess(false);
                row = rows[0].trim().split("\\s+");
            } else {
                infoArea.setText("Please also input proper matrix.\nRemember dimensions of matrix and vector must be the same");
            }
        }
        return row;

    }


    /**
     * Returns vector TextArea input as ArrayList of Doubles.
     * @return Validated matrix from vector TextArea input.
     */
    public ArrayList<Double> getVectorArray() {
        String textVector = vectorArea.getText();
        //Split
        String[] rows = textVector.trim().split("\n");
        String[] row = new String[textVector.length()];
        int isVector = rows.length;
        ArrayList<Double> vector = new ArrayList<Double>(textVector.length());
        //If there is only one row we can assume that is vector.
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

    /**
     * Called when LUPivot button pressed.
     * Calculating LU Decomposition of a square matrix form matrix TextArea input.
     */
    public void calculateLU() {

        LUDecomposition LUDec = new LUDecomposition(getMatrix());
        Displayer displayer = new Displayer(getMatrix(), getVector(), getVectorArray(), LUDec);
        resultsArea.setText(displayer.displayLUDecomposition(1));

    }

    /**
     * Called when Inverse button pressed.
     * Calculating inverse matrix form TextArea.
     */
    public void calculateIverseMatrix() {
        LUDecomposition LUDec = new LUDecomposition(getMatrix());
        Displayer displayer = new Displayer(getMatrix(), getVector(), getVectorArray(), LUDec);
        resultsArea.setText(displayer.displayLUDecomposition(2));
    }


    /**
     * Reads chosen matrix row from matrix TextArea input and return is as ArrayList of Doubles.
     * Function ignores whitespaces, so that it is possible to read vector size which is based only on numbers.
     * @param rows Input row to save it as vector without whitespaces.
     * @return Selected row represented as ArrayList of Doubles without whitespaces.
     */
    public ArrayList<Double> readSingleVector(String[] rows) {

        String[] oneRow = rows[0].trim().split("\\s+");
        ArrayList<Double> singleVector = new ArrayList<Double>();
        for (int i = 0; i < oneRow.length; ++i) {
            singleVector.add(i, Double.valueOf(oneRow[i]));
        }

        return singleVector;
    }


    /**
     * Clear all TextAreas in application.
     * @param event Event {@link ActionEvent}.
     */
    public void clearButton(ActionEvent event) {

        matrixArea.clear();
        vectorArea.clear();
        resultsArea.clear();
        infoArea.setText(initialInfo);
        buttonsAccess(true);
    }


}

