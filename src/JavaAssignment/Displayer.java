package JavaAssignment;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Wiktor Bednarek
 */
public class Displayer {


    private Matrix toDisplay;

    private LUDecomposition LUDisplay;

    /**
     * Constructor
     *
     * @param toDisplay Matrix from input field
     * @param LUdisplay Object to perform LU decomposition and matrix inversion calculations
     */
    public Displayer(Matrix toDisplay, LUDecomposition LUdisplay) {
        this.toDisplay = toDisplay;
        this.LUDisplay = LUdisplay;
    }


    public String displayLUDecomposition() {
        String description = "LU Decomposition with scaled partial pivoting\n";
        String originalMatrix = "Original matrix\n" + displayMatrix(toDisplay) + "\n";
        String lowerMatrix = "Lower matrix\n" + displayMatrix(LUDisplay.getL()) + "\n";
        String upperMatrix = "Uppper matrix\n" + displayMatrix(LUDisplay.getU()) + "\n";
        String determinant = "Determinant = " + LUDisplay.det() + "\n";


        DecimalFormat df = new DecimalFormat("#.0000000", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        final DecimalFormat decimalFormat = new DecimalFormat("###,###,##0.00", DecimalFormatSymbols.getInstance(Locale.ENGLISH));


        System.out.println("L:");
        LUDisplay.getL().display();

        System.out.println("U:");
        LUDisplay.getU().display();

        System.out.println("Determinant:");
        System.out.println(LUDisplay.det());

        ArrayList<Double> solution = new ArrayList<Double>();
        solution.add(4.0);
        solution.add(5.0);
        /*Matrix sol = new Matrix(solution);
        System.out.println("Solution: ");
        sol.display();*/

        System.out.println("Original arraylist is: " + solution);
        System.out.print("Solution: ");
        LUDisplay.solve(solution).display();
        System.out.print("Inverse matrix: ");
        LUDisplay.inverseMatrix().display();

        String concatenation = description + originalMatrix + lowerMatrix + upperMatrix + determinant;
        return concatenation;
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
