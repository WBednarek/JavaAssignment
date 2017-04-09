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

    private Matrix inputMatrix;

    private boolean isMatrixProperlyset;

    private boolean isVectorProperlyset;

    private int sizeOfMatrix;
    private int sizeOfVector;


    public Controller() {
        initialInfo = "Hello! Input or load matrix and vector to perform calculations.";
        isMatrixProperlyset = false;
        isVectorProperlyset = false;


    }

    /**
     * Set buttons possibility to click.
     *
     * @param disableButtons Parameter sets buttons to be possible or impossible to click .
     */
    public void buttonsAccess(boolean disableButtons) {
        LUButton.setDisable(disableButtons);
        inverseButton.setDisable(disableButtons);
        saveButton.setDisable(disableButtons);
        loadButton.setDisable(disableButtons);
    }

    /*
    public void disableButton(Button button) {
        if (!button.isDisabled()) ;
        {
            button.setDisable(true);
        }
    }
*/
    public void pressButon(ActionEvent event) {
        System.out.println("Java assignment");
    }


    public void inputIsOnlyOneNumber() {


    }

    public void validator() {
        if (matrixArea.getLength() == 1) {
            System.out.println("Input is only one number");
            infoArea.setText("Your input is just one number");
        } else {
            infoArea.clear();
        }
        getMatrix();


    }

    public void vectorValidator() {
        if (matrixArea.getLength() == 1) {
            System.out.println("Input is only one number");
            infoArea.setText("Your input is just one number");
        } else {
            infoArea.clear();
        }

        getVector();


    }


    public Matrix getMatrix() {
        inputIsOnlyOneNumber();
        String textMatrix = matrixArea.getText();
        String[] rows = textMatrix.trim().split("\n");
        int vectorLength = this.readSingleVector(rows).size();
        sizeOfMatrix = vectorLength;
        String[] trimmedRow = new String[vectorLength];
        for (int i = 0; i < rows.length; ++i) {
            trimmedRow = rows[i].trim().split("\\s+");

        }
        //System.out.println("TRIMMED ROW:" + Arrays.toString(trimmedRow) );
        System.out.println("TRIMMED ROW:" + trimmedRow.length);
        System.out.println("ROWS: length " + rows.length);
        System.out.println("Vector length: " + vectorLength);
        System.out.println();

        Matrix inputMatrixToGet = new Matrix(vectorLength, vectorLength);
        if (rows.length <= 1) {
            infoArea.setText("Is not a matrix");
            buttonsAccess(true);
        } else if ((!textMatrix.matches("[0-9\\s]+"))) {
            infoArea.setText("One of inputs in matrix field is non a number");
            buttonsAccess(true);
        }
        //Matrix is not squared   && rows[0].length() == trimmedRow.length    trimmedRow.length != rows.length) &&
        else if ((trimmedRow.length != rows.length) || (trimmedRow.length != vectorLength)) {
            infoArea.setText("Matrix is not squared");
            buttonsAccess(true);
        } else {
            //Matrix is valid
            isMatrixProperlyset = true;
            if (isVectorProperlyset && sizeOfMatrix == sizeOfVector) {
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


    public String[] getVector() {
        String textVector = vectorArea.getText();
        String[] rows = textVector.trim().split("\n");
        String[] row = new String[textVector.length()];

        String[] trimmedVector = new String[rows.length];

        trimmedVector = rows[0].trim().split("\\s+");


        sizeOfVector = trimmedVector.length;



        int isVector = rows.length;
        if (isVector != 1) {
            infoArea.setText("Is not a vector");
            buttonsAccess(true);
        }
        //&& !textVector.matches("\\s+")
        else if ((!textVector.matches("[0-9\\s]+"))) {
            infoArea.setText("One of inputs in vector field is non a number");
            buttonsAccess(true);
        } else {
            // Vector is valid
            isVectorProperlyset = true;
            if (isMatrixProperlyset && sizeOfMatrix == sizeOfVector) {
                buttonsAccess(false);
                row = rows[0].trim().split("\\s+");
            } else {
                infoArea.setText("Please also input proper matrix.\nRemember dimensions of matrix and vector must be the same");
            }


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

