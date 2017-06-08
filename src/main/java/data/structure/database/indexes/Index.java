package data.structure.database.indexes;

import data.structure.tree.NodeBinTree;
import data.structure.tree.NodeRBTree;
import data.structure.tree.RBTree;

import java.io.IOException;

import data.structure.database.files.FileService;

public class Index {

    protected RBTree<IndexElement> indexTree;
    protected String indexFile;
    protected String dataFile;
    protected int nextPosToWrite;
    protected boolean modified;

    // Getters and Setters.
    public boolean isModified() {
        return modified;
    }

    public RBTree<IndexElement> getIndexTree() {
        return this.indexTree;
    }

    // Constructors
    /**
     * Returns a Index to a data file wich already exists in the system.
     */
    public Index(String indexFile, String dataFile) {
        this(indexFile, dataFile, false);
    }

    /**
     * Returns a Index to a new data file.
     */
    public Index(String indexFile, String dataFile, boolean newFile) {
        this.indexFile = indexFile;
        this.dataFile = dataFile;
        this.indexTree = new RBTree<IndexElement>();
        this.modified = false;
        this.nextPosToWrite = 0;
        if (!newFile) {
            try {
                FileService.readIndexFile(indexFile, indexTree);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(-1);
            }
            this.nextPosToWrite = findNextPosition();
        }
    }

    protected int findNextPosition() {
        int result = 0;
        for (NodeBinTree<IndexElement> indexNode: indexTree) {
            IndexElement temp = indexNode.getElement();
            int next = temp.getInitPosition() + temp.getSize();
            if (result < next) {
                result = next;
            }
        }
        return result;
    }

    /**
     * Updates the index file if it's needed.
     */
    public void updateIndexFile() {
        if (!modified) return;
        try {
            FileService.writeIndexFile(indexFile, indexTree);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * Searches a IndexElement in the indexTree by its hashCode.
     */
    public IndexElement search(int hashCode) {
        NodeBinTree<IndexElement> node = this.indexTree.search(new IndexElement(hashCode));
        return (null != node) ? node.getElement() : null; 
    }

    /**
     * Insert a new index in the indexTree.
     */
    public void addIndex(int hashCode, int position, int size) {
        this.modified = true;
        this.indexTree.add(new IndexElement(hashCode, position, size));
        int nextPosToWriteCandidate = position + size;
        if (this.nextPosToWrite < nextPosToWriteCandidate) {
            this.nextPosToWrite = nextPosToWriteCandidate;
        }
    }

    /**
     * Removes a index from the indexTree.
     */
    public void removeIndex(int hashCode) {
        NodeRBTree<IndexElement> node = (NodeRBTree<IndexElement>) indexTree.search(new IndexElement(hashCode));
        if (null != node) {
            this.modified = true;
            this.indexTree.remove(node);
        }
    }

    /**
     * Returns the data pointed by hash.
     */
    public Object getData(int hashCode) throws IOException, ClassNotFoundException {
        IndexElement index = search(hashCode);
        if (null == index) return null;
        byte[] buffer = FileService.readDataFile(this.dataFile, index.getInitPosition(), index.getSize());
        return FileService.convertToObject(buffer);
    }

    /**
     * Writes a tupple in the data file.
     */
    public void setData(Object tupple) throws IOException {
        int hashCode = tupple.hashCode();
        byte[] buffer = FileService.convertToBytes(tupple);
        int size = buffer.length;
        IndexElement index = search(hashCode);
        if (null == index) {
            index = new IndexElement(hashCode);
            indexTree.add(index);
        }
        index.setInitPosition(this.nextPosToWrite);
        index.setSize(size);
        FileService.writeDataFile(this.dataFile, this.nextPosToWrite, size, buffer);
        this.modified = true;
        this.nextPosToWrite += size;
    }

    /**
     * Removes a reference to a tupple.
     * It will become a tombstone inside the data file.
     */
    public boolean remove(int hashCode) {
        NodeRBTree<IndexElement> node = (NodeRBTree<IndexElement>) this.indexTree.search(new IndexElement(hashCode));
        if (null == node) return false;
        this.indexTree.remove(node);
        this.modified = true;
        return true;
    }

    /**
     * Generates backups of current data and index files.
     */
    public void backup() throws IOException {
        String backupDataFile  = this.dataFile  + ".back";
        String backupIndexFile = this.indexFile + ".back";
        RBTree<IndexElement> newIndexTree = new RBTree<IndexElement>();
        int initPos = 0;
        for (NodeBinTree<IndexElement> indexNode: this.indexTree) {
            IndexElement index = indexNode.getElement();
            IndexElement newIndex = index.clone();
            int size = index.getSize();
            byte[] buffer = FileService.readDataFile(this.dataFile, index.getInitPosition(), size);
            FileService.writeDataFile(backupDataFile, initPos, size, buffer);
            newIndex.setInitPosition(initPos);
            newIndex.setSize(size);
            newIndexTree.add(newIndex);
            initPos +=size;
        }
        FileService.writeIndexFile(backupIndexFile, newIndexTree);
    }

}