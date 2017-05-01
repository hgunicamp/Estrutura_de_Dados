package data.structure.tree;

public class NodeBinTree<E extends Comparable<E>> {
    protected E element;
    protected NodeBinTree<E>     father;
    protected NodeBinTree<E>  leftChild;
    protected NodeBinTree<E> rightChild;
    protected NodeBinTree<E> demote;

    // Getters and Setters
    public E getElement() {
        return this.element;
    }
    public void setElement(E element) {
        this.element = element;
    }

    public NodeBinTree<E> getFather() {
        return this.father;
    }
    public void setFather(NodeBinTree<E> father) {
        this.father = father;
    }

    public NodeBinTree<E> getLeftChild() {
        return this.leftChild;
    }
    public void setLeftChild(NodeBinTree<E> lChild) {
        this.leftChild = lChild;
    }

    public NodeBinTree<E> getRightChild() {
        return this.rightChild;
    }
    public void setRightChild(NodeBinTree<E> rChild) {
        this.rightChild = rChild;
    }

    /**
     * Public Constructor
     * 
     * @return NodeBinTree<E>
     */
    public NodeBinTree(E element) {
        this.element = element;
        this.father  = null;
        this.leftChild  = null;
        this.rightChild = null;
        this.demote = null;
    }

    /**
     * Public Constructor
     * 
     * @return NodeBinTree<E>
     */
    public NodeBinTree(E element, NodeBinTree<E> father) {
        this.element = element;
        this.father  = father;
        this.leftChild  = null;
        this.rightChild = null;
        this.demote = null;
    }

    /**
     * Public Constructor
     * 
     * @return NodeBinTree<E>
     */
    public NodeBinTree(E element, NodeBinTree<E> father,
                       NodeBinTree<E> lChild) {
        this.element = element;
        this.father  = father;
        this.leftChild  = lChild;
        this.rightChild = null;
        this.demote = null;
    }

    /**
     * Public Constructor
     * 
     * @return NodeBinTree<E>
     */
    public NodeBinTree(E element, NodeBinTree<E> father,
           NodeBinTree<E> lChild, NodeBinTree<E> rChild) {
        this.element = element;
        this.father  = father;
        this.leftChild  = lChild;
        this.rightChild = rChild;
        this.demote = null;
    }

    /**
     * Swap the node content with its father.
     * 
     * @return void
     */
    public void swapFather() {
        E temp = this.element;
        this.element = this.father.getElement();
        this.father.setElement(temp);
    }

    /**
     * Swap the node content with its left child.
     * 
     * @return void
     */
    public void swapLeftChild() {
        E temp = this.element;
        this.element = this.leftChild.getElement();
        this.leftChild.setElement(temp);
    }

    /**
     * Swap the node content with its right child.
     * 
     * @return void
     */
    public void swapRightChild() {
        E temp = this.element;
        this.element = this.rightChild.getElement();
        this.rightChild.setElement(temp);
    }

    /**
     * Informs if this node is the root of the tree.
     * 
     * @return boolean
     */
    public boolean isRoot() {
        return null == this.father;
    }

    /**
     * Informs if this node is one leaf of the tree.
     * 
     * @return boolean
     */
    public boolean isLeaf() {
        return null == this.leftChild &&
               null == this.rightChild;
    }

    /**
     * Returns the number of children this node has.
     */
    public int howManyChildren() {
        int result = 0;
        if (null != leftChild)  result++;
        if (null != rightChild) result++;
        return result;
    }

    /**
     * Informs the number of descendents.
     */
    public int familySize() {
        int temp = 1;
        if (null != leftChild)  temp +=  leftChild.familySize();
        if (null != rightChild) temp += rightChild.familySize();
        return temp;
    }

    /**
     * Informs the max depth.
     */
    public int maxDepth() {
        int  leftDepth = (null != leftChild)  ?  leftChild.maxDepth(): 0;
        int rightDepth = (null != rightChild) ? rightChild.maxDepth(): 0;
        return  1 + ((leftDepth > rightDepth) ? leftDepth : rightDepth);
    }

    /**
     * Returns the sibling if it exists.
     */
    public NodeBinTree<E> sibling() {
        if (null == father) return null;
        return (this != father.leftChild) ? father.leftChild : father.rightChild;
    }

    /**
     * Returns the node with minor content at right of this.
     */
    public NodeBinTree<E> minorOnTheRight() {
        if (null == rightChild) return null;
        NodeBinTree<E> next = rightChild;
        while (null != next.leftChild) next = next.leftChild;
        return next;
    }

    /**
     * Returns the node with largest content on the left of this.
     */
    public NodeBinTree<E> largestOnTheLeft() {
        if (null == leftChild) return null;
        NodeBinTree<E> next = leftChild;
        while (null != next.rightChild) next = next.rightChild;
        return next;
    }

    /**
     * Returns the node position in an array based representation.
     */
    public int position() {
        if (this.isRoot()) return 1;
        int temp = 2 * father.position();
        return (amILeftChild()) ? temp : temp + 1;
    }

    protected boolean amILeftChild() {
        return  father.leftChild == this;
    }

    /**
     * Swaps the content of two nodes.
     */
    public void swap(NodeBinTree<E> other) {
        E temp = other.element;
        other.element = element;
        element = temp;
    }
    public void promote() {
        if (null != this.demote) return;
        this.demote = this.father;
        this.father = null;
    }

    public void demote() {
        if (null == this.demote) return;
        this.father = this.demote;
        this.demote = null;
    }

    @Override
    public String toString() {
        return element.toString();
    }

}