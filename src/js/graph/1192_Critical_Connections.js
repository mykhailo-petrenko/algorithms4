/**
# LeetCode. 1192. Critical Connections in a Network (https://leetcode.com/problems/critical-connections-in-a-network/description/)

There are n servers numbered from 0 to n - 1 connected by undirected server-to-server connections
  forming a network where connections[i] = [ai, bi] represents a connection between servers ai and bi.
Any server can reach other servers directly or indirectly through the network.

A critical connection is a connection that, if removed, will make some servers unable to reach some other server.

Return all critical connections in the network in any order.

## Example 1:
  Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
  Output: [[1,3]]
  Explanation: [[3,1]] is also accepted.

## Example 2:
  Input: n = 2, connections = [[0,1]]
  Output: [[0,1]]

## Constraints:
- 2 <= n <= 10^5
- n - 1 <= connections.length <= 10^5
- 0 <= ai, bi <= n - 1
- ai != bi
- There are no repeated connections.
 */
import { puml } from '../../../tools/puml.js';

/**
 * Return the all critical connections ("Bridges") in a Network
 *
 * @param {number} n
 * @param {[number, number][]} connections
 * @return {[number, number][]}
 */
export const criticalConnections = function (n, connections) {
  // Tarjans bridge finding algorithm

  // Time of entry into node (via dfs)
  const tin = new Uint32Array(n);
  // Minimum of (tin[node], tin[back_edge_node], lo[child])
  const lo = new Uint32Array(n);

  const graph = [];
  for (let r = 0; r < n; r++) {
    graph[r] = [];
  }

  for (let [a, b] of connections) {
    graph[a].push(b);
    graph[b].push(a);
  }

  let visited = new Uint8Array(n);
  let level = 0;

  const bridge = [];

  const dfs = (node, parent) => {
    visited[node] = 1;

    tin[node] = level;
    lo[node] = level;

    level++;

    for (let next of graph[node]) {
      if (next === parent) {
        continue;
      }

      if (visited[next]===1) {
        // Back edge
        lo[node] = Math.min(lo[node], tin[next]);
      } else {
        dfs(next, node);

        lo[node] = Math.min(lo[node], lo[next]);

        if (lo[next] > tin[node]) {
          bridge.push([node, next]);
        }
      }

    }
  }

  dfs(0, -1);

  // const labels = [];
  // for (let nodeId=0; nodeId<n; nodeId++) {
  //   if (!tin[nodeId]) {
  //     continue;
  //   }
  //   labels[nodeId] = ''+tin[nodeId]+'/'+lo[nodeId];
  // }
  // puml(n, connections, './graph/mst-debug.puml', labels);

  return bridge;
}


function Node(id, prev) {
  this.id = id;
  this.prev = prev;
}

/**
 * @param {number} n
 * @param {[number, number][]} connections
 * @return {[number, number][]}
 */
export const criticalConnections_0_DFS = function (n, connections) {
  let graph = [];

  const CONNECTED = 1;
  const IN_CYCLE = 2;

  const NOT_VISITED = 0;
  const PROCESSING = 1;
  const VISITED = 2;

  const status = [];

  for (let r = 0; r < n; r++) {
    graph[r] = new Map();
  }

  const setPath = (a, b, value) => {
    if (a < b) {
      graph[a].set(b, value);
    } else {
      graph[b].set(a, value);
    }
  };

  const getPath = (a, b) => {
    if (a < b) {
      return graph[a].get(b);
    } else {
      return graph[b].get(a);
    }
  }

  for (let [a, b] of connections) {
    setPath(parseInt(a, 10), parseInt(b, 10), CONNECTED);
  }

  const start = connections[0][0];

  const stack = [];
  stack.push(new Node(start, null));

  while (stack.length > 0) {
    const current = stack.pop();

    // stack.push({isTask: true, visited: current.id});
    if (current.isTask) {
      status[current.visited] = VISITED;
      continue;
    }

    if (status[current.id] === VISITED) {
      continue;
    }

    // Loop found. Disentangle the path to get the loop
    if (status[current.id] === PROCESSING) {
      let cursorNode = current.id;
      let cursorPath = current.prev;

      while (cursorPath) {
        setPath(cursorNode, cursorPath.id, IN_CYCLE);
        cursorNode = cursorPath.id;
        cursorPath = cursorPath.prev;

        if (cursorNode === current.id) {
          break;
        }
      }

      continue;
    }

    status[current.id] = PROCESSING;

    stack.push({isTask: true, visited: current.id});

    for (let next = 0; next < n; next++) {
      // skip the previous connection
      if (next === current.prev?.id) {
        continue;
      }

      // skip nodes without connection
      if (getPath(current.id, next) !== CONNECTED) {
        continue;
      }

      // process next nodes recursively
      stack.push(new Node(next, current));
    }
  }

  return connections.filter(([a, b]) => getPath(a, b) === CONNECTED);
}

export default criticalConnections;
