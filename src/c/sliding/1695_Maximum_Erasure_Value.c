//
// Created by Mykhailo Petrenko on 16/12/2022.
//
/*
# 1695. Maximum Erasure Value [https://leetcode.com/problems/maximum-erasure-value/]

You are given an array of positive integers nums and want to erase a subarray containing unique elements.
The score you get by erasing the subarray is equal to the sum of its elements.
Return the maximum score you can get by erasing exactly one subarray.
An array b is called to be a subarray of a if it forms a contiguous subsequence of a, that is, if it is equal to a[l],a[l+1],...,a[r] for some (l,r).

## Example 1:
    Input: nums = [4,2,4,5,6]
    Output: 17
    Explanation: The optimal subarray here is [2,4,5,6].

## Example 2:
    Input: nums = [5,2,1,2,5,2,1,2,5]
    Output: 8
    Explanation: The optimal subarray here is [5,2,1] or [1,2,5].

## Constraints:
    1 <= nums.length <= 105
    1 <= nums[i] <= 104

*/

#include "../lib/utest.h"

int maximumUniqueSubarray(int* nums, int numsSize){
    // @TODO: Solve problem =)

    return 0;
}

void doTest(int* nums, int numsSize, int expected) {
    int actual = maximumUniqueSubarray(nums, numsSize);
    assert_equals_int("Smoke. Maximum Erasure Value", &expected, &actual);
}

int main() {
    int nums1[] = {4,2,4,5,6};
    doTest(nums1, 5, 17);

    int nums2[] = {5,2,1,2,5,2,1,2,5};
    doTest(nums2, 9, 8);

    return 0;
}