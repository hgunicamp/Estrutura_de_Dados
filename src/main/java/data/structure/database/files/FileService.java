package data.structure.database.files;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import data.structure.database.indexes.Index;
import data.structure.tree.RBTree;
import data.structure.tree.NodeBinTree;
import data.structure.tree.NodeRBTree;

public class FileService {

    /**
     * Reads the indexes stored in a file to a RBTree.
     */
    public static void readIndexFile(String file, RBTree<Index> indexTree) throws IOException,
                                                                                  FileNotFoundException,
                                                                                  ClassNotFoundException {
        FileInputStream inputFile = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(inputFile);
        while (inputFile.available() > 0) {
            Index newIndex = (Index) in.readObject();
            indexTree.add(newIndex);
        }
        in.close();
        inputFile.close();
    }

    /**
     * Writes the indexTree's content to an index file.'
     */
    public static void writeIndexFile(String file, RBTree<Index> indexTree) throws IOException,
                                                                                   FileNotFoundException {
        FileOutputStream outFile = new FileOutputStream(file);
        ObjectOutputStream out = new ObjectOutputStream(outFile);
        for (NodeBinTree<Index> indexNode: indexTree) {
            out.writeObject(indexNode.getElement());
        }
        out.close();
        outFile.close();
    }


    private FileService() {}
}