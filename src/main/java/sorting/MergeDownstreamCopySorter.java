package sorting;

public class MergeDownstreamCopySorter extends Sorter {
    @Override
    public void sort(Comparable[] array) {
        Comparable[] buffer = new Comparable[array.length];

        for (int i=0; i<array.length; i++) {
            buffer[i] = array[i];
        }

        merge(array, buffer, 0,array.length - 1);
    }

    public void merge(Comparable[] a, Comparable[] b, int lo, int hi) {
        int mid = ((hi - lo) / 2) + lo;
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

        Sorter selection = new MergeDownstreamCopySorter();

        selection.demo(randomValues);
    }
}
