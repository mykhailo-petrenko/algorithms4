/**
# LeetCode. 127. Word Ladder (https://leetcode.com/problems/word-ladder/description/)

A transformation sequence from word beginWord to word endWord using a dictionary wordList is
a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
- Every adjacent pair of words differs by a single letter.
- Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
- sk == endWord


Given two words, beginWord and endWord, and a dictionary wordList,
 return the number of words in the shortest transformation sequence from beginWord to endWord,
 or 0 if no such sequence exists.


## Example 1:
  Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
  Output: 5
  Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.

## Example 2:
  Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
  Output: 0
  Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.


## Constraints:
- 1 <= beginWord.length <= 10
- endWord.length == beginWord.length
- 1 <= wordList.length <= 5000
- wordList[i].length == beginWord.length
- beginWord, endWord, and wordList[i] consist of lowercase English letters.
- beginWord != endWord
- All the words in wordList are unique.

*/
/**
 * @param {string} beginWord
 * @param {string} endWord
 * @param {string[]} wordList
 * @return {number}
 */
var ladderLength = function(beginWord, endWord, wordList) {
  const isPathExist = (a, b) => {
    let distance = 0;
    // assume that words lengths are equal
    for (let i= 0; i<a.length; i++) {
      if (a[i] === b[i]) {
        continue;
      }

      distance++;

      if (distance > 1) {
        return false;
      }
    }

    return (distance === 1);
  };

  if (!wordList.includes(endWord)) {
    return 0;
  }

  wordList.unshift(beginWord);

  const N = wordList.length;

  const graph = [];
  for (let i=0;i<N; i++) {
    graph[i] = [];
  }

  for (let i=0;i<N; i++) {
    for (let j=i+1;j<N; j++) {
      if (isPathExist(wordList[i], wordList[j])) {
        graph[i].push(j);
        graph[j].push(i);
      }
    }
  }

  const queue = [[0, 1]];
  const visited = new Uint8Array(N);

  while (queue.length > 0) {
    const [node, path] = queue.shift();

    if (visited[node] === 1) {
      continue;
    }

    visited[node] = 1;

    if (wordList[node] === endWord) {
      return path;
    }

    for (const next of graph[node]) {
      queue.push([next, path + 1]);
    }

  }

  return 0;
};

export default ladderLength;
