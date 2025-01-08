/**
# LeetCode. 473. Matchsticks to Square (https://leetcode.com/problems/matchsticks-to-square/)

You are given an integer array matchsticks where matchsticks[i] is the length of the ith matchstick.
You want to use all the matchsticks to make one square. You should not break any stick,
but you can link them up, and each matchstick must be used exactly one time.

Return true if you can make this square and false otherwise.

## Constraints:
- 1 <= matchsticks.length <= 15
- 1 <= matchsticks[i] <= 10^8

*/


/**
 * @param {number[]} matchsticks
 * @return {boolean}
 */
var makesquare = function (matchsticks) {
  const N = matchsticks.length;
  const perimeter = matchsticks.reduce((sum, m) => sum + m, 0);

  if (perimeter % 4 !== 0) {
    return false;
  }

  const edgeLength = perimeter / 4;

  matchsticks.sort((a, b) => b - a);

  if (matchsticks[0] > edgeLength) {
    return false;
  }

  const sides = [0, 0, 0, 0];

  const dfs = (index) => {
    if (index === N) {
      return true;
    }

    // place match to each side
    for (let i = 0; i < 4; ++i) {
      // put on side
      sides[i] += matchsticks[index];

      if (sides[i] <= edgeLength && dfs(index + 1)) {
        return true;
      }

      // take it back
      sides[i] -= matchsticks[index];
    }

    return false;
  }

  return dfs(0);
};

export default makesquare;
