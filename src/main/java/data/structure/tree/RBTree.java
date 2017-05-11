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
    private void RBTreeRebalance(NodeRBTree<E> startPoint) {
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

        NodeRBTree<E>       uncle = (NodeRBTree<E>) father.sibling();
        if (null != uncle && uncle.isRed()) {
            uncle.setBlack();
            father.setBlack();
            grandfather.setRed();
            startPoint = grandfather;
        } else {
            int temp = (father.amILeftChild()) ? 2 : 0;
            temp += (startPoint.amILeftChild()) ? 1 : 0;
            switch (temp) {
                case 1:
                    father.rightInternalRotation();
                    father = startPoint;
                    startPoint = (NodeRBTree<E>) father.rightChild;
                case 0:
                    father.setBlack();
                    grandfather.setRed();
                    grandfather.leftInternalRotation();
                    break;
                case 2:
                    father.leftInternalRotation();
                    father = startPoint;
                    startPoint = (NodeRBTree<E>) father.leftChild;
                case 3:
                    father.setBlack();
                    grandfather.setRed();
                    grandfather.rightInternalRotation();
                    break;
                default:
            }
        }
        RBTreeRebalance(startPoint);
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
            RBTreeRebalance(startPoint);
            ((NodeRBTree<E>) this.root).setBlack();
        } else {
            this.root = new NodeRBTree<E>(element);
            startPoint = (NodeRBTree<E>) this.root;
        }
        return startPoint;
    }
}