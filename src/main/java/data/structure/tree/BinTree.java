package data.structure.tree;

import java.util.Iterator;

import data.structure.list.Fifo;

public class BinTree<E extends Comparable<E>> implements Iterable<NodeBinTree<E>> {
    /**
     * Values to define the tree iteration order.
     * INFIX
     * PREFIX
     * POSFIX
     */
    public enum BinTreeOrder {
        INFIX, PREFIX, POSFIX
    }

    protected NodeBinTree<E> root;
    protected BinTreeOrder order;
    
    public class BinTreeIterator implements Iterator<NodeBinTree<E>> {
        private Fifo<NodeBinTree<E>> fifo;

        /**
         * Generate infix order.
         */
        private Fifo<NodeBinTree<E>> inOrder() {
            if (null == root) return null;
            Fifo<NodeBinTree<E>> result = new Fifo<NodeBinTree<E>>();
            inOrderSearch(root, result);
            return result;
        }

        private void inOrderSearch(NodeBinTree<E> start, Fifo<NodeBinTree<E>> fifo) {
            NodeBinTree<E> left  = start.getLeftChild();
            NodeBinTree<E> right = start.getRightChild();

            if (null != left)  inOrderSearch(left, fifo);
            fifo.put(start);
            if (null != right) inOrderSearch(right, fifo);

        }

        /**
         * Generate prefix order.
         */
        private Fifo<NodeBinTree<E>> preOrder() {
            if (null == root) return null;
            Fifo<NodeBinTree<E>> result = new Fifo<NodeBinTree<E>>();
            preOrderSearch(root, result);
            return result;
        }

        private void preOrderSearch(NodeBinTree<E> start, Fifo<NodeBinTree<E>> fifo) {
            NodeBinTree<E> left  = start.getLeftChild();
            NodeBinTree<E> right = start.getRightChild();

            fifo.put(start);
            if (null != left)  preOrderSearch(left, fifo);
            if (null != right) preOrderSearch(right, fifo);
        }

        /**
         * Generate posfix order.
         */
        private Fifo<NodeBinTree<E>> posOrder() {
            if (null == root) return null;
            Fifo<NodeBinTree<E>> result = new Fifo<NodeBinTree<E>>();
            posOrderSearch(root, result);
            return result;
        }

        private void posOrderSearch(NodeBinTree<E> start, Fifo<NodeBinTree<E>> fifo) {
            NodeBinTree<E> left  = start.getLeftChild();
            NodeBinTree<E> right = start.getRightChild();

            if (null != left)  posOrderSearch(left, fifo);
            if (null != right) posOrderSearch(right, fifo);
            fifo.put(start);

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

        public NodeBinTree<E> next() {
            if (fifo.isEmpty()) return null;
            return fifo.get();
        }
    }

    public Iterator<NodeBinTree<E>> iterator() {
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
     * 
     * @return boolean
     */
    public boolean isEmpty() {
        return null == this.root; 
    }

    /**
     * Informs the number of nodes in the tree.
     * 
     * @return int
     */
    public int size() {
        if (null == this.root) return 0;
        return root.familySize();
    }

    /**
     * Informs the maximum depth of this tree.
     * 
     * @return int
     */
    public int maxDepth() {
        if (null == this.root) return 0;
        return this.root.maxDepth();
    }

    /**
     * Inserts a new element into tree.
     * 
     * @return NodeBinTree<E>
     */
    private NodeBinTree<E> add(E element, NodeBinTree<E> root) {
        int comp = element.compareTo(root.getElement());
        NodeBinTree<E> nextNode = (comp == -1) ? root.getLeftChild() : root.getRightChild();

        if (null != nextNode) {
            nextNode = add(element, nextNode);
        } else if (comp == -1) {
            nextNode = new NodeBinTree<E>(element, root);
            root.setLeftChild(nextNode);
        } else {
            nextNode = new NodeBinTree<E>(element, root);
            root.setRightChild(nextNode);
        }
        return nextNode;
    }

    /**
     * Inserts a new element into tree.
     * 
     * @return NodeBinTree<E>
     */
    public NodeBinTree<E> add(E element) {
        if (null != this.root) {
            return add(element, this.root);
        } else {
            this.root = new NodeBinTree<E>(element);
            return this.root;
        }
    }

    /**
     * Returns leftt sub-tree.
     * 
     * @return BinTree<E>
     */
    public BinTree<E> leftBinTree() {
        BinTree<E> temp = new BinTree<E>();
        temp.root = this.root.getLeftChild();
        temp.root.promote();
        return temp;
    }

    /**
     * Returns right sub-tree.
     * 
     * @return BinTree<E>
     */
    public BinTree<E> rightBinTree() {
        BinTree<E> temp = new BinTree<E>();
        temp.root = this.root.getRightChild();
        temp.root.promote();
        return temp;
    }

    /**
     * Makes a node returns to its original tree.
     */
    public void cut() {
        this.root.demote();
        this.root = null;
    }

    /**
     * Returns a array representation of tree.
     * 
     * @return Object[]
     */
    public Object[] toArray() {
        int depth = this.maxDepth();
        Object[] result =  new Object[(int) Math.pow(2, depth)];
        BinTreeIterator iterator = new BinTreeIterator();
        while (iterator.hasNext()) {
            NodeBinTree<E> temp = iterator.next();
            result[temp.position()] = temp;
        }
        return result;
    }

    /**
     * Search a node in the tree.
     * 
     * @return NodeBinTree<E>
     */
    public NodeBinTree<E> search(E element) {
        return searchNextStep(element, this.root);
    }

    /**
     * Search a node in the tree starting in spefic node.
     * 
     * @return NodeBinTree<E>
     */
    public NodeBinTree<E> searchNextStep(E element, NodeBinTree<E> nextNode) {
        NodeBinTree<E> next;
        int comp = element.compareTo(nextNode.getElement());
        switch (comp) {
            case -1:
                next = nextNode.getLeftChild();
                break;
            case  1:
                next = nextNode.getRightChild();
                break;
            default:
                next = nextNode;
                break;
        }
        return (next == nextNode) ? nextNode :
                   (null != next) ? searchNextStep(element, next) : null;
    }

    /**
     * Removes the element inside the node without break the order.
     * 
     * @return E
     */
    public E remove(NodeBinTree<E> node) {
        if (null == node) return null;
        NodeBinTree<E>   leaf = removingTravel(node);
        NodeBinTree<E> father = leaf.getFather();
        if (null == father) {
            this.root = null;
        } else if (leaf.amILeftChild()) {
            father.setLeftChild(null);
        } else {
            father.setRightChild(null);
        }
        return leaf.getElement();
    }

    protected NodeBinTree<E> removingTravel(NodeBinTree<E> node) {
        if (node.isLeaf()) return node;
        NodeBinTree<E> nextNode = node.minorOnTheRight();
        if (null == nextNode) nextNode = node.largestOnTheLeft();
        node.swap(nextNode);
        return removingTravel(nextNode);
    }

    public void printArray() {
        System.out.print("[ ");
        for (Object object: this.toArray()) {
            System.out.print(object + ", ");
        }
        System.out.println("]");
    }

}