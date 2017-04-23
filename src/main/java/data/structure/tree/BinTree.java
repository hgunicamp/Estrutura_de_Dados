package data.structure.tree;

public class BinTree<E extends Comparable<E>> {
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
    int size() {
        if (null == this.root) return 0;
        return root.familySize();
    }

    /**
     * Informs the maximum depth of this tree.
     */
    int maxDepth() {
        if (null == this.root) return 0;
        return this.root.maxDepth();
    }

    private void add(E element, NodeBinTree<E> root) {
        NodeBinTree<E> nextNode;
        int comp = root.getElement().compareTo(element);
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

}