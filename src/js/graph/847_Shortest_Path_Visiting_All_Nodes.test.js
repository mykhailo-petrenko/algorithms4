const shortestPathLength = require('./847_Shortest_Path_Visiting_All_Nodes');


test('[[1,2,3],[0],[0],[0]] -> 4', () => {
    const graph = [[1,2,3],[0],[0],[0]];
    const expected = 4;

    expect(shortestPathLength(graph)).toEqual(expected);
});

test('[[1],[0,2,4],[1,3,4],[2],[1,2]] -> 4', () => {
    const graph = [[1],[0,2,4],[1,3,4],[2],[1,2]];
    const expected = 4;

    expect(shortestPathLength(graph)).toEqual(expected);
});

test('[[1],[0,2,4],[1,3,4,6],[2],[1,2,5],[4],[2,7],[6]] -> 9', () => {
    const graph = [[1],[0,2,4],[1,3,4,6],[2],[1,2,5],[4],[2,7],[6]];
    const expected = 9;

    expect(shortestPathLength(graph)).toEqual(expected);
});