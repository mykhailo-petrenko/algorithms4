package searching.st;

public abstract class ST<Key extends Comparable<Key>, Value> {
    public abstract void put(Key key, Value value);
    public abstract Value get(Key key);
    public abstract int size();

    public int size(Key from, Key to) {
        if (to.compareTo(from) < 0) {
            return 0;
        }

        int rank = rank(to) - rank(from);

        if (contains(to)) {
            rank++;
        }

        return rank;
    }

    public abstract Key min();
    public abstract Key max();

    /**
     * Largest key less than or equal to key
     * @param key
     * @return Key
     */
    public abstract Key floor(Key key);

    /**
     * Smallest key greater than or equal to key
     * @param key
     * @return Key
     */
    public abstract Key ceiling(Key key);

    /**
     * Number of keys less than key
     * @param key
     * @return
     */
    public abstract int rank(Key key);

    /**
     * Key of rank
     * @param k rank
     * @return Key
     */
    public abstract Key select(int k);

    public void deleteMin() {
        delete(min());
    }

    public void deleteMax() {
        delete(max());
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public abstract Iterable<Key> keys(Key from, Key to);

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
