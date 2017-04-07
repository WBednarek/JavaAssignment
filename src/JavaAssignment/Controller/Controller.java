package JavaAssignment.Controller;

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

        if (matrixArea.getLength() == 0) {
            System.out.println("Input is only one number");
            infoArea.setText("Your input is just one number");
        } else {
            infoArea.clear();
        }

        return true;
    }


    public void getMatrix() {
        String textMatrix = matrixArea.getText();
        String[] rows = textMatrix.trim().split("\n");
        int vectorLength = this.readSingleVector(rows).size();

        if (rows.length > 1) {
            inputMatrix = new Matrix(vectorLength, vectorLength);
            for (int i = 0; i < vectorLength; ++i) {
                String[] row = rows[i].trim().split("\\s+");
                for (int j = 0; j < vectorLength; ++j) {
                    inputMatrix.setMatrixRow(i, j, Double.valueOf(row[j]));
                }
            }
            inputMatrix.display();
        }

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

