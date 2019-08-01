package sorting;

public class InsertSorter extends Sorter {

    @Override
    public void sort(Comparable[] array) {

    }

    public static void main(String[] args) {
        Sorter sorter = new InsertSorter();

        sorter.demoSorting(args);
    }
}
