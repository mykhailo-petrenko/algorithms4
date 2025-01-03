/**
# 46. Permutations (https://leetcode.com/problems/permutations/)

Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.


Example 1:
Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

Example 2:
Input: nums = [0,1]
Output: [[0,1],[1,0]]

Example 3:
Input: nums = [1]
Output: [[1]] 

Constraints:
1 <= nums.length <= 6
-10 <= nums[i] <= 10
All the integers of nums are unique.
*/

/**
 * @param {number[]} nums
 * @return {number[][]}
 */
var permute = function(nums) {
    const out = [];

    const swap = (arr, a, b) => {
        const tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    };

    const sub = (cursor, input, level) => {
        let buff = [];

        for(let i = cursor; i < input.length; i++) {
            buff = [...input];
            swap(buff, cursor, i);

            if (level === input.length-1) {
                out.push(input);
            } else {
                sub(level+1, buff, level+1);
            }
        }
    };

    sub(0, nums, 0);

    return out;
};


function doTest(arr) {
    console.log('> 46. Permutations: ', arr);

    console.log(permute(arr));
}


doTest([1,2]);
doTest([1,2,3]);
doTest([1,2,3,4]);
