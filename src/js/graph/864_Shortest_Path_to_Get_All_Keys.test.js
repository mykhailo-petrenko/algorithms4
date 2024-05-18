import shortestPathAllKeys from './864_Shortest_Path_to_Get_All_Keys.js';

describe('864. Shortest Path to Get All Keys', () => {

  it('should pass Example 1', () => {
    const actual = shortestPathAllKeys(["@.a..","###.#","b.A.B"]);
    expect(actual).toEqual(8);
  });

  it('should pass Example 2', () => {
    const actual = shortestPathAllKeys(["@..aA","..B#.","....b"]);
    expect(actual).toEqual(6);
  });

  it('should pass Example 3', () => {
    const actual = shortestPathAllKeys(["@Aa"]);
    expect(actual).toEqual(-1);
  });

  it('should be fast enough', () => {
    const actual = shortestPathAllKeys(["..#....##.","....d.#.D#","#...#.c...","..##.#..a.","...#....##","#....b....",".#..#.....","..........",".#..##..A.",".B..C.#..@"]);
    expect(actual).toEqual(19);
  });

  it('should pass Case 19', () => {
    const actual = shortestPathAllKeys(["@fedcbBCDEFaA"]);
    expect(actual).toEqual(11);
  });
  it('should pass Case 20', () => {
    const actual = shortestPathAllKeys([".##..##...","...#.#.#B#",".#.#......",".#....#...","...###...C","#.##..#.#.","...A.c....","#..a.@..##","##..#.....","..#....b.."]);
    expect(actual).toEqual(10);
  });

});
