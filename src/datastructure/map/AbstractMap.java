package datastructure.map;

import datastructure.priorityqueues.Entry;
import java.util.Iterator;

/**
 * Created by user on 3/30/17.
 */
public abstract class AbstractMap<K, V> implements Map<K, V> {
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    //--------- nested MapEntry class ---------
    protected static class MapEntry<K, V> implements Entry<K, V> {
        private K k;
        private V v;
        public MapEntry(K key, V value){
            k = key;
            v = value;
        }

        public K getKey() {
            return k;
        }

        @Override
        public V getValue() {
            return v;
        }

        protected void setKey(K key) {
            k = key;
        }

        protected V setValue(V value) {
            V old = v;
            v = value;
            return old;
        }
    }
    //--------- end of nested MapEntry class ----------------

    //Support for public keySet method
    private class KeyIterator implements Iterator<K> {
        private Iterator<Entry<K, V>> entries = entrySet().iterator(); // reuse entrySet
        @Override
        public boolean hasNext() {
            return entries.hasNext();
        }

        @Override
        public K next() {
            return entries.next().getKey();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class KeyIterable implements Iterable<K> {
        @Override
        public Iterator<K> iterator() {
            return new KeyIterator();
        }
    }
    public Iterable<K> keySet() {
        return new KeyIterable();
    }
    // Support for public values method
    private class ValueIterator implements Iterator<V> {
        private Iterator<Entry<K,V>> entries = entrySet().iterator(); // reuse entrySet

        @Override
        public boolean hasNext() {
            return entries.hasNext();
        }

        @Override
        public V next() {
            return entries.next().getValue();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class ValueIterable implements Iterable<V> {
        @Override
        public Iterator<V> iterator() {
            return new ValueIterator();
        }
    }

    @Override
    public Iterable<V> values() {
        return new ValueIterable();
    }
}
