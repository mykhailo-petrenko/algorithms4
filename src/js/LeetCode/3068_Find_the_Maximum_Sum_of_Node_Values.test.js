import maximumValueSum from './3068_Find_the_Maximum_Sum_of_Node_Values.js';

describe('3068. Find the Maximum Sum of Node Values', () => {
  it('should pass Example 1', () => {
    const actual = maximumValueSum(
      [1,2,1],
      3,
      [[0,1],[0,2]]
    );

    expect(actual).toEqual(6);
  });

  it('should pass Example 2', () => {
    const actual = maximumValueSum(
      [2, 3],
      7,
      [[0,1]]
    );

    expect(actual).toEqual(9);
  });

  it('should pass Example 3', () => {
    const actual = maximumValueSum(
      [7,7,7,7,7,7],
    3,
      [[0,1],[0,2],[0,3],[0,4],[0,5]]
    );

    expect(actual).toEqual(42);
  });

  it('should pass ', () => {
    const actual = maximumValueSum(
      [0, 1, 2, 3, 0, 1],
      3,
      [[0,1],[0,2],[0,3],[0,4],[0,5]]
    );

    expect(actual).toEqual(15);
  });
  it('should pass case 687 =)', () => {
    const actual = maximumValueSum(
      [24,78,1,97,44],
      6,
      [[0,2],[1,2],[4,2],[3,4]]
    );

    expect(actual).toEqual(260);
  });

});
