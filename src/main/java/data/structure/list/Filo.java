package data.structure.list;

import java.util.NoSuchElementException;

public class Filo<E> {
    private DoublyLinkedList<E> filo;

    /**
     * Contrutor
     * 
     * @return Filo<E>
     */
    public Filo() {
        this.filo = new DoublyLinkedList<E>();
    }

    /**
     * Informs if thr filo is empty.
     * 
     * @return boolean
     */
    public boolean isEmpty() {
        return filo.isEmpty();
    }

    /**
     * Informs the filo size.
     * 
     * @return int
     */
    public int size() {
        return filo.size();
    }

    /**
     * Inserts a new element in the Filo.
     * 
     * @return void
     */
    public void push(E element) {
        this.filo.add(element);
    }

    /**
     * Removes the last element from the filo.
     * 
     * @return E
     */
    public E pop() throws NoSuchElementException {
        return this.filo.removeLast();
    }
}