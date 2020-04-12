package training;

import java.util.HashSet;
import java.util.Set;

public class HappyNumbers {
    public static boolean isHappy(int n) {
        Set<Integer> results = new HashSet<>();

        while (!results.contains(n)) {
            results.add(n);
            n = sum(n);

            if (n == 1) {
                return true;
            }
        }

        return false;
    }

    public static int sum(int n) {
        int prev = n;
        int digit;
        int sum = 0;

        while (n > 0) {
            n = n / 10;
            digit = prev - (n * 10);
            prev = n;

            sum += digit * digit;
        }

        return sum;
    }

    public static void main(String[] args) {
        System.out.println( isHappy(19) );
    }
}
