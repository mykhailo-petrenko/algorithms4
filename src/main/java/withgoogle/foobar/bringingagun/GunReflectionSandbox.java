package withgoogle.foobar.bringingagun;

import edu.princeton.cs.introcs.StdDraw;

import java.util.*;

public class GunReflectionSandbox {
    public static void main(String[] args) {
        solution(new int[]{3,2}, new int[]{1,1}, new int[]{2,1}, 4); // -> 7
        // [1, 0], [1, 2], [1, -2], [3, 2], [3, -2], [-3, 2], and [-3, -2].
    }

    public static int solution(int [] dimensions, int[] me, int[] guard, int distance) {
        System.out.println( reflections(dimensions, me, guard, distance) );

        return 0;
    }

    public static class Direction {
        public double x;
        public double y;

        public Direction(double x, double y) {
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

    public static int reflections(int [] dimensions, int[] me, int[] guard, int distance) {
        int R = distance;
        int R2 = R * R;
        double W = dimensions[0];
        double H = dimensions[1];
        int NXmax = (int)Math.ceil(R / W);
        int NYmax = (int)Math.ceil(R / H);
        int Xmax = (int)(NXmax * W);
        int Xmin = -Xmax;
        int Ymax = (int)(NYmax * W);
        int Ymin = -Ymax;

        Map<Direction, Double> myReflections = new HashMap<>();
        Set<Direction> guardReflections = new HashSet<>();
        List<int[]> guardReflectionsRaw = new ArrayList<>();

        StdDraw.setXscale(Xmin, Xmax);
        StdDraw.setYscale(Ymin, Ymax);
        StdDraw.setPenRadius(0.001);
        StdDraw.arc(0, 0, R, 0, 360);

        StdDraw.line(0, Ymin, 0, Ymax);
        StdDraw.line(Xmin, 0, Xmax, 0);

        StdDraw.setPenRadius(0.01);

        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.point(0, 0);

        StdDraw.setPenRadius(0.01);
        int xm, xg, ym, yg;
        int myMagnitude2;
        int guardMagnitude2;

        double myMagnitude;
        double guardMagnitude;

        for (int nx = -NXmax; nx <= NXmax; nx++) {
            if (nx % 2 != 0) {
                xm = (nx - 1) * (int) W;
                xg = (nx - 1) * (int) W + guard[0] - me[0];
            } else {
                xm = nx * (int) W- 2*me[0];
                xg = nx * (int) W - guard[0] - me[0];
            }
            for (int ny = -NYmax; ny <= NYmax; ny++) {
                if (ny % 2 != 0) {
                    ym = (ny - 1) * (int) H;
                    yg = (ny - 1) * (int) H + guard[1] - me[1];
                } else {
                    ym = ny * (int) H - 2*me[1];
                    yg = ny * (int) H - guard[1] - me[1];
                }

                if (!(nx == 1 && ny == 1)) {
                    myMagnitude2 = magnitude2(xm, ym);
                    if (myMagnitude2 <= R2) {
                        StdDraw.setPenColor(StdDraw.GREEN);
                        StdDraw.point(xm, ym);
                        myMagnitude = Math.sqrt(myMagnitude2);
                        Direction myDirection = unitVector(xm, ym, myMagnitude);

                        if (myReflections.containsKey(myDirection)) {
                            if (myReflections.get(myDirection) > myMagnitude) {
                                myReflections.put(myDirection, myMagnitude);
                            }
                        } else {
                            myReflections.put(myDirection, myMagnitude);
                        }
                    }
                }

                guardMagnitude2 = magnitude2(xg, yg);
                if (guardMagnitude2 <= R2) {
                    StdDraw.setPenColor(StdDraw.RED);
                    StdDraw.point(xg, yg);
                    guardReflectionsRaw.add(new int[]{xg, yg, guardMagnitude2});
                }

                System.out.printf("[%d, %d] -> [%d, %d]\n", xm, ym, xg, yg);
            }
        }

        for (int[] guardRaw: guardReflectionsRaw) {
            guardMagnitude = Math.sqrt(guardRaw[2]);
            Direction guardDirection = unitVector(guardRaw[0], guardRaw[1], guardMagnitude);

            if (guardReflections.contains(guardDirection)) {
                continue;
            }

            if (myReflections.containsKey(guardDirection)) {
                myMagnitude = myReflections.get(guardDirection);

                if (myMagnitude < guardMagnitude) {
                    continue;
                }
            }

            guardReflections.add(guardDirection);
        }

        return guardReflections.size();
    }

    public static Direction unitVector(int x, int y, double magnitude) {
        return new Direction(x / magnitude, y / magnitude);
    }

    public static int magnitude2(int x, int y) {
        return (x * x) + (y * y);
    }
}
