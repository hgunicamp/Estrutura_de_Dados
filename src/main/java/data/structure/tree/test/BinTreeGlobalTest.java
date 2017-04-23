package data.structure.tree.test;

import data.structure.tree.BinTree;

public class BinTreeGlobalTest {
    public static void main(String[] args) {
        // Testando inserção e profundidade.
        System.out.println("Testando inserção e profundidade.");
        BinTree<Integer> btree1 = new BinTree<Integer>();
        btree1.add(new Integer(2));
        btree1.add(new Integer(1));
        btree1.add(new Integer(3));

        System.out.println("btree1.size() = " + btree1.size());
        System.out.println("btree1.maxDepth() = " + btree1.maxDepth());

        BinTree<Integer> btree2 = new BinTree<Integer>();
        btree2.add(new Integer(1));
        btree2.add(new Integer(2));
        btree2.add(new Integer(3));

        System.out.println("btree2.size() = " + btree2.size());
        System.out.println("btree2.maxDepth() = " + btree2.maxDepth());
        System.out.println();

    }
}