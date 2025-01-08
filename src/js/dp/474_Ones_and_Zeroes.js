/**
# LeetCode. 474. Ones and Zeroes (https://leetcode.com/problems/ones-and-zeroes/)

You are given an array of binary strings strs and two integers m and n.
Return the size of the largest subset of strs such that there are at most m 0's and n 1's in the subset.
A set x is a subset of a set y if all elements of x are also elements of y.

## Constraints:
- 1 <= strs.length <= 600
- 1 <= strs[i].length <= 100
- strs[i] consists only of digits '0' and '1'.
- 1 <= m, n <= 100
*/

/**
 * @param {string[]} strs
 * @param {number} m max zero's
 * @param {number} n max one's
 * @return {number}
 */
var findMaxForm = function(strs, m, n) {
  const dp = [];

  for (let zeros=0; zeros<=m; zeros++) {
    dp[zeros] = new Array(n+1).fill(-1);
  }

  dp[0][0] = 0;

  /**
   * Calc how many zeros and ones in a string
   * @param str
   */
  const stats = (str) => {
    let zeros = 0;
    let ones = 0;

    for (const c of str) {
      if (c === '0') {
        zeros++;
      } else {
        ones++;
      }
    }

    return [zeros, ones];
  };

  let max = 0;

  // to track the most far row and column for each outer iteration (string).
  // to not iterate over potentially not reachable '-1' values.
  let maxZ = 0;
  let maxO = 0;

  // let's add string by string to the all possible valid combinations
  // dp[z][o] - is max length of valid sequence with z - zeros and o - ones.
  for (let str of strs) {
    const [currentZeros, currentOnes] = stats(str);

    // go from end to start to not count more than one time one string affection
    for (let zeros=Math.min(m, maxZ+currentZeros); zeros>=currentZeros; zeros--) {
      for (let ones=Math.min(n, maxO+currentOnes); ones>=currentOnes; ones--) {
        // dp[z][o] === -1 not existing sequence yet
        // so we can not continue from invalid point
        if (dp[zeros - currentZeros][ones - currentOnes] < 0) {
          continue;
        }

        // Select maximum (TU Kadane) sequence length: current or previously discovered.
        if (dp[zeros][ones] < (dp[zeros - currentZeros][ones - currentOnes] + 1)) {
          dp[zeros][ones] = dp[zeros - currentZeros][ones - currentOnes] + 1;

          // update global maximum.
          max = Math.max(max, dp[zeros][ones]);

          maxZ = Math.max(maxZ, zeros);
          maxO = Math.max(maxO, ones);
        }
      }
    }
  }

  return max;
};

export default findMaxForm;
