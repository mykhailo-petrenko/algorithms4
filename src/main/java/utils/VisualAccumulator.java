package utils;

import edu.princeton.cs.introcs.StdDraw;

public class VisualAccumulator extends Accumulator {
    public VisualAccumulator(int W, int H) {
        this.setup(W, H, (float)0.05);
    }

    public VisualAccumulator(int W, int H, float penRadius) {
        this.setup(W, H, penRadius);
    }

    private void setup(int W, int H, float penRadius) {
        StdDraw.setXscale(0, W);
        StdDraw.setYscale(0, H);
        StdDraw.setPenRadius(penRadius);
    }

    @Override
    public void addValue(double value) {
        super.addValue(value);

        StdDraw.setPenColor(StdDraw.DARK_GRAY);
        StdDraw.point(N, value);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.point(N, mean());
    }
}
