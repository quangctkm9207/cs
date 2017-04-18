package datastructure.stack;

import datastructure.linkedlist.SinglyLinkedList;

/**
 * Created by user on 3/17/17.
 */
public class LinkedStack<E> implements Stack<E> {
    private SinglyLinkedList<E> list = new SinglyLinkedList<>();
    public LinkedStack() {}

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void push(E e) {
        list.addFirst(e);
    }

    @Override
    public E pop() {
        return list.removeFirst();
    }

    @Override
    public E top() {
        return list.first();
    }
}
