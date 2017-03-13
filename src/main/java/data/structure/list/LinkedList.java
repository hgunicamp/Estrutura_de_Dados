package data.structure.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<E> implements Iterable<E> {
    public static final int NOT_FOUND = -1;
    protected NodeList<E> first;
    protected int size;

    public class LinkedListIterator implements Iterator<E> {
        private NodeList<E> previous;
        private NodeList<E> next;

        public LinkedListIterator() {
            this.previous = null;
            this.next = first;
        }
        
        public boolean hasNext() {
            return null != this.next;
        }

        public E next() throws NoSuchElementException {
            if (null == this.next) throw new NoSuchElementException();

            E temp = this.next.getElement();
            this.previous = this.next;
            this.next = this.next.getNext();
            return temp;
        }

        public void remove() throws NoSuchElementException {
            if (null == this.next) throw new NoSuchElementException();

            if (null != this.previous) {
                this.previous.removeNextNode();
                this.next = this.previous.getNext();
            } else {
                first = this.next.getNext();
                this.next = first;
            }
            size--;
        }
    }

    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    protected NodeList<E> goToFrontUntil(int position) {
        NodeList<E> node = this.first;
        for (int i = 1; i <= position; i++) {
            node = node.getNext();
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
        NodeList<E> node = goToFrontUntil(stopPos);

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
        NodeList<E> node = this.first;
        if (null == node) return null;
        while (null != node.getNext()) node = node.getNext();
        return node;
    }

    /**
     * Retorna o primeiro nó dentro da lista.
     * Apenas para uso interno.
     *
     * @return NodeList<E>
     */
    protected NodeList<E> getFirstNode() {
        return this.first;
    }

    /**
     * Retorna o último nó dentro da lista.
     * Apenas para uso interno.
     *
     * @return NodeList<E>
     */
    protected NodeList<E> getLasttNode() {
        NodeList<E> node = this.first;
        for (int i = 1; i < size; i++) {
            node = node.getNext();
        }
        return node;
    }

    /**
     * Construtor público.
     * Usado para criar uma nova lista vazia.
     *
     * @return LinkedList<E>
     */
    public LinkedList() {
        this.first = null;
        this.size = 0;
    }

    /**
     * Construtor privado.
     * Usado para criar uma nova lista com os dados já definidos.
     * Aenas para uso interno
     *
     * @return LinkedList<E>
     */
    private LinkedList(NodeList<E> node, int size) {
        this.first = node;
        this.size = size;
    }


    /**
     * Retorna o tamanho da lista
     *
     * @return int
     */
    public int size() {
        return this.size;
    }

    /**
     * Retorna se a lista está vazia.
     *
     * @return boolean
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Retorna o elemento de uma posição específica.
     *
     * @throws IndexOutOfBoundsException
     * @return E
     */
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
        return this.get(size-1);
    }
    
    /**
     * Substitui o elemento de uma posição específica.
     *
     * @throws IndexOutOfBoundsException
     * @return void
     */
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
    public E remove(int position) throws IndexOutOfBoundsException {
        NodeList<E> node = this.getPreviousNode(position);
        NodeList<E> temp;

        if (null != node) {
            temp = node.removeNextNode();
        } else {
            temp = this.first;
            this.first = temp.getNext();
        }

        size--;
        return temp.getElement();
    }

    /**
     * Remove o primeiro nó.
     *
     * @throws IndexOutOfBoundsException
     * @return E
     */
    public E removeFirst() throws IndexOutOfBoundsException {
        return this.remove(0);
    }

    /**
     * Remove o último nó.
     * 
     * @return E
     */
    public E removeLast() {
        return this.remove(size-1);
    }

    /**
     * Adiciona um elemento no fim da lista e retorna
     * o penúltimo elemento.
     *
     * @return void
     */
    public void add(E element) {
        NodeList<E>    node = this.getLastNode();
        NodeList<E> newNode = new NodeList<E>(element);
        if (null != node) {
            node.setNext(newNode);
        } else {
            this.first = newNode;
        }
        size++;
    }

    /**
     * Adiciona uma lista no final.
     * 
     * @return void
     */
    public void add(LinkedList<E> list) {
        if (list.size() == 0) return;

        NodeList<E>       node = this.getLastNode();
        LinkedList<E> tempList = list.clone();
        if (null != node) {
            node.setNext(tempList.getFirstNode());
        } else {
            this.first = tempList.getFirstNode();
        }
        size += tempList.size;
    }

    /**
     * Adiciona um elemento a uma posição específica na lista.
     *
     * @throws IndexOutOfBoundsException
     * @return void
     */
    public void add(int position, E element) throws IndexOutOfBoundsException {
        NodeList<E>    node = this.getPreviousNode(position);
        NodeList<E> newNode = new NodeList<E>(element);
        if (null != node) {
            node.insertAfterNode(newNode);
        } else {
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
    public void add(int position, LinkedList<E> list) throws IndexOutOfBoundsException {
        if (list.size() == 0) return;

        if (position == size-1) {
            this.add(list);
            return;
        }
        NodeList<E> node = this.getPreviousNode(position);
        LinkedList<E> tempList = list.clone();
        if (null != node) {
            tempList.getLastNode().setNext(node.getNext());
            node.setNext(tempList.getFirstNode());
        } else {
            tempList.getLastNode().setNext(this.first);
            this.first = tempList.getFirstNode();
        }
        size += list.size();
    }

    /**
     * Cria uma segunda lista com elemenatos de mesmo valor.
     *
     * @return LinkedList<E>
     */
    @Override
    public LinkedList<E> clone() {
        NodeList<E> node = this.first;
        NodeList<E> temp1 = node.clone();
        NodeList<E> temp2 = temp1;

        for (int i = 1; i < size; i++) {
            node = node.getNext();
            temp2.setNext(node.clone());
            temp2 = temp2.getNext();
        }

        return new LinkedList<E>(temp1, size);
    }

    /**
     * Busca a primeira ocorrência do elemento buscado a partir da posição especificada
     * 
     * @throws IndexOutOfBoundsException
     * @return int
     */
    public int find(int initPos, E element) throws IndexOutOfBoundsException {
        int position = NOT_FOUND;
        NodeList<E> node = this.getPreviousNode(initPos);
        if (null != node) {
            node = node.getNext();
        } else {
            node = this.first;
        }
        for (int i = initPos; i < size; i++) {
            if (node.getElement().equals(element)) {
                position = i;
                break;
            }
            node = node.getNext();
        }
        return position;
    }


    /**
     * Imprime no terminal todos os elementos da lista.
     */
    public void print() {
        System.out.println("size = " + size);
        for (E element: this) {
            System.out.println(element);
        }
    }

}
