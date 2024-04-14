import { arrayToBTree } from './btree.js';

describe('arrayToBTree', () => {
  it('should return null for an empty array', () => {
    const root = arrayToBTree([]);

    expect(root).toBeNull();
  });

  it('smoke test', () => {
    const root = arrayToBTree([1, 2, 3, null, 5, 6, null]);

    expect(root.val).toEqual(1);

    expect(root.left.val).toEqual(2);
    expect(root.right.val).toEqual(3);

    expect(root.left.left).toBeNull();
    expect(root.right.right).toBeNull();

    expect(root.left.right.val).toEqual(5);
    expect(root.right.left.val).toEqual(6);
  });
});
