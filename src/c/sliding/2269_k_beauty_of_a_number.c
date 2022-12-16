//
// Created by Mykhailo Petrenko on 15/12/2022.
//
/*
# 2269. Find the K-Beauty of a Number
The k-beauty of an integer num is defined as the number of substrings of num when it is read as a string that meet the following conditions:

It has a length of k.
It is a divisor of num.
Given integers num and k, return the k-beauty of num.

Note:
Leading zeros are allowed.
0 is not a divisor of any value.
A substring is a contiguous sequence of characters in a string.


## Example 1:
    Input: num = 240, k = 2
    Output: 2
    Explanation: The following are the substrings of num of length k:
    - "24" from "240": 24 is a divisor of 240.
    - "40" from "240": 40 is a divisor of 240.
    Therefore, the k-beauty is 2.

## Example 2:
    Input: num = 430043, k = 2
    Output: 2
    Explanation: The following are the substrings of num of length k:
    - "43" from "430043": 43 is a divisor of 430043.
    - "30" from "430043": 30 is not a divisor of 430043.
    - "00" from "430043": 0 is not a divisor of 430043.
    - "04" from "430043": 4 is not a divisor of 430043.
    - "43" from "430043": 43 is a divisor of 430043.
    Therefore, the k-beauty is 2.


## Constraints:
    1 <= num <= 109
    1 <= k <= num.length (taking num as a string)
*/

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <math.h>

#include "../lib/utest.h"

int divisorSubstrings(int num, int k){
    int count = 0;

    int len = log10(num) + 2;
    char * string = malloc(sizeof(char) * len);
    char * slidingWindow = malloc(sizeof(char) * (k + 1));

    sprintf(string, "%i", num);

    len -= k;
    int cursor;

    for (int i = 0; i < len; i++) {
        memcpy(slidingWindow, string+i, k);
        cursor = atoi(slidingWindow);

        if (cursor == 0) continue;

        if (num % cursor == 0) count++;
    }

    free(string);
    free(slidingWindow);

    return count;
}

void doTest(int num, int k, int expected) {
    int actual = divisorSubstrings(num, k);
    assert_equals_int("K beauty smoke", &expected, &actual);
}

int main() {

    doTest(10, 1, 1);
    doTest(240, 2, 2);
    doTest(430043, 2, 2);

    return 0;
}