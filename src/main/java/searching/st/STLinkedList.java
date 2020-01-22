package searching.st;

import java.util.Iterator;

public class STLinkedList<K, V> extends ST<K, V> {
    int N;
    Node root;

    class Node {
        K key;
        V value;
        Node next;

        Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public class STLinkedListKeysIterator implements Iterator<K> {
        private Node cursor;

        public STLinkedListKeysIterator() {
            cursor = root;
        }

        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        @Override
        public K next() {
            Node val = cursor;

            cursor = cursor.next;

            return val.key;
        }
    }

    public class STLinkedListKeysIterable implements Iterable<K> {
        @Override
        public Iterator<K> iterator() {
            return new STLinkedListKeysIterator();
        }
    }

    public STLinkedList() {
        N = 0;
        root = null;
    }

    @Override
    public void put(K k, V v) {
        Node node = getNode(k);

        if (node == null) {
            node = new Node(k, v, this.root);
            this.root = node;
            N++;
        } else {
            node.value = v;
        }
    }

    @Override
    public V get(K k) {
        Node node = getNode(k);

        if (node == null) {
            return null;
        }

        return node.value;
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public Iterable<K> keys() {
        return new STLinkedListKeysIterable();
    }

    @Override
    public void delete(K k) {
        Node prev = root;

        if (prev == null) {
            return;
        }

        if (prev.key.equals(k)) {
            root = prev.next;
            return;
        }

        while (prev.next != null) {
            if (prev.next.key.equals(k)) {

                prev.next = prev.next.next;
                N--;
                break;
            }

            prev = prev.next;
        }
    }

    private Node getNode(K k) {
        Node node = root;

        if (node == null) {
            return null;
        }

        while (node != null) {

            if (node.key.equals(k)) {
                return node;
            }

            node = node.next;
        }

        return null;
    }
}
