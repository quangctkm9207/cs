package datastructure.priorityqueues;

import java.util.Comparator;

/**
 * Created by user on 3/28/17.
 */
public class DefaultComparator<E> implements Comparator<E> {
    @Override
    public int compare(E a, E b) {
        return ((Comparable<E>) a).compareTo(b);
    }
}
