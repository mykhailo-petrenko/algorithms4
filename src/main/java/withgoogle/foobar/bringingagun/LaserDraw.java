package withgoogle.foobar.bringingagun;

import edu.princeton.cs.introcs.StdDraw;

public class LaserDraw {

    public static int SIZE = 800;

    public LaserDraw(int [] size, int radius) {
        double NXmax = Math.ceil(radius / (double)size[0]);
        double NYmax = Math.ceil(radius / (double)size[1]);
        int Xmax = (int)(NXmax * size[0]);
        int Ymax = (int)(NYmax * size[1]);
        int dimension = Math.max(Xmax, Ymax);

        StdDraw.setCanvasSize(SIZE, SIZE);
        StdDraw.setXscale(-dimension, dimension);
        StdDraw.setYscale(-dimension, dimension);

        StdDraw.setPenRadius(0.001);

        StdDraw.line(0, -dimension, 0, dimension);
        StdDraw.line(-dimension, 0, dimension, 0);

        StdDraw.arc(0, 0, radius, 0, 360);
    }

    public void me(int x, int y) {
        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.point(x, y);
    }

    public void guard(int x, int y) {
        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.point(x, y);
    }

    public void ray(int x, int y) {
        StdDraw.setPenRadius(0.001);
        StdDraw.setPenColor(StdDraw.CYAN);
        StdDraw.line(0, 0, x, y);
    }
}
