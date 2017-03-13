package data.structure.list;

public class NodeList<E> {
    private E element;
    private NodeList<E> next;
    private NodeList<E> previous;

    // Constructor
    public NodeList(E e) {
        this.element = e;
        this.previous = null;
        this.next = null;
    }

    // Getters and Setters
    public E getElement() {
        return element;
    }

    public void setElement(E e) {
        this.element = e;
    }

    public NodeList<E> getNext() {
        return this.next;
    }

    public void setNext(NodeList<E> node) {
        this.next = node;
    }

    public NodeList<E> getPrevious() {
        return this.previous;
    }

    public void setPrevious(NodeList<E> node) {
        this.previous = node;
    }

    public boolean isLast() {
        return null == this.next;
    }

    public boolean isFirst() {
        return null == this.previous;
    }

    public NodeList<E> removeNextNode() {
        if (this.isLast()) return null;

        NodeList<E> temp = this.next;
        this.next = temp.getNext();
        if (null != temp.getPrevious() && null != this.next) {
            this.next.setPrevious(this);
        }
        return temp; 
    }

    public NodeList<E> removePreviousNode() {
        if (this.isFirst()) return null;

        NodeList<E> temp = this.previous;
        this.previous = temp.getPrevious();
        if (null != this.previous) {
            this.previous.setNext(this);
        }
        return temp;
    }

    public void insertAfterNode(NodeList<E> node) {
        if (!this.isLast()) {
            node.setNext(this.next);
        }
        this.next = node;
    }

    public void insertBeforeNode(NodeList<E> node) {
        if (!this.isFirst()) {
            node.setPrevious(this.previous);
        }
        this.previous = node;
    }

    @Override
    public NodeList<E> clone() {
        return new NodeList<E>(this.element);
    }

    public boolean equals(NodeList<E> other) {
        return this.element.equals(other.getElement());
    }

}
