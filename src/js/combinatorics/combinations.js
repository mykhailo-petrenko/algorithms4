
/*
# 77. Combinations (https://leetcode.com/problems/combinations/)

Given two integers n and k, return all possible combinations of k numbers chosen from the range [1, n].
You may return the answer in any order.

## Example 1:
    Input: n = 4, k = 2
    Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
    Explanation: There are 4 choose 2 = 6 total combinations.
    Note that combinations are unordered, i.e., [1,2] and [2,1] are considered to be the same combination.

## Example 2:
Input: n = 1, k = 1
Output: [[1]]
Explanation: There is 1 choose 1 = 1 total combination.

## Constraints:
    1 <= n <= 20
    1 <= k <= n
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
                out.push([i+1]);
            }

        } else {
            
            for (let i = from; i <= n - length; i++) {

                out = out.concat(
                    sub(i + 1, length - 1)
                        .map(out => [i+1, ...out])
                );

            }

        }


        return out;
    };

    return sub(0, k);
}


// int main =))

function doTest(n, k) {
    console.log(`n=${n}, k=${k}: `, combine(n, k));
};

doTest(4, 2);
doTest(4, 3);
doTest(5, 3);
