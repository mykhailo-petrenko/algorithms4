const shortestPathLength = require('./847_Shortest_Path_Visiting_All_Nodes');

{
    const input = [[2,10],[2,7],[0,1,3,4,5,8],[2],[2],[2],[8],[9,11,8,1],[7,6,2],[7],[11,0],[7,10]];

    test(JSON.stringify(input) + ' -> 15', () => {
        const graph = input;
        const expected = 15;
    
        expect(shortestPathLength(graph)).toEqual(expected);
    });
}

test('[[7],[3],[3,9],[1,2,4,5,7,11],[3],[3],[9],[3,10,8,0],[7],[11,6,2],[7],[3,9]] -> 17', () => {
    const graph = [[7],[3],[3,9],[1,2,4,5,7,11],[3],[3],[9],[3,10,8,0],[7],[11,6,2],[7],[3,9]];
    const expected = 17;

    expect(shortestPathLength(graph)).toEqual(expected);
});

test('[[2,5,7],[2,4],[0,1],[5],[5,6,1],[4,10,8,0,3],[4,9],[0],[5],[6],[5]] -> 13', () => {
    const graph = [[2,5,7],[2,4],[0,1],[5],[5,6,1],[4,10,8,0,3],[4,9],[0],[5],[6],[5]];
    const expected = 13;

    expect(shortestPathLength(graph)).toEqual(expected);
});

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

test('[[1],[2],[3],[0]] -> 3', () => {
    const graph = [[1],[2],[3],[0]];
    const expected = 3;

    expect(shortestPathLength(graph)).toEqual(expected);
});

test('[[1],[2],[3],[0]] -> 3', () => {
    const graph = [[1,2,3],[0,2,3],[0,1,3],[0,1,2]];
    const expected = 3;

    expect(shortestPathLength(graph)).toEqual(expected);
});

test('[[1],[0,2,4],[1,3],[2],[1,5],[4]] -> 6', () => {
    const graph = [[1],[0,2,4],[1,3],[2],[1,5],[4]];
    const expected = 6;

    expect(shortestPathLength(graph)).toEqual(expected);
});

// {
//     const input = [[1,2,3,4,5,6,7,8,9],[0,2,3,4,5,6,7,8,9],[0,1,3,4,5,6,7,8,9],[0,1,2,4,5,6,7,8,9],[0,1,2,3,5,6,7,8,9],[0,1,2,3,4,6,7,8,9],[0,1,2,3,4,5,7,8,9],[0,1,2,3,4,5,6,8,9],[0,1,2,3,4,5,6,7,9,10],[0,1,2,3,4,5,6,7,8,11],[8],[9]];
//     const expected = 11;
//     test(JSON.stringify(input) + ' -> ' + expected, () => {
//         const graph = input;
   
//         expect(shortestPathLength(graph)).toEqual(expected);
//     });
// }
