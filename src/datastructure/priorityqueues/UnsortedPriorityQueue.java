package datastructure.priorityqueues;

import datastructure.list.LinkedPositionalList;
import datastructure.list.Position;
import datastructure.list.PositionalList;
import java.util.Comparator;

/**
 * Created by user on 3/28/17.
 *
 * An implementation of a priority queue with an unsorted list
 */
public class UnsortedPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {
    /**
     * primary collection of priority queue entries
     */
    private PositionalList<Entry<K, V>> list = new LinkedPositionalList<>();

    public UnsortedPriorityQueue() {
        super();
    }
    public UnsortedPriorityQueue(Comparator<K> comp){
        super(comp);
    }

    /**
     * Returns the Position of an entry having minimal key
     */
    private Position<Entry<K, V>> findMin() {
        Position<Entry<K, V>> small = list.first();
        for (Position<Entry<K, V>> walk: list.positions()) {
            if (compare(walk.getElement(), small.getElement()) < 0) {
                small = walk;
            }
        }
        return small;
    }

    /**
     * Inserts a key-value pair and returns the entry created
     */
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        checkKey(key);
        Entry<K, V> newest = new PQEntry<K, V>(key, value);
        list.addLast(newest);
        return newest;
    }

    /**
     * Returns (but does not remove) an entry with minimal key
     */
    public Entry<K, V> min() {
        if (list.isEmpty()) return null;
        return findMin().getElement();
    }

    public Entry<K, V> removeMin() {
        if (list.isEmpty()) return null;
        return list.remove(findMin());
    }

    public int size() {
        return list.size();
    }
}
