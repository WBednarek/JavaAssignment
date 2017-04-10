package JavaAssignment.View;

import JavaAssignment.Model.LUDecomposition;
import JavaAssignment.Model.Matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

/**
 * Prepare to display selected operation output in resultsArea based on data from input areas: matrix TextArea and vector TextArea.
 * @author Wiktor Bednarek.
 */
public class Displayer {

    /**
     * Matrix from matrix input TextArea.
     */
    private Matrix toDisplay;

    /**
     * Vector from vector input TextArea as array of Strings.
     */
    private String[] vector;

    /**
     * Vector from vector input TextArea as ArrayList of Doubles.
     */
    private ArrayList<Double> vectorArray;

    /**
     * Object to perform LU Decomposition and inverse matrix.
     */
    private LUDecomposition LUDisplay;

    /**
     * Constructor
     * @param toDisplay Matrix from matrix input TextArea.
     * @param vector Vector from vector input TextArea as array of Strings.
     * @param vectorArray Vector from vector input TextArea as ArrayList of Doubles.
     * @param LUDisplay Object to perform LU Decomposition and inverse matrix.
     */
    public Displayer(Matrix toDisplay, String[] vector, ArrayList<Double> vectorArray, LUDecomposition LUDisplay) {
        this.toDisplay = toDisplay;
        this.vector = vector;
        this.vectorArray = vectorArray;
        this.LUDisplay = LUDisplay;

    }

    /**
     * Display solution in results TextArea of selected operation: LU Decomposition or Inverse matrix.
     *
     * @param operation Takes values: 1 for LU Decomposition, 2 for Inverse matrix.
     * @return String with solution of LU Decomposition or Inverse matrix.
     */
    public String displaySolution(int operation) {

        String description = "LU Decomposition with scaled partial pivoting\n";
        String originalMatrix = "Original matrix\n" + displayMatrix(toDisplay) + "\n";
        String originalVector = "Original vector\n" + displayVector(vector) + "\n";
        String lowerMatrix = "Lower matrix\n" + displayMatrix(LUDisplay.getLowerMatrix()) + "\n";
        String upperMatrix = "Uppper matrix\n" + displayMatrix(LUDisplay.getUpperMatrix()) + "\n";
        String determinant = "Determinant = " + LUDisplay.det() + "\n";
        String solution = "";
        switch (operation) {
            case 1:
                String LUsolution = "Solution\n" + displayMatrix(LUDisplay.solve(vectorArray)) + "\n";
                solution = description + originalMatrix + originalVector + lowerMatrix + upperMatrix + LUsolution + determinant;
                break;
            case 2:
                String inverseMatrixSolution = "Inverse matrix\n" + displayMatrix(LUDisplay.inverseMatrix()) + "\n";
                solution = description + originalMatrix + originalVector + lowerMatrix + upperMatrix + inverseMatrixSolution + determinant;
                break;
            default:
                break;
        }
        return solution;
    }

    /**
     * Prepare vector from vector input TextArea to display in output TextArea.
     * @param vec Vector from vector input TextArea as array of Strings.
     * @return Vector prepared to display in output TextArea.
     */
    public String displayVector(String[] vec) {

        for(int i = 0; i < vec.length; ++i) {
            vec[i] = String.format(Locale.ENGLISH, "%.7f", Double.valueOf(vec[i]));
            //Add additional space in displaying
            vec[i] = vec[i] + " ";

        }

        String displayVector = Arrays.toString(vec);
        String showVector = "";
        //Add tab before vector
        showVector += "\t";
        //
        showVector += displayVector.replace(",", "")  //remove the commas
                .replace("[", "")  //remove the right bracket
                .replace("]", "")  //remove the left bracket
                .trim();
        showVector += "\n";


        return showVector;
    }

    /**
     * Prepare matrix from matrix input TextArea to display in output TextArea.
     * @param toDisplay Matrix from matrix input TextArea.
     * @return Matrix prepared to display in output TextArea.
     */

    public String displayMatrix(Matrix toDisplay) {
        StringBuilder displayMatrix = new StringBuilder();
        for (int row = 0; row < toDisplay.getNumOfRows(); row++) {
            displayMatrix.append("\t");
            for (int column = 0; column < toDisplay.getNumOfColumns(); column++) {
                //Set displayMatrix elements with decimal precision of 7 places.
                displayMatrix.append(String.format(Locale.ENGLISH, "%.7f", toDisplay.getMatrixElement(row, column))).append("  ");
            }
            displayMatrix.append("\n");
        }
        return displayMatrix.toString();
    }
    /*
        DecimalFormat df = new DecimalFormat("#.0000000", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        final DecimalFormat decimalFormat = new DecimalFormat("###,###,##0.00", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
*/

}
