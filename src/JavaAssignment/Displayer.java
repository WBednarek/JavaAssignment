package JavaAssignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

/**
 * Created by Wiktor Bednarek
 */
public class Displayer {


    private Matrix toDisplay;

    private LUDecomposition LUDisplay;

    private String[] vector;

    private ArrayList<Double> vectorArray;

    /**
     * Constructor
     *
     * @param toDisplay Matrix from input field
     * @param LUdisplay Object to perform LU decomposition and matrix inversion calculations
     */
    public Displayer(Matrix toDisplay, String[] vector, ArrayList<Double> vectorArray, LUDecomposition LUdisplay) {
        this.toDisplay = toDisplay;
        this.vector = vector;
        this.vectorArray = vectorArray;
        this.LUDisplay = LUdisplay;

    }

    public String displayInverseMatrix() {
        int rows = toDisplay.getNumOfRows();
        LUDisplay.inverseMatrix();
        return new String();
    }

    public String displayLUDecomposition(int operation) {

        String description = "LU Decomposition with scaled partial pivoting\n";
        String originalMatrix = "Original matrix\n" + displayMatrix(toDisplay) + "\n";
        String originalVector = "Original vector\n" + displayVector(vector) + "\n";
        String lowerMatrix = "Lower matrix\n" + displayMatrix(LUDisplay.getLowerMatrix()) + "\n";
        String upperMatrix = "Uppper matrix\n" + displayMatrix(LUDisplay.getU()) + "\n";
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


/*

        DecimalFormat df = new DecimalFormat("#.0000000", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        final DecimalFormat decimalFormat = new DecimalFormat("###,###,##0.00", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
*/





    public String displayVector(String[] vec) {

        for(int i = 0; i < vec.length; ++i)
        {
            vec[i] = String.format(Locale.ENGLISH, "%.7f", Double.valueOf(vec[i]));

        }

        String displayVector = Arrays.toString(vec);
        String showVector = "";
        showVector += "\t";
        showVector += displayVector.replace(",", "")  //remove the commas
                .replace("[", "")  //remove the right bracket
                .replace("]", "")  //remove the left bracket
                .trim();
        showVector += "\n";


        return showVector;
    }

    public String displayMatrix(Matrix toDisplay) {
        String displayMatrix = "";
        for (int row = 0; row < toDisplay.getNumOfRows(); row++) {
            displayMatrix += "\t";
            displayMatrix += toDisplay.getRow(row).toString()
                    .replace(",", "")  //remove the commas
                    .replace("[", "")  //remove the right bracket
                    .replace("]", "")  //remove the left bracket
                    .trim();
            displayMatrix += "\n";
        }
        return displayMatrix;

    }


}
