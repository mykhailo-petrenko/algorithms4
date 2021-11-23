package training.slidingwindow;

/**
 * 1456. Maximum Number of Vowels in a Substring of Given Length
 * https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/
 *
 * Given a string s and an integer k.
 *
 * Return the maximum number of vowel letters in any substring of s with length k.
 *
 * Vowel letters in English are (a, e, i, o, u).
 *
 *
 * Example 1:
 *   Input: s = "abciiidef", k = 3
 *   Output: 3
 *   Explanation: The substring "iii" contains 3 vowel letters.
 *
 * Example 2:
 *   Input: s = "aeiou", k = 2
 *   Output: 2
 *   Explanation: Any substring of length 2 contains 2 vowels.
 *
 * Example 3:
 *   Input: s = "leetcode", k = 3
 *   Output: 2
 *   Explanation: "lee", "eet" and "ode" contain 2 vowels.
 *
 * Example 4:
 *   Input: s = "rhythms", k = 4
 *   Output: 0
 *   Explanation: We can see that s doesn't have any vowel letters.
 *
 * Example 5:
 *   Input: s = "tryhard", k = 4
 *   Output: 1
 *
 * Example 6:
 *   Input: s = "weallloveyou", k = 7
 *   Output: 4
 *
 * Constraints:
 *   1 <= s.length <= 10^5
 *   s consists of lowercase English letters.
 *   1 <= k <= s.length
 */
public class MaxNumberOfVowelsInASubstring {
    public int maxVowels(String s, int k) {
        int N = s.length() - k;
        int max = 0;
        int pointer = 0;

        int hi = 0;
        for (int lo = 0; lo <= N; lo++) {

            while (hi < lo + k) {
                if (isVoWel(s.charAt(hi))) {
                    pointer++;

                    max = Math.max(max, pointer);
                }
                hi++;
            }

            if (isVoWel(s.charAt(lo))) {
                pointer--;
            }
        }

        return max;
    }

    boolean isVoWel(char c) {
        return (
            'a' == c ||
            'e' == c ||
            'i' == c ||
            'o' == c ||
            'u' == c
            );
    }
}
