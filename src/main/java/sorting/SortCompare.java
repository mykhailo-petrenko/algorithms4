package sorting;

import edu.princeton.cs.introcs.StdDraw;
import edu.princeton.cs.introcs.StdStats;
import edu.princeton.cs.introcs.Stopwatch;

import java.awt.*;

public class SortCompare {

    public enum SORTER {
        SELECTION,
        INSERTION,
        SHELL
    }

    public static void main(String[] args) {
        int times = 10;
        int[] NUMS = new int[]{100, 1000, 3000, 5000, 7000, 10000};

        SORTER[] algorithms = new SORTER[]{
            SORTER.SELECTION,
            SORTER.INSERTION,
            SORTER.SHELL
        };

        Color[] colors = new Color[]{
            Color.BLACK,
            Color.CYAN,
            Color.GREEN,
            Color.RED,
            Color.ORANGE,
        };

        String[] colorsLabels = new String[] {
            "Black",
            "Cyan",
            "Green",
            "Red",
            "Orange",
        };

        double[][] measurements = new double[algorithms.length][NUMS.length];
        double maxTime = 0;
        int a = 0;

        for (SORTER algorithm: algorithms) {
            for (int iN = 0; iN < NUMS.length; iN++) {
                int N = NUMS[iN];

                double[] localMeasurements = new double[times];

                for (int t = 0; t < times; t++) {
                    Double[] inputArray = new Double[N];

                    for (int i = 0; i < N; i++) {
                        inputArray[i] = Math.random();
                    }

                    localMeasurements[t] = stopwatch(algorithm, inputArray);
                }

                double sum = 0.0;
                for (double t : localMeasurements) {
                    sum += t;
                }

                measurements[a][iN] = sum / times;

                if (measurements[a][iN] > maxTime) {
                    maxTime = measurements[a][iN];
                }
            }

            a++;
        }

        StdDraw.setCanvasSize(500, 500);
        StdDraw.setXscale(0, NUMS[NUMS.length - 1]);
        StdDraw.setYscale(0, maxTime);

        for (int i = 0; i < algorithms.length; i++) {
            System.out.printf("%15s (%7s)\t", algorithms[i], colorsLabels[i]);
            StdDraw.setPenColor(colors[i]);
            StdDraw.setPenRadius(0.01);

            int k = 0;
            for (double m: measurements[i]) {
                System.out.printf("|\t%f\t", m);
                StdDraw.point(NUMS[k++], m);
            }

            System.out.println("");
        }
    }

    public static Sorter getSorter(SORTER sorter) {
        switch (sorter) {
            case SELECTION:
                return new SelectionSorter();
            case INSERTION:
                return new InsertionSorter();
            case SHELL:
                return new ShellSorter();
            default:
                throw new Error("Please define sorter.");
        }
    }

    public static double stopwatch(SORTER sorter, Comparable[] array) {
        Stopwatch s = new Stopwatch();

        getSorter(sorter).sort(array);

        return s.elapsedTime();
    }
}
