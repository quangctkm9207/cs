package datastructure.stack;

/**
 * Created by user on 3/17/17.
 */
public class ArrayStack<E> implements Stack<E> {
    public static final int CAPACITY = 1000;
    private E[] data;
    private int t = -1;  //index of the top element in stack
    public ArrayStack(){
        this(CAPACITY);
    }

    public ArrayStack(int capacity) {
        data = (E[]) new Object[capacity];
    }


    @Override
    public int size() {
        return (t + 1);
    }

    @Override
    public boolean isEmpty() {
        return (t == -1);
    }

    @Override
    public void push(E e) throws IllegalStateException{
        if (size() == data.length) throw new IllegalStateException("Stack is full");
        data[++t] = e;          // increment t before storing new item
    }

    @Override
    public E pop() {
        if (isEmpty()) return null;
        E answer = data[t];
        data[t] = null; // Assist Java's garbage collection mechanism
        t--;
        return answer;
    }

    @Override
    public E top() {
        if (isEmpty()) return null;
        return data[t];
    }
}
