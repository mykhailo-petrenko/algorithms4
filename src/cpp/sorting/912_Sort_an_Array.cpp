/**
 * LeetCode. 912. Sort an Array (https://leetcode.com/problems/sort-an-array/description/)
 *
 * Given an array of integers nums, sort the array in ascending order and return it.
 *
 * You must solve the problem without using any built-in functions in O(nlog(n)) time complexity
 * and with the smallest space complexity possible.
 *
 * Constraints:
 * - 1 <= nums.length <= 5 * 10^4
 * - -5 * 10^4 <= nums[i] <= 5 * 10^4
 */
//
// Created by Mykhailo Petrenko on 25/07/2024.
//
#include <iostream>
#include <vector>

using namespace std;

/**
 * Just use quick sort.
 */
class Solution {
private:
    static void swap(vector<int>& nums, const int i, const int j) {
        auto tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    static int partition(vector<int>& nums, int lo, int hi) {
        // Pivot value
        int pivot = nums[lo]; // Choose the first element as the pivot
        lo--;
        hi++;

        while (true) {
            // Move the left index to the right at least once and while the element at
            // the left index is less than the pivot
            do {
                lo++;
            }
            while (nums[lo] < pivot);

            // Move the right index to the left at least once and while the element at
            // the right index is greater than the pivot
            do {
                hi--;
            }
            while (nums[hi] > pivot);

            // If the indices crossed, return
            if (lo >= hi)
            {
                return hi;
            }

            // Swap the elements at the left and right indices
            swap(nums, lo, hi);
        }
    }

    static void quicksort(vector<int>& nums, int lo, int hi)
    {
        if (lo >= 0 && hi > 0 && lo < hi) {
            int p = partition(nums, lo, hi);
            quicksort(nums, lo, p);
            quicksort(nums, p + 1, hi);
        }
    }
public:
    vector<int> sortArray(vector<int>& nums) {
        quicksort(nums, 0, nums.size() - 1);

        return nums;
    }
};

void print_array(vector<int>& nums) {
    for (auto n: nums) {
        cout << n << " ";
    }

    cout << endl;
}

int main() {
    Solution test;

    vector<int> nums = {5,1,1,2,0,0};
    test.sortArray(nums);
    print_array(nums);

    return 0;
}