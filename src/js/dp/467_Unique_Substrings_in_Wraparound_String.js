/**
 * # LeetCode. 467. Unique Substrings in Wraparound String (https://leetcode.com/problems/unique-substrings-in-wraparound-string/)
 *
 * We define the string base to be the infinite wraparound string of "abcdefghijklmnopqrstuvwxyz", so base will look like this:
 *
 * "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".
 * Given a string s, return the number of unique non-empty substrings of s are present in base.
 *
 *
 * ## Constraints:
 * - 1 <= s.length <= 105
 * - s consists of lowercase English letters.
 *
 */

/**
 * @param {string} s
 * @return {number}
 */
var findSubstringInWraproundString = function(s) {
  const BASE = 'a'.charCodeAt(0);

  const getCharCodeAt = (i) => {
    return s.charCodeAt(i) - BASE;
  }

  // since base string is lower case Eng letters, lets consider it as a looping (by mod 26) sequence ot int [0:25]
  // As we are considering only unique substring we interested only in longest substrings.
  // Write the longest sequence length which ends to "symbol" s into dp[s].
  //
  const dp = new Uint32Array(26);
  let currentLength = 1;
  dp[getCharCodeAt(0)] = 1;

  for (let i=1; i<s.length; i++) {
    const current = getCharCodeAt(i);
    const prev = getCharCodeAt(i-1);

    if (((current - prev + 26) % 26) === 1) {
      currentLength++;
    } else {
      currentLength = 1;
    }

    dp[current] = Math.max(dp[current], currentLength);
  }

  return dp.reduce((sum, el) => sum + el, 0);
};

export default findSubstringInWraproundString;
