package demo;

import edu.princeton.cs.introcs.StdDraw;
import edu.princeton.cs.introcs.StdRandom;
import utils.Accumulator;

public class TestVisualAccumulator {
    public static void main(String[] args) {
        int T = Integer.parseInt(args[0]);
        Accumulator acc = new Accumulator();

        StdDraw.setXscale(0, T);
        StdDraw.setYscale(0, 1);

        for (int i=0; i<T; i++) {
            acc.addValue(StdRandom.uniform());
            StdDraw.point(i, acc.mean());
        }

        System.out.println(acc);


    }
}
