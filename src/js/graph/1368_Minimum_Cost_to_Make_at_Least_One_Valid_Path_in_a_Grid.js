/**
 # LeetCode. 1368. Minimum Cost to Make at Least One Valid Path in a Grid (https://leetcode.com/problems/minimum-cost-to-make-at-least-one-valid-path-in-a-grid/description/)

 Given an m x n grid. Each cell of the grid has a sign pointing to the next cell you should visit if you are currently in this cell. The sign of grid[i][j] can be:

 - 1 which means go to the cell to the right. (i.e go from grid[i][j] to grid[i][j + 1])
 - 2 which means go to the cell to the left. (i.e go from grid[i][j] to grid[i][j - 1])
 - 3 which means go to the lower cell. (i.e go from grid[i][j] to grid[i + 1][j])
 - 4 which means go to the upper cell. (i.e go from grid[i][j] to grid[i - 1][j])
 Notice that there could be some signs on the cells of the grid that point outside the grid.

 You will initially start at the upper left cell (0, 0). A valid path in the grid is a path that starts from the upper left cell (0, 0) and ends at the bottom-right cell (m - 1, n - 1) following the signs on the grid. The valid path does not have to be the shortest.

 You can modify the sign on a cell with cost = 1. You can modify the sign on a cell one time only.

 Return the minimum cost to make the grid have at least one valid path.

 ## Constraints:
 - m == grid.length
 - n == grid[i].length
 - 1 <= m, n <= 100
 - 1 <= grid[i][j] <= 4

 */
import { PriorityQueue } from '@datastructures-js/priority-queue';


/**
 * @param {number[][]} grid
 * @return {number}
 */
var minCost = function (grid) {
  const ROWS = grid.length;
  if (ROWS === 0) {
    return 0;
  }

  const COLS = grid[0].length;
  if (COLS === 0) {
    return 0;
  }

  const visited = new Array(ROWS * COLS).fill(Infinity);

  const node = (r, c, path) => {
    return {
      r, c, path
    };
  };

  const q = new PriorityQueue({
    compare: (a, b) => {
      return (a.path - b.path);
    }
  });

  const coord = (r, c) => {
    return r * COLS + c;
  };

  q.enqueue(node(0, 0, 0));

  while (!q.isEmpty()) {
    const {r, c, path} = q.dequeue();
    const rc = coord(r, c);

    if (path >= visited[rc]) {
      continue;
    }

    visited[rc] = path;
    const direction = grid[r][c];

    // 1 right
    if (c < (COLS - 1)) {
      const next = node(r, c+1, path + ((direction === 1) ? 0 : 1));
      const nextXY = coord(r, c+1);

      if (next.path < visited[nextXY]) {
        q.enqueue(next);
      }
    }

    // 2 left
    if (c > 0) {
      const next = node(r, c-1, path + ((direction === 2) ? 0 : 1));
      const nextXY = coord(r, c-1);

      if (visited[nextXY] > next.path) {
        q.enqueue(next);
      }
    }

    // 3 lower
    if (r < (ROWS - 1)) {
      const next = node(r+1, c, path + ((direction === 3) ? 0 : 1));
      const nextXY = coord(r+1, c);

      if (visited[nextXY] > next.path) {
        q.enqueue(next);
      }
    }

    // 4 upper
    if (r > 0) {
      const next = node(r-1, c, path + ((direction === 4) ? 0 : 1));
      const nextXY = coord(r-1, c);

      if (visited[nextXY] > next.path) {
        q.enqueue(next);
      }
    }
  }

  const target = coord(ROWS - 1, COLS - 1);
  if (visited[target] !== Infinity) {
    return visited[target];
  } else {
    return -1;
  }
};

export default minCost;
