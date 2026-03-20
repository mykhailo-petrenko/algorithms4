/**
 * Union Find
 * Goals is to give fast an answer on a question:
 * - Are this two graph nodes connected?
 * - Are this two nodes belong to the same group?
 *
 * @param {number} n Nodes count
 * @returns {{
 *      add: (number, number) => void,
 *      union: (number, number) => void,
 *      connected: (number, number) => boolean,
 *      find: (number) => number,
 *      group: (number) => number,
 *      groupsCount: () => number,
 *      entities: number[], debug: () => void
 * }}
 * @constructor
 */
export function UF(n) {
    const parent = [];
    const rank = [];
    let groupsCount = n;

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

        // Already the same group - do nothing,
        if (a === b) {
            return;
        }

        // merge group with a fewer members into more crowded group
        if (rank[a] < rank[b]) {
            parent[a] = b;
            rank[b] = rank[b] + rank[a];
        } else {
            parent[b] = a;
            rank[a] = rank[a] + rank[b];
        }

        groupsCount--;
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
        groupsCount: () => groupsCount,
        debug,
    };
}

export default UF;
