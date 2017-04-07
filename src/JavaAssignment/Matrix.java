package JavaAssignment;

/**
 * Created by Wiktor Bednarek on 2017-04-06.
 */


import java.util.ArrayList;

/**
 * Matrix class allows to crate matrix based on ArrayList of ArrayList.
 */
public class Matrix {

    private ArrayList<ArrayList<Double>> matrix;

    /**
     * Default Constructor.
     */
    public Matrix() {
        matrix = new ArrayList<ArrayList<Double>>();
    }

    /**
     * Constructor
     */
    public Matrix(int numOfRows, int numOfColumns) {
        matrix = new ArrayList<ArrayList<Double>>();

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
            ArrayList<Double> list = new ArrayList<Double>();
            matrix.add(list);
            for (int j = 0; j < numOfColumns; ++j) {
                list.add(0.0);
            }
        }

    }

    public void setMatrixRow(int rowElement, int columnElement, Double matrixValue) {

        this.getRow(rowElement).set(columnElement, matrixValue);
    }

    public void addMatrixRow(ArrayList<Double> row) {

        matrix.add(row);
    }


    /**
     * @return Number of rows in matrix
     */

    public int getNumOfRows() {
        return matrix.size();
    }

    /**
     * Returns number of columns in matrix
     *
     * @return Number of columns in matrix
     */
    public int getNumOfColumns() {
        return matrix.get(0).size();
    }


    /**
     * Returns ArrayList of values of selected row
     *
     * @param rowNumber Selected row number to return
     * @return ArrayList with values of selected row
     */
    public ArrayList<Double> getRow(int rowNumber) {
        return matrix.get(rowNumber);
    }

    /**
     * Returns ArrayList of values of selected column
     *
     * @param columnNumber Selected column number to return
     * @return ArrayList with values of selected column
     */
    public ArrayList<Double> getColumn(int columnNumber) {
        ArrayList<Double> tmp = new ArrayList<Double>();

        for (int i = 0; i <= this.getNumOfRows(); ++i) {
            tmp.add(matrix.get(columnNumber).get(i));
        }

        return tmp;
    }

    /**
     * Displaying content of matrix
     */
    public void display() {
        System.out.println(matrix);
    }



}
