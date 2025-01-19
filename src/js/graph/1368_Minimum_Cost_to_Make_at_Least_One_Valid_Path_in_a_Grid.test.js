import testSubject from './1368_Minimum_Cost_to_Make_at_Least_One_Valid_Path_in_a_Grid.js';

describe('1368. Minimum Cost to Make at Least One Valid Path in a Grid', () => {
  function runTest(input, expected) {
    const actual = testSubject(input);
    expect(actual).toEqual(expected);
  }

  (() => {
    const testCases = [
      [
        [[1,1,1,1],[2,2,2,2],[1,1,1,1],[2,2,2,2]], // input
        3 // expected
      ],
      [
        [[1,1,3],[3,2,2],[1,1,4]], // input
        0 // expected
      ],
      [
        [[1,2],[4,3]], // input
        1 // expected
      ],
    ];

    for (const testCase of testCases) {
      const [ input, expected ] = testCase;

      it(`should equal ${expected} for "${input.join(',')}"`, () => {
        runTest(input, expected);
      });
    }
  })();
});
