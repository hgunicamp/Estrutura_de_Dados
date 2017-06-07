package data.structure.database.files;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;

import data.structure.database.indexes.IndexElement;
import data.structure.tree.RBTree;
import data.structure.tree.NodeBinTree;

public class FileService {

    /**
     * Relative paths.
     */
    public static final String  BASEDIR = System.getProperty("user.dir");
    public static final String  DATADIR = BASEDIR + "/database/data/";
    public static final String INDEXDIR = BASEDIR + "/database/index/";

    /**
     * Reads the indexes stored in a file to a RBTree.
     */
    public static void readIndexFile(String file, RBTree<IndexElement> indexTree) throws IOException,
                                                                                         FileNotFoundException,
                                                                                         ClassNotFoundException {
        FileInputStream inputFile = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(inputFile);
        while (inputFile.available() > 0) {
            IndexElement newIndexElement = (IndexElement) in.readObject();
            indexTree.add(newIndexElement);
        }
        in.close();
        inputFile.close();
    }

    /**
     * Writes the indexTree's content to an index file.'
     */
    public static void writeIndexFile(String file, RBTree<IndexElement> indexTree) throws IOException,
                                                                                          FileNotFoundException {
        FileOutputStream outFile = new FileOutputStream(file);
        ObjectOutputStream out = new ObjectOutputStream(outFile);
        for (NodeBinTree<IndexElement> indexNode: indexTree) {
            out.writeObject(indexNode.getElement());
        }
        out.close();
        outFile.close();
    }

    /**
     * Reads 'size'' bytes from data file starting at 'position'.
     */
    public static void readDataFile(String file, int position, int size, byte[] buffer) throws IOException,
                                                                                               FileNotFoundException {
        RandomAccessFile inputFile = new RandomAccessFile(file, "r");
        inputFile.seek(position);
        inputFile.read(buffer, 0, size);
        inputFile.close();
    }

    /**
     * Writes 'size'' bytes from data file starting at 'position'.
     */
    public static void writeDataFile(String file, int position, int size, byte[] buffer) throws IOException,
                                                                                                FileNotFoundException {
        RandomAccessFile outFile = new RandomAccessFile(file, "rw");
        outFile.seek(position);
        outFile.write(buffer, 0, size);
        outFile.close();
    }

    /**
     * Converts an Serializable object into a byte array.
     * 
     * @return byte[]
     */
    public static byte[] convertToBytes(Object object) throws IOException {
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        ObjectOutput out = new ObjectOutputStream(outBuffer);
        out.writeObject(object);
        out.flush();
        return outBuffer.toByteArray();
    }

    public static Object convertToObject(byte[] buffer) throws IOException,
                                                               ClassNotFoundException {
        ByteArrayInputStream inputBuffer = new ByteArrayInputStream(buffer);
        ObjectInputStream input = new ObjectInputStream(inputBuffer);
        return input.readObject();
    }

    // Avoids create an object of this class.
    private FileService() {}
}
