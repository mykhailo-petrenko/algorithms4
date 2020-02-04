package sorting.priorityqueue;

public class IndexPriorityQueueMin<T extends Comparable<T>> implements IndexPQ<T> {
    private int[] keyToIndex;
    private int[] indexToKey;
    private T[] keys;

    private int N;

    public IndexPriorityQueueMin(int capacity) {
        N = 0;
        keys = (T[]) new Comparable[capacity + 1];
        indexToKey = new int[capacity + 1];
        keyToIndex = new int[capacity + 1];
    }

    @Override
    public void insert(int index, T key) {
        N++;

        keys[N] = key;
        keyToIndex[N] = index;
        indexToKey[index] = N;

        swim(N);
    }

    @Override
    public T peekKey() {
        return keys[1];
    }

    @Override
    public int peek() {
        return keyToIndex[1];
    }

    @Override
    public int dequeue() {
        int index = this.peek();

        swap(1, N);

        keys[N] = null;
        keyToIndex[N] = 0;
        indexToKey[index] = 0;

        N--;

        if (N > 1) {
            sink(1);
        }

        return index;
    }

    @Override
    public T get(int index) {
        int keyIndex = indexToKey[index];
        return keys[keyIndex];
    }

    @Override
    public boolean contains(int index) {
        return indexToKey[index] != 0;
    }

    @Override
    public void delete(int index) {
        int keyIndex = indexToKey[index];

        swap(keyIndex, N);
        N--;
        sink(keyIndex);
    }

    @Override
    public boolean isEmpty() {
        return N == 0;
    }

    @Override
    public int size() {
        return N;
    }

    private void swap(int i, int j) {
        T tmpKey = keys[i];
        keys[i] = keys[j];
        keys[j] = tmpKey;

        int iIndex = keyToIndex[i];
        int jIndex = keyToIndex[j];

        keyToIndex[i] = jIndex;
        keyToIndex[j] = iIndex;

        indexToKey[iIndex] = j;
        indexToKey[jIndex] = i;
    }

    private boolean less(int i, int j) {
        return keys[i].compareTo(keys[j]) < 0;
    }

    private void swim(int i) {
        int parent = i >> 1;
        while (i > 1 && less(i, parent)) {
            swap(parent, i);
            i = parent;
            parent = parent >> 1;
        }
    }

    private void sink(int i) {
        int child = i << 1;

        while (child <= N) {
            if (child < N && less(child + 1, child)) {
                child++;
            }

            if (!less(child, i)) {
                break;
            }

            swap(child, i);
            i = child;
            child = child << 1;
        }
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            b.append(keys[i].toString());
            b.append(" ");
        }
        return b.toString();
    }
}
