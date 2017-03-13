package data.structure.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<E> extends LinkedList<E> {
    protected NodeList<E> last;

    public class DoublyLinkedListBackwardIterator implements Iterator<E> {
        private NodeList<E> previous;
        private NodeList<E> next;

        public DoublyLinkedListBackwardIterator() {
            this.next = null;
            this.previous = last;
        }

        public boolean hasNext() {
            return null != previous;
        }

        public E next() throws NoSuchElementException {
            if (null == this.previous) throw new NoSuchElementException();

            NodeList<E> temp = this.previous;
            this.next = this.previous;
            this.previous = temp.getPrevious();
            return temp.getElement();
        }

        public void remove() throws NoSuchElementException {
            if (null == this.previous) throw new NoSuchElementException();

            if (null != this.next) {
                this.next.removePreviousNode();
                this.previous = this.next.getPrevious();
            } else {
                last = this.previous.getPrevious();
                this.previous = last;
            }
            size--;
        }

    }

    public Iterator<E> backIterator() {
        return new DoublyLinkedListBackwardIterator();
    }

    protected NodeList<E> goToBackUntil(int position) {
        NodeList<E> node = this.last;
        for (int i = size - 2; i >= position; i--) {
            node = node.getPrevious();
        }
        return node;
    }

    /**
     * Retorna o nó anterior à 'position' dentro da lista.
     * Apenas para uso interno.
     *
     * @throws IndexOutOfBoundsException
     * @return NodeList<E>
     */
    private NodeList<E> getPreviousNode(int position) throws IndexOutOfBoundsException {
        if (position >= size) throw new IndexOutOfBoundsException();

        int stopPos = position - 1;
        boolean forwardDir = stopPos < ((this.size / 2) - 1);

        NodeList<E> node = (forwardDir) ? this.goToFrontUntil(stopPos) : this.goToBackUntil(stopPos);

        if (stopPos < 0) node = null;

        return node;
    }

    /**
     * Retorna o último nó dentro da lista.
     * Apenas para uso interno.
     *
     * @return NodeList<E>
     */
    private NodeList<E> getLastNode() {
        return this.last;
    }

    /**
     * Construtor público.
     * Usado para criar uma nova lista vazia.
     *
     * @return DoublyLinkedList
     */
    public DoublyLinkedList() {
        super();
        this.last = null;
    }

    /**
     * Construtor público.
     * Usado para criar uma nova lista através de um array.
     *
     * @return LinkedList<E>
     */
    public DoublyLinkedList(Object[] elements) {
        this.first = null;
        this.last = null;
        this.size = 0;

        for (Object element: elements) {
            this.add((E) element);
        }
    }

    /**
     * Construtor privado.
     * Usado para criar uma nova lista com os dados já definidos.
     * Aenas para uso interno
     *
     * @return DoublyLinkedList<E>
     */
    private DoublyLinkedList(NodeList<E> first, NodeList<E> last, int size) {
        this.first = first;
        this.last = last;
        this.size = size;
    }

    /**
     * Retorna o elemento de uma posição específica.
     *
     * @throws IndexOutOfBoundsException
     * @return E
     */
    @Override
    public E get(int position) throws IndexOutOfBoundsException {
        NodeList<E> node = this.getPreviousNode(position);
        return (null != node) ? node.getNext().getElement() : this.first.getElement();
    }

    /**
     * Retorna o primeiro elemento da lista.
     * 
     * @return E''
     */
    public E getFirst() {
        if (size == 0) { return null; }
        return this.first.getElement();
    }

    /**
     * Retorna o último elemento da lista.
     * 
     * @return E''
     */
    public E getLast() {
        if (size == 0) { return null; }
        return this.last.getElement();
    }
    
    /**
     * Substitui o elemento de uma posição específica.
     *
     * @throws IndexOutOfBoundsException
     * @return void
     */
    @Override
    public void set(int position, E element) throws IndexOutOfBoundsException {
        NodeList<E> node = this.getPreviousNode(position);
        node = (null != node) ? node.getNext() : this.first;
        node.setElement(element);
    }

    /**
     * Remove o nó de uma posição específica, retornando o seu elemento.
     *
     * @throws IndexOutOfBoundsException
     * @return E
     */
    @Override
    public E remove(int position) throws IndexOutOfBoundsException {
        if (position == size-1) return this.removeLast();
        if (position == 0)      return this.removeFirst();

        NodeList<E> node = this.getPreviousNode(position);

        size--;
        return node.removeNextNode().getElement();
    }

    /**
     * Remove o primeiro nó.
     *
     * @throws NoSuchElementException
     * @return E
     */
    @Override
    public E removeFirst() throws NoSuchElementException {
        if (size == 0) throw new NoSuchElementException();

        size--;
        NodeList<E> node = this.first;
        this.first = node.getNext();
        if (size != 0) {
            this.first.setPrevious(null);
        } else {
            this.last = null;
        }
        return node.getElement();
    }

    /**
     * Remove o último nó.
     * 
     * @return E
     */
    @Override
    public E removeLast() throws NoSuchElementException {
        if (size == 0) throw new NoSuchElementException();

        size--;
        NodeList<E> node = this.last;
        this.last = node.getPrevious();
        if (size != 0) {
            this.last.setNext(null);
        } else {
            this.first = null;
        }
        return node.getElement();
    }


    @Override
    public void add(E element) {
        NodeList<E> newNode = new NodeList<E>(element);
        if (null != this.first) {
            newNode.setPrevious(this.last);
            this.last.setNext(newNode);
            this.last = newNode;
        } else {
            this.first = newNode;
            this.last  = newNode;
        }
        size++;
    }

    /**
     * Adiciona uma lista no final.
     * 
     * @return void
     */
    public void add(DoublyLinkedList<E> list) {
        if (list.size() == 0) return;

        DoublyLinkedList<E> tempList = list.clone();
        if (size != 0) {
            this.last.setNext(tempList.getFirstNode());
            tempList.getFirstNode().setPrevious(this.last);
        } else {
            this.first = tempList.getFirstNode();
        }
        this.last = tempList.getLastNode();
        size += tempList.size;
    }

    /**
     * Adiciona um elemento a uma posição específica na lista.
     *
     * @throws IndexOutOfBoundsException
     * @return void
     */
    @Override
    public void add(int position, E element) throws IndexOutOfBoundsException {
        if (position == size-1) {
            this.add(element);
            return;
        }

        NodeList<E>    node = this.getPreviousNode(position);
        NodeList<E> newNode = new NodeList<E>(element);
        if (null != node) {
            newNode.setPrevious(node);
            node.getNext().setPrevious(newNode);
            node.insertAfterNode(newNode);
        } else {
            this.first.setPrevious(newNode);
            newNode.setNext(this.first);
            this.first = newNode;
        }
        size++;
    }

    /**
     * Adiciona todos os elementos de outra lista em uma posição específica na lista.
     *
     * @throws IndexOutOfBoundsException
     * @return void
     */
    public void add(int position, DoublyLinkedList<E> list) throws IndexOutOfBoundsException {
        if (list.size() == 0) return;

        if (position == size-1) {
            this.add(list);
            return;
        }
        NodeList<E> node = this.getPreviousNode(position);
        DoublyLinkedList<E> tempList = list.clone();
        if (null != node) {
            tempList.getLastNode().setNext(node.getNext());
            node.getNext().setPrevious(tempList.getLasttNode());
            tempList.getFirstNode().setPrevious(node);
            node.setNext(tempList.getFirstNode());
        } else {
            tempList.getLastNode().setNext(this.first);
            this.first.setPrevious(tempList.getLastNode());
            this.first = tempList.getFirstNode();
        }
        size += tempList.size();
    }

    /**
     * Cria uma segunda lista com elemenatos de mesmo valor.
     *
     * @return DoublyLinkedList<E>
     */
    @Override
    public DoublyLinkedList<E> clone() {
        NodeList<E> node = this.first;
        NodeList<E> temp1 = node.clone();
        NodeList<E> temp2 = temp1;

        for (int i = 1; i < size; i++) {
            node = node.getNext();
            temp2.setNext(node.clone());
            temp2.getNext().setPrevious(temp2);;
            temp2 = temp2.getNext();
        }

        return new DoublyLinkedList<E>(temp1, temp2, size);
    }


}
