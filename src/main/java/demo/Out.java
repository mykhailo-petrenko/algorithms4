package demo;

import edu.princeton.cs.introcs.StdOut;
import edu.princeton.cs.introcs.StdRandom;

public class Out {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        double min = Double.parseDouble(args[1]);
        double max = Double.parseDouble(args[2]);

        for (int i = 0; i<n; i++) {
            double result = StdRandom.uniform(min, max);

            StdOut.printf("%.2f\n", result);
        }

        StdOut.println("THE END");
    }
}
