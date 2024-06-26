/*
847. Shortest Path Visiting All Nodes. (https://leetcode.com/problems/shortest-path-visiting-all-nodes/)

You have an undirected, connected graph of n nodes labeled from 0 to n - 1.
You are given an array graph where graph[i] is a list of all the nodes connected with node i by an edge.

Return the length of the shortest path that visits every node.
You may start and stop at any node, you may revisit nodes multiple times, and you may reuse edges.
 */

class PQ {
    constructor(compare = null) {
        this.array = [];
        this.length = 0;
        this.compare = compare;

        // Use Default comparator if not defined
        if (!this.compare) {
            this.compare = (a, b) => {
                return a - b;
            };
        }
    }

    clear() {
        this.array.length = 0;
        this.length = 0;
    }

    size() {
        return this.length;
    }

    enqueue(value) {
        this.length++;
        let i = this.length;
        let parent;
        let diff;

        this.array[i] = value;

        while (i > 1) {
            parent = Math.floor(i/2);

            diff = this.compare(this.array[i], this.array[parent]);

            if (diff < 0) {
                this._swap(i, parent);
                i = parent;
            } else {
                break;
            }
        }
    }

    dequeue() {
        const out = this.array[1];

        this.array[1] = this.array[this.length];
        this.array[this.length] = undefined;
        this.length--;

        let i = 1;
        let left, right, next;

        while(i < this.length) {
            left = i * 2;
            right = i * 2 + 1;

            if(this.array[left] && this.compare(this.array[i],this.array[left]) > 0) {
                next = left;
            } else if(this.array[right] && this.compare(this.array[i],this.array[right]) > 0) {
                next = right;
            } else {
                break;
            }

            this._swap(i, next);
            i = next;
        }

        return out;
    }

    _swap(i, j) {
        const tmp = this.array[i];
        this.array[i] = this.array[j];
        this.array[j] = tmp;
    }
}

/**
 * @param {number[][]} graph
 * @return {number}
 */
const shortestPathLength = function(graph) {
    let len = Infinity;
    const N = graph.length;
    const FULL_MASK = (1 << N) - 1;

    const q = new PQ((a, b) => {
        return a[1] - b[1];
    });

    const path = [];
    for (let i=0; i<N;i++) {
        path[i] = [];
    }

    for (let node = 0; node < N; node++) {
        if (graph[node].length === 1) {
            q.enqueue([node, 1 << node]);
            path[node][1 << node] = 0;
        }
    }

    if (q.size() === 0) {
        q.enqueue([0, 1]);
        path[0][1] = 0;
    }

    let nextVisited;
    while (q.size() > 0) {
        const [node, visited] = q.dequeue();

        if (FULL_MASK === visited) {
            len = Math.min(len, path[node][visited]);
            continue;
        }

        for (let next of graph[node]) {
            nextVisited = visited | (1 << next);
            if (path[next][nextVisited] === undefined) {
                path[next][nextVisited] = Infinity;
            }
            
            if ((path[node][visited] + 1) < path[next][nextVisited]) {
                path[next][nextVisited] = path[node][visited] + 1;
                q.enqueue([next, nextVisited]);
            }
        }
    }

    return len;
};


module.exports = shortestPathLength;