import smallestStringWithSwaps from './1202_Smallest_String_With_Swaps.js';

describe('1202. Smallest String With Swaps', () => {
  function runTest(input, expected) {
    const actual = smallestStringWithSwaps(...input);
    expect(actual).toEqual(expected);
  }

  (() => {
    const testCases = [
      [
        ["dcab", [[0,3],[1,2]]], // input
        "bacd" // expected
      ],
      [
        ["dcab", [[0,3],[1,2],[0,2]]], // input
        "abcd" // expected
      ],
      [
        ["cba", [[0,1],[1,2]]], // input
        "abc" // expected
      ],
      [
        ["cbaaaa", [[0,1],[1,2],[2,4]]], // input
        "aabaca" // expected
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
