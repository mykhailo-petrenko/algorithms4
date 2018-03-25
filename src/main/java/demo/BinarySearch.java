package demo;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public class BinarySearch {

    public static int rank(int v, int a[]) {
        int min = 0;
        int max = a.length - 1;

        while (min <= max) {
            int mid = ((max - min) / 2) + min;

            if (v > a[mid]) {
                min = mid + 1;
            } else if (v < a[mid]) {
                max = mid - 1;
            } else {
                return mid;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int[] list = in.readAllInts();
        int key;

        while(!StdIn.isEmpty()) {
            key = rank(StdIn.readInt(), list);
            if (key != -1) {
                StdOut.println(key);
            }
        }
    }
}
