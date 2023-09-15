package linear;
import linear.algebra.GaussianElimination;

public class EquationSolver {
    public static double[][] stringsToDoubles(String[] inp) {
        int rows = inp.length;
        int cols = inp[0].split(",").length;
        double[][] outp = new double[rows][cols];
        for(int i = 0; i < inp.length; i++) {
            String[] splitted = inp[i].split(",");
            for(int j = 0; j < cols; j++) {
                outp[i][j] = Double.parseDouble(splitted[j]);
            }
        }
        return outp;
    }

    public static void main(String[] args) {
        double[][] inp = stringsToDoubles(args);
        GaussianElimination g = new GaussianElimination(inp.length, inp[0].length, inp);
        g.print();
        g.rowEchelonForm();
        g.print();
        g.backSubstitution();
        g.print();

    }
}