import testSubject from './2017_Grid_Game.js';

describe('2017. Grid Game', () => {
  function runTest(input, expected) {
    const actual = testSubject(input);
    expect(actual).toEqual(expected);
  }

  (() => {
    const testCases = [
      [
        [[2,5,4],[1,5,1]],
        4 // expected
      ],
      [
        [[3,3,1],[8,5,2]],
        4 // expected
      ],
      [
        [[1,3,1,15],[1,3,3,1]],
        7 // expected
      ],
      [
        [[20,3,20,17,2,12,15,17,4,15],
         [20,10,13,14,15,5,2,3,14,3]],
        63 // expected
      ],
    ];

    for (const testCase of testCases) {
      const [ input, expected ] = testCase;

      it(`should equal ${expected} for "${input.join()}"`, () => {
        runTest(input, expected);
      });
    }
  })();
});
