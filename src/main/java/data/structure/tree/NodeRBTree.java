package data.structure.tree;

public class NodeRBTree<E extends Comparable<E>> extends NodeBinTree<E> {
    private boolean red;

    // Getters and Setters
    public boolean isRed() {
        return red;
    }

    public void setRed() {
        this.red = true;
    }

    public boolean isBlack() {
        return !red;
    }

    public void setBlack() {
        this.red = false;
    }

    // Constructors
    /**
     * Public Constructor
     * 
     * @return NodeRBTree<E>
     */
    public NodeRBTree(E element) {
        super(element);
        this.red = false;
    }

    /**
     * Public Constructor
     * 
     * @return NodeRBTree<E>
     */
    public NodeRBTree(E element, NodeRBTree<E> father) {
        super(element, father);
        this.red = true;
    }

    /**
     * Informs the how many black nodes I need to cross until get the null node.
     * 
     * @return int
     */
    public int blackHeight() {
        int leftBlackHeight = (null != leftChild) ? 
                ((NodeRBTree<E>)leftChild).blackHeight() : 1;
        int rightBlackHeight = (null != rightChild) ? 
                ((NodeRBTree<E>)rightChild).blackHeight() : 1;
        return ((this.isBlack()) ? 1 : 0) +
                Math.max(leftBlackHeight, rightBlackHeight);
    }

    /**
     * Informs if the sibling Node is black.
     * 
     * @return boolean
     */
    public boolean isMySiblingBlack() {
        NodeRBTree<E> sibling = (NodeRBTree<E>) this.sibling();
        return (null != sibling) ? sibling.isBlack() : true;
    }

    /**
     * Intorms if the father sibling is black.
     * 
     * @return boolean
     */
    public boolean isMyUncleBlack() {
        return (null != father) ? ((NodeRBTree<E>) father).isMySiblingBlack() : true;
    }

    /**
     * Informs if I have at least one red child.
     */
    public boolean doIHaveRedChild() {
        boolean result = false;
        if (null != leftChild) {
            result |= ((NodeRBTree<E>) leftChild).red;
        }
        if (null != rightChild) {
            result |= ((NodeRBTree<E>) rightChild).red;
        }
        return result;
    }

    @Override
    public String toString() {
        return "[ " + element.toString() + ", " + ((red) ? "red" : "black") + "]"; 
    }

}