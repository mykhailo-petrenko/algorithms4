import ladderLength from './127_Word_Ladder.js';

describe('LeetCode. 127. Word Ladder', () => {

  it('should pass Example 1', () => {
    const actual = ladderLength(
      "hit",
      "cog",
      ["hot","dot","dog","lot","log","cog"]
    );

    expect(actual).toEqual(5);
  });

  it('should pass Example 2', () => {
    const actual = ladderLength(
      "hit",
      "cog",
      ["hot","dot","dog","lot","log"]
    );

    expect(actual).toEqual(0);
  });

});
