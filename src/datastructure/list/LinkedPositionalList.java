package datastructure.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by user on 3/19/17.
 */
public class LinkedPositionalList<E> implements PositionalList<E> {

    //------------- nested Node class ---------------
    private static class Node<E> implements Position<E> {
        private E element;
        private Node<E> prev;
        private Node<E> next;

        public Node(E e, Node<E> p, Node<E> n) {
            element = e;
            prev = p;
            next = n;
        }

        @Override
        public E getElement() throws IllegalArgumentException {
            if (next == null)
                throw new IllegalArgumentException("Position no longer valid");
            return element;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }
    }

    //-------------- end of nested Node class -------------

    // instance variables of the LinkedPositionalList
    private Node<E> header;  // header sentinel
    private Node<E> trailer; // trailer sentinel
    private int size;        // number of elements in the list

    /**
     * Constructs a new empty list.
     */
    public LinkedPositionalList() {
        header = new Node(null, null, null); // create header
        trailer = new Node(null, header, null); // trailer is preceded by header
        header.next = trailer; // header is followed by trailer
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Position<E> first() {
        return position(header.getNext());
    }

    @Override
    public Position<E> last() {
        return position(trailer.getPrev());
    }

    @Override
    public Position<E> before(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return position(node.getPrev());
    }

    @Override
    public Position<E> after(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return position(node.getNext());
    }

    @Override
    public Position<E> addFirst(E e) {
        return addBetween(e, header, header.getNext());
    }

    @Override
    public Position<E> addLast(E e) {
        return addBetween(e, trailer.getPrev(), trailer);
    }

    @Override
    public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return addBetween(e, node.getPrev(), node);
    }

    @Override
    public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return addBetween(e, node, node.getNext());
    }

    @Override
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        E answer = node.getElement();
        node.setElement(e);
        return answer;
    }

    @Override
    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        Node<E> pre = node.getPrev();
        Node<E> succ = node.getNext();
        pre.setNext(succ);
        succ.setPrev(pre);
        size--;
        E answer = node.getElement();
        node.setElement(null);  // help with garbage collection
        node.setNext(null);     // and convention for defunct node
        node.setPrev(null);
        return answer;
    }

    // private utilities

    /**
     * Validates the position and returns it as a node.
     */
    private Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node)) throw new IllegalArgumentException("Invalid p");
        Node<E> node = (Node<E>) p; // safe cast
        if (node.getNext() == null)
            throw new IllegalArgumentException("p is no longer in the list");
        return node;
    }

    /**
     * Returns the given node as a Position (or null, if it is a sentinel).
     */
    private Position<E> position(Node<E> node) {
        if (node == header || node == trailer)
            return null; // do not expose user to the sentinels
        return node;
    }

    /**
     * Adds element e to the linked list between the given nodes.
     */
    private Position<E> addBetween(E e, Node<E> pred, Node<E> succ) {
        Node<E> newest = new Node<>(e, pred, succ);
        pred.setNext(newest);
        succ.setPrev(newest);
        size++;
        return newest;
    }

    //--------- nested PositionIterator class ------------
    private class PositionIterator implements Iterator<Position<E>> {
        private Position<E> cursor = first(); // position of the next element to report
        private Position<E> recent = null; // position of last reported element

        /**
         * Test whether the iterator has a next object
         */
        public boolean hasNext() {
            return (cursor != null);
        }

        @Override
        public Position<E> next() throws NoSuchElementException {
            if (cursor == null) throw new NoSuchElementException("nothing left");
            recent = cursor;
            cursor = after(cursor);
            return recent;
        }

        @Override
        public void remove() throws NoSuchElementException {
            if (recent == null) throw new NoSuchElementException("nothing to remove");
            LinkedPositionalList.this.remove(recent);
            recent = null;
        }
    } // -------- end of nested PositionIterator class ------------

    //--------- nested PositionIterable class -------------
    private class PositionIterable implements Iterable<Position<E>> {
        @Override
        public Iterator<Position<E>> iterator() {
            return new PositionIterator();
        }
    } //----------- end of nested PositionIterable class ---------

    public Iterable<Position<E>> positions() {
        return new PositionIterable();
    }


    private class ElementIterator implements Iterator<E> {
        Iterator<Position<E>> posIterator = new PositionIterator();

        public boolean hasNext() {
            return posIterator.hasNext();
        }

        @Override
        public E next() {
            return posIterator.next().getElement();
        }

        @Override
        public void remove() {
            posIterator.remove();
        }
    }

    public Iterator<E> iterator() {
        return new ElementIterator();
    }

}
