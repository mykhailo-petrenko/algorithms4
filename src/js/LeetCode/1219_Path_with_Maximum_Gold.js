/**
# LeetCode. 1219. Path with Maximum Gold (https://leetcode.com/problems/path-with-maximum-gold/description/)

In a gold mine grid of size m x n, each cell in this mine has an integer representing the amount of gold in that cell, 0 if it is empty.

Return the maximum amount of gold you can collect under the conditions:

Every time you are located in a cell you will collect all the gold in that cell.
From your position, you can walk one step to the left, right, up, or down.
You can't visit the same cell more than once.
Never visit a cell with 0 gold.
You can start and stop collecting gold from any position in the grid that has some gold.


## Example 1:
  Input: grid = [[0,6,0],[5,8,7],[0,9,0]]
  Output: 24
  Explanation:
  [[0,6,0],
  [5,8,7],
  [0,9,0]]
  Path to get the maximum gold, 9 -> 8 -> 7.

## Example 2:
  Input: grid = [[1,0,7],[2,0,6],[3,4,5],[0,3,0],[9,0,20]]
  Output: 28
  Explanation:
  [[1,0,7],
  [2,0,6],
  [3,4,5],
  [0,3,0],
  [9,0,20]]
  Path to get the maximum gold, 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7.


## Constraints:
- m == grid.length
- n == grid[i].length
- 1 <= m, n <= 15
- 0 <= grid[i][j] <= 100
- There are at most 25 cells containing gold.

*/

import { MaxPriorityQueue } from '@datastructures-js/priority-queue';

/**
 * @param {number[][]} grid
 * @return {number}
 */
var getMaximumGold = function(grid) {
  const ROWS = grid.length;
  const COLS = grid[0].length;

  const isAvailable = (r, c) => {
    return !(r < 0 || c < 0 || r >= ROWS || c >= COLS || (grid[r][c] === 0));
  };

  const nonZeroes = [];

  for (let r = 0; r < grid.length; r++) {
    for (let c = 0; c < grid[0].length; c++) {
      if (grid[r][c] === 0) {
        continue;
      }

      nonZeroes.push([r, c]);
    }
  }

  const nodeBit = new Map();
  let bit = 1;
  let ALL_VISITED = 0;
  for (const [r, c] of nonZeroes) {
    nodeBit.set(r * COLS + c, bit);
    ALL_VISITED = ALL_VISITED | bit;
    bit = bit << 1;
  }

  const bfs = (r, c) => {
    let max = 0;
    const queue = new MaxPriorityQueue();

    queue.enqueue([r, c, 0, 0], 0);

    while (queue.size() > 0) {
      const [nextR, nextC, visited, path] = queue.dequeue().element;
      const bit = nodeBit.get(nextR * COLS + nextC);

      if ((bit & visited) !== 0) {
        continue;
      }

      const nextVisited = visited | bit;
      const nextPath = path + grid[nextR][nextC];

      max = Math.max(max, nextPath);

      if (ALL_VISITED === nextVisited) {
        break;
      }

      if (isAvailable(nextR+1, nextC)) {
        queue.enqueue([nextR+1, nextC, nextVisited, nextPath], nextPath);
      }

      if (isAvailable(nextR, nextC+1)) {
        queue.enqueue([nextR, nextC+1, nextVisited, nextPath], nextPath);
      }

      if (isAvailable(nextR-1, nextC)) {
        queue.enqueue([nextR-1, nextC, nextVisited, nextPath], nextPath);
      }

      if (isAvailable(nextR, nextC-1)) {
        queue.enqueue([nextR, nextC-1, nextVisited, nextPath], nextPath);
      }

    }

    return max;
  }

  let max = 0;

  for (const [r, c] of nonZeroes) {
    max = Math.max(max, bfs(r, c));
  }

  return max;
};

export default getMaximumGold;
