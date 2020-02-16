package withgoogle.foobar.bringingagun;

import edu.princeton.cs.introcs.StdDraw;

import java.util.Objects;

public class GunReflectionSandbox {
    public static void main(String[] args) {
        solution(new int[]{3,2}, new int[]{1,1}, new int[]{2,1}, 4); // -> 7
        // [1, 0], [1, 2], [1, -2], [3, 2], [3, -2], [-3, 2], and [-3, -2].
    }

    public static int solution(int [] dimensions, int[] me, int[] guard, int distance) {
        reflections(dimensions, me, guard, distance);

        return 0;
    }

    public static class Direction {
        public int x;
        public int y;

        public Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Direction)) return false;
            Direction direction = (Direction) o;
            return x == direction.x && y == direction.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public static void reflections(int [] dimensions, int[] me, int[] guard, int distance) {
        int R = distance;
        int R2 = R * R;
        double W = dimensions[0];
        double H = dimensions[1];
        int Xmin = (int) Math.floor((me[0] - R)/W);
        int Xmax = (int) Math.ceil((me[0] + R)/W);
        int Ymin = (int) Math.floor((me[1] - R)/H);
        int Ymax = (int) Math.ceil((me[1] + R)/H);

        System.out.printf("[%f : %f] [%f : %f]\n", Xmin * W, Xmax * W, Ymin * H, Ymax * H);

        StdDraw.setXscale(Xmin * W, Xmax * W);
        StdDraw.setYscale(Ymin * H, Ymax * H);
        StdDraw.setPenRadius(0.005);
        StdDraw.arc(me[0], me[1], R, 0, 360);

        StdDraw.line(0, Ymin * H, 0, Ymax * H);
        StdDraw.line(Xmin * W, 0, Xmax * W, 0);

        StdDraw.setPenRadius(0.05);

        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.point(me[0], me[1]);

        StdDraw.setPenRadius(0.01);
        int xm, xg, ym, yg;

        for (int nx = Xmin; nx <= Xmax; nx++) {
            if (nx % 2 != 0) {
                xm = (nx - 1) * (int) W + me[0];
                xg = (nx - 1) * (int) W + guard[0];
            } else {
                xm = nx * (int) W - me[0];
                xg = nx * (int) W - guard[0];
            }
            for (int ny = Ymin; ny <= Ymax; ny++) {
                if (ny % 2 != 0) {
                    ym = (ny - 1) * (int) H + me[1];
                    yg = (ny - 1) * (int) H + guard[1];
                } else {
                    ym = ny * (int) H - me[1];
                    yg = ny * (int) H - guard[1];
                }

                if (!(nx == 1 && ny == 1)) {
                    if (distance(me[0], me[1], xm, ym) <= R2) {
                        StdDraw.setPenColor(StdDraw.GREEN);
                        StdDraw.point(xm, ym);
                    }
                }

                if (distance(me[0], me[1], xg, yg) <= R2) {
                    StdDraw.setPenColor(StdDraw.RED);
                    StdDraw.point(xg, yg);
                }

                System.out.printf("[%d, %d] -> [%d, %d]\n", xm, ym, xg, yg);
            }
        }
    }

    public static int distance(int x0, int y0, int x1, int y1) {
        return (int)Math.round(Math.pow((x1 - x0), 2) + Math.pow((y1 - y0), 2));
    }

    public static int magnitude(int x, int y) {
        return (x * x) + (y * y);
    }
}
