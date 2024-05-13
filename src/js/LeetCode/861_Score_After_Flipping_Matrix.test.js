import matrixScore from './861_Score_After_Flipping_Matrix.js';

describe('861. Score After Flipping Matrix', () => {
  it('should pass Example 1', () => {
    const grid = [[0,0,1,1],[1,0,1,0],[1,1,0,0]];

    const actual = matrixScore(grid);

    expect(actual).toEqual(39);
  });

  it('should pass Example 1', () => {
    const grid = [[0]];

    const actual = matrixScore(grid);

    expect(actual).toEqual(1);
  });

  it('should be 0 for all 1 matrix', () => {
    const grid = [[1, 1, 1], [1, 1, 1], [1, 1, 1]];

    const actual = matrixScore(grid);

    expect(actual).toEqual(21);
  });

  it('should be 21 for all 1 matrix', () => {
    const grid = [[1, 1, 1], [1, 1, 1], [1, 1, 1]];

    const actual = matrixScore(grid);

    expect(actual).toEqual(21);
  });

  it('should pass chess pattern 1', () => {
    const grid = [
      [1, 0, 1, 0],
      [0, 1, 0, 1],
      [1, 0, 1, 0],
      [0, 1, 0, 1],
    ];

    const actual = matrixScore(grid);

    expect(actual).toEqual(60);
  });

  it('should pass chess pattern 2', () => {
    const grid = [
      [0, 1, 0, 1],
      [1, 0, 1, 0],
      [0, 1, 0, 1],
      [1, 0, 1, 0],
    ];

    const actual = matrixScore(grid);

    expect(actual).toEqual(60);
  });
});
