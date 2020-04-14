package searching.st;

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
                if (node.left == null) {
                    node.left = new Node(k, v);
                    N++;
                    break;
                }

                node = node.left;
            } else if (node.key.compareTo(k) > 0) {
                if (node.right == null) {
                    node.right = new Node(k, v);
                    N++;
                    break;
                }

                node = node.right;
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
                node = node.left;
            } else if (node.key.compareTo(k) > 0) {
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
        return null;
    }

    @Override
    public void delete(K k) {
        super.delete(k);
    }
}
