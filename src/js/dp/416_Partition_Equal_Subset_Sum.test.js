import canPartition from './416_Partition_Equal_Subset_Sum.js';

describe('416. Partition Equal Subset Sum', () => {

  it('should pass Example 1', () => {
    const nums = [1,5,11,5];
    const expected = true;

    const actual = canPartition(nums);

    expect(actual).toEqual(expected);
  });

  it('should pass Example 2', () => {
    const nums = [1,2,3,5];
    const expected = false;

    const actual = canPartition(nums);

    expect(actual).toEqual(expected);
  });

  const tests = [
    {
      nums: [1,2,3,5,1,5,3,6,8,9],
      expected: false,
    },
    {
      nums: [10, 2, 1, 9],
      expected: true,
    }
  ];

  for (const {nums, expected} of tests) {
    it(`should return ${expected} for [${nums.join(',')}]`, () => {
      const actual = canPartition(nums);
      expect(actual).toEqual(expected);
    });
  }

});
