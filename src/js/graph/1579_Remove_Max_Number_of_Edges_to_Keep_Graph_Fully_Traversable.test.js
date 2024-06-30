import maxNumEdgesToRemove from "./1579_Remove_Max_Number_of_Edges_to_Keep_Graph_Fully_Traversable.js";

describe('LeetCode. 1579. Remove Max Number of Edges to Keep Graph Fully Traversable', function () {

    it('should pass Example #1', function () {
        const n = 4;
        const edges = [[3,1,2],[3,2,3],[1,1,3],[1,2,4],[1,1,2],[2,3,4]];
        const expected = 2;

        const actual = maxNumEdgesToRemove(n, edges);

        expect(actual).toEqual(expected);
    });


    it('should pass Example #2', function () {
        const n = 4;
        const edges = [[3,1,2],[3,2,3],[1,1,4],[2,1,4]];
        const expected = 0;

        const actual = maxNumEdgesToRemove(n, edges);

        expect(actual).toEqual(expected);
    });


    it('should pass Example #3', function () {
        const n = 4;
        const edges = [[3,2,3],[1,1,2],[2,3,4]];
        const expected = -1;

        const actual = maxNumEdgesToRemove(n, edges);

        expect(actual).toEqual(expected);
    });

});
