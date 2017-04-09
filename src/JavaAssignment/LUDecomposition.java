package JavaAssignment;

import java.util.ArrayList;

/**
 * Created by Wiktor Bednarek
 */
public class LUDecomposition {

    private int[] pivot;
    private int pivsign;
    private Matrix LU;

    private int numberOfMatrixRows;
    private int numberOfMatrixColumns;


    public LUDecomposition(Matrix A) {

        LU = new Matrix(A);
        numberOfMatrixRows = A.getNumOfRows();
        numberOfMatrixColumns = A.getNumOfColumns();
        pivot = new int[numberOfMatrixRows];

        for (int i = 0; i < numberOfMatrixRows; ++i) {
            pivot[i] = i;
        }
        pivsign = 1;
        ArrayList<Double> LUrowi;
        ArrayList<Double> LUcolj = new ArrayList<Double>(numberOfMatrixRows);

        for (int j = 0; j < numberOfMatrixColumns; ++j) {

            for (int i = 0; i < numberOfMatrixRows; ++i) {
                LUcolj.add(i, LU.getMatrixElement(i, j));
            }

            for (int i = 0; i < numberOfMatrixRows; ++i) {
                LUrowi = LU.getRow(i);

                int kmax = Math.min(i, j);
                double s = 0.0;
                for (int k = 0; k < kmax; ++k) {
                    s += LUrowi.get(k) * LUcolj.get(k);
                }

                LUcolj.set(i, LUcolj.get(i) - s);
                LUrowi.set(j, LUcolj.get(i));
            }

            int p = j;

            for (int i = j + 1; i < numberOfMatrixRows; ++i) {
                if (Math.abs(LUcolj.get(i)) > Math.abs(LUcolj.get(p))) {
                    p = i;
                }
            }
            if (p != j) {
                for (int k = 0; k < numberOfMatrixColumns; ++k) {
                    double t = LU.getMatrixElement(p, k);
                    LU.setMatrixElement(p, k, LU.getMatrixElement(j, k));
                    LU.setMatrixElement(j, k, t);
                }

                int k = pivot[p];
                pivot[p] = pivot[j];
                pivot[j] = k;
                pivsign = -pivsign;

            }

            Double el = LU.getMatrixElement(j, j);
            if (j < numberOfMatrixRows & el != 0.0) {
                for (int i = j + 1; i < numberOfMatrixRows; ++i) {
                    LU.setMatrixElement(i, j, (LU.getMatrixElement(i, j) / LU.getMatrixElement(j, j)));
                }
            }

        }

    }

    public Matrix getLowerMatrix() {
        Matrix X = new Matrix(numberOfMatrixRows, numberOfMatrixColumns);
        Matrix L = new Matrix(X);

        for (int i = 0; i < numberOfMatrixRows; i++) {
            for (int j = 0; j < numberOfMatrixColumns; j++) {
                if (i > j) {
                    L.setMatrixElement(i, j, LU.getMatrixElement(i, j));
                } else if (i == j) {
                    L.setMatrixElement(i, j, 1.0);
                } else {
                    L.setMatrixElement(i, j, 0.0);
                }
            }
        }
        return X;
    }

    public Matrix getUpperMatrix() {
        Matrix X = new Matrix(numberOfMatrixColumns, numberOfMatrixColumns);
        Matrix U = new Matrix(X);
        for (int i = 0; i < numberOfMatrixColumns; i++) {
            for (int j = 0; j < numberOfMatrixColumns; j++) {
                if (i <= j) {

                    U.setMatrixElement(i, j, LU.getMatrixElement(i, j));
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


    public double det() {
        if (numberOfMatrixRows != numberOfMatrixColumns) {
            throw new IllegalArgumentException("Matrix must be square.");
        }
        double d = pivsign;
        for (int j = 0; j < numberOfMatrixColumns; j++) {
            d *= LU.getMatrixElement(j, j);

        }
        return d;
    }

    public boolean isNonSingular() {
        for (int j = 0; j < numberOfMatrixColumns; j++) {
            if (LU.getMatrixElement(j, j) == 0)
                return false;
        }
        return true;
    }


    public Matrix solve(Matrix B) {

        if (!this.isNonSingular()) {
            throw new RuntimeException("Your matrix is singular");
        } else {
            // Copy right hand side with pivoting
            int nx = B.getNumOfRows();
            Matrix Xmat = B.getSubMatrix(pivot, 0, nx - 1);

            Matrix X = new Matrix(Xmat);
            Double result = 0.0;
            for (int k = 0; k < numberOfMatrixColumns; k++) {
                for (int i = k + 1; i < numberOfMatrixColumns; i++) {
                    for (int j = 0; j < nx; j++) {
                        result = X.getMatrixElement(k, j) * LU.getMatrixElement(i, k);
                        X.setMatrixElement(i, j, (X.getMatrixElement(i, j) - result));
                    }
                }
            }
            Double division;
            Double subtraction;
            for (int k = numberOfMatrixColumns - 1; k >= 0; k--) {
                for (int j = 0; j < nx; j++) {
                    division = X.getMatrixElement(k, j) / LU.getMatrixElement(k, k);
                    X.setMatrixElement(k, j, division);

                }
                for (int i = 0; i < k; i++) {
                    for (int j = 0; j < nx; j++) {

                        subtraction = X.getMatrixElement(i, j) - X.getMatrixElement(k, j) * LU.getMatrixElement(i, k);
                        X.setMatrixElement(i, j, subtraction);
                    }
                }
            }
            return Xmat;
        }


    }

    public Matrix solve(ArrayList<Double> b) throws RuntimeException {
        return solve(new Matrix(b));
    }

    public Matrix inverseMatrix() {
        return solve(Matrix.identityMatrix(numberOfMatrixColumns, numberOfMatrixRows));
    }
}




