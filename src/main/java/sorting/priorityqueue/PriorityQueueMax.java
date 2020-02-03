package sorting.priorityqueue;

/**
 * Priority Queue with Max element on the top
 * could store maximum of `size` number of element (setup via constructors arg)
 * @param <T>
 */
public class PriorityQueueMax<T extends Comparable<T>> implements PQ<T> {
    private T[] pt;
    private int N;

    /**
     * Priority Queue without size limit
     */
    public PriorityQueueMax() {
        N = 0;
        resize(2);
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
        if (pt.length == (N + 1)) {
            resize(pt.length * 2);
        }

        N++;
        pt[N] = key;
        swim(N);
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
        pt[N] = null;
        N--;

        if (N > 1) {
            sink(1);

            if (N > 2 && pt.length == (N * 4)) {
                resize(N * 2);
            }
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

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            b.append(pt[i].toString());
            b.append(" ");
        }
        return b.toString();
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
            child = child << 1;
        }
    }

    private void resize(int newSize) {
        T[] target = (T[]) new Comparable[newSize];

        for (int i = 1; i <= N; i++) {
            target[i] = pt[i];
        }

        this.pt = target;
    }

}
