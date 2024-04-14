import { distanceK } from './863_All_Nodes_Distance_K_in_BT.js';
import { arrayToBTree, findNode } from '../utils/btree.js';

describe('863. All Nodes Distance K in Binary Tree', () => {
  const toTest = (tree, target, k, expected) => {
    const root = arrayToBTree(tree);
    const targetNode = findNode(root, target);
    const actual = distanceK(root, targetNode, k);

    actual.sort();
    expected.sort();

    expect(actual).toEqual(expected);
  };

  it('should pass', () => {
    // distanceK
    toTest([3,5,1,6,2,0,8,null,null,7,4], 5, 2, [7,4,1]);

    toTest([1], 1, 3, []);
  });
});
