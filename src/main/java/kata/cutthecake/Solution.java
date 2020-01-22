package kata.cutthecake;

/**
 * The cake is not a lie!
 * ======================
 *
 * Commander Lambda has had an incredibly successful week: she completed the first test run of her LAMBCHOP doomsday device, she captured six key members of the Bunny Rebellion, and she beat her personal high score in Tetris. To celebrate, she's ordered cake for everyone - even the lowliest of minions! But competition among minions is fierce, and if you don't cut exactly equal slices of cake for everyone, you'll get in big trouble.
 *
 * The cake is round, and decorated with M&Ms in a circle around the edge. But while the rest of the cake is uniform, the M&Ms are not: there are multiple colors, and every minion must get exactly the same sequence of M&Ms. Commander Lambda hates waste and will not tolerate any leftovers, so you also want to make sure you can serve the entire cake.
 *
 * To help you best cut the cake, you have turned the sequence of colors of the M&Ms on the cake into a string: each possible letter (between a and z) corresponds to a unique color, and the sequence of M&Ms is given clockwise (the decorations form a circle around the outer edge of the cake).
 *
 * Write a function called solution(s) that, given a non-empty string less than 200 characters in length describing the sequence of M&Ms, returns the maximum number of equal parts that can be cut from the cake without leaving any leftovers.
 *
 * Languages
 * =========
 *
 * To provide a Python solution, edit solution.py
 * To provide a Java solution, edit Solution.java
 *
 * Test cases
 * ==========
 * Your code should pass the following test cases.
 * Note that it may also be run against hidden test cases not shown here.
 *
 * -- Java cases --
 * Input:
 * Solution.solution("abcabcabcabc")
 * Output:
 *     4
 *
 * Input:
 * Solution.solution("abccbaabccba")
 * Output:
 *     2
 */
public class Solution {
    public static int solution(String s) {
        int l = s.length();
        int chunkSize = 1;

        while (chunkSize < l) {
            if (validateCake(chunkSize, s)) {
                break;
            }

            chunkSize = searchNextDivider(chunkSize, l);
        }

        return l / chunkSize;
    }

    static boolean validateCake(int chunkSize, String s) {
        int length = s.length();
        for (int offset = chunkSize; offset < length; offset += chunkSize) {
            if (!s.regionMatches(0, s, offset, chunkSize)) {
                return false;
            }
        }
        return true;
    }

    static int searchNextDivider(int start, int length) {
        int result = start + 1;

        while (length % result != 0) {
            result++;
        }

        return result;
    }
}
