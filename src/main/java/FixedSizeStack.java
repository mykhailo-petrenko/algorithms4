public class FixedSizeStack<T> {

    private int size;
    private T[] stack;
    private int N;

    FixedSizeStack(int initialSize) {
        this.stack = (T[]) new Object[initialSize];
        this.size = initialSize;
        this.N = 0;
    }

    public void push(T item) {
        if (N==size) {
            this.resizeStack(size * 2);
        }
        stack[N++] = item;
    }

    public T pop() {
        T el = stack[--N];
        stack[N] = null;

        if (N <= (size/2)) {
            this.resizeStack(size/2);
        }

        return el;
    }

    public boolean isEmpty() {
        return N==0;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Size = " + size + "; N = " + N;
    }

    private void resizeStack(int newSize) {
        T[] buffer = (T[]) new Object[newSize];
        int n = Math.min(newSize, size);

        for(int i = 0; i<n; i++) {
            if (stack[i]==null) {
                break;
            }

            buffer[i] = stack[i];
        }

        this.stack = buffer;
        this.size = newSize;
    }

}
