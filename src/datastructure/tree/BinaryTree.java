package datastructure.tree;

import datastructure.list.Position;

/**
 * Created by user on 3/21/17.
 */
public interface BinaryTree<E> extends Tree<E> {

    Position<E> left(Position<E> p) throws IllegalArgumentException;

    Position<E> right(Position<E> p) throws IllegalArgumentException;

    Position<E> sibling(Position<E> p) throws IllegalArgumentException;
}
