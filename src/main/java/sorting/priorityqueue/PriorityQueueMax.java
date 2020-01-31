package sorting.priorityqueue;

/**
 * Priority Queue with Max element on the top
 * could store maximum of `size` number of element (setup via constructors arg)
 * @param <T>
 */
public class PriorityQueueMax<T extends Comparable<T>> implements PQ<T> {
    private T[] pt;
    private int N;
    private int size;

    /**
     * Priority Queue with Max element on the top
     * @param size maximum of stored elements
     */
    public PriorityQueueMax(int size) {
        N = 0;
        this.size = size;
        pt = (T[]) new Comparable[size + 2];
    }

    private boolean less(int i, int j) {
        return pt[i].compareTo(pt[j]) < 0;
    }

    private void swap(int i, int j) {
        T b = pt[i];
        pt[i] = pt[j];
        pt[j] = b;
    }

    /**
     * Insert key
     * @param key new node of type T
     */
    @Override
    public void insert(T key) {
        int tail = N + 1;

        pt[tail] = key;
        swim(tail);

        if (tail <= this.size) {
            N = tail;
        }
    }

    /**
     * View first element (by priority) in queue
     * @return T
     */
    @Override
    public T peek() {
        return pt[1];
    }

    /**
     * Get (and remove from queue) first element (by priority) from queue
     * @return
     */
    @Override
    public T pop() {
        T result = pt[1];

        swap(1, N);
        N--;

        if (N > 1) {
            sink(1);
        }

        return result;
    }

    /**
     * Empty indicator
     * @return bool
     */
    @Override
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * Count of stored keys inside of array
     * @return int
     */
    @Override
    public int size() {
        return N;
    }

    /**
     * Go upstairs while parent is less then i'th value or until root
     * @param i pointer to node
     */
    private void swim(int i) {
        int parent = i >> 1; // i / 2
        while (i > 1 && less(parent, i)) {
            swap(parent, i);
            i = parent;
            parent = parent >> 1;
        }
    }

    /**
     * Go down while child greater then i'th element
     * @param i pointer to node
     */
    private void sink(int i) {
        int child = i << 1; // i * 2

        while (child <= N) {
            if (child < N && less(child, child + 1)) {
                child++;
            }

            if (!less(i, child)) {
                break;
            }

            swap(i, child);
            i = child;
        }
    }

}
