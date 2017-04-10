package JavaAssignment.Model;

import java.util.ArrayList;

/**
 * Calculate LU Decomposition of selected {@link Matrix}.
 * @author Wiktor Bednarek.
 */
public class LUDecomposition {
    /**
     * Array of pivot.
     */
    private int[] pivot;
    /**
     * Sign of pivot.
     */
    private int singOfPivot;
    /**
     * Matrix field for decomposition.
     */
    private Matrix LUMatrix;

    /**
     * Number of matrix rows.
     */
    private int numberOfMatrixRows;
    /**
     * Number of matrix columns.
     */
    private int numberOfMatrixColumns;

    /**
     * LU Decomposition constructor.
     *
     * @param A Square matrix to decompose.
     */
    public LUDecomposition(Matrix A) {

        LUMatrix = new Matrix(A);
        numberOfMatrixRows = A.getNumOfRows();
        numberOfMatrixColumns = A.getNumOfColumns();
        pivot = new int[numberOfMatrixRows];


        for (int i = 0; i < numberOfMatrixRows; ++i) {
            pivot[i] = i;
        }
        singOfPivot = 1;
        ArrayList<Double> LUrowi;
        ArrayList<Double> LUcolj = new ArrayList<Double>(numberOfMatrixRows);
        //Initial loop
        for (int j = 0; j < numberOfMatrixColumns; ++j) {

            for (int i = 0; i < numberOfMatrixRows; ++i) {
                LUcolj.add(i, LUMatrix.getMatrixElement(i, j));
            }

            for (int i = 0; i < numberOfMatrixRows; ++i) {
                LUrowi = LUMatrix.getRow(i);
                //Dot product.
                int kmax = Math.min(i, j);
                double s = 0.0;
                for (int k = 0; k < kmax; ++k) {
                    s += LUrowi.get(k) * LUcolj.get(k);
                }

                LUcolj.set(i, LUcolj.get(i) - s);
                LUrowi.set(j, LUcolj.get(i));
            }

            //Pivot calculation and exchange
            int p = j;

            for (int i = j + 1; i < numberOfMatrixRows; ++i) {
                if (Math.abs(LUcolj.get(i)) > Math.abs(LUcolj.get(p))) {
                    p = i;
                }
            }
            if (p != j) {
                for (int k = 0; k < numberOfMatrixColumns; ++k) {
                    double t = LUMatrix.getMatrixElement(p, k);
                    LUMatrix.setMatrixElement(p, k, LUMatrix.getMatrixElement(j, k));
                    LUMatrix.setMatrixElement(j, k, t);
                }

                int k = pivot[p];
                pivot[p] = pivot[j];
                pivot[j] = k;
                singOfPivot = -singOfPivot;

            }

            Double el = LUMatrix.getMatrixElement(j, j);
            if (j < numberOfMatrixRows & el != 0.0) {
                for (int i = j + 1; i < numberOfMatrixRows; ++i) {
                    LUMatrix.setMatrixElement(i, j, (LUMatrix.getMatrixElement(i, j) / LUMatrix.getMatrixElement(j, j)));
                }
            }

        }

    }

    /**
     * Get lower triangular matrix.
     * @return Matrix object of lower triangular matrix.
     */
    public Matrix getLowerMatrix() {
        Matrix X = new Matrix(numberOfMatrixRows, numberOfMatrixColumns);
        Matrix L = new Matrix(X);

        for (int i = 0; i < numberOfMatrixRows; i++) {
            for (int j = 0; j < numberOfMatrixColumns; j++) {
                if (i > j) {
                    L.setMatrixElement(i, j, LUMatrix.getMatrixElement(i, j));
                } else if (i == j) {
                    L.setMatrixElement(i, j, 1.0);
                } else {
                    L.setMatrixElement(i, j, 0.0);
                }
            }
        }
        return X;
    }

    /**
     * Get upper triangular matrix.
     * @return Matrix object of upper triangular matrix.
     */
    public Matrix getUpperMatrix() {
        Matrix X = new Matrix(numberOfMatrixColumns, numberOfMatrixColumns);
        Matrix U = new Matrix(X);
        for (int i = 0; i < numberOfMatrixColumns; i++) {
            for (int j = 0; j < numberOfMatrixColumns; j++) {
                if (i <= j) {

                    U.setMatrixElement(i, j, LUMatrix.getMatrixElement(i, j));
                } else {
                    U.setMatrixElement(i, j, 0.0);
                }
            }
        }
        return X;
    }

    /**
     * Return pivot permutation vector
     *
     * @return pivot
     */

    /**
     *  Compute matrix determinant.
     * @return Determinant of matrix.
     */
    public double det() {
        if (numberOfMatrixRows != numberOfMatrixColumns) {
            throw new IllegalArgumentException("Matrix must be square.");
        }
        double d = singOfPivot;
        for (int j = 0; j < numberOfMatrixColumns; j++) {
            d *= LUMatrix.getMatrixElement(j, j);

        }
        return d;
    }

    /**
     * Check if matrix is singular.
     *
     * @return Boolean value if martix is non singular.
     */
    public boolean isMatrixNonSingular() {
        for (int j = 0; j < numberOfMatrixColumns; j++) {
            if (LUMatrix.getMatrixElement(j, j) == 0)
                return false;
        }
        return true;
    }

    /**
     * Solve following problem A*X = B
     * @param B Matrix with number of rows of A and any number of columns.
     * @return Object Matrix XMat where L*U*XMat = B(piv,:)
     * @exception RuntimeException That matrix is singular.
     */
    public Matrix solve(Matrix B) {

        if (!this.isMatrixNonSingular()) {
            throw new RuntimeException("Your matrix is singular");
        } else {
            // Initialise local Matrix with pivoting.
            int nx = B.getNumOfRows();
            Matrix XMat = B.getSubMatrix(pivot, 0, nx - 1);

            Matrix X = new Matrix(XMat);
            Double result = 0.0;
            //Compute L*Y = B(piv, :)
            for (int k = 0; k < numberOfMatrixColumns; k++) {
                for (int i = k + 1; i < numberOfMatrixColumns; i++) {
                    for (int j = 0; j < nx; j++) {
                        result = X.getMatrixElement(k, j) * LUMatrix.getMatrixElement(i, k);
                        X.setMatrixElement(i, j, (X.getMatrixElement(i, j) - result));
                    }
                }
            }
            Double division;
            Double subtraction;
            //Compute U*X = Y;
            for (int k = numberOfMatrixColumns - 1; k >= 0; k--) {
                for (int j = 0; j < nx; j++) {
                    division = X.getMatrixElement(k, j) / LUMatrix.getMatrixElement(k, k);
                    X.setMatrixElement(k, j, division);

                }
                for (int i = 0; i < k; i++) {
                    for (int j = 0; j < nx; j++) {

                        subtraction = X.getMatrixElement(i, j) - X.getMatrixElement(k, j) * LUMatrix.getMatrixElement(i, k);
                        X.setMatrixElement(i, j, subtraction);
                    }
                }
            }
            return XMat;
        }

    }

    /**
     * Solve following problem A*b = Y.
     * @param b One dimensional ArrayList of Doubles (vector) which size is equal to matrix size.
     * @return Matrix XMat where L*U*XMat = B(piv,:).
     * @throws RuntimeException This matrix is singular.
     */
    public Matrix solve(ArrayList<Double> b) throws RuntimeException {
        return solve(new Matrix(b));
    }

    /**
     * Calculate inverse matrix.
     * @return Inverted Matrix.
     */
    public Matrix inverseMatrix() {
        return solve(Matrix.identityMatrix(numberOfMatrixColumns, numberOfMatrixRows));
    }
}




