package data.structure.tree;

import java.util.Iterator;

import data.structure.list.Fifo;

public class BinTree<E extends Comparable<E>> implements Iterable<E> {
    /**
     * Values to define the tree iteration order.
     * INFIX
     * PREFIX
     * POSFIX
     */
    public enum BinTreeOrder {
        INFIX, PREFIX, POSFIX
    }

    private NodeBinTree<E> root;
    private BinTreeOrder order;
    
    public class BinTreeIterator implements Iterator<E> {
        private Fifo<E> fifo;

        /**
         * Generate infix order.
         */
        private Fifo<E> inOrder() {
            if (null == root) return null;
            Fifo<E> result = new Fifo<E>();
            inOrderSearch(root, result);
            return result;
        }

        private void inOrderSearch(NodeBinTree<E> start, Fifo<E> fifo) {
            NodeBinTree<E> left  = start.getLeftChild();
            NodeBinTree<E> right = start.getRightChild();

            if (null != left)  preOrderSearch(left, fifo);
            fifo.put(start.getElement());
            if (null != right) preOrderSearch(right, fifo);

        }

        /**
         * Generate prefix order.
         */
        private Fifo<E> preOrder() {
            if (null == root) return null;
            Fifo<E> result = new Fifo<E>();
            preOrderSearch(root, result);
            return result;
        }

        private void preOrderSearch(NodeBinTree<E> start, Fifo<E> fifo) {
            NodeBinTree<E> left  = start.getLeftChild();
            NodeBinTree<E> right = start.getRightChild();

            fifo.put(start.getElement());
            if (null != left)  preOrderSearch(left, fifo);
            if (null != right) preOrderSearch(right, fifo);
        }

        /**
         * Generate posfix order.
         */
        private Fifo<E> posOrder() {
            if (null == root) return null;
            Fifo<E> result = new Fifo<E>();
            posOrderSearch(root, result);
            return result;
        }

        private void posOrderSearch(NodeBinTree<E> start, Fifo<E> fifo) {
            NodeBinTree<E> left  = start.getLeftChild();
            NodeBinTree<E> right = start.getRightChild();

            if (null != left)  posOrderSearch(left, fifo);
            if (null != right) posOrderSearch(right, fifo);
            fifo.put(start.getElement());

        }

        public BinTreeIterator() {
            switch (order) {
                case PREFIX:
                    fifo = preOrder();
                    break;
                case POSFIX:
                    fifo = posOrder();
                    break;
                default:
                    fifo = inOrder();
                    break;
            }
        }

        public boolean hasNext() {
            return fifo != null && !fifo.isEmpty();
        }

        public E next() {
            if (fifo.isEmpty()) return null;
            return fifo.get();
        }
    }

    public Iterator<E> iterator() {
        return new BinTreeIterator();
    }

    // Getters and Setters
    public E getRoot() {
        return this.root.getElement();
    }

    public BinTreeOrder getOrder() {
        return this.order;
    }

    public void setOrder(BinTreeOrder order) {
        this.order = order;
    }

    // Constructors
    public BinTree(){
        this.root = null;
        this.order = BinTreeOrder.INFIX;
    }

    public BinTree(E element) {
        this.root = new NodeBinTree<E>(element);
        this.order = BinTreeOrder.INFIX;
    }

    /**
     * Informs if the tree is empty.
     */
    public boolean isEmpty() {
        return null == this.root; 
    }

    /**
     * Informs the number of nodes in the tree.
     */
    public int size() {
        if (null == this.root) return 0;
        return root.familySize();
    }

    /**
     * Informs the maximum depth of this tree.
     */
    public int maxDepth() {
        if (null == this.root) return 0;
        return this.root.maxDepth();
    }

    private void add(E element, NodeBinTree<E> root) {
        NodeBinTree<E> nextNode;
        int comp = element.compareTo(root.getElement());
        switch (comp) {
            case -1:
                nextNode = root.getLeftChild();
                break;
            case  1:
                nextNode = root.getRightChild();
                break;
            default:
                nextNode = null;
                break;
        }
        if (null != nextNode) {
            add(element, nextNode);
        } else if (comp == 1) {
            nextNode = new NodeBinTree<E>(element, root);
            root.setRightChild(nextNode);
        } else {
            nextNode = new NodeBinTree<E>(element, root, root.getLeftChild());
            root.setLeftChild(nextNode);
        }
    }

    /**
     * Inserts a new element into tree.
     */
    public void add(E element) {
        if (null != this.root) {
            add(element, this.root);
        } else {
            this.root = new NodeBinTree<E>(element);
        }
    }

    /**
     * Returns leftt sub-tree.
     */
    public BinTree<E> leftBinTree() {
        BinTree<E> temp = new BinTree<E>();
        temp.root = this.root.getLeftChild();
        return temp;
    }

    /**
     * Returns right sub-tree.
     */
    public BinTree<E> rightBinTree() {
        BinTree<E> temp = new BinTree<E>();
        temp.root = this.root.getRightChild();
        return temp;
    }

}