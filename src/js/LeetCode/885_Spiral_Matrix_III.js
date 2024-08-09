/**
 * # LeetCode. 885. Spiral Matrix III (https://leetcode.com/problems/spiral-matrix-iii/description/)
 *
 * You start at the cell (rStart, cStart) of an rows x cols grid facing east.
 * The northwest corner is at the first row and column in the grid, and the southeast corner is at the last row and column.
 *
 * You will walk in a clockwise spiral shape to visit every position in this grid.
 * Whenever you move outside the grid's boundary, we continue our walk outside the grid (but may return to the grid boundary later.).
 * Eventually, we reach all rows * cols spaces of the grid.
 *
 * Return an array of coordinates representing the positions of the grid in the order you visited them.
 *
 * ## Constraints:
 * - 1 <= rows, cols <= 100
 * - 0 <= rStart < rows
 * - 0 <= cStart < cols
 */

/**
 * @param {number} rows
 * @param {number} cols
 * @param {number} rStart
 * @param {number} cStart
 * @return {number[][]}
 */
var spiralMatrixIII = function(rows, cols, rStart, cStart) {
  const coordinates = function*() {
    let step = 0;
    let direction = 1;

    while(true) {
      step++;
      for (let i=0; i<step; i++) {
        yield [0, direction];
      }
      for (let i=0; i<step; i++) {
        yield [direction, 0];
      }

      direction = -1 * direction;
    }
  }

  const MAX = rows * cols;
  let trajectory = [];

  let row = rStart
  let col = cStart;
  trajectory.push([row, col]);

  for (const [dRow, dCol] of coordinates()) {
    if (trajectory.length >= MAX) {
      break;
    }

    row += dRow;
    col += dCol;

    if (col >= 0 && col < cols && row >= 0 && row < rows) {
      trajectory.push([row, col]);
    }
  }

  return trajectory;
};

export default spiralMatrixIII;
