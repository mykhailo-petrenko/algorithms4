//
// Created by Mykhailo Petrenko on 06/10/2023.
//
/*
# 343. Integer Break (https://leetcode.com/problems/integer-break/)

Given an integer n, break it into the sum of k positive integers, where k >= 2, and maximize the product of those integers.

Return the maximum product you can get.

## Example 1:
    Input: n = 2
    Output: 1
    Explanation: 2 = 1 + 1, 1 × 1 = 1.

## Example 2:
    Input: n = 10
    Output: 36
    Explanation: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.


## Constraints:
    2 <= n <= 58
*/

#include "utest.h"
#include <time.h>

clock_t timeStart() {
    return clock();
}

void timeMeasure(clock_t start) {
    clock_t difference = clock() - start;
    double time_taken = ((double)difference) / CLOCKS_PER_SEC; // in seconds

    printf("> %f seconds\n", time_taken);
}

int MEMOISED_ANSWERS[59] = {0};
int integerBreak(int n);
int max(int a);

int max(int a) {
    if (MEMOISED_ANSWERS[a] > 0) return MEMOISED_ANSWERS[a];

    int b = integerBreak(a);

    if (a > b) {
        MEMOISED_ANSWERS[a] = a;
        return a;
    }

    MEMOISED_ANSWERS[a] = b;
    return b;
}

int integerBreak(int n) {
    if (n <= 2) {
        return 1;
    }

    if (n == 3) {
        return 2;
    }

    if (n == 4) {
        return 4;
    }


    int maxMul = 0;

    int left = 2;
    int right = n - left;

    while (left <= right) {
        int mul = max(left) * max(right);

        if (mul < maxMul) {
            break;
        }

        maxMul = mul;

        left++;
        right--;
    }


    return maxMul;
}

void test(int in, int expected) {
    clock_t t = timeStart();
    int actual = integerBreak(in);
    timeMeasure(t);

    char * title = malloc(sizeof(char) * 255);
    sprintf(title, "integerBreak(%d)", in);

    assert_equals_int(title, &expected, &actual);
}

int main() {

    test(2, 1);

    test(3, 2);
    test(4, 4);
    test(5, 6);
    test(6, 9);
    test(7, 12);
    test(8, 18);
    test(46, 19131876);
    test(58, 1549681956);


    return 0;
}
