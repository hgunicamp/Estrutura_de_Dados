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
     * 
     * @return int
     */
    public int howManyChildren() {
        int result = 0;
        if (null != leftChild)  result++;
        if (null != rightChild) result++;
        return result;
    }

    /**
     * Informs the number of descendents.
     * 
     * @return int
     */
    public int familySize() {
        int temp = 1;
        if (null != leftChild)  temp +=  leftChild.familySize();
        if (null != rightChild) temp += rightChild.familySize();
        return temp;
    }

    /**
     * Informs the max depth.
     * 
     * @return int
     */
    public int maxDepth() {
        int  leftDepth = (null != leftChild)  ?  leftChild.maxDepth(): 0;
        int rightDepth = (null != rightChild) ? rightChild.maxDepth(): 0;
        return  1 + ((leftDepth > rightDepth) ? leftDepth : rightDepth);
    }

    /**
     * Returns the sibling if it exists.
     * 
     * @return NodeBinTree<E>
     */
    public NodeBinTree<E> sibling() {
        if (null == father) return null;
        return (this != father.leftChild) ? father.leftChild : father.rightChild;
    }

    /**
     * Returns the node with minor content at right of this.
     * 
     * @return NodeBinTree<E>
     */
    public NodeBinTree<E> minorOnTheRight() {
        if (null == rightChild) return null;
        NodeBinTree<E> next = rightChild;
        while (null != next.leftChild) next = next.leftChild;
        return next;
    }

    /**
     * Returns the node with largest content on the left of this.
     * 
     * @return NodeBinTree<E>
     */
    public NodeBinTree<E> largestOnTheLeft() {
        if (null == leftChild) return null;
        NodeBinTree<E> next = leftChild;
        while (null != next.rightChild) next = next.rightChild;
        return next;
    }

    /**
     * Returns the node position in an array based representation.
     * 
     * @return int
     */
    public int position() {
        if (this.isRoot()) return 1;
        int temp = 2 * father.position();
        return (amILeftChild()) ? temp : temp + 1;
    }

    /**
     * Informs if this node is a left child.
     * 
     * @return boolean
     */
    protected boolean amILeftChild() {
        return  father.leftChild == this;
    }

    /**
     * Performs a internal left rotation.
     * 
     * @return NodeBinTree<E>
     */
    public NodeBinTree<E> leftInternalRotation(NodeBinTree<E> root) {
        NodeBinTree<E> A = this;
        NodeBinTree<E> B = this.rightChild;
        NodeBinTree<E> C = B.leftChild;
        NodeBinTree<E> father = A.father;
        B.father = father;
        A.father = B;
        B.leftChild = A;
        A.rightChild = C;
        if (null != C) C.father = A;
        if (null != father) {
            if (father.leftChild == A) {
                father.leftChild = B;
            } else {
                father.rightChild = B;
            }
        }
        return (this != root) ? root: B;
    }

    /**
     * Performs a internal right rotation.
     * 
     * @return NodeBinTree<E>
     */
    public NodeBinTree<E> rightInternalRotation(NodeBinTree<E> root) {
        NodeBinTree<E> B = this;
        NodeBinTree<E> A = this.leftChild;
        NodeBinTree<E> C = A.rightChild;
        NodeBinTree<E> father = B.father;
        A.father = father;
        B.father = A;
        A.rightChild = B;
        B.leftChild = C;
        if (null != C) C.father = B;
        if (null != father) {
            if (father.leftChild == B) {
                father.leftChild = A;
            } else {
                father.rightChild = A;
            }
        }
        return (this != root) ? root: A;
    }

    /**
     * Swaps the content of two nodes.
     */
    public void swap(NodeBinTree<E> other) {
        E temp = other.element;
        other.element = element;
        element = temp;
    }

    /**
     * Become this node a root node.
     */
    public void promote() {
        if (null != this.demote) return;
        this.demote = this.father;
        this.father = null;
    }

    /**
     * Brings this node back to condition before it became a root node.
     */
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