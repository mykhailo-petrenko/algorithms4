package sorting;

public class QuickWithShellSort extends QuickSort {
    public static int SWITCH_TO_SHELL_BOUND = 7;
    public static final ShellSorter shell = new ShellSorter();

    public static void sort(Comparable[] array, int start, int end) {
        if (start + SWITCH_TO_SHELL_BOUND >= end) {
            shell.sort(array, start, end);
            return;
        }

        int median = partition(array, start, end);

        sort(array, start, median - 1);
        sort(array, median + 1, end);
    }

    public static void main(String[] args) {
        int n = 100;
        if (args.length > 0) {
            n = Integer.parseInt(args[0]);
        }

//        for (int i = n; i < 300; i++) {
            Integer[] values = generateWorstInts(n);

            Sorter selection = new MergeUpSorter();

            selection.demo(values);
//        }
    }
}
