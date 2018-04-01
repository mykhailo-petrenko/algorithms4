package data.structures.list.fifo;

public interface Queue<T> {
    void enqueue(T item);
    T dequeue();
    int size();
    boolean isEmpty();
}
