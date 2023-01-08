/**
# 120. Triangle (https://leetcode.com/problems/triangle/)

Given a triangle array, return the minimum path sum from top to bottom.
For each step, you may move to an adjacent number of the row below. 
More formally, if you are on index i on the current row, you may move to either index i or index i + 1 on the next row.
 */

/**
 * @param {number[][]} triangle
 * @return {number}
 */
var minimumTotal = function(triangle) {
    const N = triangle.length;
    const buffer = [];

    for (let r = 0; r<N; r++) {
        buffer[r] = [];
        for (let c = 0; c<r+1; c++) {
            buffer[r][c] = Infinity;
        }
    }

    buffer[0][0] = triangle[0][0];
    let left, right;

    for (let r = 0; r<N-1; r++) {
        for (let c = 0; c<r+1; c++) {
          left = c;
          right = c+1;

          buffer[r+1][left] = Math.min(buffer[r+1][left], buffer[r][c] + triangle[r+1][left])
          buffer[r+1][right] = Math.min(buffer[r+1][right], buffer[r][c] + triangle[r+1][right])
        }
    }

    let min = buffer[N-1][0];

    for (let c = 1; c < buffer[N-1].length; c++) {
      min = Math.min(min, buffer[N-1][c]);
    }

    return min;
};

function doTest(triangle, expected) {
  console.log('> 120. Triangle');
  console.log('In: ', triangle);

  const actual = minimumTotal(triangle);

  if (actual === expected) {
    console.log(' + SUCCESS! return ', actual);
  } else {
    console.error(` -- Expected: ${expected} but got the ${actual}`);
  }
}

doTest([[2],[3,4],[6,5,7],[4,1,8,3]], 11);

// doTest([[-10]], -10);