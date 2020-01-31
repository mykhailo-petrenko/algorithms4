package sorting.priorityqueue;

public interface PQ<T extends Comparable<T>> {
    /**
     * Insert a key into priority queue
     * @param key
     */
    void insert(T key);

    /**
     * Get firs element in queue (not remove from queue
     * Max or min depends on implementation
     * @return
     */
    T peek();

    /**
     * Remove and Get firs element in queue
     * Max or min depends on implementation
     * @return
     */
    T pop();

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
