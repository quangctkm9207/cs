package datastructure.priorityqueues;

import java.util.Comparator;

/**
 * Created by user on 3/28/17.
 */
public abstract class AbstractPriorityQueue<K, V> implements PriorityQueue<K, V> {

    //--------------- nested PQEntry class -----------------
    protected static class PQEntry<K, V> implements Entry<K, V> {
        private K k;
        private V v;

        public PQEntry(K key, V value) {
            k = key;
            v = value;
        }

        @Override
        public K getKey() {
            return k;
        }

        @Override
        public V getValue() {
            return v;
        }

        // Utilities not exposed as part of the Entry interface
        protected void setKey(K key) {
            k = key;
        }

        protected void setValue(V value) {
            v = value;
        }
    }
    //------------- end of nested PQEntry class ---------------

    // instance variables for an AbstractPriorityQueue

    private Comparator<K> comp;

    protected AbstractPriorityQueue(Comparator<K> c) {
        comp = c;
    }

    protected AbstractPriorityQueue() {
        this(new DefaultComparator<K>());
    }

    protected int compare(Entry<K, V> a, Entry<K, V> b) {
        return comp.compare(a.getKey(), b.getKey());
    }

    /**
     * Determines whether a key is valid
     * */
    protected boolean checkKey(K key) throws IllegalArgumentException {
        try {
            return (comp.compare(key, key) == 0); // see if key can be compared to itself
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Incompatible key");
        }
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
}
