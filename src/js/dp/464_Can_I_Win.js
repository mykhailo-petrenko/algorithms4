/**
# LeetCode. 464. Can I Win (https://leetcode.com/problems/can-i-win/description/)

In the "100 game" two players take turns adding, to a running total, any integer from 1 to 10.
The player who first causes the running total to reach or exceed 100 wins.

What if we change the game so that players cannot re-use integers?

For example, two players might take turns drawing from a common pool of numbers
from 1 to 15 without replacement until they reach a total >= 100.

Given two integers `maxChoosableInteger` and desiredTotal,
return true if the first player to move can force a win,
otherwise, return false. Assume both players play optimally.

## Constraints:
- 1 <= maxChoosableInteger <= 20
- 0 <= desiredTotal <= 300
 */

/**
 * @param {number} maxChoosableInteger
 * @param {number} desiredTotal
 * @return {boolean}
 */
var canIWin = function(maxChoosableInteger, desiredTotal) {
  if (maxChoosableInteger >= desiredTotal) {
    return true;
  }

  const maxSum = ((1 + maxChoosableInteger) / 2) * maxChoosableInteger;

  if (desiredTotal > maxSum) {
    return false;
  }

  const cache = {};

  const calcHash = (set, state) => {
    return `${set}:${state}`;
  }

  const dfs = (selected = 0, state = 0) => {
    const hash = calcHash(selected, state);

    if (cache[hash] !== undefined) {
      return cache[hash];
    }

    for (let i = 1; i<=maxChoosableInteger; i++) {
      if (selected & (1 << i)) {
        continue;
      }

      if ((state + i) >= desiredTotal) {
        cache[hash] = true;
        return true;
      }

      if (!dfs(selected | (1 << i), state + i)) {
        cache[hash] = true;
        return true;
      }
    }

    cache[hash] = false;
    return false;
  };

  return dfs(0, 0);
};

export default canIWin;
