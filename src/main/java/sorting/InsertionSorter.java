package sorting;

public class InsertionSorter extends Sorter {

    @Override
    public void sort(Comparable[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i+1; j > 0; j--) {
                if (less(array[j], array[j-1])) {
                    exchange(array, j, j-1);
                } else {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        int n = 10;
        if (args.length > 0) {
            n = Integer.parseInt(args[0]);
        }

        Integer[] randomValues = new Integer[n];

        for (int i = 0; i < n; i++) {
            randomValues[i] = (int)(Math.random() * 100);
        }

        Sorter selection = new InsertionSorter();

        selection.demo(randomValues);
    }
}
