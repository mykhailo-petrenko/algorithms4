package data.structures.lifo;

import java.util.Iterator;

public class LinkedListStack<T> implements Stack<T>, Iterable<T> {
    private Node first;
    private int N;

    private class Node {
        T item;
        Node next;
    }

    public LinkedListStack() {
        first = null;
        N = 0;
    }

    @Override
    public void push(T item) {
        Node oldFirst = first;

        first = new Node();
        first.item = item;
        first.next = oldFirst;

        N++;
    }

    @Override
    public T pop() {
        T item = first.item;

        first = first.next;

        N--;

        return item;
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public String toString() {
        return "Size = " + N;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListStackIterator();
    }

    private class LinkedListStackIterator implements Iterator<T> {
        private Node current;

        LinkedListStackIterator() {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T item = current.item;

            current = current.next;

            return item;
        }
    }
}
