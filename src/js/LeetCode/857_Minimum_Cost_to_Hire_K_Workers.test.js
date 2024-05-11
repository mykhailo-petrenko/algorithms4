import mincostToHireWorkers from './857_Minimum_Cost_to_Hire_K_Workers.js';

describe('857. Minimum Cost to Hire K Workers', () => {

  it('should pass Example 1', () => {
    const actual = mincostToHireWorkers([10,20,5], [70,50,30], 2);
    expect(actual).toEqual(105.00000);
  });

  it('should pass Example 2', () => {
    const actual = mincostToHireWorkers([3,1,10,10,1], [4,8,2,2,7], 3);
    expect(actual).toEqual(30.66667);
  });
});
