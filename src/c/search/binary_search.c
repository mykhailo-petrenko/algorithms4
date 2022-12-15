//
// Created by Mykhailo Petrenko on 05/07/2022.
//

#include <stdio.h>

#include "../lib/utest.h"

int binary_search(int needle, int* nums, int from, int to) {
    int lo = from;
    int hi = to;
    int mid;

    while (lo < hi) {
        mid = lo + (hi - lo) / 2;

        if (needle == nums[mid]) {
            return mid;
        }

        if (needle > nums[mid]) {
            lo = mid + 1;
        } else {
            hi = mid;
        }
    }

    if (needle == nums[mid]) {
        return lo;
    }

    return -1;
}


void do_test(int *nums, int from, int to, int search, int expected) {
    int out = binary_search(search, nums, from, to);
    assert_equals_int("mock", &expected, &out);
}

int main() {
    int nums[] = {1,3,5,6,8,9,10,11,13,14,15,18,23,28,50,68,101,505};
    int N = 18;
    int out;

    do_test(&nums[0], 0, N, 1, 0);
    do_test(&nums[0], 0, N, 3, 1);
    do_test(&nums[0], 0, N, 68, 15);
    do_test(&nums[0], 0, N, 101,  16);
    do_test(&nums[0], 0, N, 505, 17);


    do_test(&nums[0], 0, N, -1, -1);
    do_test(&nums[0], 0, N, 0, -1);
    do_test(&nums[0], 0, N, 100, -1);
    do_test(&nums[0], 0, N, 700, -1);
    do_test(&nums[0], 0, N, 506, -1);

    return 0;
}