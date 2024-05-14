import getMaximumGold from './1219_Path_with_Maximum_Gold.js';

describe('1219. Path with Maximum Gold', () => {

  it('should pass Example 1', () => {
    const grid = [
      [0,6,0],
      [5,8,7],
      [0,9,0]
    ];
    const actual = getMaximumGold(grid);

    expect(actual).toEqual(24);
  });

  it('should pass Example 2', () => {
    const grid = [[1,0,7],[2,0,6],[3,4,5],[0,3,0],[9,0,20]];
    const actual = getMaximumGold(grid);

    expect(actual).toEqual(28);
  });

  it('should find maximum', () => {
    const grid = [
      [1, 0, 7, 0, 0, 0],
      [2, 0, 6, 0, 1, 0],
      [3, 5, 6, 7, 4, 2],
      [4, 3, 1, 0, 2, 0],
      [3, 0, 5, 0, 20,0]
    ];
    const actual = getMaximumGold(grid);

    expect(actual).toEqual(60);
  });

  it('should find maximum', () => {
    const grid = [
      [ 0,1, 6,20, 0],
      [ 0,0, 3, 0, 0],
      [16,9,16, 8, 0],
      [14,0, 4,20, 9]];
    const actual = getMaximumGold(grid);

    expect(actual).toEqual(92);
  });

  it('performance test', () => {
    const grid = [[1,1,1,1,1],[1,1,1,1,1],[1,1,1,1,1],[1,1,1,1,1],[1,1,1,1,1]];
    const actual = getMaximumGold(grid);

    expect(actual).toEqual(25);
  });

});
