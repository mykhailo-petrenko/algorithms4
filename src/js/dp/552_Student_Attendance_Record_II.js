/**
 * # LeetCode. 552. Student Attendance Record II (https://leetcode.com/problems/student-attendance-record-ii/description/)
 * An attendance record for a student can be represented as a string where each character signifies whether the student was absent, late, or present on that day.
 *
 * The record only contains the following three characters:
 * - 'A': Absent.
 * - 'L': Late.
 * - 'P': Present.
 *
 * Any student is eligible for an attendance award if they meet both of the following criteria:
 * - The student was absent ('A') for strictly fewer than 2 days total.
 * - The student was never late ('L') for 3 or more consecutive days.
 *
 * Given an integer n, return the number of possible attendance records of length n that make a student eligible for an attendance award.
 * The answer may be very large, so return it modulo 10^9 + 7.
 *
 * Example 1:
 *   Input: n = 2
 *   Output: 8
 *   Explanation: There are 8 records with length 2 that are eligible for an award:
 *   "PP", "AP", "PA", "LP", "PL", "AL", "LA", "LL"
 *   Only "AA" is not eligible because there are 2 absences (there need to be fewer than 2).
 *
 * Example 2:
 *   Input: n = 1
 *   Output: 3
 *
 * Example 3:
 *   Input: n = 10101
 *   Output: 183236316
 *
 * Constraints:
 * - 1 <= n <= 10^5
 */

/**
 * @param {number} n
 * @return {number}
 */
var checkRecord = function (n) {
  const MOD = Math.pow(10, 9) + 7;

  const P = 0; // Present
  const A = 1; // Absent
  // dp[i][a][l]
  // - `i` = [0:n]
  // - `a`: 0 - P, 1 is A
  // - `l`: consecutive Late (0, 1, 2)
  const dp = new Array(n)
    .fill(0)
    .map(() => new Array(2).fill(0).map(() => new Uint32Array(3)));

  // Base cases for the first day
  dp[0][P][0] = 1; // Present
  dp[0][P][1] = 1; // Late
  dp[0][A][0] = 1; // Absent

  for (let i = 1; i < n; i++) {
    // Absence case - include previous present and late
    dp[i][A][0] = (dp[i - 1][P][0] + dp[i - 1][P][1] + dp[i - 1][P][2]) % MOD;

    // Late case
    dp[i][P][1] = dp[i - 1][P][0]; // first day late
    dp[i][P][2] = dp[i - 1][P][1]; // second day late
    dp[i][A][1] = dp[i - 1][A][0]; // the same but with absence
    dp[i][A][2] = dp[i - 1][A][1];

    // Present
    dp[i][P][0] = (dp[i - 1][P][0] + dp[i - 1][P][1] + dp[i - 1][P][2]) % MOD;
    // Add the Present to Absence case
    dp[i][A][0] = (dp[i][A][0] + dp[i - 1][A][0] + dp[i - 1][A][1] + dp[i - 1][A][2]) % MOD;
  }

  let out = 0;
  for (let absence of [P, A]) {
    for (let late = 0; late < 3; ++late) {
      out = (out + dp[n - 1][absence][late]) % MOD;
    }
  }

  return out;
};

export default checkRecord;
