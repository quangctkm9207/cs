package datastructure.tree;

import datastructure.list.Position;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by user on 3/21/17.
 */
public abstract class AbstractTree<E> implements Tree<E> {
    @Override
    public boolean isInternal(Position<E> p) throws IllegalArgumentException {
        return numChildren(p) > 0;
    }

    @Override
    public boolean isExternal(Position<E> p) throws IllegalArgumentException {
        return numChildren(p) == 0;
    }

    @Override
    public boolean isRoot(Position<E> p) throws IllegalArgumentException {
        return p == root();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    public int depth(Position<E> p) {
        if (isRoot(p)) {
            return 0;
        } else {
            return 1 + depth(p);
        }
    }

    public int height(Position<E> p) {
        int h = 0;
        for (Position<E> c : children(p)) {
            h = Math.max(h, 1 + height(c));
        }
        return h;
    }

    //--------nested ElementIterator class--------------
    /* This class adapts the iteration produced by positions() to return elements. */
    private class ElementIterator implements Iterator<E> {
        Iterator<Position<E>> posIterator = positions().iterator();

        @Override
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

    // TODO: Verify these override methods or implement them
    @Override
    public Iterator<E> iterator() {
        return new ElementIterator();
    }

    @Override
    public Iterable<Position<E>> positions() {
        return preorder();
    }

    //--------- Preorder Traversal ------------

    /**
     * Adds positions of the subtree rooted at Position p to the given snapshot.
     */
    private void preorderSubtree(Position<E> p, List<Position<E>> snapshot) {
        snapshot.add(p); // for preorder, we add position p before exploring subtrees
        for (Position<E> c : children(p)) {
            preorderSubtree(c, snapshot);
        }
    }

    public Iterable<Position<E>> preorder() {
        List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty()) {
            preorderSubtree(root(), snapshot);
        }
        return snapshot;
    }

    //----------- Postorder Traversal ------------

    /**
     * Adds positions of the subtree rooted at Position p to the given snapshot.
     */
    private void postorderSubtree(Position<E> p, List<Position<E>> snapshot) {
        for (Position<E> c : children(p)) {
            postorderSubtree(p, snapshot);
        }
        snapshot.add(p); // for postorder, we add position p after exploring subtrees
    }

    public Iterable<Position<E>> postorder() {
        List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty()) {
            postorderSubtree(root(), snapshot); // fill the snapshot recursively
        }
        return snapshot;
    }

    //----------- Breadth-First Traversal -----------

    public Iterable<Position<E>> breadthFirst() {
        List<Position<E>> snapShot = new ArrayList<>();
        if (!isEmpty()) {
            Queue<Position<E>> fringe = new LinkedList<>();
            fringe.add(root());  // start with the root
            while (!fringe.isEmpty()) {
                Position<E> p = fringe.remove(); // remove from front of the queue
                snapShot.add(p);                 // report this position
                for (Position<E> c: children(p)) {
                    fringe.add(c);               // add children to back of queue
                }
            }
        }
        return snapShot;
    }
}
