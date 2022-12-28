//
// Created by Mykhailo Petrenko on 28/12/2022.
//
/*
# 424. Longest Repeating Character Replacement (https://leetcode.com/problems/longest-repeating-character-replacement/)

You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character.
You can perform this operation at most k times.
Return the length of the longest substring containing the same letter you can get after performing the above operations.

## Example 1:
    Input: s = "ABAB", k = 2
    Output: 4
    Explanation: Replace the two 'A's with two 'B's or vice versa.

## Example 2:
    Input: s = "AABABBA", k = 1
    Output: 4
    Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
    The substring "BBBB" has the longest repeating letters, which is 4.

## Constraints:
    1 <= s.length <= 10^5
    s consists of only uppercase English letters.
    0 <= k <= s.length
 * */

#include "../lib/utest.h"

#define OFFSET 'A'
#define ABS_SIZE 26

int characterReplacement(char * s, int k) {
    int *dict = calloc(ABS_SIZE, sizeof(int));
    int dictMaxLen = 0;
    int len = 0;
    int max = 0;

    char * head = s;
    char * tail = s;

    while (*head != '\0') {
        len++;
        // add to dict
        if (++dict[*head - OFFSET] > dictMaxLen) {
            dictMaxLen = dict[*head - OFFSET];
        }

        while(len > (dictMaxLen + k)) {
            len--;
            dict[*tail - OFFSET]--;
            tail++;
        }
        if (len > max) max = len;
        head++;
    }

    return max;
}

void doTest(char * s, int k, int expected) {
    int actual = characterReplacement(s, k);
    assert_equals_int("characterReplacement(char * s, int k)", &expected, &actual);
}

int main() {

    doTest("ABAB", 2, 4);
    doTest("AABABBA", 1, 4);
    doTest("AACBABCBA", 1, 3);


    return 0;
}