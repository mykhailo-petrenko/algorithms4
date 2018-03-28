package data.structures.fifo;

import java.util.Iterator;

public class LinkedListQueue<T> implements Queue<T>, Iterable<T> {
    private Node first;
    private Node last;
    private int N;

    private class Node {
        T item;
        Node next;
    }

    LinkedListQueue() {
        N = 0;
        first = null;
        last = null;
    }

    @Override
    public void enqueue(T item) {
        Node oldlast = last;

        last = new Node();
        last.item = item;
        last.next = null;

        if (isEmpty()) {
            first = last;
        } else {
            oldlast.next = last;
        }

        N++;
    }

    @Override
    public T dequeue() {
        T item = first.item;
        first = first.next;

        if (isEmpty()) {
            last = null;
        }

        N--;

        return item;
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public boolean isEmpty() {
        return (first==null);
    }

    @Override
    public Iterator<T> iterator() {
        return new ReverseLinkedListQueueIterator();
    }

    private class ReverseLinkedListQueueIterator implements Iterator<T> {
        Node current;

        ReverseLinkedListQueueIterator() {
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
