public class Node<E> {
    private E element;
    private Node<E> next;

    // Constructor
    public Node(E e) {
        this.element = e;
        this.next = null;
    }

    // Getters and Setters
    public E getElement() {
        return element;
    }

    public void setElement(E e) {
        this.element = e;
    }

    public Node<E> getNext() {
        return this.next;
    }

    public void setNext(Node<E> node) {
        this.next = node;
    }

    public boolean isLast() {
        return null == this.next;
    }

    public Node<E> removeNextNode() {
        if (this.isLast()) return null;

        Node<E> temp = this.next;
        this.next = temp.getNext();
        return temp; 
    }

    public void insertAfterNode(Node<E> node) {
        if (this.isLast()) {
            this.next = node;
            return;
        }

        Node<E> theirLast = node;

        while (!theirLast.isLast()) {
            theirLast = theirLast.getNext();
        }

        theirLast.setNext(this.next);
        this.next = node;
    }

    @Override
    public Node<E> clone() {
        return new Node<E>(this.element);
    }
}