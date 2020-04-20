package searching.st;

/**
 * Red Black BST is a way to implement 2-3 ST via BST
 *
 * @param <K>
 * @param <V>
 */
// @TODO: Implement via Red-Black BST
// @TODO: Implement Tree visualisation for better debug experience
public class STRedBlackBST<K extends Comparable<K>, V> extends ST<K, V> {
    private Node root = null;
    private int N = 0;

    static boolean RED = true;
    static boolean BLACK = false;

    class Node {
        K key;
        V value;
        boolean color;
        Node left;
        Node right;

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

    }

    @Override
    public V get(K k) {
        return null;
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public Iterable<K> keys() {
        return null;
    }

    private boolean isRed(Node node) {
        return node.color == RED;
    }

    private Node rotateLeft(Node node) {
        Node newSubtreeRoot = node.right;

        node.right = newSubtreeRoot.left;
        newSubtreeRoot.left = node;

        return newSubtreeRoot;
    }

    private Node rotateRight(Node node) {
        Node newSubtreeRoot = node.left;

        node.left = newSubtreeRoot.right;
        newSubtreeRoot.right = node;

        return newSubtreeRoot;
    }

    private void flipColor(Node node) {
        if (node.left != null) {
            node.left.setRed();
        }

        if (node.right != null) {
            node.right.setBlack();
        }

        node.setRed();
    }
}
