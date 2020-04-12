package sorting;

import edu.princeton.cs.introcs.StdRandom;

public class QuickSort extends Sorter {
    @Override
    public void sort(Comparable[] array) {
        shuffle(array);

        sort(array, 0, array.length -1);
    }

    public static void sort(Comparable[] array, int start, int end) {
        if (start >= end) {
            return;
        }

        int median = partition(array, start, end);

        sort(array, start, median - 1);
        sort(array, median + 1, end);
    }

    protected static int partition(Comparable[] array, int start, int end) {
        int i = start;
        int j = end + 1;

        while (true) {
            while (less(array[++i], array[start])) {
                if (i == end) {
                    break;
                }
            }

            while (less(array[start], array[--j])) {
                if (j == start) {
                    break;
                }
            }

            if (i >= j) {
                break;
            }

            swap(array, i, j);
        }

        swap(array, start, j);

        return j;
    }

    private static void shuffle(Comparable[] array) {
        StdRandom.shuffle(array);
    }

    public static void main(String[] args) {
        int n = 100;
        if (args.length > 0) {
            n = Integer.parseInt(args[0]);
        }

//        for (int i = n; i < 300; i++) {
            Integer[] values = generateWorstInts(n);

            Sorter selection = new MergeUpSorter();

            selection.demo(values);
//        }
    }
}
