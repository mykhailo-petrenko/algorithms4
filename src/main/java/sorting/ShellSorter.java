package sorting;

/**
 * Shell Sort
 * the idea to do he h-sorted array, and then decrease h incrementally to h=1.
 */
public class ShellSorter extends Sorter {
    @Override
    public void sort(Comparable[] array) {
        int n = array.length;

        int h = 1;
        int hMax = n / 3;

        while (h < hMax) {
            h = h * 3 + 1;
        }

        while (h > 0) {
            for (int j = h; j < n; j++) {
                for (int i = j; i >= h; i -= h) {
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

    public static void main(String args[]) {
        int n = 10;
        if (args.length > 0) {
            n = Integer.parseInt(args[0]);
        }

        Integer[] randomValues = generateRandomInts(n);

        Sorter s = new ShellSorter();

        s.demo(randomValues);
    }
}
