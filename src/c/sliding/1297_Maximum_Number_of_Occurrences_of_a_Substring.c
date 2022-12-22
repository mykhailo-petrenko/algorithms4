//
// Created by Mykhailo Petrenko on 21/12/2022.
//

#include <stdlib.h>
#include <string.h>

#include "../lib/utest.h"

/*
# 1297. Maximum Number of Occurrences of a Substring (https://leetcode.com/problems/maximum-number-of-occurrences-of-a-substring/)

Given a string s, return the maximum number of ocurrences of any substring under the following rules:
    The number of unique characters in the substring must be less than or equal to maxLetters.
    The substring size must be between minSize and maxSize inclusive.

## Example 1:
    Input: s = "aababcaab", maxLetters = 2, minSize = 3, maxSize = 4
    Output: 2
    Explanation: Substring "aab" has 2 ocurrences in the original string.
    It satisfies the conditions, 2 unique letters and size 3 (between minSize and maxSize).

## Example 2:
    Input: s = "aaaa", maxLetters = 1, minSize = 3, maxSize = 3
    Output: 2
    Explanation: Substring "aaa" occur 2 times in the string. It can overlap.

## Constraints:
    1 <= s.length <= 105
    1 <= maxLetters <= 26
    1 <= minSize <= maxSize <= min(26, s.length)
    s consists of only lowercase English letters.

 */

#define ABC_SIZE 26
#define CHAR_OFFSET 'a'

int charCode(char c) {
    return c - CHAR_OFFSET;
}

struct SymbolStat {
    int length;
    int uniqSymbolsCount;
    int symbolCount[ABC_SIZE];
};

void initStat(struct SymbolStat *stat) {
    stat->length = 0;
    stat->uniqSymbolsCount = 0;
    for (int i=0; i<ABC_SIZE; i++) {
        stat->symbolCount[i] = 0;
    }
}

void addSymbol(struct SymbolStat *stat, const int charCode) {
    stat->length++;
    stat->symbolCount[charCode]++;
    if (stat->symbolCount[charCode] == 1) {
        stat->uniqSymbolsCount++;
    }
}

struct TrieNode {
    int count;
    struct TrieNode *child[ABC_SIZE];
};

struct TrieNode * addNode(struct TrieNode *root, char *key) {
    struct TrieNode *node = root;

    while (*key != '\0') {
        int symbol = charCode(*key);
        if (node->child[symbol] == NULL) {
            node->child[symbol] = calloc(1, sizeof(struct TrieNode));
        }

        node = node->child[symbol];
        key++;
    }

    return node;
}

int maxFreq(char * s, int maxLetters, int minSize, int maxSize) {
    int occurences = 0;
    char * substring = malloc(sizeof(char) * (maxSize + 2));

    struct SymbolStat stat;
    struct TrieNode *trie = calloc(1, sizeof(struct TrieNode));
    struct TrieNode *node;

    char * head = s;
    char * tail = s;
    int headSymbol;


    for (tail = s; *tail != '\0'; tail++) {
        initStat(&stat);

        for (head = tail; stat.length <= maxSize && *head != '\0'; head++) {
            headSymbol = charCode(*head);

            addSymbol(&stat, headSymbol);

            if (stat.uniqSymbolsCount > maxLetters) {
                break;
            }

            if (stat.length >= minSize) {
                memcpy(substring, tail, stat.length);
                substring[stat.length] = '\0';

                node = addNode(trie, substring);
                node->count++;

                if (occurences < node->count) {
                    occurences = node->count;
                }
            }
        }
    }

    return occurences;
}

int main() {
    int actual;
    int expected;

    actual = maxFreq("aabcaabad", 2, 2, 4);
    expected = 2;
    assert_equals_int("Maximum Number of Occurrences", &expected, &actual);

    actual = maxFreq("aaaa", 1, 3, 3);
    assert_equals_int("Maximum Number of Occurrences", &expected, &actual);

    return 0;
}