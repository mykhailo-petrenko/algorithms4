package withgoogle.foobar.elevatormaintenance;

import java.util.Arrays;
import java.util.Comparator;

/**
 * foobar:~/elevator-maintenance mihalytch$ cat readme.txt
 * Elevator Maintenance
 * ====================
 *
 * You've been assigned the onerous task of elevator maintenance - ugh! It wouldn't be so bad, except that all the elevator documentation has been lying in a disorganized pile at the bottom of a filing cabinet for years, and you don't even know what elevator version numbers you'll be working on.
 *
 * Elevator versions are represented by a series of numbers, divided up into major, minor and revision integers. New versions of an elevator increase the major number, e.g. 1, 2, 3, and so on. When new features are added to an elevator without being a complete new version, a second number named "minor" can be used to represent those new additions, e.g. 1.0, 1.1, 1.2, etc. Small fixes or maintenance work can be represented by a third number named "revision", e.g. 1.1.1, 1.1.2, 1.2.0, and so on. The number zero can be used as a major for pre-release versions of elevators, e.g. 0.1, 0.5, 0.9.2, etc (Commander Lambda is careful to always beta test her new technology, with her loyal henchmen as subjects!).
 *
 * Given a list of elevator versions represented as strings, write a function solution(l) that returns the same list sorted in ascending order by major, minor, and revision number so that you can identify the current elevator version. The versions in list l will always contain major numbers, but minor and revision numbers are optional. If the version contains a revision number, then it will also have a minor number.
 *
 * For example, given the list l as ["1.1.2", "1.0", "1.3.3", "1.0.12", "1.0.2"], the function solution(l) would return the list ["1.0", "1.0.2", "1.0.12", "1.1.2", "1.3.3"]. If two or more versions are equivalent but one version contains more numbers than the others, then these versions must be sorted ascending based on how many numbers they have, e.g ["1", "1.0", "1.0.0"]. The number of elements in the list l will be at least 1 and will not exceed 100.
 *
 * Constraints
 * =========
 *
 * Your code will be compiled using standard Java 8. All tests will be run by calling the solution() method inside the Solution class
 *
 * Execution time is limited.
 *
 * Wildcard imports and some specific classes are restricted (e.g. java.lang.ClassLoader). You will receive an error when you verify your solution if you have used a blacklisted class.
 *
 * Third-party libraries, input/output operations, spawning threads or processes and changes to the execution environment are not allowed.
 *
 * Your solution must be under 32000 characters in length including new lines and and other non-printing characters.
 *
 * Test cases
 * ==========
 * Your code should pass the following test cases.
 * Note that it may also be run against hidden test cases not shown here.
 *
 * -- Java cases --
 * Input:
 * Solution.solution({"1.11", "2.0.0", "1.2", "2", "0.1", "1.2.1", "1.1.1", "2.0"})
 * Output:
 *     0.1,1.1.1,1.2,1.2.1,1.11,2,2.0,2.0.0
 *
 * Input:
 * Solution.solution({"1.1.2", "1.0", "1.3.3", "1.0.12", "1.0.2"})
 * Output:
 *     1.0,1.0.2,1.0.12,1.1.2,1.3.3
 */
public class Solution {
    public static class VersionComparator implements Comparator<int[]> {
        @Override
        public int compare(int[] o1, int[] o2) {
            int l = Math.min(o1.length, o2.length);
            int diff;

            for (int i = 0; i <l; i++) {
                diff = o1[i] - o2[i];

                if (diff == 0) {
                    continue;
                }

                return diff;
            }

            return o1.length - o2.length;
        }
    }
    public static String[] solution(String[] l) {
        int[][] r = new int[l.length][];

        for (int i = 0; i < l.length; i++) {
            String[] b = l[i].split("\\.");
            r[i] = new int[b.length];
            for (int j = 0; j < b.length; j++) {
                r[i][j] = Integer.parseInt(b[j], 10);
            }
        }

        Arrays.sort(r, new VersionComparator());

        for (int i = 0; i < l.length; i++) {
            String s = Integer.toString(r[i][0]);
            for (int j = 1; j < r[i].length; j++) {
                s += "." + Integer.toString(r[i][j]);
            }
            l[i] = s;
        }

        return l;
    }
}
