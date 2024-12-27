/**

# LeetCode. 416. Partition Equal Subset Sum (https://leetcode.com/problems/partition-equal-subset-sum/description/)

Given an integer array nums,
return true if you can partition the array into two subsets such that the sum of the elements in both subsets is equal
or false otherwise.

## Example 1:
  Input: nums = [1,5,11,5]
  Output: true
  Explanation: The array can be partitioned as [1, 5, 5] and [11].

## Example 2:
  Input: nums = [1,2,3,5]
  Output: false
  Explanation: The array cannot be partitioned into equal sum subsets.

## Constraints:
  1 <= nums.length <= 200
  1 <= nums[i] <= 100

*/

/**
 * @param {number[]} nums
 * @return {boolean}
 */
var canPartition = function(nums) {
  // To get the two subsets with equal sums they (sums) should be equal to half sum of all elements.
  const desc = (a, b) => b - a;
  nums.sort(desc);

  const total_sum = nums.reduce((sum, n) => sum + n, 0);

  // total sum should be even, because event by definition could be divided into 2 int equal parts
  if (total_sum % 2 !== 0) {
    return false;
  }

  const target_sum = total_sum / 2;

  // Try to use the Greedy approach (as for Knapsack problem)
  // solve for 1, then 2, 3 and till target_sum

  const knapsacks = new Array(target_sum + 1).fill(false);
  knapsacks[0] = true;

  for (const m of nums) {
    for (let i = target_sum; i>=0; i--) {
      const prev = i - m;

      if (prev < 0) {
        break;
      }

      if (knapsacks[prev] === true) {
        knapsacks[i] = true;

        if (i === target_sum) {
          return true;
        }
      }
    }
  }

  return knapsacks[target_sum];
};

export default canPartition;
