package data.structures.list.deque;

import java.util.Iterator;

public class LinkedListDeque<Item> implements Deque<Item>, Iterable<Item> {
    private class Node {
        Item item;
        Node prev;
        Node next;
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

    @Override
    public void pushLeft(Item item) {

    }

    @Override
    public void pushRight(Item item) {
        Node oldFirst = first;

        first = new Node();
        first.item = item;

        if (N==0) {
            oldFirst = first;
        }

        if (N==1) {
            oldFirst.next = first;
        }

        first.next = oldFirst;
        first.prev = oldFirst.prev;

        oldFirst.prev = first;

        N++;
    }

    @Override
    public Item popLeft() {
        return null;
    }

    @Override
    public Item popRight() {
        return null;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();

        for (Item i : this) {
            string.append(i);
            string.append(",");
        }

        return "LinkedListDeque{N=" + N + "}:" + string.toString();
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
