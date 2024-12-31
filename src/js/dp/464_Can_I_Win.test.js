import canIWin from './464_Can_I_Win.js';

describe('464. Can I Win ', () => {

  const tests = [
    [10, 11, false],
    [10, 0,  true],
    [10, 1,  true],
    [10, 20,  true],
    [10, 30,  true],
    [10, 30,  true],
    [10, 40,  false],
    [20, 80,  true],
    [5, 50, false],
    [5, 15, true],
    [20, 189, false],
  ];

  for (const [maxChoosableInteger, desiredTotal, expected] of tests) {
    it(`should return ${expected} for (${maxChoosableInteger}, ${desiredTotal})`, () => {
      const actual = canIWin(maxChoosableInteger, desiredTotal);
      expect(actual).toEqual(expected);
    });
  }
});
