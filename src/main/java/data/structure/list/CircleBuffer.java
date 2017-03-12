package data.structure.list;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;

public class CircleBuffer<E> {
    private Object[] elements;
    private int size;
    private int numElements;
    private int inPos;
    private int outPos;

    /**
     * Updates index to next valid position.
     * 
     * @return int
     */
    private int nextPosition(int posNow) {
        posNow++;
        if (posNow == size) posNow = 0;
        return posNow;
    }

    /**
     * Constructor.
     * 
     * @return CircleBuffer<E>
     */
    public CircleBuffer(int size) {
        this.elements = new Object[size];
        this.size = size;
        this.numElements = 0;
        this.inPos = 0;
        this.outPos = 0;
    }

    /**
     * Returns the buffer size.
     * 
     * @return int
     */
    public int size() {
        return this.size;
    }

    /**
     * Returns the number of element inside the buffer.
     * 
     * @return int
     */
    public int numElements() {
        return numElements;
    }

    /**
     * Show if there exists elements inside the BufferOverflowException.
     * 
     * @return boolean
     */
    public boolean hasElements() {
        return numElements > 0;
    }

    /**
     * Show if there the buffer is already full.
     * 
     * @return boolean
     */
    public boolean isFull() {
        return numElements == size;
    }

    /**
     * Show if there the buffer is empty.
     * 
     * @return boolean
     */
    public boolean isEmpty() {
        return numElements == 0;
    }

    /**
     * Inserts a new element inside the buffer.
     * 
     * @throws BufferOverflowException
     * @return void
     */
    public void put(E element) throws BufferOverflowException {
        if (this.isFull()) throw new BufferOverflowException();

        elements[inPos] = element;
        inPos = nextPosition(inPos);
        numElements++;
    }

    /**
     * Removes a element from the buffer.
     * 
     * @throws BufferUnderflowException
     * @return E
     */
    public E get() throws BufferUnderflowException {
        if (this.isEmpty()) throw new BufferUnderflowException();

        E temp = (E) elements[outPos];
        outPos = nextPosition(outPos);
        numElements--;
        return temp;

    }

    public void print() {
        for (int i  = 0; i < size; i++) {
            System.out.println(elements[i]);
        }
    }

}