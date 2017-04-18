package datastructure.list;

/**
 * Created by user on 3/19/17.
 */
public class SimpleArrayList<E> implements List<E> {
    // instance variables
    public static final int CAPACITY = 16;
    private E[] data;
    private int size = 0;

    public SimpleArrayList() {
        this(CAPACITY);
    }

    public SimpleArrayList(int capacity) {
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
        if (size == data.length) { // not enough capacity
            throw new IllegalStateException("Array is full");
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
}
