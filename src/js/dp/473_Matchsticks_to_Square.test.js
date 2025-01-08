import testSubject from './473_Matchsticks_to_Square.js';

describe('473. Matchsticks to Square', () => {
  function runTest(input, expected) {
    const actual = testSubject(...input);
    expect(actual).toEqual(expected);
  }

  (() => {
    const testCases = [
      [
        [[1,1,2,2,2]], // input
        true // expected
      ],
      [
        [[3,3,3,3,4]], // input
        false // expected
      ],
      [
        [[1,1,2,2,2,2]], // input
        false // expected
      ],
      [
        [[5,5,5,5,4,4,4,4,3,3,3,3]],
        true
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
