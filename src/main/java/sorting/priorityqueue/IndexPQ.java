package sorting.priorityqueue;

public interface IndexPQ<T extends Comparable<T>> {
    /**
     * Insert a `key` associated with `index` into priority queue
     * @param index
     * @param key
     */
    void insert(int index, T key);

    /**
     * Return top (min or max depends on implementation) key
     * @return T key
     */
    T peekKey();

    /**
     * Return top (min or max depends on implementation) index
     * @return int Index
     */
    int peek();

    /**
     * Remove the top key, index and return the index
     * @return int Index
     */
    int dequeue();

    /**
     * Return `key` associated with `index`
     * @param index
     * @return
     */
    T get(int index);

    /**
     * Is the `index` associated with some `key`
     * @param index
     * @return
     */
    boolean contains(int index);

    /**
     * Remove `index` and it associated `key`
     * @param index
     */
    void delete(int index);

    /**
     * Is Queue is empty
     * @return
     */
    boolean isEmpty();

    /**
     * Elements in queue count
     * @return
     */
    int size();
}
