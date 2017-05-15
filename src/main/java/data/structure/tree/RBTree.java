package data.structure.tree;

public class RBTree<E extends Comparable<E>> extends BinTree<E> {

    /**
     * Public constructor.
     * 
     * @return RBTree<E>
     */
    public RBTree() {
        super();
    }

    /**
     * Public constructor.
     * 
     * @return RBTree<E>
     */
    public RBTree(E element) {
        this.root = new NodeRBTree<E>(element);
        this.order = BinTreeOrder.INFIX;
    }

    /**
     * Inserts a new element into tree.
     * 
     * @return NodeRBTree<E>
     */
    private NodeRBTree<E> add(E element, NodeRBTree<E> root) {
        int comp = element.compareTo(root.getElement());
        NodeRBTree<E> nextNode = (NodeRBTree<E>) ((comp == -1) ? root.getLeftChild() : root.getRightChild());

        if (null != nextNode) {
            nextNode = add(element, nextNode);
        } else if (comp == -1) {
            nextNode = new NodeRBTree<E>(element, root);
            root.setLeftChild(nextNode);
        } else {
            nextNode = new NodeRBTree<E>(element, root);
            root.setRightChild(nextNode);
        }
        return nextNode;
    }

    /**
     * Makes the rebalance after insertion.
     */
    private void rebalanceAfterAdd(NodeRBTree<E> startPoint) {
        NodeRBTree<E> father = (NodeRBTree<E>) startPoint.getFather();
        if (null == father) {
            this.root = startPoint;
            return;
        }
        NodeRBTree<E> grandfather = (NodeRBTree<E>) father.getFather();
        if (null == grandfather) {
            this.root = father;
            return;
        }

        if (father.isBlack()) return;

        NodeRBTree<E> uncle = (NodeRBTree<E>) father.sibling();
        if (null != uncle && uncle.isRed()) {
            uncle.setBlack();
            father.setBlack();
            grandfather.setRed();
            startPoint = grandfather;
        } else {
            int  temp = (father.amILeftChild()) ? 2 : 0;
            temp += (startPoint.amILeftChild()) ? 1 : 0;
            switch (temp) {
                case 1:
                    father.rightInternalRotation(null);
                    father = startPoint;
                    startPoint = (NodeRBTree<E>) father.rightChild;
                case 0:
                    father.setBlack();
                    grandfather.setRed();
                    grandfather.leftInternalRotation(null);
                    break;
                case 2:
                    father.leftInternalRotation(null);
                    father = startPoint;
                    startPoint = (NodeRBTree<E>) father.leftChild;
                case 3:
                    father.setBlack();
                    grandfather.setRed();
                    grandfather.rightInternalRotation(null);
                    break;
                default:
            }
        }
        rebalanceAfterAdd(startPoint);
    }

    /**
     * Inserts a new element into tree.
     * 
     * @return NodeBinTree<E>
     */
    public NodeRBTree<E> add(E element) {
        NodeRBTree<E> startPoint;
        if (null != this.root) {
            startPoint = add(element, (NodeRBTree<E>) this.root);
            rebalanceAfterAdd(startPoint);
            ((NodeRBTree<E>) this.root).setBlack();
        } else {
            this.root = new NodeRBTree<E>(element);
            startPoint = (NodeRBTree<E>) this.root;
        }
        return startPoint;
    }

    public E remove(NodeRBTree<E> node) {
        if (null == node) return null;
        NodeRBTree<E>   leaf = (NodeRBTree<E>) removingTravel(node);
        NodeRBTree<E> father = (NodeRBTree<E>) leaf.getFather();
        if (null == father) {
            this.root = null;
            return leaf.getElement();
        }
        // Rebalance process.
        rebalanceAfterRemove(leaf);
        ((NodeRBTree<E>) this.root).setBlack();
        // Removing the leaf
        if (leaf.amILeftChild()) {
            father.setLeftChild(null);
        } else {
            father.setRightChild(null);
        }
        return leaf.getElement();
    }

    private void rebalanceAfterRemove(NodeRBTree<E> node) {
        if (null == node || node.isRed()) return;
        NodeRBTree<E> sibling = (NodeRBTree<E>) node.sibling();
        NodeRBTree<E>  father = (NodeRBTree<E>) node.getFather();
        NodeRBTree<E> nextNode;
        int opCase;
        // Case black sibling with black children
        opCase  = (null == sibling || sibling.isBlack() && !sibling.doIHaveRedChild()) ? 1 : 0;
        // Case black sibling with at least one red child
        opCase += (null != sibling && sibling.isBlack() &&  sibling.doIHaveRedChild()) ? 2 : 0;
        // Case red sibling
        opCase += (null != sibling && sibling.isRed()) ? 3 : 0;

        switch (opCase) {
            case 1:
                if (null != sibling) {
                    sibling.setRed();
                }
                if (father.isRed()) {
                    father.setBlack();
                    nextNode = null;
                } else {
                    nextNode = father;
                }
                break;
            case 2:
                boolean isLeft = node.amILeftChild();
                NodeRBTree<E> nephew = myCloseRedNephew(isLeft, sibling);
                if (null != nephew) {
                    nephew.setBlack();
                    if (isLeft) {
                        sibling.rightInternalRotation(null);
                        this.root = father.leftInternalRotation(this.root);
                    } else {
                        sibling.leftInternalRotation(null);
                        this.root = father.rightInternalRotation(this.root);
                    }
                } else {
                    if (isLeft) {
                        nephew = (NodeRBTree<E>) sibling.getRightChild();
                        this.root = father.leftInternalRotation(this.root);
                    } else {
                        nephew = (NodeRBTree<E>) sibling.getLeftChild();
                        this.root = father.rightInternalRotation(this.root);
                    }
                    nephew.setBlack();
                }
                nextNode = null;
                break;
            default:
                sibling.setBlack();
                father.setRed();
                if (node.amILeftChild()) {
                    this.root = father.leftInternalRotation(this.root);
                } else {
                    this.root = father.rightInternalRotation(this.root);
                }
                nextNode = node;
                break;
        }
        rebalanceAfterRemove(nextNode);
    }

    private NodeRBTree<E> myCloseRedNephew(boolean isLeft, NodeRBTree<E> sibling) {
        NodeRBTree<E> nephew = (NodeRBTree<E>) ((isLeft) ? sibling.getLeftChild() : sibling.getRightChild());
        return (nephew.isRed()) ? nephew: null;
    }

}
