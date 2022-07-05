//
// Created by Mykhailo Petrenko on 05/07/2022.
//

#include <stdlib.h>
#include <stdio.h>

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
            lo = mid;
        } else {
            hi = mid;
        }
    }

    return mid;
}


int main() {
    int* nums = {1,3,5,6,8,9,10,11,13,14,15,18,23,28,50,68,101,505};
    int N = 18;
    int out;

    out = binary_search(3, nums, 0, N);
    printf("nums[%d] = %d", out, 3);

    return 0;
}