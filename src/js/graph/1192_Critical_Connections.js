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

  // @TODO: minimum spanning tree
  const mst = [];

  for (let r = 0; r < n; r++) {
    mst[r] = [];
  }

  const setPath = (graph, a, b, value) => {
    if (a < b) {
      graph[b][a] = value;
    } else {
      graph[a][a] = value;
    }
  };

  const getPath = (graph, a, b) => {
    if (a < b) {
      return graph[b][a];
    } else {
      return graph[a][b];
    }
  }

  const {find, union} = new UF(n);

  const _debug_connections = [];

  for (let [a, b] of connections) {
    if (find(a) !== find(b)) {
      setPath(mst, a, b, CONNECTED);
      union(a, b);

      _debug_connections.push([a, b, 'bold']);
    } else {
      // skip
      _debug_connections.push([a, b, 'dotted']);
    }
  }

  puml(n, _debug_connections, './graph/mst-debug.puml');

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
