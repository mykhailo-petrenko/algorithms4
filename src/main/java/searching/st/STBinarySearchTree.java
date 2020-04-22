package searching.st;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

/**
 * BST
 */
public class STBinarySearchTree<K extends Comparable<K>, V> extends ST<K, V> {
    private class Node {
        private K key;
        private V value;
        private Node left;
        private Node right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node root;
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
            root = new Node(k, v);
            N++;
            return;
        }

        Node node = root;

        while (true) {
            if (node.key.compareTo(k) < 0) {
                if (node.right == null) {
                    node.right = new Node(k, v);
                    N++;
                    break;
                }

                node = node.right;
            } else if (node.key.compareTo(k) > 0) {
                if (node.left == null) {
                    node.left = new Node(k, v);
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
        Node node = root;

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

    private Node deleteNode(Node node, K k) {
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
        Node min = detouchRightMinimum(node, k);
        min.left = node.left;
        min.right = node.right;

        N--;

        // replace current node by "right minimum"
        return min;
    }

    private Node detouchRightMinimum(Node root, K k) {
        Node node = root.right;

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
        Node min = node.left;
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
    private void inorder(Node node, Queue<K> q) {
        if (node == null) {
            return;
        }

        inorder(node.left, q);

        q.add(node.key);

        inorder(node.right, q);
    }

    private static final int WIDTH = 1024;
    private static final int HEIGHT = 800;
    private static final int INDENT_VERTICAL = 10;
    private static final int INTERVAL_VERTICAL = 60;
    private static final int LABEL_OFFSET_X = 0;
    private static final int LABEL_OFFSET_Y = -15;

    private class VNode {
        Node node;
        int level;
        int position;

        int parentX;
        int parentY;

        public VNode(Node node, int level, int position, int parentX, int parentY) {
            this.node = node;
            this.level = level;
            this.position = position;
            this.parentX = parentX;
            this.parentY = parentY;
        }
    }

    public void visualize() {
        StdDraw.setCanvasSize(WIDTH, HEIGHT);
        StdDraw.setXscale(0, WIDTH);
        StdDraw.setYscale(HEIGHT, 0);

        bfsVisualize();
    }

    private void bfsVisualize() {
        Queue<VNode> q = new LinkedList<>();

        q.add(new VNode(root, 0, 0, -1, -1));

        while (!q.isEmpty()) {
            VNode point = q.poll();

            int level = point.level;
            int position = point.position;
            Node node = point.node;

            int chunks = (int) Math.pow(2, level) + 1;
            int y = (level * INTERVAL_VERTICAL) + INDENT_VERTICAL;
            int x = (WIDTH / chunks) * (position + 1);

            StdDraw.setPenColor(Color.BLACK);
            StdDraw.setPenRadius(0.01);
            StdDraw.point(x, y);

            StdDraw.setPenRadius(0.001);
            StdDraw.setPenColor(Color.DARK_GRAY);
            StdDraw.text(x + LABEL_OFFSET_X, y + LABEL_OFFSET_Y, node.key.toString());

            if (point.parentX != -1) {
                StdDraw.setPenColor(Color.GRAY);
                StdDraw.setPenRadius(0.001);
                StdDraw.line(x, y, point.parentX, point.parentY);
            }

            level += 1;
            position *= 2;

            if (node.left != null) {
                q.add(new VNode(node.left, level, position, x, y));
            }

            if (node.right != null) {
                q.add(new VNode(node.right, level, position + 1, x, y));
            }
        }
    }

    public static void main(String[] args) {
        Integer[] init = new Integer[] {7, 2, 1, 5, 3, 6, 4, 21, 15, 38, 36, 55, 54, 37};

        STBinarySearchTree<Integer, Integer> bst = new STBinarySearchTree<>(init, init);

        bst.visualize();
    }
}
