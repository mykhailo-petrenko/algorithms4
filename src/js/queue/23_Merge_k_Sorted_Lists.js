/**
 * # LeetCode. 23. Merge k Sorted Lists (https://leetcode.com/problems/merge-k-sorted-lists/description/)
 *
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 * Merge all the linked-lists into one sorted linked-list and return it.
 *
 * ## Example 1:
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * Output: [1,1,2,3,4,4,5,6]
 * Explanation: The linked-lists are:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * merging them into one sorted list:
 * 1->1->2->3->4->4->5->6
 *
 * ## Example 2:
 * Input: lists = []
 * Output: []
 *
 * ## Example 3:
 * Input: lists = [[]]
 * Output: []
 *
 * ## Constraints:
 * - k == lists.length
 * - 0 <= k <= 10^4
 * - 0 <= lists[i].length <= 500
 * - -104 <= lists[i][j] <= 10^4
 * - lists[i] is sorted in ascending order.
 * - The sum of lists[i].length will not exceed 10^4.
 */

/**
 * Definition for singly-linked list.
 * export function ListNode(val, next) {
 *   this.val = (val === undefined ? 0 : val)
 *   this.next = (next === undefined ? null : next)
 * }
 */

import { MinPriorityQueue } from '@datastructures-js/priority-queue';

/**
 * @param {ListNode[]} lists
 * @return {ListNode}
 */
var mergeKLists = function (lists) {
  if (lists.length === 0) {
    return null;
  }

  if (lists.length === 1) {
    return lists[0];
  }

  const q = new MinPriorityQueue();

  for (const node of lists) {
    if (node) {
      q.enqueue(node, node.val);
    }
  }

  if (q.size() === 0) {
    return null;
  }

  const head = q.dequeue().element;
  let cursor = head;

  if (head.next) {
    q.enqueue(head.next, head.next.val);
    head.next = null;
  }

  while (q.size() > 0) {
    cursor.next = q.dequeue().element;
    cursor = cursor.next;

    if (cursor.next) {
      q.enqueue(cursor.next, cursor.next.val);
      cursor.next = null;
    }
  }

  return head;
};

export default mergeKLists;
