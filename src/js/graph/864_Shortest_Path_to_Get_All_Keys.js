/**
# LeetCode. 864. Shortest Path to Get All Keys (https://leetcode.com/problems/shortest-path-to-get-all-keys/description/)

You are given an m x n grid where:

'.' is an empty cell.
'#' is a wall.
'@' is the starting point.
Lowercase letters represent keys.
Uppercase letters represent locks.
You start at the starting point and one move consists of walking one space in one of the four cardinal directions. You cannot walk outside the grid, or walk into a wall.

If you walk over a key, you can pick it up and you cannot walk over a lock unless you have its corresponding key.

For some 1 <= k <= 6, there is exactly one lowercase and one uppercase letter of the first k letters of the English alphabet in the grid.
This means that there is exactly one key for each lock, and one lock for each key;
and also that the letters used to represent the keys and locks were chosen in the same order as the English alphabet.

Return the lowest number of moves to acquire all keys. If it is impossible, return -1.

## Example 1:
  Input: grid = [
    "@ . a . . ",
    "# # # . # ",
    "b . A . B "
]
  Output: 8
  Explanation: Note that the goal is to obtain all the keys not to open all the locks.

## Example 2:
  Input: grid = ["@..aA","..B#.","....b"]
  Output: 6

## Example 3:
  Input: grid = ["@Aa"]
  Output: -1


## Constraints:
- m == grid.length
- n == grid[i].length
- 1 <= m, n <= 30
- grid[i][j] is either an English letter, '.', '#', or '@'.
- There is exactly one '@' in the grid.
- The number of keys in the grid is in the range [1, 6].
- Each key in the grid is unique.
- Each key in the grid has a matching lock.\

 */


/**
 * @param {string[]} grid
 * @return {number}
 */
var shortestPathAllKeys = function(grid) {
  const ROWS = grid.length;
  const COLS = grid[0].length;
  const N = ROWS * COLS;

  const KEYS = ['a','b','c','d','e','f'];
  const LOCKS = ['A','B','C','D','E','F'];
  const isValid = (r, c) => {
    return (r >= 0 && c >= 0 && r < ROWS && c < COLS && (g[r][c] !== '#'));
  };
  const isKey = (c) => {
    return KEYS.indexOf(c);
  };

  const isLock = (c) => {
    return LOCKS.indexOf(c);
  };

  const start = [];
  let keysLength = 0;

  const g = [];
  let i =0;
  for (const row of grid) {
    g[i] = row.split('');

    for (let k=0; k < COLS; k++) {
      if (g[i][k] === '@') {
        start[0] = i;
        start[1] = k;
      } else if (isKey(g[i][k]) !== -1) {
        keysLength++;
      }
    }

    i++;
  }

  const visited = new Set();
  const q = [];
  q.push([...start, 0, 0]);

  const TARGET = (1 << keysLength) - 1;

  const attemptNext = (r, c, path, wallet) => {
    const l = isLock(g[r][c]);

    if (l !== -1 && 0 === (wallet & (1 << l))) {
      return;
    }

    const k = isKey(g[r][c]);
    let nextWallet = wallet;
    if (k !== -1) {
      nextWallet = nextWallet | (1 << k);
    }

    if (visited.has(`${r}-${c}-${nextWallet}`)) {
      return;
    }

    visited.add(`${r}-${c}-${nextWallet}`);

    q.push([r, c, path+1, nextWallet]);
  };

  while (q.length > 0) {
    const [r, c, path, wallet] = q.shift();

    if (wallet === TARGET) {
      return path;
    }

    isValid(r-1, c) && attemptNext(r-1, c, path, wallet); // up
    isValid(r, c+1) && attemptNext(r, c+1, path, wallet); // right
    isValid(r+1, c) && attemptNext(r+1, c, path, wallet); // down
    isValid(r, c-1) && attemptNext(r, c-1, path, wallet); // left
  }

  return -1;
};

export default shortestPathAllKeys;
