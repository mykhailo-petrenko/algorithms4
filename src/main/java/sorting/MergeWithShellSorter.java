package sorting;

/**
 * Use ShellSort for small pieces of array
 */
public class MergeWithShellSorter extends Sorter {
    public static int SWITCH_TO_SHELL_BOUND = 7;
    public static final ShellSorter shell = new ShellSorter();

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

        if (delta <= SWITCH_TO_SHELL_BOUND) {
            shell.sort(a, lo, hi);
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
