package searching.st;

public abstract class ST<Key, Value> {
    public abstract void put(Key key, Value value);
    public abstract Value get(Key key);
    public abstract int size();

    public abstract Iterable<Key> keys();

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public boolean isEmpty() {
        return size() != 0;
    }

    public void delete(Key key) {
        put(key, null);
    }
}
