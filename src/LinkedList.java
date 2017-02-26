public class LinkedList<E> {
    private Node<E> first;
    private int size;

    /**
     * Retorna o penúltimo nó dentro da lista.
     * Apenas para uso interno.
     *
     * @return node
     */
    private Node<E> getPreviousNode(int position) throws IndexOutOfBoundsException {
        if (position >= size) throw new IndexOutOfBoundsException();

        Node<E> node = this.first;
        int stopPos = position - 1;

        for (int i = 0; i < stopPos; i++) {
            node = node.getNext();
        }

        if (stopPos < 0) node = null;

        return node;
    }

    /**
     * Retorna o último nó dentro da lista.
     * Apenas para uso interno.
     *
     * @return node
     */
    private Node<E> getLastNode() {
        Node<E> node = this.first;
        if (null == node) return null;
        while (null != node.getNext()) node = node.getNext();
        return node;
    }

    /**
     * Retorna o primeiro nó dentro da lista.
     * Apenas para uso interno.
     *
     * @return node
     */
    private Node<E> getFirstNode() {
        return this.first;
    }

    /**
     * Construtor público.
     * Usado para criar uma nova lista vazia.
     *
     * @return LinkedList
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
     * @return LinkedList
     */
    private LinkedList(Node<E> node, int size) {
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
     * @return E
     */
    public E get(int position) throws IndexOutOfBoundsException {
        Node<E> node = this.getPreviousNode(position);
        return (null != node) ? node.getNext().getElement() : this.first.getElement();
    }
    
    /**
     * Substitui o elemento de uma posição específica.
     *
     * @return void
     */
    public void set(int position, E element) throws IndexOutOfBoundsException {
        Node<E> node = this.getPreviousNode(position);
        node = (null != node) ? node.getNext() : this.first;
        node.setElement(element);
    }
    
    /**
     * Removo o nó de uma posição específica, retornando o seu elemento.
     *
     * @return E
     */
    public E remove(int position) throws IndexOutOfBoundsException {
        Node<E> node = this.getPreviousNode(position);
        Node<E> temp;

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
     * @return E
     */
    public E removeFirst() throws IndexOutOfBoundsException {
        return this.remove(0);
    }

    /**
     * Adiciona um elemento no fim da lista.
     *
     * @return void
     */
    public void add(E element) {
        Node<E> node = this.getLastNode();
        if (null != node) {
            node.setNext(new Node<E>(element));
        } else {
            this.first = new Node<E>(element);
        }
        size++;
    }

    /**
     * Adiciona um elemento a uma posição específica na lista.
     *
     * @return void
     */
    public void add(int position, E element) throws IndexOutOfBoundsException {
        Node<E> node = this.getPreviousNode(position);
        if (null != node) {
            node.insertAfterNode(new Node<E>(element));
        } else {
            node = new Node<E>(element);
            node.setNext(this.first);
            this.first = node;
        }
        size++;
    }

    /**
     * Adiciona todos os elementos de outra lista em uma posição específica na lista.
     *
     * @return void
     */
    public void add(int position, LinkedList<E> list) throws IndexOutOfBoundsException {
        Node<E> node = this.getPreviousNode(position);
        LinkedList<E> tempList = list.clone();
        if (null != node) {
            node.insertAfterNode(tempList.getFirstNode());
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
        Node<E> node = this.first;
        Node<E> temp1 = node.clone();
        Node<E> temp2 = temp1;

        for (int i = 1; i < size; i++) {
            node = node.getNext();
            temp2.setNext(node.clone());
            temp2 = temp2.getNext();
        }

        return new LinkedList<E>(temp1, size);
    }

    /**
     * Imprime no terminal todos os elementos da lista.
     */
    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.println(this.get(i));
        }
    }


}
