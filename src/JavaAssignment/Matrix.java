package JavaAssignment;

/**
 * Created by Wiktor Bednarek on 2017-04-06.
 */


import java.util.ArrayList;

/**
 * Matrix class allows to crate matrix based on ArrayList of ArrayList.
 */
public class Matrix {


    private ArrayList<ArrayList<Integer>> matrix;

    /**
     * Default Constructor.
     */
    public Matrix() {
        matrix = new ArrayList<ArrayList<Integer>>();
    }

    /**
     * Constructor
     */
    public Matrix(int numOfRows, int numOfColumns) {


        /**
         * Ensuring minimum capacity for number of rows in matrix
         */
        matrix.ensureCapacity(numOfRows);

        /**
         * Ensuring minimum capacity for number of columns in matrix
         */
        for (int i = 0; i < numOfRows; ++i) {
            matrix.ensureCapacity(numOfColumns);
        }

        /**
         * Initializing matrix with zeros
         */
        for (int i = 0; i < numOfRows; ++i) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            matrix.add(list);
            for (int j = 0; j < numOfColumns; ++j) {
                list.add(0);
            }
        }

    }

    /**
     * @return Number of rows in matrix
     */

    private int getNumOfRows() {
        return matrix.size();
    }

    /**
     * Returns number of columns in matrix
     *
     * @return Number of columns in matrix
     */
    private int getNumOfColumns() {
        return matrix.get(0).size();
    }


    /**
     * Returns ArrayList of values of selected row
     *
     * @param rowNumber Selected row number to return
     * @return ArrayList with values of selected row
     */
    private ArrayList<Integer> getRow(int rowNumber) {
        return matrix.get(rowNumber);
    }

    /**
     * Returns ArrayList of values of selected column
     *
     * @param columnNumber Selected column number to return
     * @return ArrayList with values of selected column
     */
    private ArrayList<Integer> getColumn(int columnNumber) {
        ArrayList<Integer> tmp = new ArrayList<Integer>();

        for (int i = 0; i < this.getNumOfRows(); ++i) {
            tmp.add(matrix.get(columnNumber).get(i));
        }

        return tmp;
    }


}
