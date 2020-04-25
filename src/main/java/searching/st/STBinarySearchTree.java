package searching.st;

import graphs.visualization.BSTVisualizer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * BST
 */
public class STBinarySearchTree<K extends Comparable<K>, V> extends ST<K, V> {
    public static class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> left;
        private Node<K, V> right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node<K, V> root;
    private int N;

    public STBinarySearchTree() {
        this.N = 0;
        this.root = null;
    }

    public STBinarySearchTree(K[] keys, V[] values) {
        this.N = 0;
        this.root = null;

        if (keys.length != values.length) {
            throw new RuntimeException("Keys and Values lengths mismatch.");
        }

        for (int i = 0; i < keys.length; i++) {
            this.put(keys[i], values[i]);
        }
    }

    @Override
    public void put(K k, V v) {
        if (root == null) {
            root = new Node<>(k, v);
            N++;
            return;
        }

        Node<K, V> node = root;

        while (true) {
            if (node.key.compareTo(k) < 0) {
                if (node.right == null) {
                    node.right = new Node<>(k, v);
                    N++;
                    break;
                }

                node = node.right;
            } else if (node.key.compareTo(k) > 0) {
                if (node.left == null) {
                    node.left = new Node<>(k, v);
                    N++;
                    break;
                }

                node = node.left;
            } else {
                node.value = v;
                break;
            }
        }
    }

    @Override
    public V get(K k) {
        Node<K, V> node = root;

        while (node != null) {
            if (node.key.compareTo(k) < 0) {
                node = node.right;
            } else if (node.key.compareTo(k) > 0) {
                node = node.left;
            } else {
                return node.value;
            }
        }

        return null;
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public Iterable<K> keys() {
        Queue<K> q = new LinkedList<>();
        inorder(root, q);
        return q;
    }

    @Override
    public void delete(K k) {
        root = deleteNode(root, k);
    }

    Node<K, V> getRootNode() {
        return root;
    }

    Node<K, V> getNode(K k) {
        Node<K, V> node = root;

        while (node != null) {
            if (node.key.compareTo(k) < 0) {
                node = node.right;
            } else if (node.key.compareTo(k) > 0) {
                node = node.left;
            } else {
                return node;
            }
        }

        return null;
    }

    private Node<K, V> deleteNode(Node<K, V> node, K k) {
        if (node == null) {
            return null;
        }

        if (node.key.compareTo(k) < 0) {
            node.right = deleteNode(node.right, k);
            return node;
        }

        if (node.key.compareTo(k) > 0) {
            node.left = deleteNode(node.left, k);
            return node;
        }

        // Has no children - just remove
        if (node.left == null && node.right == null) {
            N--;
            return null;
        }

        // If has just Right child
        if (node.left == null) {
            N--;
            return node.right;
        }

        // If has just Left child
        if (node.right == null) {
            N--;
            return node.left;
        }

        // Has both child

        // Find minimum from right subtree (it is very left node without left child)
        Node<K, V> min = detouchRightMinimum(node, k);
        min.left = node.left;
        min.right = node.right;

        N--;

        // replace current node by "right minimum"
        return min;
    }

    private Node<K, V> detouchRightMinimum(Node<K, V> root, K k) {
        Node<K, V> node = root.right;

        // If right child already minimum
        if (node.left == null) {
            root.right = node.right;
            return node;
        }

        // find the very left child
        while (node.left.left != null) {
            node = node.left;
        }

        // Detouch
        Node<K, V> min = node.left;
        node.left = node.left.right;

        return min;
    }

    /**
     * Recursively traverse BST in inorder:
     * - left subtree/child
     * - current
     * - right subtree/child
     * because left subtree smaller than current key,
     * current node key smaller than right subtree
     *
     * @param node
     * @param q
     */
    private void inorder(Node<K, V> node, Queue<K> q) {
        if (node == null) {
            return;
        }

        inorder(node.left, q);

        q.add(node.key);

        inorder(node.right, q);
    }

    public static void main(String[] args) {
        Integer[] init = new Integer[] {7, 2, 1, 5, 3, 6, 4, 21, 15, 38, 36, 55, 54, 37};
        STBinarySearchTree<Integer, Integer> tree = new STBinarySearchTree<>(init, init);

        BSTVisualizer<Node<Integer, Integer>> visualizer = new BSTVisualizer<>(
            node -> new BSTVisualizer.VNode<>(node.key.toString(), node.left, node.right)
        );

        visualizer.draw(tree.getRootNode());
    }
}
