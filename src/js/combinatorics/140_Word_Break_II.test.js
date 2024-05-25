import wordBreak from './140_Word_Break_II.js';

describe('140. Word Break II ', () => {
  it('should pass Example ', () => {
    const s = "catsanddog";
    const wordDict = ["cat","cats","and","sand","dog"];

    const actual = wordBreak(s, wordDict);

    expect(actual).toEqual(["cat sand dog", "cats and dog"]);
  });

  it('should pass Example ', () => {
    const s = "pineapplepenapple";
    const wordDict = ["apple","pen","applepen","pine","pineapple"];

    const actual = wordBreak(s, wordDict);

    expect(actual).toEqual([
      "pine apple pen apple",
      "pine applepen apple",
      "pineapple pen apple",
    ]);
  });

  it('should pass Example ', () => {
    const s = "catsandog";
    const wordDict = ["cats","dog","sand","and","cat"];

    const actual = wordBreak(s, wordDict);

    expect(actual).toEqual([]);
  });
});
