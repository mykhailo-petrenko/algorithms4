package sorting;


import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public abstract class Sorter {
    abstract public void sort(Comparable[] array);

    public static boolean less(Comparable a, Comparable b) {
        return (a.compareTo(b) < 0);
    }

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

    public static boolean isSorted(Comparable[] array) {
        for (int i=1; i<array.length; i++) {
            if (less(array[i], array[i-1])) {
                return false;
            }
        }

        return true;
    }

    public <T extends Comparable<T>> void demo(T[] array) {
        show(array);

        sort(array);

        assert isSorted(array);

        show(array);
    }

    public void demoSorting(String[] args) {
        String[] a = StdIn.readAllStrings();
        show(a);

        sort(a);
        assert isSorted(a);

        show(a);
    }
}
