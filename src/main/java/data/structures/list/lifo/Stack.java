package data.structures.list.lifo;

public interface Stack<T> {
    void push(T item);
    T pop();
    int size();
    boolean isEmpty();
}
