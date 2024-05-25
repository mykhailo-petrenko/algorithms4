import mergeKLists from './23_Merge_k_Sorted_Lists.js';
import { arrayToLinkedList, linkedListToArray } from '../utils/arrayToLinkedList.js';

describe('23. Merge k Sorted Lists', () => {
  it('should pass Example 1', () => {
    doTest(
      [[1,4,5],[1,3,4],[2,6]],
      [1,1,2,3,4,4,5,6]
    );
  });

  it('should pass Example 2', () => {
    doTest(
      [],
      []
    );
  });

  it('should pass Example 3', () => {
    doTest(
      [[]],
      []
    );
  });

  it('should pass two empty lists', () => {
    doTest(
      [[], []],
      []
    );
  });

  function doTest(lists, expected) {
    lists = lists.map(arr => arrayToLinkedList(arr));
    const actual = mergeKLists(lists);
    const actualArray = linkedListToArray(actual);
    expect(actualArray).toEqual(expected);
  }
});
