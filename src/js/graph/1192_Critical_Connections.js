/**
 * 1192. Critical Connections in a Network
 */
import { puml } from '../../../tools/puml.js';


export function UF(n) {
  const parent = [];
  const rank = [];

  for (let groupId = 0; groupId < n; groupId++) {
    parent[groupId] = groupId;
    rank[groupId] = 0;
  }

  const find = (a) => {
    while (a !== parent[a]) {
      a = parent[a];
    }

    return a;
  };

  const union = (a, b) => {
    a = find(a);
    b = find(b);

    if (rank[a] > rank[b]) {
      parent[b] = a;
    } else if (rank[a] < rank[b]) {
      parent[a] = b;
    } else {
      rank[a]++;
      parent[b] = a;
    }
  };

  const debug = () => {
    const out = [];
    for (let nodeId = 0; nodeId < n; nodeId++) {
      out.push(`${nodeId} (${rank[nodeId]}) -> ${parent[nodeId]}`);
    }

    console.log(out.join('\n'));
  };

  return {
    union,
    find,
    debug
  };
}

/**
 * Return the all critical connections ("Bridges") in a Network
 *
 * @param {number} n
 * @param {[number, number][]} connections
 * @return {[number, number][]}
 */
export const criticalConnections = function (n, connections) {
  const CONNECTED = 1;
  // const IN_CYCLE = 2;

  const depth = [];
  const lo = [];

  const graph = [];
  for (let r = 0; r < n; r++) {
    graph[r] = [];
  }

  const setPath = (graph, a, b, value) => {
    if (a < b) {
      graph[b][a] = value;
    } else {
      graph[a][b] = value;
    }
  };

  const getPath = (graph, a, b) => {
    if (a < b) {
      return graph[b] ? graph[b][a] : 0;
    } else {
      return graph[a] ? graph[a][b] : 0;
    }
  }

  const getConnections = function *(graph, a) {
    for (let b = 0; b < n; b++) {
      if (a === b) {
        continue;
      }

      if (getPath(graph, a, b) === CONNECTED) {
        yield b;
      }
    }
  }

  const {find, union} = new UF(n);
  const deferred_connections = [];
  const _debug_connections = [];

  // Build MST
  for (let [a, b] of connections) {
    if (find(a) !== find(b)) {
      setPath(graph, a, b, CONNECTED);
      union(a, b);

      _debug_connections.push([a, b, 'bold']);
    } else {
      // skip
      _debug_connections.push([a, b, 'dotted']);
      deferred_connections.push([a, b]);
    }
  }

  // Set LongPath (depth in MST) from root (arbitrary node)
  let roodGroupId = find(connections[0][0]);
  depth[roodGroupId] = 1;
  lo[roodGroupId] = 1;

  let queue = [roodGroupId];
  let visited = [];

  while (queue.length > 0) {
    const node = queue.pop();

    if (visited[node]) {
      continue;
    }
    visited[node] = true;

    const level = depth[node]

    for (let child of getConnections(graph, node)) {
      if (visited[child]) {
        continue;
      }

      depth[child] = level+1;
      lo[child] = level+1;
      queue.push(child);
    }
  }

  // Add remaining connection to describe all graph edges
  for (const [a, b] of deferred_connections) {
    setPath(graph, a, b, CONNECTED);
  }

  queue = [roodGroupId];
  visited = [];

  while (queue.length > 0) {
    const node = queue.pop();
    visited[node] = true;

    for (let child of getConnections(graph, node)) {
      lo[node] = Math.min(lo[node], depth[child]);
      if (visited[child]) {
        // lo[node] = Math.min(lo[node], depth[child]);
        continue;
      }
      // lo[node] = Math.min(lo[node], lo[child]);
      queue.push(child);
    }
  }

  const labels = [];
  for (let nodeId=0; nodeId<n; nodeId++) {
    if (!depth[nodeId]) {
      continue;
    }
    labels[nodeId] = ''+depth[nodeId]+'/'+lo[nodeId];
  }
  puml(n, _debug_connections, './graph/mst-debug.puml', labels);

  // @TODO: find loops (exclude edges which belongs to loop)
  // @TODO: return the not-excluded edges - bridges/critical connections
  return [];
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
