/**
 * 1192. Critical Connections in a Network
 */
function Node(id, prev) {
  this.id = id;
  this.prev = prev;
}

/**
 * @param {number} n
 * @param {number[][]} connections
 * @return {number[][]}
 */
export const criticalConnections = function(n, connections) {
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

  while(stack.length > 0) {
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

    for (let next=0; next<n; next++) {
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
