/**
 * Union Find
 * Goals is to give fast an answer on a question:
 * - Are this two graph nodes connected?
 * - Are this two nodes belong to the same group?
 *
 * @param {number} n Nodes count
 * @returns {{union: (number, number) => void, group: (number) => void, entities: number[], debug: () => void}}
 * @constructor
 */
function UF(n) {
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
        group: find,
        entities: parent,
        debug,
    };
}

export default UF;
