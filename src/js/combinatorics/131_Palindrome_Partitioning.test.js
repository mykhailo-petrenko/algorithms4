import partition from './131_Palindrome_Partitioning.js';


describe('131. Palindrome Partitioning ', () => {
  it('should pass Example 1', () => {
    const actual = partition("aab");

    expect(actual).toEqual([["a","a","b"],["aa","b"]]);
  });

  it('should pass Example 2', () => {
    const actual = partition("a");

    expect(actual).toEqual([["a"]]);
  });

  it('should pass 9 symbols', () => {
    const actual = partition("ababaacc");

    expect(actual).toEqual([["a","b","a","b","a","a","c","c"],["a","b","a","b","a","a","cc"],["a","b","a","b","aa","c","c"],["a","b","a","b","aa","cc"],["a","b","aba","a","c","c"],["a","b","aba","a","cc"],["a","bab","a","a","c","c"],["a","bab","a","a","cc"],["a","bab","aa","c","c"],["a","bab","aa","cc"],["aba","b","a","a","c","c"],["aba","b","a","a","cc"],["aba","b","aa","c","c"],["aba","b","aa","cc"],["ababa","a","c","c"],["ababa","a","cc"]]);
  });
});

