package sorting;

import edu.princeton.cs.introcs.StdDraw;
import edu.princeton.cs.introcs.Stopwatch;

import java.awt.*;
import java.util.Random;

public class SortCompare {
    static final Random random;
    static {
        random = new Random(System.currentTimeMillis());
    }

    public enum SORTER {
        SELECTION,
        INSERTION,
        SHELL,
        MERGE_DOWNSTREAM,
        MERGE_DOWNSTREAM_COPY,
        MERGE_WITH_SHELL,
        MERGE_UP,
        QUICK_SORT,
        QUICK_WITH_SHELL,
    }

    public static void main(String[] args) {
        int times = 10;

        int start = 1000;
        int step = 5000;
        int iterations = 50;

        int[] NUMS = new int[iterations];

        for (int i = 0; i < iterations; i++) {
            NUMS[i] = start + i * step;
        }

        SORTER[] algorithms = new SORTER[]{
//            SORTER.SELECTION,
//            SORTER.INSERTION,
//            SORTER.SHELL,
//            SORTER.MERGE_DOWNSTREAM_COPY,
//            SORTER.MERGE_WITH_SHELL,
            SORTER.MERGE_DOWNSTREAM,
            SORTER.MERGE_UP,
            SORTER.QUICK_SORT,
            SORTER.QUICK_WITH_SHELL,
        };

        Color[] colors = new Color[]{
            Color.BLACK,
            Color.CYAN,
            Color.GREEN,
            Color.RED,
            Color.ORANGE,
            Color.BLUE
        };

        String[] colorsLabels = new String[] {
            "Black",
            "Cyan",
            "Green",
            "Red",
            "Orange",
            "Blue"
        };

        double[][] measurements = new double[algorithms.length][NUMS.length];
        double maxTime = 0;

        for (int iN = 0; iN < NUMS.length; iN++) {
            int N = NUMS[iN];

            double[] localMeasurements = new double[times];
            Double[] inputArrayPrototype = new Double[N];

            for (int i = 0; i < N; i++) {
                inputArrayPrototype[i] = random.nextDouble();
            }

            for (int a = 0; a < algorithms.length; a++) {
                SORTER algorithm = algorithms[a];
                Sorter sorter = getSorter(algorithm);

                for (int t = 0; t < times; t++) {
                    Double[] inputArray = inputArrayPrototype.clone();

                    try {
                        localMeasurements[t] = stopwatch(sorter, inputArray);
                    } catch (RuntimeException e) {
                        System.out.printf("Algorithm: %s, Length: %d\n", algorithm, NUMS[iN]);
                        throw e;
                    }

                    if (!Sorter.isSorted(inputArray)) {
                        System.out.println(algorithm);
                        System.out.println(N);
                        throw new RuntimeException("NOT SORTED!");
                    }
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
        }

        StdDraw.setCanvasSize(500, 500);
        StdDraw.setXscale(0, NUMS[NUMS.length - 1]);
        StdDraw.setYscale(0, maxTime);

        System.out.printf("                       \t\t");

        for (int i = 0; i < NUMS.length; i++) {
            System.out.printf("|\t%d\t", NUMS[i]);
        }

        System.out.println();

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
            case MERGE_DOWNSTREAM:
                return new MergeDownstreamSorter();
            case MERGE_DOWNSTREAM_COPY:
                return new MergeDownstreamCopySorter();
            case MERGE_WITH_SHELL:
                return new MergeWithShellSorter();
            case MERGE_UP:
                return new MergeUpSorter();
            case QUICK_SORT:
                return new QuickSort();
            case QUICK_WITH_SHELL:
                return new QuickWithShellSort();
            default:
                throw new Error("Please define sorter.");
        }
    }

    public static double stopwatch(Sorter sorter, Comparable[] array) {
        Stopwatch s = new Stopwatch();

        sorter.sort(array);

        return s.elapsedTime();
    }
}
