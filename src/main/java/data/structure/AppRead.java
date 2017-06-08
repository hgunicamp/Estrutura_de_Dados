package data.structure;

import data.structure.database.files.FileService;
import data.structure.tree.NodeBinTree;
import data.structure.database.indexes.*;
public class AppRead {
    private static String  propDataFile = "proprietario.data";
    private static String propIndexFile = "proprietario.index";
    private static String  autoDataFile = "automovel.data";
    private static String autoIndexFile = "automovel.index";
    private static String  acesDataFile = "acessorio.data";
    private static String acesIndexFile = "acessorio.index";

    public static void main(String[] args) {
        Index propIndex = new Index(
            FileService.INDEXDIR + propIndexFile,
            FileService.DATADIR  + propDataFile);
        Index autoIndex = new Index(
            FileService.INDEXDIR + autoIndexFile,
            FileService.DATADIR  + autoDataFile);
        Index acesIndex = new Index(
            FileService.INDEXDIR + acesIndexFile,
            FileService.DATADIR  + acesDataFile);
        
        try {
            System.out.println("Proprietários:");
            for (NodeBinTree<IndexElement> indexNode: propIndex.getIndexTree()) {
                IndexElement index = indexNode.getElement();
                System.out.println(propIndex.getData(index.getHashCode()));
            }
            System.out.println("Automóveis:");
            for (NodeBinTree<IndexElement> indexNode: autoIndex.getIndexTree()) {
                IndexElement index = indexNode.getElement();
                System.out.println(autoIndex.getData(index.getHashCode()));
            }
            System.out.println("Acessórios:");
            for (NodeBinTree<IndexElement> indexNode: acesIndex.getIndexTree()) {
                IndexElement index = indexNode.getElement();
                System.out.println(acesIndex.getData(index.getHashCode()));
            }
        } catch (Exception e) { e.printStackTrace(); }

    }
}