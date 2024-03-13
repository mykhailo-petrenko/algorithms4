import { minMutation } from './433_Minimum_Genetic_Mutation.js';


describe('433. Minimum Genetic Mutation', () => {
  it('should pass smoke 1', () => {
    expect(
      minMutation(
      "AACCGGTT",
      "AACCGGTA",
      ["AACCGGTA"]
      )
    ).toEqual(1);
  });

  it('should pass smoke 2', () => {
    expect(
      minMutation(
        "AACCGGTT",
        "AAACGGTA",
        ["AACCGGTA","AACCGCTA","AAACGGTA"]
      )
    ).toEqual(2);
  });

  it('should pass smoke 3', () => {
    expect(
      minMutation(
        "AACCGGTT",
        "AACCGGTA",
        []
      )
    ).toEqual(-1);
  });

  it('should pass smoke 4', () => {
    expect(
      minMutation(
        "AAAACCCC",
        "CCCCCCCC",
        ["AAAACCCA","AAACCCCA","AACCCCCA","AACCCCCC","ACCCCCCC","CCCCCCCC","AAACCCCC","AACCCCCC"]
      )
    ).toEqual(4);
  });
});
