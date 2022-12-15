//
// Created by Mykhailo Petrenko on 15/12/2022.
//

/*
# 2379. Minimum Recolors to Get K Consecutive Black Blocks

 You are given a 0-indexed string blocks of length n, where blocks[i] is either 'W' or 'B', representing the color of the ith block. The characters 'W' and 'B' denote the colors white and black, respectively.
You are also given an integer k, which is the desired number of consecutive black blocks.
In one operation, you can recolor a white block such that it becomes a black block.
Return the minimum number of operations needed such that there is at least one occurrence of k consecutive black blocks.

## Example 1:
    Input: blocks = "WBBWWBBWBW", k = 7
    Output: 3
Explanation:
    One way to achieve 7 consecutive black blocks is to recolor the 0th, 3rd, and 4th blocks
    so that blocks = "BBBBBBBWBW".
    It can be shown that there is no way to achieve 7 consecutive black blocks in less than 3 operations.
    Therefore, we return 3.

## Example 2:
    Input: blocks = "WBWBBBW", k = 2
    Output: 0
Explanation:
    No changes need to be made, since 2 consecutive black blocks already exist.
    Therefore, we return 0.

## Constraints:
    n == blocks.length
    1 <= n <= 100
    blocks[i] is either 'W' or 'B'.
    1 <= k <= n
*/

#include "../lib/utest.h"

int minimumRecolors(char * blocks, int k){
    if (k == 0) {
        return 0;
    }

    int tail = 0;
    int head = 0;

    int minRepaints;
    int currentRepaints = 0;

    for (head = 0; head < k; head++) {
        if (blocks[head] == 'W') {
            currentRepaints++;
        }
    }

    minRepaints = currentRepaints;

    for (; blocks[head] != '\0'; head++, tail++) {
        if (blocks[head] == 'W') {
            currentRepaints++;
        }
        if (blocks[tail] == 'W') {
            currentRepaints--;
        }

        minRepaints = (minRepaints < currentRepaints) ? minRepaints : currentRepaints;
    }

    return minRepaints;
}

int minimumRecolorsPointers(char * blocks, int k) {
    int currentRepaints = 0;
    int minRepaints;
    char * tail = blocks;

    while (k-- > 0) {
        if (*blocks == 'W') {
            currentRepaints++;
        }
        blocks++;
    }

    minRepaints = currentRepaints;

    while(*blocks != '\0') {
        if (*tail == 'W') {
            currentRepaints--;
        }
        if (*blocks == 'W') {
            currentRepaints++;
        }

        if (minRepaints > currentRepaints) {
            minRepaints = currentRepaints;
        }

        tail++;
        blocks++;
    }


    return minRepaints;
}

void do_test(char * blocks, int k, int expected) {
    int out = minimumRecolorsPointers(blocks, k);
    assert_equals_int("mock", &expected, &out);
}

int main() {

    do_test("WBBWWBBWBW", 7, 3);
    do_test("WBWBBBW", 2, 0);

    return 0;
}