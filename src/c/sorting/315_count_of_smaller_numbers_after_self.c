#include <stdio.h>
#include <stdlib.h>

#include "../lib/utest.h"

int findSmallerCount(int *where, int from, int to) {
    int N = where[from ];

    for (int i = from + 1; i < to; i++) {
        if (where[i] < N) {
            return to - i;
        }
    }

    return 0;
}

int comparator(int* a, int* b) {
    return *b - *a;
}

/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int* countSmaller(int* nums, int numsSize, int* returnSize){
    int* out = malloc(numsSize * sizeof(int));
    *returnSize = numsSize;

    for (int i = numsSize-1; i >=0; i--) {
        out[i] = findSmallerCount(nums, i, numsSize);

        qsort(&nums[i], numsSize - i, sizeof(int), comparator);
    }

    return out;
}

int main() {
    int in[] = {5,2,6,1};
    int expected[] = {2, 1, 1, 0};

    int* returnSize = malloc(sizeof(int));

    int* out = countSmaller(in, 4, returnSize);

    assert_equals_array("Basic test", expected, out, *returnSize);

    printf("SUCCESS\n");

    return 0;
}