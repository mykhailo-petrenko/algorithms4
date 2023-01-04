//
// Created by Mykhailo Petrenko on 01.01.2023.
//
#include <stdlib.h>
#include "../lib/utest.h"

/*
# 19. Remove Nth Node From End of List (https://leetcode.com/problems/remove-nth-node-from-end-of-list/)

Given the head of a linked list, remove the nth node from the end of the list and return its head.

Example 1:
    Input: head = [1,2,3,4,5], n = 2
    Output: [1,2,3,5]

Example 2:
    Input: head = [1], n = 1
    Output: []

Example 3:
    Input: head = [1,2], n = 1
    Output: [1]
*/

struct ListNode {
    int val;
    struct ListNode *next;
};

struct ListNode* removeNthFromEnd(struct ListNode* head, int n){
    n = n + 1;
    struct ListNode* cursor = head;
    struct ListNode** buffer = malloc(sizeof(struct ListNode*) * n);
    int i = 0;

    while(cursor != NULL) {
        buffer[i % n] = cursor;
        i++;

        cursor = cursor->next;
    }

    if (n > i + 1) {
        return NULL;
    } else if (n == i + 1) {
        return head->next;
    }

    struct ListNode* tmp = buffer[i % n]->next;

    buffer[i % n]->next = NULL;

    if (tmp != NULL) {
        buffer[i % n]->next = tmp->next;
    }

    return head;
}

void doTest(const int *arr, int length, int n) {
    struct ListNode *head = malloc(sizeof(struct ListNode *));
    head->val = arr[0];
    struct ListNode *cursor = head;

    for(int i=1; i<length; i++) {
        cursor->next = malloc(sizeof(struct ListNode *));
        cursor = cursor->next;
        cursor->val = arr[i];
    }

    struct ListNode *actual = removeNthFromEnd(head, n);
    int *actualArr = malloc(sizeof(int) * length);
    int i = 0;
    while (actual != NULL) {
        actualArr[i] = actual->val;
        actual = actual->next;
        i++;
    }

    int * expectedArr = malloc(sizeof(int)*length);

    for (int i=0, j=0; i<length; i++, j++) {
        expectedArr[j] = arr[i];
        if (i == length - n) {
            j--;
        }
    }

    assert_equals_array(
            "struct ListNode* removeNthFromEnd(struct ListNode* head, int n)",
            expectedArr,
            actualArr,
            length-1
            );
}

int main() {
    int in1[] = {1,2,3,4,5};
    doTest(in1, 5, 2);

    int in2[] = {1};
    doTest(in2, 1, 1);

    int in3[] = {1,2};
    doTest(in3, 2, 1);

    int in4[] = {1,2,3,4,5};
    doTest(in4, 5, 5);

    return 0;
}