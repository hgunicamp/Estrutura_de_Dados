package data.structure.database.indexes;

import data.structure.tree.NodeBinTree;
import data.structure.tree.NodeRBTree;
import data.structure.tree.RBTree;
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
        return result + 1;
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
    }

    /**
     * Removes a index from the indexTree.
     */
    public void removeIndex(int hashCode) {
        this.modified = true;
        NodeRBTree<IndexElement> node = (NodeRBTree<IndexElement>) indexTree.search(new IndexElement(hashCode));
        this.indexTree.remove(node);
    }

}