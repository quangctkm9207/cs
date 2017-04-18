package datastructure.linkedlist;

/**
 * Created by user on 3/13/17.
 */
public class SinglyLinkedList<E> {

    //-----------nested Node class--------------
    private static class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> n) {
            next = n;
        }
    }
    //---------- end of nested Node class -----------

    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    public SinglyLinkedList() {}

    public int size() {
        return size;
    }

    public boolean isEmpty() {
       return size == 0;
    }

    public E first() {
        if(isEmpty()) return null;
        return head.getElement();
    }

    public E last() {
        if(isEmpty()) return null;
        return tail.getElement();
    }

    public void addFirst(E e)  {
        head = new Node<>(e, head);
        if (size == 0)
            tail = head;
        size ++;
    }

    public void addLast(E e) {
        Node<E> lastElement = new Node<>(e, null);
        if (isEmpty()) {
            head = lastElement;
        } else {
            tail.setNext(lastElement);
        }
        tail = lastElement;
        size ++;
    }

    public E removeFirst() {
        if (head == null) return null;
        E answer = head.getElement();
        head = head.getNext();
        size --;
        if (size == 0)
            tail = null;
        return answer;
    }

}
