package training.slidingwindow;

/**
 * 1358. Number of Substrings Containing All Three Characters
 *
 * https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/
 *
 * Given a string s consisting only of characters a, b and c.
 *
 * Return the number of substrings containing at least one occurrence of all these characters a, b and c.
 *
 * Example 1:
 *   Input: s = "abcabc"
 *   Output: 10
 *   Explanation: The substrings containing at least one occurrence of the characters a, b and c are
 *   "abc", "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc" and "abc" (again).
 *
 * Example 2:
 *   Input: s = "aaacb"
 *   Output: 3
 *   Explanation: The substrings containing at least one occurrence of the characters a, b and c are "aaacb", "aacb" and "acb".
 *
 * Example 3:
 *   Input: s = "abc"
 *   Output: 1
 *
 * Constraints:
 *   3 <= s.length <= 5 x 10^4
 *   s only consists of a, b or c characters.
 */
public class NumberOfSubstrings {
    public static int LETTER_SHIFT = 97;
    public int numberOfSubstrings(String s) {
        int N = s.length();
        int[] indexes = new int[s.length()];
        int[] cnt = new int[]{0,0,0};
        int substringsCount = 0;

        for (int i = 0; i < N; i++) {
            indexes[i] = charToI(s, i);
        }

        int head = 0;
        int tail = 0;


        int tailMax = N - cnt.length;
        while (tail <= tailMax ) {

            if (isValid(cnt)) {
                while(isValid(cnt)) {
                    cnt[indexes[tail]]--;
                    tail++;

                    substringsCount += N - head + 1;
                }

                continue;
            }

            if (head < N) {
                cnt[indexes[head]]++;
                head++;
            } else {
                break;
            }

        }

        return substringsCount;
    }

    public int charToI(String s, int i) {
        return (int)s.charAt(i) - LETTER_SHIFT;
    }

    public boolean isValid(int[] cnt) {
        return (cnt[0] != 0 && cnt[1] != 0 && cnt[2] != 0);
    }
}
