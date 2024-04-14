export function TreeNode(val, left=null, right=null) {
  this.val = val;
  this.left = left;
  this.right = right;
}

/**
 *
 * @param nodesList {number[]}
 * @return {TreeNode}
 */
export function arrayToBTree(nodesList) {
  if (!nodesList) {
    return null;
  }

  const createNode = (index) => {
    if (nodesList.length <= index || nodesList[index] === null) {
      return null;
    }

    const left = index * 2 + 1;
    const right= index * 2 + 2;

    return new TreeNode(
      nodesList[index],
      createNode(left),
      createNode(right)
    )
  };

  return createNode(0);
}


/**
 *
 * @param root {TreeNode}
 * @param targetValue {number}
 * @return {TreeNode}
 */
export function findNode(root, targetValue) {
  if (!root) {
    return null;
  }

  if (root.val === targetValue) {
    return root;
  }

  return (
    findNode(root.left, targetValue)
    ||
    findNode(root.right, targetValue)
  );
}
