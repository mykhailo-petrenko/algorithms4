/**
 # LeetCode. 131. Palindrome Partitioning (https://leetcode.com/problems/palindrome-partitioning/description/)
 Given a string s, partition s such that every
 substring
 of the partition is a
 palindrome
 . Return all possible palindrome partitioning of s.

 ## Example 1:
 Input: s = "aab"
 Output: [["a","a","b"],["aa","b"]]

 ## Example 2:
 Input: s = "a"
 Output: [["a"]]


 ## Constraints:
 - 1 <= s.length <= 16
 - s contains only lowercase English letters.
 */

/**
 * @param {string} s
 * @return {string[][]}
 */
var partition = function(s) {
  const N = s.length;

  const isValidPalindrome = new Array(N).fill(0).map(() => new Array(N).fill(true));

  for (let start = (N-1); start >= 0; start--) {
    for (let end = start + 1; end < N; end++) {
      isValidPalindrome[start][end] = (
        (s.charAt(start) === s.charAt(end))
        &&
        (isValidPalindrome[1+start][end-1] === true)
      );
    }
  }


  const out = [];
  const current = [];

  const dfs = (start) => {
    if (start === N) {
      out.push([...current]);
      return;
    }

    for (let end = start; end < N; end++) {
      if (isValidPalindrome[start][end]) {
        current.push(
          s.slice(start, 1 + end)
        );

        dfs(end + 1);

        current.pop();
      }
    }
  }

  dfs(0);

  return out;
};

export default partition;
