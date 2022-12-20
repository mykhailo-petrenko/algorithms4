/*
# 695. Max Area of Island (https://leetcode.com/problems/max-area-of-island/description/)

You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
The area of an island is the number of cells with a value 1 in the island.
Return the maximum area of an island in grid. If there is no island, return 0.


Example 1:
    Input: grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
    Output: 6
    Explanation: The answer is not 11, because the island must be connected 4-directionally.

Example 2:
    Input: grid = [[0,0,0,0,0,0,0,0]]
    Output: 0


Constraints:
    m == grid.length
    n == grid[i].length
    1 <= m, n <= 50
    grid[i][j] is either 0 or 1.
*/

/**
 * @param {number[][]} grid
 * @return {number}
 */

var siblings = function* (y, x) {
    if (y > 0) {
        yield [y-1, x];
    }

    if (x > 0) {
        yield [y, x-1];
    }
}

var getTopMostGroup = (graph, a) => {
    let parent;

    while (graph[a][0] !== a) {
        parent = graph[a][0];
        a = parent;
    }

    return graph[a];
};

var vertexUnion = (graph, a, b) => {
    let groupA = getTopMostGroup(graph, a);
    let groupB = getTopMostGroup(graph, b);
    let tmp;
    
    if (groupA[0] === groupB[0]) {
        return groupA[1];
    }

    if (groupA[0] > groupB[0]) {
        tmp = groupA;
        groupA = groupB;
        groupB = tmp;
    }

    groupB[0] = groupA[0];
    groupA[1] += groupB[1];

    return groupA[1];
};

var maxAreaOfIsland = function(grid) {
    const M = grid.length;
    const N = grid[0].length;
    let cellId;
    let sCellId;
    let maxArea = 0;
    let area;

    const graph = {}; // graph[cellId] = [groupID, area];

    for (let y = 0; y < M; y++) {
        for (let x = 0; x < N; x++) {
            cellId = x + y * N;

            if (grid[y][x] !== 1) continue;

            if (graph[cellId] === undefined) {
                graph[cellId] = [cellId, 1];
            }

            for ([sY, sX] of siblings(y, x)) {
                if (grid[sY][sX] !== 1) continue;

                sCellId = sX + sY * N;

                area = vertexUnion(graph, sCellId, cellId)
                maxArea = Math.max(maxArea, area);
            }
        }
    }

    if (maxArea === 0 && Object.keys(graph).length > 0) {
        return 1;
    }

    return maxArea;
};



let input;

input = [
    [0,0,1,0,0,0,0,1,0,0,0,0,0],
    [0,0,0,0,0,0,0,1,1,1,0,0,0],
    [0,1,1,0,1,0,0,0,0,0,0,0,0],
    [0,1,0,0,1,1,0,0,1,0,1,0,0],
    [0,1,0,0,1,1,0,0,1,1,1,0,0],
    [0,0,0,0,0,0,0,0,0,0,1,0,0],
    [0,0,0,0,0,0,0,1,1,1,0,0,0],
    [0,0,0,0,0,0,0,1,1,0,0,0,0]
];

assert(6, maxAreaOfIsland(input), "Area should be 6");

input = [[1]];
assert(1, maxAreaOfIsland(input), "Area should be 1");

input = [[0]];
assert(0, maxAreaOfIsland(input), "Area should be 0");

input = [[0, 0, 0, 0], [0, 0, 0, 0]];
assert(0, maxAreaOfIsland(input), "Area should be 0");

function assert(expected, actual, message) {
    if (expected === actual) {
        console.log(` + OK. ${message}. '${expected} === ${actual}'.`);
    } else {
        console.error(`x FAILED! ${message}. Expected '${expected}' but got '${actual}' :o(`);
    }
}