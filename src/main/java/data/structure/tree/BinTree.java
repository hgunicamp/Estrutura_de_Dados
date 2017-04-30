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

    private NodeBinTree<E> root;
    private BinTreeOrder order;
    
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
        temp.root.promote();
        return temp;
    }

    /**
     * Returns right sub-tree.
     */
    public BinTree<E> rightBinTree() {
        BinTree<E> temp = new BinTree<E>();
        temp.root = this.root.getRightChild();
        temp.root.promote();
        return temp;
    }

    public void cut() {
        this.root.demote();
        this.root = null;
    }

    /**
     * Returns a array representation of tree
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
     */
    public NodeBinTree<E> search(E element) {
        return searchNextStep(element, this.root);
    }

    private NodeBinTree<E> searchNextStep(E element, NodeBinTree<E> nextNode) {
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
     * Rotates to the left by a specific node.
     */
    private void rotateToLeft(NodeBinTree<E> node) {
        NodeBinTree<E> minor = node.minorOnTheRight();
        if (null == minor) return;

        NodeBinTree<E> nodeRight = node.getRightChild();
        NodeBinTree<E>    father = node.getFather();
        if (nodeRight != minor) {
            NodeBinTree<E> rightOfMinor = minor.getRightChild();
            NodeBinTree<E>  minorFather = minor.getFather();
            minorFather.setLeftChild(rightOfMinor);
            if (null != rightOfMinor) rightOfMinor.setFather(minorFather);
            minor.setRightChild(nodeRight);
            nodeRight.setFather(minor);
        }

        minor.setLeftChild(node);
        minor.setFather(father);
        node.setFather(minor);
        node.setRightChild(null);
        
        if (null == father) {
            this.root = minor;
            return;
        }

        if (father.getRightChild() == node) {
            father.setRightChild(minor);
        } else {
            father.setLeftChild(minor);
        }

    }

    /**
     * Rotates to the right by a specific node.
     */
    private void rotateToRight(NodeBinTree<E> node) {
        NodeBinTree<E> largest = node.largestOnTheLeft();
        if (null == largest) return;

        NodeBinTree<E> nodeLeft = node.getLeftChild();
        NodeBinTree<E>   father = node.getFather();
        if (nodeLeft != largest) {
            NodeBinTree<E> leftOfLargest = largest.getLeftChild();
            NodeBinTree<E> largestFather = largest.getFather();
            largestFather.setRightChild(leftOfLargest);
            if (null != leftOfLargest) leftOfLargest.setFather(largestFather);
            largest.setLeftChild(nodeLeft);
            nodeLeft.setFather(largest);
        }

        largest.setRightChild(node);
        largest.setFather(father);
        node.setFather(largest);
        node.setLeftChild(null);
        
        if (null == father) {
            this.root = largest;
            return;
        }

        if (father.getLeftChild() == node) {
            father.setLeftChild(largest);
        } else {
            father.setRightChild(largest);
        }

    }

    /**
     * Removes the element inside the node without break the order.
     */
    public E remove(NodeBinTree<E> node) {
        if (node.howManyChildren() == 2) this.rotateToLeft(node);
        NodeBinTree<E> father =  node.getFather();
        NodeBinTree<E>  child = (node.getLeftChild() != null) ? 
            node.getLeftChild() : node.getRightChild();
        if (null != father) {
            if (father.getLeftChild() == node) {
                father.setLeftChild(child);
            } else {
                father.setRightChild(child);
            }
        } else {
            this.root = child;
        }
        if (null != child) child.setFather(father);
        return node.getElement();
    }

    public void printArray() {
        System.out.print("[ ");
        for (Object object: this.toArray()) {
            System.out.print(object + ", ");
        }
        System.out.println("]");
    }

}