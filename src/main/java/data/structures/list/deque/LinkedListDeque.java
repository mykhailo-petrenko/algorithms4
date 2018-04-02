package data.structures.list.deque;

import java.util.Iterator;

/**
 * Deque - bidirectional queue ans stack
 * @param <Item>
 */
public class LinkedListDeque<Item> implements Deque<Item>, Iterable<Item> {
    private class Node {
        Item item;
        Node prev;
        Node next;

        Node(Item item) {
            this.item = item;
        }
    }

    private Node first;
    private int N;

    public LinkedListDeque() {
        N = 0;
        first = null;
    }

    @Override
    public boolean isEmpty() {
        return first==null;
    }

    @Override
    public int size() {
        return N;
    }

    /**
     * Push to right side
     * XXX <- Item
     * @param item
     */
    @Override
    public void pushRight(Item item) {
        Node last = new Node(item);
        last.next = first;

        if (N==0) {
            first = last;
        } else if (N==1) {
            first.next = last;
            first.prev = last;
            last.prev = first;
        } else {
            last.prev = first.prev;
            last.prev.next = last;
            first.prev = last;
        }

        N++;
    }

    /**
     * Push to left side
     * Item -> XXX
     * @param item
     */
    @Override
    public void pushLeft(Item item) {
        Node oldfirst = first;
        first = new Node(item);
        first.next = oldfirst;

        if (N>1) {
            first.prev = oldfirst.prev;
            oldfirst.prev.next = first;
        }
        if (N>0) {
            oldfirst.prev = first;
            first.next = oldfirst;
        }

        if (N==1) {
            first.prev = oldfirst;
            oldfirst.next = first;
        }

        N++;
    }

    /**
     * Pop from left side
     * Item <- XXX
     * @return
     */
    @Override
    public Item popLeft() {
        Item item = first.item;

        if (N>1) {
            Node oldfirst = first;
            first = first.next;
            first.prev = oldfirst.prev;
            oldfirst.prev.next = first;
        } else {
            first.next = null;
            first.prev = null;
            first = null;
        }

        N--;

        return item;
    }

    /**
     * Pop from right side
     * XXX -> Item
     * @return
     */
    @Override
    public Item popRight() {
        Item item;

        if (N>1) {
            item = first.prev.item;
            first.prev.prev.next = first;
            first.prev = first.prev.prev;
        } else {
            item = first.item;
            first.prev = null;
            first.next = null;
            first = null;
        }

        N--;

        return item;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();

        for (Item i : this) {
            if (string.length() > 0) {
                string.append(", ");
            }
            string.append(i);
        }

        return "LinkedListDeque{N=" + N + "}: " + string.toString();
    }

    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current;
        private int left;

        public DequeIterator() {
            current = first;
            left = N;
        }

        @Override
        public boolean hasNext() {
            return left > 0;
        }

        @Override
        public Item next() {
            Item item = current.item;

            current = current.next;
            left--;

            return item;
        }
    }
}
