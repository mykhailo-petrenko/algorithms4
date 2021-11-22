package training.slidingwindow;

import java.util.Arrays;

/**
 * 1763. Longest Nice Substring
 * https://leetcode.com/problems/longest-nice-substring/
 *
 * A string s is nice if, for every letter of the alphabet that s contains,
 * it appears both in uppercase and lowercase.
 * For example, "abABB" is nice because 'A' and 'a' appear, and 'B' and 'b' appear.
 * However, "abA" is not because 'b' appears, but 'B' does not.
 *
 * Given a string s, return the longest substring of s that is nice.
 * If there are multiple, return the substring of the earliest occurrence.
 * If there are none, return an empty string.
 *
 *
 * Example 1:
 *   Input: s = "YazaAay"
 *   Output: "aAa"
 *   Explanation: "aAa" is a nice string because 'A/a' is the only letter of the alphabet in s, and both 'A' and 'a' appear.
 *   "aAa" is the longest nice substring.
 *
 * Example 2:
 *   Input: s = "Bb"
 *   Output: "Bb"
 *   Explanation: "Bb" is a nice string because both 'B' and 'b' appear. The whole string is a substring.
 *
 * Example 3:
 *   Input: s = "c"
 *   Output: ""
 *   Explanation: There are no nice substrings.
 *
 * Example 4:
 *   Input: s = "dDzeE"
 *   Output: "dD"
 *   Explanation: Both "dD" and "eE" are the longest nice substrings.
 *   As there are multiple longest nice substrings, return "dD" since it occurs earlier.
 *
 * Constraints:
 *   1 <= s.length <= 100
 *   s consists of uppercase and lowercase English letters.
 */
public class LongestNiceSubstring {

    class Store {
        int a = 'a';
        int A = 'A';
        int z = 'z';

        int lowerMask = 0;
        int upperMask = 0;

        int[] lowerMap = new int[z - a + 1];
        int[] upperMap = new int[z - a + 1];

        public void Store() {
            Arrays.fill(lowerMap, 0);
            Arrays.fill(lowerMap, 0);
        }

        public boolean isNice() {
            return lowerMask == upperMask;
        }

        public void put(char c) {
            if (isLower(c)) {
                lowerMask = lowerMask | 1 << (c - a);
                lowerMap[c - a] += 1;
            } else {
                upperMask = upperMask | 1 << (c - A);
                upperMap[c - A] += 1;
            }
        }

        public void pop(char c) {
            if (isLower(c) && lowerMap[c - a] > 0) {
                if (--lowerMap[c - a] == 0) {
                    lowerMask = lowerMask & ~(1 << (c - a));
                }
            }

            if (isUpper(c) && upperMap[c - A] > 0) {
                if (--upperMap[c - A] == 0) {
                    upperMask = upperMask & ~(1 << (c - A));
                }
            }
        }

        boolean isUpper(char c) {
            return c < a;
        }

        boolean isLower(char c) {
            return c >= a;
        }

        public String toString() {
            String s = "----\nLower: ";
            s += Integer.toBinaryString(lowerMask);
            s += "\nUpper: ";
            s += Integer.toBinaryString(upperMask);

            return s;
        }
    }

    public String longestNiceSubstring(String s) {
        int maxLen = 0;
        int maxStart = 0;
        int maxCurrent;

        int N = s.length();

        Store store = new Store();

        int direction = 1;
        int end;
        for (int start = 0; start < N - 1; ) {

            if (direction == 1) {
                end = start;

                while (end < N) {
                    store.put(s.charAt(end));

                    if (store.isNice()) {
                        maxCurrent = end - start + 1;

                        if (maxCurrent > maxLen) {
                            maxStart = start;
                            maxLen = maxCurrent;
                        }
                    }

                    end++;
                }
            } else {
                end = N - 1;

                while (end > start) {
                    if (store.isNice()) {
                        maxCurrent = end - start + 1;

                        if (maxCurrent > maxLen) {
                            maxStart = start;
                            maxLen = maxCurrent;
                        }
                    }

                    store.pop(s.charAt(end));

                    end--;
                }
            }

            direction = (direction == 1) ? -1 : 1;
            store.pop(s.charAt(start));
            start++;

            if (maxLen > N - start) {
                break;
            }
        }

        return s.substring(maxStart, maxStart + maxLen);
    }
}
