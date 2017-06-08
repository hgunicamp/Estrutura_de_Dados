package data.structure.database.indexes;

import java.io.Serializable;

public class IndexElement implements Comparable<IndexElement>, Serializable {

    protected int hashCode;
    protected int initPosition;
    protected int size;

    // Getters and Setters.
    public int getHashCode() {
        return this.hashCode;
    }

    public void setHashCode(int hashCode) {
        this.hashCode = hashCode;
    }

    public int getInitPosition() {
        return this.initPosition;
    }

    public void setInitPosition(int initPos) {
        this.initPosition = initPos;
    }

    public int getSize(){
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    // Constructors
    public IndexElement() {
        this.hashCode = 0;
        this.initPosition = 0;
        this.size = 0;
    }

    public IndexElement(int hashCode) {
        this();
        this.hashCode = hashCode;
    }

    public IndexElement(int hashCode, int initPos, int size) {
        this.hashCode = hashCode;
        this.initPosition = initPos;
        this.size = size;
    }

    public int compareTo(IndexElement other) {
        return (this.hashCode < other.hashCode) ? -1 : (this.hashCode > other.hashCode) ? 1 : 0;
    } 

    @Override
    public IndexElement clone() {
        return new IndexElement(this.hashCode);
    }
}