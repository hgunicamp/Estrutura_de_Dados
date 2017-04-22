package data.structure.tree;

public class NodeBinTree<E extends Comparable<E>> {
    private E element;
    private NodeBinTree<E>     father;
    private NodeBinTree<E>  leftChild;
    private NodeBinTree<E> rightChild;

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

}