/**
 * LeetCode. 2257. Count Unguarded Cells in the Grid (https://leetcode.com/problems/count-unguarded-cells-in-the-grid/description/)
 *
 * You are given two integers m and n representing a 0-indexed m x n grid.
 * You are also given two 2D integer arrays guards and walls where guards[i] = [rowi, coli]
 *  and walls[j] = [rowj, colj] represent the positions of the ith guard and jth wall respectively.
 *
 * A guard can see every cell in the four cardinal directions (north, east, south, or west) starting from their position
 * unless obstructed by a wall or another guard.
 * A cell is guarded if there is at least one guard that can see it.
 *
 * Return the number of unoccupied cells that are not guarded.
 */

/**
 * @param {number} m
 * @param {number} n
 * @param {number[][]} guards
 * @param {number[][]} walls
 * @return {number}
 */
var countUnguarded = function(m, n, guards, walls) {
  const state = {
    FREE: 0,
    GUARDED: 1,
    WALL: 2,
    GUARD: 3
  };
  const ROWS = m;
  const COLS = n;
  const matrix = [];

  let out = m*n - guards.length - walls.length;

  for (let row = 0; row < ROWS; row++) {
    matrix[row] = new Array(COLS).fill(state.FREE);
  }

  for (const [r, c] of walls) {
    matrix[r][c] = state.WALL;
  }

  for (const [r, c] of guards) {
    matrix[r][c] = state.GUARD;
  }

  for (const [r, c] of guards) {
    // trace North
    for (let row=r-1, col=c; row >=0; row--) {
      if (matrix[row][col]===state.FREE) {
        out--;
        matrix[row][col] = state.GUARDED;
      }

      if (matrix[row][col]!==state.GUARDED) {
        break;
      }
    }
    // trace East
    for (let row=r, col=c+1; col < COLS; col++) {
      if (matrix[row][col]===state.FREE) {
        out--;
        matrix[row][col] = state.GUARDED;
      }

      if (matrix[row][col]!==state.GUARDED) {
        break;
      }
    }
    // trace West
    for (let row=r, col=c-1; col >= 0; col--) {
      if (matrix[row][col]===state.FREE) {
        out--;
        matrix[row][col] = state.GUARDED;
      }

      if (matrix[row][col]!==state.GUARDED) {
        break;
      }
    }
    // trace South
    for (let row=r+1, col=c; row < ROWS; row++) {
      if (matrix[row][col]===state.FREE) {
        out--;
        matrix[row][col] = state.GUARDED;
      }

      if (matrix[row][col]!==state.GUARDED) {
        break;
      }
    }
  }

  return out;
};

export default countUnguarded;
