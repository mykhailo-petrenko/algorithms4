/*
# 784. Letter Case Permutation

Given a string s, you can transform every letter individually to be lowercase or uppercase to create another string.
Return a list of all possible strings we could create. Return the output in any order.

## Example 1:
    Input: s = "a1b2"
    Output: ["a1b2","a1B2","A1b2","A1B2"]

## Example 2:
    Input: s = "3z4"
    Output: ["3z4","3Z4"]

## Constraints:
    1 <= s.length <= 12
    s consists of lowercase English letters, uppercase English letters, and digits.
 */

/**
 * Generate all possible combinations of k numbers chosen from the range [1, n].
 * 
 * @param {number} n 
 * @param {number} k 
 */
const combine = (n, k) => {

    const sub = (from, length) => {
        let out = [];

        if (length === 1) {

            for (let i = from; i < n; i++) {
                out.push([i]);
            }

        } else {
            
            for (let i = from; i <= n - length; i++) {

                out = out.concat(
                    sub(i + 1, length - 1)
                        .map(out => [i, ...out])
                );

            }

        }


        return out;
    };

    return sub(0, k);
}

/**
 * Letter Case Permutation
 * 
 * @param {string} s
 * @return {string[]}
 */
var letterCasePermutation = function(s) {
    const CHAR_CODE_A = 'a'.charCodeAt(0);
    const CHAR_CODE_Z = 'z'.charCodeAt(0);


    s = s.toLowerCase();
    const sArr = s.split('');
    const letters = [];

    for (let i=0; i<s.length; i++) {
        if (s.charCodeAt(i) >= CHAR_CODE_A && s.charCodeAt(i) <= CHAR_CODE_Z) {
            letters.push(i);
        }
    }

    const out = [s];

    const N = letters.length;
    let node = null;

    for (let k = 1; k <= N; k++) {
        const combinations = combine(N, k);
        for (const combination of combinations) {
            node = [...sArr];
            for (const i of combination) {
                node[letters[i]] = node[letters[i]].toUpperCase();
            }
            out.push(node.join(''));
        }
    }

    return out;
};

function doTest(string) {
    console.log('letterCasePermutation> ', string);
    console.log(letterCasePermutation(string));
    console.log('');
}


doTest('ab');
doTest('1a32b43');
doTest('abc');
doTest('a1b2c');


