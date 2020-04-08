package sorting;

/**
 *
 */
public class MergeUpSorter extends Sorter {
    @Override
    public void sort(Comparable[] array) {
        Comparable[] buffer = new Comparable[array.length];

        for (int i=0; i<array.length; i++) {
            buffer[i] = array[i];
        }

        for (int chunk = 1; chunk < array.length; chunk *= 2) {
            for (int lo = 0; lo < array.length - chunk; lo += chunk + chunk) {
                merge(array, buffer, lo, lo + chunk - 1, Math.min(lo + chunk + chunk - 1, array.length - 1));

            }

        }
    }

    public void merge(Comparable[] a, Comparable[] b, int lo, int mid, int hi) {
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
        int n = 7;
        if (args.length > 0) {
            n = Integer.parseInt(args[0]);
        }

        Integer[] randomValues = generateWorstInts(n);

        Sorter selection = new MergeUpSorter();

        selection.demo(randomValues);
    }
}
