package searching.st;

import graphs.visualization.BSTVisualizer;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Red Black BST is a way to implement 2-3 ST via BST
 *
 * @param <K>
 * @param <V>
 */
// @TODO: Implement via Red-Black BST
// @TODO: Implement Tree visualisation for better debug experience
public class STRedBlackBST<K extends Comparable<K>, V> extends ST<K, V> {
    private Node<K, V> root = null;
    private int N = 0;

    static boolean RED = true;
    static boolean BLACK = false;

    public static class Node<K, V> {
        K key;
        V value;
        boolean color;
        Node<K, V> left;
        Node<K, V> right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.color = RED;
            left = null;
            right = null;
        }

        public Node(K key, V value, boolean color) {
            this.key = key;
            this.value = value;
            this.color = color;
            left = null;
            right = null;
        }

        public void setRed() {
            color = RED;
        }

        public void setBlack() {
            color = BLACK;
        }
    }

    @Override
    public void put(K k, V v) {
        Node<K, V> newNode = new Node<>(k, v);
        root = putNode(root, newNode);
        root.setBlack();
    }

    private Node<K, V> putNode(Node<K, V> pointer, Node<K, V> node) {
        if (pointer == null) {
            return node;
        }

        if (node.key.compareTo(pointer.key) > 0) {
            pointer.right = putNode(pointer.right, node);
        } else if (node.key.compareTo(pointer.key) < 0) {
            pointer.left = putNode(pointer.left, node);
        } else {
            return node;
        }

        if (isRed(pointer.right) && !isRed(pointer.left)) {
            pointer = rotateLeft(pointer);
        }

        if (isRed(pointer.left) && isRed(pointer.left.left)) {
            pointer = rotateRight(pointer);
        }

        if (isRed(pointer.left) && isRed(pointer.right)) {
            flipColor(pointer);
        }

        return pointer;
    }

    @Override
    public V get(K k) {
        Node<K, V> node = root;

        while (node != null) {
            if (node.key.compareTo(k) > 0) {
                node = node.left;
            } else if (node.key.compareTo(k) < 0){
                node = node.right;
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
        Queue<K> iterable = new LinkedList<>();

        inOrderTraverse(root, iterable);

        return iterable;
    }

    private boolean isRed(Node<K, V> node) {
        return node != null && node.color == RED;
    }

    private Node<K, V> rotateLeft(Node<K, V> node) {
        Node<K, V> newSubtreeRoot = node.right;

        node.right = newSubtreeRoot.left;
        newSubtreeRoot.left = node;
        newSubtreeRoot.color = node.color;
        node.setRed();

        return newSubtreeRoot;
    }

    private Node<K, V> rotateRight(Node<K, V> node) {
        Node<K, V> newSubtreeRoot = node.left;

        node.left = newSubtreeRoot.right;
        newSubtreeRoot.right = node;
        newSubtreeRoot.color = node.color;
        node.setRed();

        return newSubtreeRoot;
    }

    private void flipColor(Node<K, V> node) {
        if (node.left != null) {
            node.left.setBlack();
        }

        if (node.right != null) {
            node.right.setBlack();
        }

        node.setRed();
    }

    private void inOrderTraverse(Node<K, V> node, Queue<K> iterable) {
        if (node == null) {
            return;
        }

        inOrderTraverse(node.left, iterable);
        iterable.add(node.key);
        inOrderTraverse(node.right, iterable);
    }

    private BSTVisualizer<Node<K, V>> visualizerInstance;

    public BSTVisualizer<Node<K, V>> visualizer() {
        if (visualizerInstance == null) {
            visualizerInstance = new BSTVisualizer<>(
                node -> new BSTVisualizer.VNode<>(
                    node.key.toString(),
                    node.left,
                    node.right,
                    (node.color) ? Color.RED : Color.BLACK
                )
            );
        }

        return visualizerInstance;
    }

    public static void main(String[] args) {
        int[] init = new int[] {1, 2, 3, 4, 5, 6};
//        int[] init = new int[] {7, 6, 5, 4, 3, 2, 1};
//        int[] init = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};

        STRedBlackBST<Integer, Integer> tree = new STRedBlackBST<>();

//        for (int k : init) {
//            tree.put(k, k);
//        }
        for (int i = 0; i <= 100; i++) {
            tree.put(i, i);
        }
//        for (int i = 100; i >= 0; i--) {
//            tree.put(i, i);
//        }

        tree.visualizer().draw(tree.root);
    }
}
