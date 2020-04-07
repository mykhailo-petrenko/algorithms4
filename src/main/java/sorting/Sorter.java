package sorting;


import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public abstract class Sorter {
    /**
     * Shoud sort array in-place
     * @param array
     */
    abstract public void sort(Comparable[] array);

    /**
     * Is `a` less than `b`
     * @param a
     * @param b
     * @return boolean `true` - `a < b`, `false` - `a >= b`
     */
    public static boolean less(Comparable a, Comparable b) {
        return (a.compareTo(b) < 0);
    }

    /**
     * Swap i-th and j-th elements od array
     * Alias for exchange(...)
     * @param array
     * @param i
     * @param j
     */
    public static void swap(Comparable[] array, int i, int j) {
        exchange(array, i, j);
    }

    /**
     * Swap i-th and j-th elements od array
     * @param array
     * @param i
     * @param j
     */
    public static void exchange(Comparable[] array, int i, int j) {
        Comparable buffer = array[i];
        array[i] = array[j];
        array[j] = buffer;
    }

    public static void show(Comparable[] a) {
        for (Comparable el : a) {
            StdOut.print(el);
            StdOut.print(" ");
        }
        StdOut.println();
    }

    /**
     * Is the all of array elements in ACS order?
     *
     * @param array Sorted array
     * @return boolean true - if sorted, false - if not.
     */
    public static boolean isSorted(Comparable[] array) {
        for (int i=1; i<array.length; i++) {
            if (less(array[i], array[i-1])) {
                return false;
            }
        }

        return true;
    }

    /**
     * Visualize array before and after sorting.
     * Perform sorting between visualisations.
     *
     * @param array Unsorted array
     * @param <T> Element type
     */
    public <T extends Comparable<T>> void demo(T[] array) {
        show(array);

        sort(array);

        show(array);

        if (!isSorted(array)) {
            throw new AssertionError("The array has not been sorted.");
        }
    }

    public void demoSorting(String[] args) {
        String[] a = StdIn.readAllStrings();
        show(a);

        sort(a);

        if (!isSorted(a)) {
            throw new AssertionError("The array has not been sorted.");
        }

        show(a);
    }

    public static Integer[] generateRandomInts(int n) {
        Integer[] randomValues = new Integer[n];

        for (int i = 0; i < n; i++) {
            randomValues[i] = (int)(Math.random() * 100);
        }

        return randomValues;
    }
}
