package sorting;

public class MergeDownstreamSorter extends Sorter {
    @Override
    public void sort(Comparable[] array) {

    }

    public void merge(Comparable[] a, Comparable b, int min, int mid, int hi) {

    }

    public static void main(String[] args) {
        int n = 10;
        if (args.length > 0) {
            n = Integer.parseInt(args[0]);
        }

        Integer[] randomValues = generateRandomInts(n);

        Sorter selection = new MergeDownstreamSorter();

        selection.demo(randomValues);
    }
}
