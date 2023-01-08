/*
# 198. House Robber (https://leetcode.com/problems/house-robber/description/)

You are a professional robber planning to rob houses along a street. 
Each house has a certain amount of money stashed, 
the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected 
and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

## Example 1:
  Input: nums = [1,2,3,1]
  Output: 4
  Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
  Total amount you can rob = 1 + 3 = 4.

## Example 2:
  Input: nums = [2,7,9,3,1]
  Output: 12
  Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
  Total amount you can rob = 2 + 9 + 1 = 12.
 

## Constraints:
  1 <= nums.length <= 100
  0 <= nums[i] <= 400
*/

/**
 * @param {number[]} nums
 * @return {number}
 */
var rob = function(nums) {
    const buffer = [];
    let max = 0;

    if (nums.length < 3) {
      return Math.max(nums[0], nums[1]);
    }

    for (let i = 0; i<nums.length; i++) {
      buffer[i] = 0;
    }

    buffer[0] = nums[0];
    buffer[1] = nums[1];
    
    for (let i = 2; i<nums.length; i++) {
      buffer[i] = Math.max(buffer[i], buffer[i-2] + nums[i]);
      if (i>2) {
        buffer[i] = Math.max(buffer[i], buffer[i-3] + nums[i]);
      }
    }

    return Math.max(buffer[nums.length-2], buffer[nums.length-1]);
};


function doTest(nums, expected) {
  const actual = rob(nums);

  if (actual === expected) {
    console.log(' + SUCCESS! return ', actual);
  } else {
    console.error(` -- Expected: ${expected} but got the ${actual}`);
  }
}

doTest([1,2,3,1], 4);
doTest([2,7,9,3,1], 12);
doTest([226,174,214,16,218,48,153,131,128,17,157,142,88,43,37,157,43,221,191,68,206,23,225,82,54,118,111,46,80,49,245,63,25,194,72,80,143,55,209,18,55,122,65,66,177,101,63,201,172,130,103,225,142,46,86,185,62,138,212,192,125,77,223,188,99,228,90,25,193,211,84,239,119,234,85,83,123,120,131,203,219,10,82,35,120,180,249,106,37,169,225,54,103,55,166,124], 7102);