package searching.st;

public abstract class ST<Key extends Comparable<Key>, Value> {
    abstract void put(Key key, Value value);
    abstract Value get(Key key);
    abstract int size();

    int size(Key from, Key to) {
        if (to.compareTo(from) < 0) {
            return 0;
        }

        int rank = rank(to) - rank(from);

        if (contains(to)) {
            rank++;
        }

        return rank;
    }

    abstract Key min();
    abstract Key max();

    /**
     * Largest key less than or equal to key
     * @param key
     * @return Key
     */
    abstract Key floor(Key key);

    /**
     * Smallest key greater than or equal to key
     * @param key
     * @return Key
     */
    abstract Key ceiling(Key key);

    /**
     * Number of keys less than key
     * @param key
     * @return
     */
    abstract int rank(Key key);

    /**
     * Key of rank
     * @param k rank
     * @return Key
     */
    abstract Key select(int k);

    void deleteMin() {
        delete(min());
    }

    void deleteMax() {
        delete(max());
    }

    Iterable<Key> keys() {
        return keys(min(), max());
    }

    abstract Iterable<Key> keys(Key from, Key to);

    boolean contains(Key key) {
        return get(key) != null;
    }

    boolean isEmpty() {
        return size() != 0;
    }

    void delete(Key key) {
        put(key, null);
    }



}
