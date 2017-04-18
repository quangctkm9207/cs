package datastructure.tree;

import datastructure.list.Position;

/**
 * Created by user on 3/23/17.
 * <p>
 * Concrete implementation of a binary tree using a node-based, linked structure
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

    //-------------- nested Node class -------------
    protected static class Node<E> implements Position<E> {
        private E element;
        private Node<E> parent;
        private Node<E> left;
        private Node<E> right;

        /**
         * Constructs a node with the given element and neighbors
         */
        public Node(E e, Node<E> above, Node<E> leftChild, Node<E> rightChild) {
            element = e;
            parent = above;
            left = leftChild;
            right = rightChild;
        }

        // Accessor methods
        public E getElement() throws IllegalStateException {
            return element;
        }

        public Node<E> getParent() {
            return parent;
        }

        public Node<E> getLeft() {
            return left;
        }

        public Node<E> getRight() {
            return right;
        }

        // Update methods
        public void setElement(E e) {
            element = e;
        }

        public void setParent(Node<E> parentNode) {
            parent = parentNode;
        }

        public void setLeft(Node<E> leftNode) {
            left = leftNode;
        }

        public void setRight(Node<E> rightNode) {
            right = rightNode;
        }
    }
    //--------------- end of nested Node class -----------

    /**
     * Factory function to create a new node storing element e
     */
    protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) {
        return new Node<E>(e, parent, left, right);
    }

    // LinkedBinaryTree instance variables
    protected Node<E> root = null;
    private int size = 0;

    // constructor
    public LinkedBinaryTree() {
    }

    // nonpublic utility
    /**
    * Validates the position and return it as a node
    */
    protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node)){
            throw new IllegalArgumentException("Not valid position type");
        }

        Node<E> node = (Node<E>) p; // safe cast
        if (node.getParent() == node) {
            throw new IllegalArgumentException("p is no longer in the tree");
        }

        return node;
    }

    // accessor methods (not already implemented in AbstractBinaryTree)
    /**
     * Returns the number of nodes in the tree
     */
    public int size() {
        return size;
    }

    /**
     * Returns the root Position of the tree (or null if tree is empty)
     */
    public Position<E> root() {
        return root;
    }

    /**
     * Returns the Position of p's parent (or null if p is root)
     */
    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getParent();
    }

    @Override
    public Position<E> left(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getLeft();
    }

    @Override
    public Position<E> right(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getRight();
    }

    /**
     * Places element e at the root of an empty tree and return its new Position
     */
    public Position<E> addRoot(E e) throws IllegalStateException {
        if (!isEmpty()) throw new IllegalStateException("Tree is not empty");
        root = createNode(e, null, null, null);
        size = 1;
        return root;
    }

    /**
     * Creates a new left child of Position p storing element e; returns its Position
     */
    public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> parent = validate(p);
        if (parent.getLeft() != null) {
            throw new IllegalArgumentException("p already has a left child");
        }
        Node<E> child = createNode(e, parent, null, null);
        parent.setLeft(child);
        size++;
        return child;
    }

    /**
     * Creates a new right child of Position p storing element e; return its Position
     */
    public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> parent = validate(p);
        if (parent.getRight() != null) {
            throw new IllegalArgumentException("p already has a right child");
        }
        Node<E> child = createNode(e, parent, null, null);
        parent.setRight(child);
        size++;
        return child;
    }

    /**
     * Replaces the element at Position p with e and returns the replaced element
     */
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        E temp = node.getElement();
        node.setElement(e);
        return temp;
    }

    /**
     * Attaches tree t1 and t2 as left and right subtrees of external p
     */
    public void attach(Position<E> p, LinkedBinaryTree<E> t1,
                       LinkedBinaryTree<E> t2) throws IllegalArgumentException {
        Node<E> node = validate(p);
        if (isInternal(p)) {
            throw new IllegalArgumentException("p must be a leaf");
        }
        size += t1.size() + t2.size();
        if (!t1.isEmpty()) {
            t1.root.setParent(node);
            node.setLeft(t1.root);
            t1.root = null;
            t1.size = 0;
        }

        if (!t1.isEmpty()) {
            t2.root.setParent(node);
            node.setRight(t2.root);
            t2.root = null;
            t2.size = 0;
        }

    }

    /**
     * Removes the node at Position p and replaces it with its child, if any
     */
    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        if (numChildren(p) == 2){
            throw new IllegalArgumentException("p has two children");
        }
        Node<E> child = (node.getLeft() != null ? node.getLeft() : node.getRight());
        if (child != null){
            child.setParent(node.getParent());
        }
        if (node == root) {
            root = child;
        } else {
            Node<E> parent = node.getParent();
            if (node == parent.getLeft()){
                parent.setLeft(child);
            } else {
                parent.setRight(child);
            }
        }
        size --;
        E temp = node.getElement();
        node.setElement(null);  // help garbage collection
        node.setLeft(null);
        node.setRight(null);
        node.setParent(node);   // our convention for defunct node
        return temp;
    }
}
