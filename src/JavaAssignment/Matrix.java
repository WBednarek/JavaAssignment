package JavaAssignment;


import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
/**
 * Matrix is based on 2 dimensional ArrayList of Doubles.
 * Matrix class allows to crate matrix based on ArrayList of ArrayList.
 * @author Wiktor Bednarek
 */

public class Matrix {

    /**
     * Matrix data field based on 2 dimensional ArrayList of Doubles.
     */
    private ArrayList<ArrayList<Double>> matrix;

    /**
     * Default Constructor.
     */
    public Matrix() {
        matrix = new ArrayList<ArrayList<Double>>();
    }

    /**
     * Initializes matrix numOfRows-by-numOfColumns.
     * @param numOfRows Number of matrix rows.
     * @param numOfColumns Number of matrix columns.
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

    /**
     * Copy Constructor.
     *
     * @param mat Matrix to copy.
     */
    public Matrix(Matrix mat) {

        matrix = new ArrayList<ArrayList<Double>>();
        /**
         * Ensuring minimum capacity for number of rows in matrix.
         */
        matrix.ensureCapacity(mat.getNumOfRows());

        /**
         * Ensuring minimum capacity for number of columns in matrix.
         */
        for (int i = 0; i < mat.getNumOfRows(); ++i) {
            matrix.ensureCapacity(mat.getNumOfColumns());
        }

        /**
         * Copying elements to matrix.
         */
        for (int i = 0; i < mat.getNumOfRows(); ++i) {
            matrix.add(i, mat.getRow(i));
            for (int j = 0; j < mat.getNumOfColumns(); ++j) {
                matrix.get(i).set(j, mat.getMatrixElement(i, j));
            }
        }

    }


    /**
     * Constructor of vector - matrix (1D).
     * Input vector must not be null.
     * @param vectorB Values of input vector.
     */
    public Matrix(@NotNull ArrayList<Double> vectorB) {
        matrix = new ArrayList<ArrayList<Double>>();
        matrix.add(0, new ArrayList<Double>(vectorB.size()));
        for (int i = 0; i < vectorB.size(); ++i) {
            matrix.get(0).add(i, vectorB.get(i));
        }
    }

    /**
     * Calculate inverse matrix.
     * @param numberOfRows    Number of rows of matrix to inverse.
     * @param numberOfColumns Number of columns of matrix to inverse.
     * @return Inverted matrix.
     */
    public static Matrix identityMatrix(int numberOfRows, int numberOfColumns) {
        Matrix A = new Matrix(numberOfRows, numberOfColumns);
        Matrix X = new Matrix(A);
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                X.setMatrixElement(i, j, (i == j ? 1.0 : 0.0));
            }
        }
        return A;
    }


    /**
     * Returning submatrix.
     * @param r Array contains row indices.
     * @param j0 Index of initial column.
     * @param j1 Final index of column.
     * @return Matrix A(r(:), j0:j1).
     * @exception ArrayIndexOutOfBoundsException Out of matrix boundary.
     */
    public Matrix getSubMatrix(int[] r, int j0, int j1) {
        Matrix X = new Matrix(r.length, j1 - j0 + 1);
        Matrix B = new Matrix(X);

        try {
            for (int i = 0; i < r.length; i++) {
                for (int j = j0; j <= j1; j++) {
                    B.setMatrixElement(i, j - j0, matrix.get(j).get(r[i]));
                }
            }
        } catch (ArrayIndexOutOfBoundsException exeption) {
            throw new ArrayIndexOutOfBoundsException("Out of Boundary");
        }

        return X;

    }

    /**
     * Get selected matrix element.
     *
     * @param row    Row number to select.
     * @param column Column number to select.
     * @return Selected matrix element.
     */
    public double getMatrixElement(int row, int column) {
        return matrix.get(row).get(column);
    }

    /**
     * Set selected element in matrix.
     *
     * @param rowElement    Element of selected index in row.
     * @param columnElement Element of selected index in column.
     * @param matrixValue   Value to put into selected matrix index.
     */

    public void setMatrixElement(int rowElement, int columnElement, Double matrixValue) {

        this.getRow(rowElement).set(columnElement, matrixValue);
    }

    /**
     * Adding new row to matrix.
     *
     * @param row Row to add to matrix.
     */
    public void addMatrixRow(ArrayList<Double> row) {

        matrix.add(row);
    }

    /**
     * Get number of matrix row.
     * @return Number of rows in matrix.
     */

    public int getNumOfRows() {
        return matrix.size();
    }

    /**
     * Returns number of columns in matrix.
     *
     * @return Number of columns in matrix.
     */
    public int getNumOfColumns() {
        return matrix.get(0).size();
    }

    /**
     * Returns ArrayList of values of selected row.
     *
     * @param rowNumber Selected row number to return.
     * @return ArrayList with values of selected row.
     */
    public ArrayList<Double> getRow(int rowNumber) {
        return matrix.get(rowNumber);
    }

    /**
     * Returns ArrayList of values of selected column.
     * @param columnNumber Selected column number to return.
     * @return ArrayList with values of selected column.
     */
    public ArrayList<Double> getColumn(int columnNumber) {
        ArrayList<Double> tmp = new ArrayList<Double>();

        for (int i = 0; i <= this.getNumOfRows(); ++i) {
            tmp.add(matrix.get(columnNumber).get(i));
        }

        return tmp;
    }

    /**
     * Displaying content of matrix.
     */
    public void display() {
        System.out.println(matrix);
    }


}
