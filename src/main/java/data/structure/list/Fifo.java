package data.structure.list;

public class Fifo<E> {
    private LinkedList<E> fifo;

    /**
     * Contrutor
     * 
     * @return Fifo<E>
     */
    public Fifo() {
        this.fifo = new LinkedList<E>();
    }

    /**
     * Informs if the fifo is empty.
     * 
     * @return boolean
     */
    public boolean isEmpty() {
        return fifo.size() == 0;
    }

    /**
     * Informs the fifo size.
     * 
     * @return int
     */
    public int size() {
        return fifo.size();
    }

    /**
     * Inserts a new element in the Fifo.
     * 
     * @return void
     */
    public void put(E element) {
        this.fifo.add(element);
    }

    /**
     * Removes the first element from the fifo.
     * 
     * @return E
     */
    public E get() throws IndexOutOfBoundsException {
        return fifo.removeFirst();
    }

}