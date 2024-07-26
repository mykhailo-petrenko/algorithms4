/**
 * # LeetCode. 1334. Find the City With the Smallest Number of Neighbors at a Threshold Distance (https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/description/)
 *
 * There are n cities numbered from 0 to n-1. Given the array edges where edges[i] = [fromi, toi, weighti]
 * represents a bidirectional and weighted edge between cities fromi and toi, and given the integer distanceThreshold.
 *
 * Return the city with the smallest number of cities that are reachable through some path and whose distance is at most distanceThreshold,
 * If there are multiple such cities, return the city with the greatest number.
 *
 * Notice that the distance of a path connecting cities i and j is equal to the sum of the edges' weights along that path.
 *
 * ## Constraints:
 * - * 2 <= n <= 100
 * - 1 <= edges.length <= n * (n - 1) / 2
 * - edges[i].length == 3
 * - 0 <= fromi < toi < n
 * - 1 <= weighti, distanceThreshold <= 10^4
 * - All pairs (fromi, toi) are distinct.
 */

import { MinPriorityQueue } from '@datastructures-js/priority-queue';

/**
 * @param {number} n
 * @param {number[][]} edges
 * @param {number} distanceThreshold
 * @return {number}
 */
var findTheCity = function (n, edges, distanceThreshold) {
  const graph = [];

  for (let i = 0; i < n; i++) {
    graph[i] = [];
  }

  for (const [from, to, weight] of edges) {
    graph[from].push([to, weight]);
    graph[to].push([from, weight]);
  }

  for (let i = 0; i < n; i++) {
    graph[i].sort((a, b) => {
      return a[1] - b[1];
    })
  }

  const dfs = (start, maxNodes=Infinity) => {
    const visited = new Set();
    const q = new MinPriorityQueue()
    q.enqueue([start, 0], 0);
    let nodesCount = -1;

    while (!q.isEmpty()) {
      const [node, path] = q.dequeue().element;

      if (visited.has(node)) {
        continue;
      }

      if (path > distanceThreshold) {
        continue;
      }

      visited.add(node);

      nodesCount++;

      if (nodesCount > maxNodes) {
        return nodesCount;
      }

      for (const [next, weight] of graph[node]) {
        q.enqueue([next, path + weight], path + weight);
      }
    }

    return nodesCount;
  };

  let node = -1;
  let minNodesCount = Infinity;

  for (let i=n-1; i >= 0; i--) {
    const count = dfs(i, minNodesCount);

    if (count < minNodesCount) {
      node = i;
      minNodesCount = count;
    }
  }

  return node;
};


export default findTheCity;
