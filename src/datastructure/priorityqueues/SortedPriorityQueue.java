package datastructure.priorityqueues;

import datastructure.list.LinkedPositionalList;
import datastructure.list.Position;
import datastructure.list.PositionalList;
import java.util.Comparator;

/**
 * Created by user on 3/28/17.
 */
public class SortedPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {
    private PositionalList<Entry<K, V>> list = new LinkedPositionalList<>();

    public SortedPriorityQueue() {
        super();
    }

    public SortedPriorityQueue(Comparator<K> comp) {
        super(comp);
    }

    @Override
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        checkKey(key);
        Entry<K, V> newest = new PQEntry<K, V>(key, value);
        Position<Entry<K,V>> walk = list.last();
        //walk backward, looking for smaller key
        while (walk != null && compare(newest, walk.getElement()) < 0)
            walk = list.before(walk);
        if (walk == null)
            list.addFirst(newest);
        else
            list.addAfter(walk, newest);
        return newest;
    }

    @Override
    public Entry<K, V> min() {
        if (list.isEmpty()) return null;
        return list.first().getElement();
    }

    @Override
    public Entry<K, V> removeMin() {
        if (list.isEmpty()) return null;
        return list.remove(list.first());
    }

    @Override
    public int size() {
        return list.size();
    }
}
