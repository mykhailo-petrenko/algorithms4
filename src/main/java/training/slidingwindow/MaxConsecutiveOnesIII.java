package training.slidingwindow;

/**
 * 1004. Max Consecutive Ones III
 *
 * https://leetcode.com/problems/max-consecutive-ones-iii/
 *
 * Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.
 *
 * Example 1:
 *   Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
 *   Output: 6
 *   Explanation: [1,1,1,0,0,1,1,1,1,1,1]
 *   Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 *
 * Example 2:
 *   Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
 *   Output: 10
 *   Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 *   Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 *
 *
 * Constraints:
 *   1 <= nums.length <= 105
 *   nums[i] is either 0 or 1.
 *   0 <= k <= nums.length
 *
 */
public class MaxConsecutiveOnesIII {
    public int longestOnes(int[] nums, int k) {
        int max = 0; // Max consecutive found length
        int zeroes = 0; // Count od flipped zeroes on current iteration
        int head = 0; // pointer to last symbol in current iteration
        int tail = 0; // pointer to first symbol in current iteration

        // move forward up to the end of array
        while (head < nums.length && tail < nums.length) {
            // When the `1` just increment the `head` and move forward
            if (nums[head] == 0) {
                // if we have enough zeroes to swap
                if (zeroes < k) {
                    // just 'swap' and move forward then
                    zeroes++;
                } else {
                    // when the `tail` points to zero
                    if (nums[tail] == 0) {
                        // flip zero back
                        zeroes--;
                    }

                    // move tile forward to shorten the current sequence
                    tail++;
                    continue;
                }
            }

            // calc the length and save to `max` (if we found the largest sequence)
            max = Math.max(head - tail + 1, max);

            head++;
        }

        return max;
    }
}
