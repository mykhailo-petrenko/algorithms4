
export function ListNode(val, next) {
  this.val = (val === undefined ? 0 : val)
  this.next = (next === undefined ? null : next)
}

/**
 * Convert Array into linked list with testing purposes
 *
 * @param arr {any[]} Array
 * @return {ListNode} Linked List
 */
export function arrayToLinkedList(arr) {
  if (!arr || arr.length === 0) {
    return null;
  }
  const head = new ListNode(arr[0], null);
  let cursor = head;

  for (let i=1; i<arr.length; i++) {
    cursor.next = new ListNode(arr[i], null);
    cursor = cursor.next;
  }

  return head;
}

/**
 * Linked List to Array
 *
 * @param list {ListNode} Linked List
 * @return {any[]} Array
 */
export function linkedListToArray(list) {
  const out = [];

  while (list) {
    out.push(list.val);
    list = list.next;
  }

  return out;
}
