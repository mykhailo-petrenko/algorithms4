package sorting;

/**
 * Use ShellSort for small pieces of array
 */
public class MergeWithShellSorter extends Sorter {
    private static final int MAX_LENGTH_FOR_SHELLSORT = 100;
    @Override
    public void sort(Comparable[] array) {
        Comparable[] buffer = new Comparable[array.length];

        for (int i=0; i<array.length; i++) {
            buffer[i] = array[i];
        }

        merge(array, buffer, 0,array.length - 1);
    }

    public void merge(Comparable[] a, Comparable[] b, int lo, int hi) {
        int delta = hi - lo;

        if (delta <= MAX_LENGTH_FOR_SHELLSORT) {
            shellSort(a, lo, hi);
            return;
        }

        int mid = (delta/ 2) + lo;
        if (lo < mid) {
            merge(a, b, lo, mid);
        }

        if (mid < hi) {
            merge(a, b, mid + 1, hi);
        }

        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                b[k] = a[j++];
            } else if (j > hi) {
                b[k] = a[i++];
            } else if (less(a[i], a[j])) {
                b[k] = a[i++];
            } else {
                b[k] = a[j++];
            }
        }

        for (int k = lo; k <= hi; k++) {
            a[k] = b[k];
        }
    }

    public void shellSort(Comparable[] array, int start, int end) {
        int n = end - start;

        int h = 1;
        int hMax = n / 3;

        while (h < hMax) {
            h = h * 3 + 1;
        }

        while (h > 0) {
            for (int j = h + start; j <= end; j++) {
                for (int i = j; i >= h + start; i -= h) {
                    if (less(array[i], array[i-h])) {
                        exchange(array, i, i-h);
                    } else {
                        break;
                    }
                }
            }

            h = h / 3;
        }
    }

    public static void main(String[] args) {
        int n = 10;
        if (args.length > 0) {
            n = Integer.parseInt(args[0]);
        }

        Integer[] randomValues = generateRandomInts(n);

        Sorter selection = new MergeWithShellSorter();

        selection.demo(randomValues);
    }
}
