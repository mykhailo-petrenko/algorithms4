//
// Created by Mykhailo Petrenko on 21/12/2022.
//

#include <stdlib.h>
#include <stdio.h>
#include <string.h>

const int ABC_SIZE = 26;
const int CHAR_OFFSET = 'a';

int charCode(char s) {
    return s - CHAR_OFFSET;
}

struct TrieNode {
    int count;
    struct TrieNode *child[ABC_SIZE];
};

void initNode(struct TrieNode * node) {
    node->count = 0;
    for (int i=0; i<ABC_SIZE; i++) {
        node->child[i] = NULL;
    }
}

int * p = NULL;

struct TrieNode *addNode(struct TrieNode *root, char *key) {
    struct TrieNode *node = root;
    int symbolCode;

    while (*key != '\0') {
        symbolCode = charCode(*key);

        if (node->child[symbolCode] == NULL) {
            node->child[symbolCode] = calloc(1, sizeof(struct TrieNode));

            initNode(node->child[symbolCode]);
        }

        node = node->child[symbolCode];
        key++;
    }

    return node;
}

struct TrieNode getNode(char *key) {

}

int main() {
    struct TrieNode root;
    struct TrieNode *node;

    initNode(&root);
    char s1[5];

    strcpy(s1,"abc");
    node = addNode(&root, s1);
    node->count++;

    printf("%d - %s \n", node->count, s1);

    strcpy(s1,"bcd");
    node = addNode(&root, s1);
    node->count++;

    printf("%d - %s \n", node->count, s1);

    strcpy(s1, "a");
    node = addNode(&root, s1);
    node->count++;

    printf("%d - %s \n", node->count, s1);

    strcpy(s1, "abc");
    node = addNode(&root, s1);
    node->count++;

    printf("%d - %s \n", node->count, s1);
}