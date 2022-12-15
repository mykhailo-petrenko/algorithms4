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


int main() {
    int nums[] = {1,3,5,6,8,9,10,11,13,14,15,18,23,28,50,68,101,505};
    int N = 18;
    int out;

    out = binary_search(1, &nums[0], 0, N);
    printf("nums[%d] = %d\n", out, 1);

    out = binary_search(3, &nums[0], 0, N);
    printf("nums[%d] = %d\n", out, 3);

    out = binary_search(68, &nums[0], 0, N);
    printf("nums[%d] = %d\n", out, 68);

    out = binary_search(101, &nums[0], 0, N);
    printf("nums[%d] = %d\n", out, 101);

    out = binary_search(505, &nums[0], 0, N);
    printf("nums[%d] = %d\n", out, 505);

    out = binary_search(-1, &nums[0], 0, N);
    printf("nums[%d] = %d\n", out, -1);

    out = binary_search(100, &nums[0], 0, N);
    printf("nums[%d] = %d\n", out, 100);

    out = binary_search(700, &nums[0], 0, N);
    printf("nums[%d] = %d\n", out, 700);

    return 0;
}