/**
# LeetCode. 2017. Grid Game (https://leetcode.com/problems/grid-game/)

You are given a 0-indexed 2D array grid of size 2 x n, where grid[r][c] represents the number of points at position (r, c) on the matrix.
 Two robots are playing a game on this matrix.

Both robots initially start at (0, 0) and want to reach (1, n-1).
Each robot may only move to the right ((r, c) to (r, c + 1)) or down ((r, c) to (r + 1, c)).

At the start of the game, the first robot moves from (0, 0) to (1, n-1), collecting all the points from the cells on its path.
For all cells (r, c) traversed on the path, grid[r][c] is set to 0.
Then, the second robot moves from (0, 0) to (1, n-1), collecting the points on its path.
Note that their paths may intersect with one another.

The first robot wants to minimize the number of points collected by the second robot.
In contrast, the second robot wants to maximize the number of points it collects.
If both robots play optimally, return the number of points collected by the second robot.

## Constraints:

- grid.length == 2
- n == grid[r].length
- 1 <= n <= 5 * 10^4
- 1 <= grid[r][c] <= 10^5

 */

/**
 * @param {number[][]} grid
 * @return {number}
 */
var gridGame = function(grid) {
  if (grid.length !== 2) {
    return 0;
  }

  const COLS = grid[0].length;

  let suffix = 0;
  for (let c = COLS-1; c > 0 ; c--) {
    suffix += grid[0][c];
  }

  let robot2 = suffix;

  let prefix = 0;
  for (let c = 1; c < COLS; c++) {
    suffix -= grid[0][c];
    prefix += grid[1][c-1];
    robot2 = Math.min( robot2, Math.max( prefix, suffix ) );
  }

  return robot2;
};

export default gridGame;
