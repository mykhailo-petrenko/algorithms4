import combinationSum2 from './40_Combination_Sum_II.js';

describe('40. Combination Sum II', () => {
  it('should pass Example #1', () => {
    const candidates = [10,1,2,7,6,1,5];
    const target = 8;

    // [1,1,2,5,6,7,10]
    const expected = [
      [1,1,6],
      [1,2,5],
      [1,7],
      [2,6],
    ];

    checkResult(candidates, target, expected);
  });

  it('should pass Example #2', () => {
    const candidates = [2,5,2,1,2];
    const target = 5;

    const expected = [
      [1,2,2],
      [5],
    ];

    checkResult(candidates, target, expected);
  });

  it('should run fast =)', () => {
    const candidates = [1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1];
    const target = 27;

    const expected = [];

    checkResult(candidates, target, expected);
  });

  it('should run fast #2', () => {
    const candidates = [1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1];
    const target = 27;

    const expected = [[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1]];

    checkResult(candidates, target, expected);
  });

  it('should run fast #3', () => {
    checkResult(
      [14,6,25,9,30,20,33,34,28,30,16,12,31,9,9,12,34,16,25,32,8,7,30,12,33,20,21,29,24,17,27,34,11,17,30,6,32,21,27,17,16,8,24,12,12,28,11,33,10,32,22,13,34,18,12],
      27,
      [[10,17],[11,16],[13,14],[27],[6,10,11],[6,21],[6,6,7,8],[6,7,14],[6,8,13],[6,9,12],[7,20],[7,8,12],[7,9,11],[8,8,11],[8,9,10],[9,18],[9,9,9]]
    );
  });


  function checkResult(candidates, target, expected) {
    const actual = combinationSum2(candidates, target);

    for (const r of actual) {
      r.sort((a, b) => a - b);
    }

    actual.sort();
    expect(actual).toEqual(expected);
  }

});
