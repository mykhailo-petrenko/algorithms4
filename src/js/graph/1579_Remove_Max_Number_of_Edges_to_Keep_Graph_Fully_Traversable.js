/**
 * # LeetCode. 1579. Remove Max Number of Edges to Keep Graph Fully Traversable (https://leetcode.com/problems/remove-max-number-of-edges-to-keep-graph-fully-traversable/description/)
 *
 * Alice and Bob have an undirected graph of n nodes and three types of edges:
 *
 * Type 1: Can be traversed by Alice only.
 * Type 2: Can be traversed by Bob only.
 * Type 3: Can be traversed by both Alice and Bob.
 * Given an array edges where edges[i] = [typei, ui, vi] represents a bidirectional edge of type typei between nodes ui and vi,
 * find the maximum number of edges you can remove so that after removing the edges,
 * the graph can still be fully traversed by both Alice and Bob.
 * The graph is fully traversed by Alice and Bob if starting from any node, they can reach all other nodes.
 *
 * Return the maximum number of edges you can remove, or return -1 if Alice and Bob cannot fully traverse the graph.
 *
 * ## Example 1:
 * Input: n = 4, edges = [[3,1,2],[3,2,3],[1,1,3],[1,2,4],[1,1,2],[2,3,4]]
 * Output: 2
 * Explanation: If we remove the 2 edges [1,1,2] and [1,1,3]. The graph will still be fully traversable by Alice and Bob.
 * Removing any additional edge will not make it so. So the maximum number of edges we can remove is 2.
 *
 * ## Example 2:
 * Input: n = 4, edges = [[3,1,2],[3,2,3],[1,1,4],[2,1,4]]
 * Output: 0
 * Explanation: Notice that removing any edge will not make the graph fully traversable by Alice and Bob.
 *
 * ## Example 3:
 * Input: n = 4, edges = [[3,2,3],[1,1,2],[2,3,4]]
 * Output: -1
 * Explanation: In the current graph, Alice cannot reach node 4 from the other nodes. Likewise, Bob cannot reach 1.
 * Therefore it's impossible to make the graph fully traversable.
 *
 * ## Constraints:
 * - 1 <= n <= 10^5
 * - 1 <= edges.length <= min(10^5, 3 * n * (n - 1) / 2)
 * - edges[i].length == 3
 * - 1 <= typei <= 3
 * - 1 <= ui < vi <= n
 * - All tuples (typei, ui, vi) are distinct.
 */

import UF from "../utils/uf.js";

/**
 * @param {number} n
 * @param {number[][]} edges
 * @return {number}
 */
var maxNumEdgesToRemove = function(n, edges) {
    const uf3 = new UF(n+1);
    const uf1 = new UF(n+1);
    const uf2 = new UF(n+1);

    let toRemove = 0;

    // MST for type 3 (A | B)
    for (const [type, u, v] of edges.filter(e => e[0] === 3)) {
        if (uf3.group(u) === uf3.group(v)) {
            toRemove++;
        } else {
            uf3.union(u, v);
            uf1.union(u, v);
            uf2.union(u, v);
        }
    }

    // MST for type 1 (A)
    for (const [type, u, v] of edges.filter(e => e[0] === 1)) {
        if (uf1.group(u) === uf1.group(v)) {
            toRemove++;
        } else {
            uf1.union(u, v);
        }
    }

    // MST for type 2 (B)
    for (const [type, u, v] of edges.filter(e => e[0] === 2)) {
        if (uf2.group(u) === uf2.group(v)) {
            toRemove++;
        } else {
            uf2.union(u, v);
        }
    }

    let groupsCount1 = 0;
    let groupsCount2 = 0;
    for (let i=1; i<=n; i++) {
        if (uf1.entities[i] === i) {
            groupsCount1++;
        }

        if (uf2.entities[i] === i) {
            groupsCount2++;
        }
    }

    if (groupsCount1 > 1 || groupsCount2 > 1) {
        return -1;
    }

    return toRemove;
};

export default maxNumEdgesToRemove;
