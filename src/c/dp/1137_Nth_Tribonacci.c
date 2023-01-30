//
// Created by Mykhailo Petrenko on 30/01/2023.
//
/*
# 1137. N-th Tribonacci Number (https://leetcode.com/problems/n-th-tribonacci-number/)

The Tribonacci sequence Tn is defined as follows:
T0 = 0, T1 = 1, T2 = 1, and Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0.
Given n, return the value of Tn.

## Example 1:
    Input: n = 4
    Output: 4
    Explanation:
    T_3 = 0 + 1 + 1 = 2
    T_4 = 1 + 1 + 2 = 4

## Example 2:
    Input: n = 25
    Output: 1389537
 * */

#include "utest.h"

int tribonacci(int n){
    int buffer[3] = {0, 1, 1};

    for (int i = 3; i<=n; i++) {
        buffer[i % 3] = buffer[0] + buffer[1] + buffer[2];
    }

    return buffer[n%3];
}



int main() {
    int expected, actual;

    actual = tribonacci(0);
    expected = 0;
    assert_equals_int("tribonacci(0)", &expected, &actual);

    actual = tribonacci(1);
    expected = 1;
    assert_equals_int("tribonacci(1)", &expected, &actual);

    actual = tribonacci(2);
    expected = 1;
    assert_equals_int("tribonacci(2)", &expected, &actual);

    actual = tribonacci(3);
    expected = 2;
    assert_equals_int("tribonacci(3)", &expected, &actual);

    actual = tribonacci(4);
    expected = 4;
    assert_equals_int("tribonacci(4)", &expected, &actual);

    actual = tribonacci(5);
    expected = 7;
    assert_equals_int("tribonacci(5)", &expected, &actual);

    actual = tribonacci(6);
    expected = 13;
    assert_equals_int("tribonacci(6)", &expected, &actual);

    actual = tribonacci(7);
    expected = 24;
    assert_equals_int("tribonacci(7)", &expected, &actual);

    actual = tribonacci(25);
    expected = 1389537;
    assert_equals_int("tribonacci(25)", &expected, &actual);

    return 0;
}