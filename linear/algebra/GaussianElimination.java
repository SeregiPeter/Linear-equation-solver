package linear.algebra;

public class GaussianElimination {
    private int rows;
    private int cols;
    private double[][] matrix;

    public GaussianElimination(int rows, int cols, double[][] matrix) {
        this.rows = rows;
        this.cols = cols;
        this.matrix = new double[rows][cols];
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
    }

    public double[][] getMatrix() {
        double[][] copy = new double[rows][cols];
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                copy[i][j] = matrix[i][j];
            }
        }
        return copy;
    }


    public void setMatrix(double[][] newMatrix) {
        checkMatrixDimensions(newMatrix);
        for(int i = 0; i < newMatrix.length; i++) {
            for(int j = 0; j < newMatrix[0].length; j++) {
                matrix[i][j] = newMatrix[i][j];
            }
        }
    }
    private void checkMatrixDimensions(double[][] newMatrix) {
        if(newMatrix.length != rows || newMatrix[0].length != cols) throw new IllegalArgumentException("The size of the new matrix should be the same as the size of the old one.");
    }
    public int getRows() { return rows;}
    public int getCols() { return cols;}

    private int argMax(int r, int c) {
        int maxIndex = r;
        double maxValue = 0;
        for(int i = r+1; i < rows; i++) {
            if(Math.abs(matrix[i][c]) > maxValue) {
                maxValue = Math.abs(matrix[i][c]);
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    private void swapRows(int r1, int r2) {
        for(int i = 0; i < cols; i++) {
            double temp = matrix[r1][i];
            matrix[r1][i] = matrix[r2][i];
            matrix[r2][i] = temp;
        }
    }

    private void multiplyAndAddRow(int addRow, int mulRow, int colIndex) {
        double f = matrix[addRow][colIndex] / matrix[mulRow][colIndex];
        for(int i = colIndex; i < matrix[0].length; i++) {
            matrix[addRow][i] -= matrix[mulRow][i] * f;
        }
    }

    private void multiplyRow(int rowIndex, int colIndex) {
        double d = matrix[rowIndex][colIndex];
        for(int i = 0; i < matrix[0].length; i++) {
            matrix[rowIndex][i] /= d;
        }
    }

    public void rowEchelonForm() {
        int h = 0;
        int k = 0;
        while(h < rows && k < cols) {
            int i_max = argMax(h, k);
            if(matrix[i_max][k] == 0) k++;
            else {
                swapRows(h, i_max);
                for(int i = h+1; i < rows; i++) {
                    multiplyAndAddRow(i, h, k);
                }
                multiplyRow(h, k);
                h++;
                k++;
            }
        }
    }

    public void backSubstitution() {
        for(int i = rows-1; i >= 0; i--) {
            for(int x = 0; x < rows; x++) if(matrix[i][i] == 0) throw new IllegalArgumentException("There is no solution with these parameters.");
            for(int j = i-1; j >= 0; j--) {
                multiplyAndAddRow(j,i,i);
            }
        }
    }

    public void print() {
        char[] variables = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols - 1; j++) {
                String o = matrix[i][j] >= 0 ? ("+" + matrix[i][j] + variables[j] + "\t") : ("" + matrix[i][j] +  variables[j] + "\t");
                System.out.print(o);
            }
            String o = matrix[i][cols-1] >= 0 ? ("=\t+" + matrix[i][cols-1]) : ("=\t" + matrix[i][cols-1]);
            System.out.println(o);
        }
    }
}