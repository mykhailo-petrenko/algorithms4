#include <stdio.h>
#include <stdlib.h>

#include "../lib/utest.h"

int findSmallerCount(int *where, int from, int to) {
    int target = where[from ];

    int lo = from + 1;
    int hi = to;
    int mid;

    while (lo < hi) {
        mid = lo + (hi - lo) / 2;

        if (where[mid] >= target) {
            lo = mid + 1;
        } else {
            hi = mid;
        }
    }

    return to - lo;
}

int comparator(int* a, int* b) {
    return *b - *a;
}

void swap(int* a, int* b) {
    int t = *b;
    *b = *a;
    *a = t;
}

/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int* countSmaller(int* nums, int numsSize, int* returnSize){
    int* out = malloc(numsSize * sizeof(int));
    *returnSize = numsSize;

    for (int i = numsSize-1, j; i >=0; i--) {
        out[i] = findSmallerCount(nums, i, numsSize);
        j = i;
        while(nums[j] < nums[j+1] && j < numsSize - 1) {
            swap(&nums[j], &nums[j+1]);
            j++;
        }
//        qsort(&nums[i], numsSize - i, sizeof(int), comparator);
    }

    return out;
}

int main() {
    int in[] = {5,2,6,1};
    int expected[] = {2, 1, 1, 0};
    int* returnSize = malloc(sizeof(int));
    int* out;

    out = countSmaller(in, 4, returnSize);
    assert_equals_array("Basic test", expected, out, *returnSize);

    int in2[] = {-1, -1};
    int expected2[] = {0, 0};
    out = countSmaller(in2, 2, returnSize);
    assert_equals("[-1, -1] -> [0, 0]", expected2, out, *returnSize);

    int in3[] = {1};
    int expected3[] = {0};
    out = countSmaller(in3, 1, returnSize);
    assert_equals("[1] -> [0]", expected3, out, *returnSize);

    printf("SUCCESS\n");

    return 0;
}