/**
 * Union Find
 * Goals is to give fast an answer on a question:
 * - Are this two graph nodes connected?
 * - Are this two nodes belong to the same group?
 *
 * @param {number} n Nodes count
 * @returns {{add: (number, number) => void, union: (number, number) => void, connected: (number, number) => boolean, group: (number) => void, entities: number[], debug: () => void}}
 * @constructor
 */
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

    const connected = (a, b) => {
        a = find(a);
        b = find(b);

        return (a === b);
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
        add: union,
        find,
        group: find,
        connected,
        entities: parent,
        debug,
    };
}

export default UF;
