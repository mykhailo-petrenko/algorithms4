/**
 * LeetCode. 273. Integer to English Words (https://leetcode.com/problems/integer-to-english-words/description/?envType=daily-question&envId=2024-08-07)
 */

/**
 * @param {number} num
 * @param {string | null} zero name
 * @return {string}
 */
var numberToWords = function(num, zero = 'Zero') {
  if (num === 0) {
    return zero;
  }

  const a = (n) => {
    switch(n) {
      case 1: return "One";
      case 2: return "Two";
      case 3: return "Three";
      case 4: return "Four";
      case 5: return "Five";
      case 6: return "Six";
      case 7: return "Seven";
      case 8: return "Eight";
      case 9: return "Nine";
    }

    return null;
  };

  const b = (n) => {
    switch(n) {
      case 1: return "Ten";
      case 2: return "Twenty";
      case 3: return "Thirty";
      case 4: return "Forty";
      case 5: return "Fifty";
      case 6: return "Sixty";
      case 7: return "Seventy";
      case 8: return "Eighty";
      case 9: return "Ninety";
    }

    return null;
  };

  if (num < 10) {
    return a(num);
  }

  if (num < 20) {
    if (num === 10) {
      return b(1);
    }
    if (num === 11) {
      return 'Eleven';
    }
    if (num === 12) {
      return 'Twelve';
    }
    if (num === 13) {
      return 'Thirteen';
    }
    if (num === 15) {
      return 'Fifteen';
    }
    if (num === 18) {
      return 'Eighteen';
    }

    return [
      a(Math.floor(num % 10)),
      'teen'
    ].join('');
  }

  if (num < 100) {
    const tens = Math.floor(num / 10);
    return [
      b(tens),
      a(num % 10)
    ].filter((n) => n).join(' ');
  }

  if (num < 1000) {
    const hundreds = Math.floor(num / 100);
    num = num % 100;
    return [
      a(hundreds),
      'Hundred',
      numberToWords(num, null),
    ].filter((n) => n).join(' ');
  }

  if (num < 1000000) {
    const thousands = Math.floor(num / 1000);
    num = num % 1000;
    return [
      numberToWords(thousands),
      'Thousand',
      numberToWords(num, null),
    ].filter((n) => n).join(' ');
  }

  if (num < 1000000000) {
    const millions = Math.floor(num / 1000000);
    num = num % 1000000;
    return [
      numberToWords(millions),
      'Million',
      numberToWords(num, null),
    ].filter((n) => n).join(' ');
  }

  const billions = Math.floor(num / 1000000000);
  num = num % 1000000000;
  return [
    numberToWords(billions),
    'Billion',
    numberToWords(num, null),
  ].filter((n) => n).join(' ');
};

export default numberToWords;
