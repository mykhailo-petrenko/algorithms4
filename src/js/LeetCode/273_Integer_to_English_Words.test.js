import numberToWords from './273_Integer_to_English_Words.js';

describe('273. Integer to English Words', () => {
  const cases = [
    [1, 'One'],
    [2, 'Two'],
    [3, 'Three'],
    [4, 'Four'],
    [5, 'Five'],
    [6, 'Six'],
    [7, 'Seven'],
    [8, 'Eight'],
    [9, 'Nine'],
    [0, 'Zero'],
    [10, 'Ten'],
    [11, 'Eleven'],
    [12, 'Twelve'],
    [13, 'Thirteen'],
    [14, 'Fourteen'],
    [15, 'Fifteen'],
    [18, 'Eighteen'],
    [260, 'Two Hundred Sixty'],
    [555, 'Five Hundred Fifty Five'],
    [508, 'Five Hundred Eight'],
    [520, 'Five Hundred Twenty'],
    [900, 'Nine Hundred'],
    [987, 'Nine Hundred Eighty Seven'],
    [1555, 'One Thousand Five Hundred Fifty Five'],
    [12777, 'Twelve Thousand Seven Hundred Seventy Seven'],
    [87508, 'Eighty Seven Thousand Five Hundred Eight'],
    [543520, 'Five Hundred Forty Three Thousand Five Hundred Twenty'],
    [1510, 'One Thousand Five Hundred Ten'],
    [456260, 'Four Hundred Fifty Six Thousand Two Hundred Sixty'],
    [876900, 'Eight Hundred Seventy Six Thousand Nine Hundred'],
    [444987, 'Four Hundred Forty Four Thousand Nine Hundred Eighty Seven'],
    [147483647, 'One Hundred Forty Seven Million Four Hundred Eighty Three Thousand Six Hundred Forty Seven'],
    [2147483647, 'Two Billion One Hundred Forty Seven Million Four Hundred Eighty Three Thousand Six Hundred Forty Seven'],
  ];

  for (const [num, expected] of cases) {
    it(`numberToWords(${num}) should return ${expected}`, () => {
      expect(numberToWords(num)).toEqual(expected);
    });
  }
});
