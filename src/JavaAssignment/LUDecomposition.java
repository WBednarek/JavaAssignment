package JavaAssignment;

import java.util.ArrayList;

/**
 * Created by Wiktor Bednarek on 2017-04-07.
 */
public class LUDecomposition {

    private int[] piv;
    private int pivsign;
    private Matrix LU;

    private int m;
    private int n;


    public LUDecomposition(Matrix A) {

        LU = new Matrix(A);
        m = A.getNumOfRows();
        n = A.getNumOfColumns();
        piv = new int[m];

        for (int i = 0; i < m; ++i) {
            piv[i] = i;
        }
        pivsign = 1;
        ArrayList<Double> LUrowi;
        //double[] LUColj = new double[m];
        ArrayList<Double> LUcolj = new ArrayList<Double>(m);

        for (int j = 0; j < n; ++j) {

            for (int i = 0; i < m; ++i) {
                LUcolj.add(i, LU.getMatrixElement(i, j));
            }

            for (int i = 0; i < m; ++i) {
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

            for (int i = j + 1; i < m; ++i) {
                if (Math.abs(LUcolj.get(i)) > Math.abs(LUcolj.get(p))) {
                    p = i;
                }
            }
            if (p != j) {
                for (int k = 0; k < n; ++k) {
                    double t = LU.getMatrixElement(p, k);
                    LU.setMatrixElement(p, k, LU.getMatrixElement(j, k));
                    LU.setMatrixElement(j, k, t);
                }

                int k = piv[p];
                piv[p] = piv[j];
                piv[j] = k;
                pivsign = -pivsign;

            }

            Double el = LU.getMatrixElement(j, j);
            if (j < m & el != 0.0) {
                for (int i = j + 1; i < m; ++i) {
                    LU.setMatrixElement(i, j, (LU.getMatrixElement(i, j) / LU.getMatrixElement(j, j)));
                }
            }

        }

    }

    public Matrix getL() {
        Matrix X = new Matrix(m, n);
        Matrix L = new Matrix(X);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
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

    public Matrix getU() {
        Matrix X = new Matrix(n, n);
        Matrix U = new Matrix(X);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
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
     * @return piv
     */


    public double det() {
        if (m != n) {
            throw new IllegalArgumentException("Matrix must be square.");
        }
        double d = pivsign;
        for (int j = 0; j < n; j++) {
            d *= LU.getMatrixElement(j, j);

        }
        return d;
    }

    public boolean isNonSingular() {
        for (int j = 0; j < n; j++) {
            if (LU.getMatrixElement(j, j) == 0)
                return false;
        }
        return true;
    }


    public Matrix solve(Matrix B) {

        if (!this.isNonSingular()) {
            throw new RuntimeException("Matrix is singular.");
        } else {
            // Copy right hand side with pivoting
            int nx = B.getNumOfRows();
            Matrix Xmat = B.getSubMatrix(piv, 0, nx - 1);

            Matrix X = new Matrix(Xmat);
            Double result = 0.0;
            // Solving L*Y = B(piv,:)
            for (int k = 0; k < n; k++) {
                for (int i = k + 1; i < n; i++) {
                    for (int j = 0; j < nx; j++) {
                        //X[i][j] -= X[k][j] * LU[i][k];
                        result = X.getMatrixElement(k, j) * LU.getMatrixElement(i, k);
                        X.setMatrixElement(i, j, (X.getMatrixElement(i, j) - result));
                    }
                }
            }
            // Solving U*X = Y;
            Double division;
            Double substracion;
            for (int k = n - 1; k >= 0; k--) {
                for (int j = 0; j < nx; j++) {

                    division = X.getMatrixElement(k, j) / LU.getMatrixElement(k, k);
                    X.setMatrixElement(k, j, division);

                    // X[k][j] /= LU[k][k];
                }
                for (int i = 0; i < k; i++) {
                    for (int j = 0; j < nx; j++) {

                        substracion = X.getMatrixElement(i, j) - X.getMatrixElement(k, j) * LU.getMatrixElement(i, k);
                        X.setMatrixElement(i, j, substracion);
                        // X[i][j] -= X[k][j] * LU[i][k];
                    }
                }
            }
            return Xmat;
        }


    }

    public Matrix solve(ArrayList<Double> b) throws RuntimeException {
        return solve(new Matrix(b));
    }
}




