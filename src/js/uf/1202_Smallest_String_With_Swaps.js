/**
# LeetCode. 1202. Smallest String With Swaps (https://leetcode.com/problems/smallest-string-with-swaps/description/)

You are given a string s, and an array of pairs of indices in the string pairs where pairs[i] = [a, b] indicates 2 indices(0-indexed) of the string.
You can swap the characters at any pair of indices in the given pairs any number of times.
Return the lexicographically smallest string that s can be changed to after using the swaps.

See (./1202_Smallest_String_With_Swaps.test.js) for examples.

*/


/**
 * @param {string} s
 * @param {number[][]} pairs
 * @return {string}
 */
var smallestStringWithSwaps = function(s, pairs) {
  const N = s.length;
  const uf = UF(N);
  const A = 'a'.charCodeAt(0);
  const groups = [];

  // Build the symbols connection DB
  for (let pair of pairs ) {
    uf.add(pair[0], pair[1]);
  }

  // For each connected symbols group - build the db of symbols and their quantity
  // (improvised has table for a 26 known elements).
  for (let i=0; i<N; i++) {
    const char = s.charCodeAt(i) - A;
    const groupId = uf.group(i);

    if (groups[groupId] === undefined) {
      groups[groupId] = new Uint32Array(26);
    }

    groups[groupId][char]++;
  }

  const out = [];

  // Extract the symbol with the lowest cost for each group per symbol.
  for (let i=0; i<N; i++) {
    const groupId = uf.group(i);

    for (let char = 0; char < 26; char++) {
      if (groups[groupId][char] === 0) {
        continue;
      }

      out[i] = char;
      groups[groupId][char]--;
      break;
    }
  }

  // Decode codes to symbols.
  return out.map((code) => String.fromCharCode(code + A)).join('');
};

/**
 * Union Find
 * Goals is to give fast an answer on a question:
 * - Are this two graph nodes connected?
 * - Are this two nodes belong to the same group?
 *
 * @param {number} n Nodes count
 * @returns {{add: (number, number) => void, union: (number, number) => void, connected: (number, number) => boolean, group: (number) => number, entities: number[], debug: () => void}}
 * @constructor
 */
function UF(n) {
  const parent = [];
  const rank = [];

  for (let groupId = 0; groupId < n; groupId++) {
    parent[groupId] = groupId;
    rank[groupId] = 0;
  }

  const find = (a) => {
    while (a !== parent[a]) {
      a = parent[a];
    }

    return a;
  };

  const union = (a, b) => {
    a = find(a);
    b = find(b);

    if (rank[a] > rank[b]) {
      parent[b] = a;
    } else if (rank[a] < rank[b]) {
      parent[a] = b;
    } else {
      rank[a]++;
      parent[b] = a;
    }
  };

  const connected = (a, b) => {
    a = find(a);
    b = find(b);

    return (a === b);
  };

  const debug = () => {
    const out = [];
    for (let nodeId = 0; nodeId < n; nodeId++) {
      out.push(`${nodeId} (${rank[nodeId]}) -> ${parent[nodeId]}`);
    }

    console.log(out.join('\n'));
  };

  return {
    union,
    add: union,
    find,
    group: find,
    connected,
    entities: parent,
    debug,
  };
}

export default smallestStringWithSwaps;
