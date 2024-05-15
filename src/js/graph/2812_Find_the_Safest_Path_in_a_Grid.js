/**
# LeetCode. 2812. Find the Safest Path in a Grid (https://leetcode.com/problems/find-the-safest-path-in-a-grid/description/)
You are given a 0-indexed 2D matrix grid of size n x n, where (r, c) represents:

A cell containing a thief if grid[r][c] = 1
An empty cell if grid[r][c] = 0
You are initially positioned at cell (0, 0). In one move, you can move to any adjacent cell in the grid, including cells containing thieves.

The safeness factor of a path on the grid is defined as the minimum manhattan distance from any cell in the path to any thief in the grid.

Return the maximum safeness factor of all paths leading to cell (n - 1, n - 1).

An adjacent cell of cell (r, c), is one of the cells (r, c + 1), (r, c - 1), (r + 1, c) and (r - 1, c) if it exists.

The Manhattan distance between two cells (a, b) and (x, y) is equal to |a - x| + |b - y|, where |val| denotes the absolute value of val.

## Example 1
  Input: grid = [[1,0,0],[0,0,0],[0,0,1]]
  Output: 0
  Explanation: All paths from (0, 0) to (n - 1, n - 1) go through the thieves in cells (0, 0) and (n - 1, n - 1).

## Example 2
  Input: grid = [[0,0,1],[0,0,0],[0,0,0]]
  Output: 2
  Explanation: The path depicted in the picture above has a safeness factor of 2 since:
  - The closest cell of the path to the thief at cell (0, 2) is cell (0, 0). The distance between them is | 0 - 0 | + | 0 - 2 | = 2.
  It can be shown that there are no other paths with a higher safeness factor.

## Example 3
  Input: grid = [[0,0,0,1],[0,0,0,0],[0,0,0,0],[1,0,0,0]]
  Output: 2
  Explanation: The path depicted in the picture above has a safeness factor of 2 since:
  - The closest cell of the path to the thief at cell (0, 3) is cell (1, 2). The distance between them is | 0 - 1 | + | 3 - 2 | = 2.
  - The closest cell of the path to the thief at cell (3, 0) is cell (3, 2). The distance between them is | 3 - 3 | + | 0 - 2 | = 2.
  It can be shown that there are no other paths with a higher safeness factor.

## Constraints:
- 1 <= grid.length == n <= 400
- grid[i].length == n
- grid[i][j] is either 0 or 1.
- There is at least one thief in the grid.

 */

import { MaxPriorityQueue } from '@datastructures-js/priority-queue';

/**
 * @param {number[][]} grid
 * @return {number}
 */
var maximumSafenessFactor = function(grid) {
  const N = grid.length;
  const LAST = N - 1;

  if (grid[0][0] === 1 || grid[LAST][LAST] === 1) {
    return 0;
  }


  const thiefs = [];
  const graph = [];
  for (let r=0; r<N; r++) {
    graph[r] = [];
    for (let c=0; c<N; c++) {
      if (grid[r][c] === 1) {
        thiefs.push([r, c, 0]);
      }

      graph[r][c] = Infinity;
    }
  }

  const isValid = (r, c) => {
    return (r >=0 && c >= 0 && r < N && c < N);
  }

  while (thiefs.length > 0) {
    const [r, c, distance] = thiefs.shift();

    if (graph[r][c] <= distance) {
      continue;
    }

    graph[r][c] = distance;

    if (isValid(r+1, c) && (graph[r+1][c] > distance)) {
      thiefs.push([r+1, c, distance+1]);
    }

    if (isValid(r, c+1) && (graph[r][c+1] > distance)) {
      thiefs.push([r, c+1, distance+1]);
    }

    if (isValid(r-1, c) && (graph[r-1][c] > distance)) {
      thiefs.push([r-1, c, distance+1]);
    }

    if (isValid(r, c-1) && (graph[r][c-1] > distance)) {
      thiefs.push([r, c-1, distance+1]);
    }
  }

  const q = new MaxPriorityQueue();
  q.enqueue([0, 0, Infinity], 0);
  const visited = new Uint8Array(N*N);

  while (q.size() > 0) {
    const {element, priority} = q.dequeue();
    const [r, c, prevMin] = element;
    const min = Math.min(graph[r][c], prevMin);

    if (r === LAST && c === LAST) {
      return min;
    }

    if (r > 0 && c > 0 && min===0) {
      return 0;
    }

    if (visited[r * N + c] === 1) {
      continue;
    }

    visited[r * N + c] = 1;

    if (isValid(r+1, c) && (visited[(r+1) * N + c] === 0)) {
      q.enqueue([r+1, c, min], Math.min(graph[r+1][c], min));
    }
    if (isValid(r, c+1) && (visited[(r) * N + c+1] === 0)) {
      q.enqueue([r, c+1, min], Math.min(graph[r][c+1], min));
    }
    if (isValid(r, c-1) && (visited[(r) * N + c-1] === 0)) {
      q.enqueue([r, c-1, min], Math.min(graph[r][c-1], min));
    }
    if (isValid(r-1, c) && (visited[(r-1) * N + c] === 0)) {
      q.enqueue([r-1, c, min], Math.min(graph[r-1][c], min));
    }
  }

  return -1;
};

//  console.log( graph.map(row => row.join(' . ')).join('\n') );

export default maximumSafenessFactor;
