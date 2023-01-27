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

class Node {
    constructor(id) {
        this.id = id;
        
        this.visited = new Set();
        this.visited.add(id);

        this.path = 0;
    }

    siblings(graph) {
        return graph[this.id];
    }
}

class MyQueue {
    constructor() {
        this.array = [];
    }

    size() {
        return this.array.length;
    }

    enqueue(value) {
        this.array.push(value);
    }

    push(value) {
        this.array.unshift(value);
    }

    dequeue() {
        return this.array.shift();
    }

    clear() {
        this.array.length = 0;
    }
}

/**
 * @param {number[][]} graph
 * @return {number}
 */
const shortestPathLength = function(graph) {
    const N = graph.length;

    const compare = (a, b) => {
        let diff = 0;

        diff = a.path - b.path;
        

        return diff;
    };

    let q = new PQ(compare);
    let q2 = new PQ(compare);

    let start = 0;

    for (let node = 0; node < N; node++) {
        if (graph[node].length === 1) {
            start = node;
            q.enqueue(new Node(start));
            // break;
        }
    }

    if (q.size() === 0) {
        q.enqueue(new Node(start));
    }

    const solutions = new Array(N).fill(Infinity);
    let len = 0;
    let attempt = 0;

    while(q.size() > 0 || q2.size() > 0) {
        const node = (q.size() > 0) ? q.dequeue() : q2.dequeue();

        if (node.path > (node.visited.size * 1.5)) {
            continue;
        }

        if (node.visited.size === N) {
            solutions[node.id] = Math.min(solutions[node.id], node.path);

            // if (attempt === 0 && solutions[node.id] > N-1) {
            //     q.clear();
            //     q.enqueue(new Node(node.id));
            //     attempt++;

            //     continue;
            // }
            // continue;
            break;
        }

        for (const sibling of node.siblings(graph)) {
            const next = new Node(sibling);
            const back = (node.visited.has(next.id)) ? 1 : 0;

            for (const v of node.visited.values()) {
                next.visited.add(v);
            }

            next.path = node.path + 1;
            q.enqueue(next)
        }
    }
// console.log(solutions);
    len = solutions.reduce((prev, next) => Math.min(prev, next));

    return len;
};


module.exports = shortestPathLength;