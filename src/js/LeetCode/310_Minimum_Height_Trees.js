/**
 # 310. Minimum Height Trees (https://leetcode.com/problems/minimum-height-trees/description/)

 A tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.
 Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges where edges[i] = [ai, bi] indicates that there is an undirected edge between the two nodes ai and bi in the tree, you can choose any node of the tree as the root. When you select a node x as the root, the result tree has height h. Among all possible rooted trees, those with minimum height (i.e. min(h))  are called minimum height trees (MHTs).
 Return a list of all MHTs' root labels. You can return the answer in any order.
 The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.


 ## Example 1:
 Input: n = 4, edges = [[1,0],[1,2],[1,3]]
 Output: [1]
 Explanation: As shown, the height of the tree is 1 when the root is the node with label 1 which is the only MHT.

 ## Example 2:
 Input: n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
 Output: [3,4]


 ## Constraints:
 - 1 <= n <= 2 * 10^4
 - edges.length == n - 1
 - 0 <= ai, bi < n
 - ai != bi
 - All the pairs (ai, bi) are distinct.
 - The given input is guaranteed to be a tree and there will be no repeated edges.
 */

/**
 * @param {number} n
 * @param {number[][]} edges
 * @return {number[]}
 */
var findMinHeightTrees = function(n, edges) {
  const g = [];
  const connections = new Uint32Array(n);

  if (n === 1) {
    return [0];
  }

  for (let node = 0; node < n; node++) {
    g[node] = [];
  }

  // build graph and count in/out edges for each node to detect leafs
  for (const [u, v] of edges) {
    g[u].push(v);
    g[v].push(u);
    connections[u]++;
    connections[v]++;
  }

  const queue = [];
  for (let node = 0; node < n; node++) {
    // Start from leafs
    if (connections[node] === 1) {
      queue.push(node);
    }
  }

  const out = [];
  while(queue.length > 0) {
    out.length = 0;

    const leafsCount = queue.length;
    for (let i = 0; i < leafsCount; i++) {
      const leaf = queue.shift();
      out.push(leaf);

      for (const next of g[leaf]) {
        connections[next]--;

        if (connections[next] === 1) {
          queue.push(next);
        }
      }
    }
  }

  return out;
};

/**
 * @param {number} n
 * @param {number[][]} edges
 * @return {number[]}
 */
var findMinHeightTreesBruteForceWithDFS = function (n, edges) {
  const g = [];

  for (let r = 0; r < n; r++) {
    g[r] = [];
  }

  for (const [u, v] of edges) {
    g[u].push(v);
    g[v].push(u);
  }

  const maxDepth = new Uint32Array(n);
  let minDepth = Infinity;

  const search = (root) => {
    const visited = new Uint8Array(n);
    const queue = [[root, 0]];

    while (queue.length > 0) {
      const [node, depth] = queue.pop();

      if (visited[node] === 1) {
        continue;
      }

      visited[node] = 1;

      maxDepth[root] = Math.max(maxDepth[root], depth);

      if (depth > minDepth) {
        break;
      }

      for (let next of g[node]) {
        if (visited[next] === 1) {
          continue;
        }
        queue.push([next, depth + 1]);
      }

    }

    minDepth = Math.min(maxDepth[root], minDepth);
  };

  for (let root = 0; root < n; root++) {
    search(root);
  }

  const out = [];

  // build list with nodes which have the min depth
  for (let i = 0; i < n; i++) {
    if (maxDepth[i] === minDepth) {
      out.push(i);
    }
  }

  return out;
};

/**
 * @param {number} n
 * @param {number[][]} edges
 * @return {number[]}
 */
var findMinHeightTreesFloydWarshall = function (n, edges) {

  const g = [];
  const MAX = Math.pow(2, 31);

  // Use Floyd-Warshall algorithm to find min distances between each pair of nodes
  for (let r = 0; r < n; r++) {
    g[r] = new Uint32Array(n);

    for (let c = 0; c < n; c++) {
      if (r === c) {
        g[r][c] = 0;
      } else {
        g[r][c] = MAX;
      }
    }
  }

  for (const [u, v] of edges) {
    g[u][v] = 1;
    g[v][u] = 1;
  }

  for (let k = 0; k < n; k++) {
    for (let i = 0; i < n; i++) {
      for (let j = 0; j < n; j++) {
        if (g[i][k] !== MAX && g[k][j] !== MAX && g[i][j] > (g[i][k] + g[k][j])) {
          g[i][j] = g[i][k] + g[k][j];
        }
      }
    }
  }

  // get the max depth for each node
  const maxDistance = [];
  let min = MAX;
  for (let i = 0; i < n; i++) {
    maxDistance[i] = 0;

    for (let j = 0; j < n; j++) {
      maxDistance[i] = Math.max(maxDistance[i], g[i][j])
    }

    min = Math.min(maxDistance[i], min);
  }

  const out = [];

  // build list with nodes which have the min depth
  for (let i = 0; i < n; i++) {
    if (maxDistance[i] === min) {
      out.push(i);
    }
  }

  return out;
};

// export default findMinHeightTreesFloydWarshall;
// export default findMinHeightTreesBruteForceWithDFS;
export default findMinHeightTrees;
