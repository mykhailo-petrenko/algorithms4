/**
# LeetCode. 861. Score After Flipping Matrix (https://leetcode.com/problems/score-after-flipping-matrix/description/)

You are given an m x n binary matrix grid.
A move consists of choosing any row or column and toggling each value in that row or column (i.e., changing all 0's to 1's, and all 1's to 0's).
Every row of the matrix is interpreted as a binary number, and the score of the matrix is the sum of these numbers.
Return the highest possible score after making any number of moves (including zero moves).



## Example 1:
  Input: grid = [[0,0,1,1],[1,0,1,0],[1,1,0,0]]
  Output: 39
  Explanation: 0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39

## Example 2:
  Input: grid = [[0]]
  Output: 1


## Constraints:
- m == grid.length
- n == grid[i].length
- 1 <= m, n <= 20
- grid[i][j] is either 0 or 1.

*/

/**
 * @param {number[][]} grid
 * @return {number}
 */
var matrixScore = function(grid) {
  const ROWS = grid.length;
  const COLS = grid[0]?.length || 0;

  const rowToInt = (rowIndex) => {
    let num = 0;
    for (let i = grid[rowIndex].length - 1, j=0; i >= 0; i--, j++) {
      num = num + grid[rowIndex][i] * (1 << j);
    }

    return num;
  }

  const gridSum = () => {
    let sum = 0;

    for (let row = 0; row < ROWS; row++) {
      sum = sum + rowToInt(row);
    }

    return sum;
  };

  let max = gridSum();

  const flipRow = (row) => {
    for (let col = 0; col < COLS; col++) {
      grid[row][col] = 1 - grid[row][col];
    }
  };

  const flipCol = (col) => {
    for (let row = 0; row < ROWS; row++) {
      grid[row][col] = 1 - grid[row][col];
    }
  };

  let sum;

  for (let r=0; r < ROWS; r++) {
    for (let c=0; c < COLS; c++) {
      flipRow(r);
      sum = gridSum();
      if (sum > max) {
        max = sum;
      } else {
        flipRow(r);
      }

      flipCol(c);
      sum = gridSum();
      if (sum > max) {
        max = sum;
      } else {
        flipCol(c);
      }
    }
  }

  return max;
};

export default matrixScore;
