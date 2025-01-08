import testSubject from './474_Ones_and_Zeroes.js';

describe('474. Ones and Zeroes', () => {
  function runTest(input, expected) {
    const actual = testSubject(...input);
    expect(actual).toEqual(expected);
  }

  (() => {
    const testCases = [
      [
        [
          ["10","0001","111001","1","0"],
          5,
          3
        ],
        4
      ],[
        [
          ["10","0","1"],
          1,
          1
        ],
        2
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
