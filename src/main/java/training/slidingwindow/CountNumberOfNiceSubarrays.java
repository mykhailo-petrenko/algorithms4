package training.slidingwindow;

/**
 * 1248. Count Number of Nice Subarrays
 * https://leetcode.com/problems/count-number-of-nice-subarrays/
 *
 * Given an array of integers nums and an integer k.
 * A continuous subarray is called nice if there are k odd numbers on it.
 *
 * Return the number of nice sub-arrays.
 *
 *
 * Example 1:
 *   Input: nums = [1,1,2,1,1], k = 3
 *   Output: 2
 *   Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].
 *
 * Example 2:
 *   Input: nums = [2,4,6], k = 1
 *   Output: 0
 *   Explanation: There is no odd numbers in the array.
 *
 * Example 3:
 *   Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2
 *   Output: 16
 *
 * Constraints:
 *   1 <= nums.length <= 50000
 *   1 <= nums[i] <= 10^5
 *   1 <= k <= nums.length
 */
public class CountNumberOfNiceSubarrays {
    public int numberOfSubarrays(int[] nums, int k) {
        int count = 0;
        int odds = 0;

        int lo = 0;
        int hi = 0;

        while (hi <= nums.length) {
            if (odds == k) {
                int postfixLen = 0;

                while (hi < nums.length && (nums[hi] & 1) == 0) {
                    postfixLen++;
                    hi++;
                }

                while (odds == k) {
                    count += 1 + postfixLen;
                    if ((nums[lo] & 1) == 1) {
                        odds--;
                    }
                    lo++;
                }

                continue;
            }

            if (odds < k) {
                // is odd
                if (hi < nums.length && (nums[hi] & 1) == 1) {
                    odds++;
                }

                hi++;
            }
        }

        return count;
    }
}
