package data.structure.tree.test;

import data.structure.tree.BinTree;
import data.structure.tree.NodeBinTree;
import data.structure.tree.BinTree.BinTreeOrder;

public class BinTreeGlobalTest {
    public static void main(String[] args) {
        // Testando inserção e profundidade.
        System.out.println("Testando inserção e profundidade.");
        BinTree<Integer> btree1 = new BinTree<Integer>();
        btree1.add(new Integer(4));
        btree1.add(new Integer(2));
        btree1.add(new Integer(6));
        btree1.add(new Integer(1));
        btree1.add(new Integer(3));
        btree1.add(new Integer(5));
        btree1.add(new Integer(9));
        btree1.add(new Integer(7));
        btree1.add(new Integer(8));

        System.out.println("btree1.size() = " + btree1.size());
        System.out.println("btree1.maxDepth() = " + btree1.maxDepth());
        btree1.printArray();

        BinTree<Integer> btree2 = new BinTree<Integer>();
        btree2.add(new Integer(7));
        btree2.add(new Integer(2));
        btree2.add(new Integer(9));
        btree2.add(new Integer(1));
        btree2.add(new Integer(5));
        btree2.add(new Integer(8));
        btree2.add(new Integer(10));
        btree2.add(new Integer(3));
        btree2.add(new Integer(6));
        btree2.add(new Integer(4));

        System.out.println("btree2.size() = " + btree2.size());
        System.out.println("btree2.maxDepth() = " + btree2.maxDepth());
        btree2.printArray();
        System.out.println();

        System.out.println("Testando busca.");
        System.out.println(" 1 em btree1: " + btree1.search(new Integer(1)));
        System.out.println("10 em btree1: " + btree1.search(new Integer(10)));
        System.out.println(" 3 em btree2: " + btree2.search(new Integer(3)));
        System.out.println("11 em btree2: " + btree2.search(new Integer(11)));
        System.out.println();

        System.out.println("Testando rotação à esquerda");
        NodeBinTree<Integer> pivot = btree1.search(new Integer(6));
        System.out.println("Rodando por: " + pivot);
        btree1.rotateToLeft(pivot);
        System.out.println("btree1.size() = " + btree1.size());
        System.out.println("btree1.maxDepth() = " + btree1.maxDepth());
        btree1.printArray();

        System.out.println("Testando rotação à direita");
        pivot = btree2.search(new Integer(5));
        System.out.println("Rodando por: " + pivot);
        btree2.rotateToRight(pivot);
        System.out.println("btree2.size() = " + btree2.size());
        System.out.println("btree2.maxDepth() = " + btree2.maxDepth());
        btree2.printArray();

    }
}