package datastructure.list;

/**
 * Created by user on 3/19/17.
 *
 * An implementation of an DynamicArrayList
 * which does not limit the size of array
 * The difference compared with the SimpleArrayList is that it has resize method
 */
public class DynamicArrayList<E> implements List<E> {
    // instance variables
    public static final int CAPACITY = 16;
    private E[] data;
    private int size = 0;

    public DynamicArrayList() {
        this(CAPACITY);
    }

    public DynamicArrayList(int capacity) {
        data = (E[]) new Object[capacity];
    }

    // Time complexity: O(1)
    @Override
    public int size() {
        return size;
    }

    // Time complexity: O(1)
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        return data[i];
    }
    // Time complexity: O(1)
    @Override
    public E set(int i, E e) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        E temp = data[i];
        data[i] = e;
        return temp;
    }

    // Time complexity: O(n)
    @Override
    public void add(int i, E e) throws IndexOutOfBoundsException {
        checkIndex(i, size + 1);
        if (size == data.length) {
           resize(2 * data.length); // Double the array size
        }
        for (int k = size - 1; k >= i; k --){ // start by shifting rightmost
            data[k +1] = data[k];
        }
        data[i] = e; //ready to place the new element
        size ++;
    }

    // Time complexity: O(n)
    @Override
    public E remove(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        E temp = data[i];
        for (int k = i; k < size - 1; k ++) { // shift elements to fill hole
            data[k] = data[k + 1];
        }
        data[size - 1] = null; // aid garbage collection
        size --;
        return temp;
    }

    // Utility method
    protected void checkIndex(int i, int n) throws IndexOutOfBoundsException {
        if (i < 0 || i >= n) {
            throw new IndexOutOfBoundsException("Illegal index: " + i);
        }
    }

    /**
     * Resizes internal  array to have given capacity >= size.
     */

    protected void resize(int capacity) {
        E[] temp = (E[]) new Object[capacity]; // safe cast; compiler may give warning
        for (int k = 0; k < size; k ++) {
            temp[k] = data[k];
        }
        data = temp;    // start using the new array
    }
}
