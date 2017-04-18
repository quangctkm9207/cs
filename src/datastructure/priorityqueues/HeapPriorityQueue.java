package datastructure.priorityqueues;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by user on 3/28/17.
 */
public class HeapPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {
    /**
     * primary collection of priority queue entries
     */
    protected ArrayList<Entry<K, V>> heap = new ArrayList<>();

    public HeapPriorityQueue() {
        super();
    }

    public HeapPriorityQueue(Comparator<K> comp) {
        super(comp);
    }

    // protected utilities
    protected int parent(int j) {
        return (j - 1) / 2;
    }

    protected int left(int j) {
        return 2 * j + 1;
    }

    protected int right(int j) {
        return 2 * j + 2;
    }

    protected boolean hasLeft(int j) {
        return left(j) < heap.size();
    }

    protected boolean hasRight(int j) {
        return right(j) < heap.size();
    }

    /**
     * Exchanges the entries at indices i and j of the array list
     */
    protected void swap(int i, int j) {
        Entry<K, V> temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    /**
     * Moves the entry at index j higher, if necessary, to restore the heap property.
     */
    protected void upheap(int j) {
        while (j > 0) {
            int p = parent(j);
            if (compare(heap.get(j), heap.get(p)) >= 0) break; // heap property verified
            swap(j, p);
            j = p;
        }
    }

    /**
     * Moves the entry at index j lower, if necessary, to restore the heap property.
     */
    protected void downheap(int j) {
        while (hasLeft(j)) {
            int leftIndex = left(j);
            int smallChildIndex = leftIndex; // although right maybe smaller
            if (hasRight(j)) {
                int rightIndex = right(j);
                if (compare(heap.get(leftIndex), heap.get(rightIndex)) > 0)
                    smallChildIndex = rightIndex;
            }

            if (compare(heap.get(smallChildIndex), heap.get(j)) >= 0)
                break;
            swap(j, smallChildIndex);
            j = smallChildIndex;
        }
    }


    // public methods

    /**
     * Returns the number of items in the priority queue
     */
    @Override
    public int size() {
        return heap.size();
    }

    /**
     * Returns but does not remove an entry with minimal key (if any)
     */
    public Entry<K, V> min() {
        if (heap.isEmpty()) return null;
        return heap.get(0);
    }

    /**
     * Inserts a key-value pair and returns the entry created
     */
    @Override
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        checkKey(key);
        Entry<K, V> newest = new PQEntry<K, V>(key, value);
        heap.add(newest);
        upheap(heap.size() - 1);
        return newest;
    }

    @Override
    public Entry<K, V> removeMin() {
        if (heap.isEmpty()) return null;
        Entry<K, V> answer = heap.get(0);
        swap(0, heap.size() -1);
        heap.remove(heap.size() - 1);
        downheap(0);
        return answer;
    }
}
