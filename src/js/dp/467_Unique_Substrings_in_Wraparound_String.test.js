import findSubstringInWraproundString from './467_Unique_Substrings_in_Wraparound_String.js';

describe('467. Unique Substrings in Wraparound String', () => {
  function runTest(s, expected) {
    const actual = findSubstringInWraproundString(s);
    expect(actual).toEqual(expected);
  }

  it('should pass Example 1', () => {
    const s = "a";
    const expected = 1;

    runTest(s, expected);
  });

  it('should pass Example 2', () => {
    const s = "cac";
    const expected = 2;

    runTest(s, expected);
  });

  it('should pass Example 3', () => {
    const s = "zab";
    const expected = 6;

    runTest(s, expected);
  });

  (() => {
    const testCases = [
      ["xyzabfghuv", 24],
    ];

    for (const testCase of testCases) {
      const [ s, expected ] = testCase;

      it(`should equal ${expected} for "${s}"`, () => {
        runTest(s, expected);
      });
    }
  })();
});
