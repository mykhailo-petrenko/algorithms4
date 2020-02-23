package sorting;

public class SelectionSorter extends Sorter {
    @Override
    public void sort(Comparable[] array) {
        int n = array.length;
        int smallestIndex;

        for (int i = 0; i < n; i++) {
            smallestIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (less(array[j], array[smallestIndex])) {
                    smallestIndex = j;
                }
            }

            if (i != smallestIndex) {
                exchange(array, i, smallestIndex);
            }
        }
    }

    public static void main(String[] args) {
        int n = 10;
        if (args.length > 0) {
            n = Integer.parseInt(args[0]);
        }

        Integer[] randomValues = generateRandomInts(n);

        Sorter selection = new SelectionSorter();

        selection.demo(randomValues);
    }
}
