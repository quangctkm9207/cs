package datastructure.tree;

import datastructure.list.Position;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 3/21/17.
 */
public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E> {
    @Override
    public Position<E> sibling(Position<E> p) throws IllegalArgumentException {
        Position<E> parent = parent(p);
        if (p == left(parent)) {
            return right(parent);
        } else {
            return left(parent);
        }
    }

    @Override
    public int numChildren(Position<E> p) throws IllegalArgumentException {
        int count = 0;
        if (left(p) != null)
            count++;
        if (right(p) != null)
            count++;
        return count;
    }

    @Override
    public Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException {
        List<Position<E>> snapshot = new ArrayList<>();
        if (left(p) != null)
            snapshot.add(left(p));
        if (right(p) != null)
            snapshot.add(right(p));

        return snapshot;
    }

    //--------- Inorder Traversal ----------------
    private void inorderSubtree(Position<E> p, List<Position<E>> snapshot) {
        if (left(p) != null) {
            inorderSubtree(left(p), snapshot);
        }
        snapshot.add(p);
        if (right(p) != null) {
            inorderSubtree(right(p), snapshot);
        }
    }

    /** Returns an iterable collection of positions of the tree, reported in inorder. */
    public Iterable<Position<E>> inorder() {
        List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty()) {
            inorderSubtree(root(), snapshot); // fill the snapshot recursively
        }
        return snapshot;
    }

    @Override
    public Iterable<Position<E>> positions() {
        return inorder();
    }
}
