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

});
