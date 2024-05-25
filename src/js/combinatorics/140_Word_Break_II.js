/**
 * # LeetCode. 140. Word Break II (https://leetcode.com/problems/word-break-ii/description/)
 *
 * Given a string s and a dictionary of strings wordDict, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences in any order.
 *
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 *
 * ## Example 1:
 * Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
 * Output: ["cats and dog","cat sand dog"]
 *
 * ## Example 2:
 * Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
 * Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
 * Explanation: Note that you are allowed to reuse a dictionary word.
 *
 * ## Example 3:
 * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * Output: []
 *
 * Constraints:
 * - 1 <= s.length <= 20
 * - 1 <= wordDict.length <= 1000
 * - 1 <= wordDict[i].length <= 10
 * - s and wordDict[i] consist of only lowercase English letters.
 * - All the strings of wordDict are unique.
 * - Input is generated in a way that the length of  the answer doesn't exceed 10^5.
 *
 * */

/**
 * @param {string} s
 * @param {string[]} wordDict
 * @return {string[]}
 */
var wordBreak = function(s, wordDict) {
  const source = [];

  for (const word of wordDict) {
    let shift = 0;
    while (shift < s.length) {
      const pos = s.indexOf(word, shift);

      if (pos === -1) {
        break;
      }

      source.push({
        word,
        start: pos,
        end: pos + word.length
      });

      shift = pos + 1;
    }
  }

  source.sort((a, b) => a.start - b.start);

  const wordLen = (set) => {
    return set
      .map((w) => w.end - w.start)
      .reduce((a, b) => a + b, 0);
  };

  let out = [];
  let N = source.length;

  const dfs = (set, start) => {
    if (wordLen(set) === s.length) {
      out.push(set.map(w => w.word).join(' '));
      return;
    }
    if (start === N) {
      return;
    }

    for (let i = start; i<N; i++) {
      if (set.length === 0 && source[i].start === 0) {
        set.push(source[i]);
      } else if (set.length > 0 && source[i].start === set[set.length-1].end) {
        set.push(source[i]);
      } else {
        continue;
      }

      dfs(set, i+1);

      set.pop();
    }
  }

  dfs([],0);

  return out;
};

/**
 * @param {string} s
 * @param {string[]} wordDict
 * @return {string[]}
 */
var wordBreakRecursive = function(s, wordDict) {
  let out = [];

  let source = [];

  for (const word of wordDict) {
    let shift = 0;
    while (shift < s.length) {
      const pos = s.indexOf(word, shift);

      if (pos === -1) {
        break;
      }

      source.push({
        word,
        start: pos,
        end: pos + word.length
      });

      shift = pos + 1;
    }
  }

  source.sort((a, b) => a.start - b.start);

  const wordLen = (set) => {
    return set
      .map((w) => w.end - w.start)
      .reduce((a, b) => a + b, 0);
  };

  const dfs = (set, index) => {
    if (s.length === wordLen(set)) {
      out.push(
        set.map((w) => w.word).join(' ')
      );

      return;
    }

    if (index === source.length) {
      return;
    }

    if (
      (set.length === 0 && source[index].start === 0)
      ||
      (set.length > 0 && (set[set.length-1].end === (source[index].start)))
    ) {
      dfs([...set, source[index]], index + 1);
    }
    dfs(set, index + 1);
  }

  dfs([],0);

  return out;
};


export default wordBreak;
