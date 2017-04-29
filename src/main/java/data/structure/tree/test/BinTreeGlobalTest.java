package data.structure.tree.test;

import data.structure.tree.BinTree;
import data.structure.tree.NodeBinTree;
import data.structure.tree.BinTree.BinTreeOrder;

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
        System.out.print("[ ");
        for (Object integer: btree1.toArray()) {
            System.out.print(integer + ", ");
        }
        System.out.println("]");

        BinTree<Integer> btree2 = new BinTree<Integer>();
        btree2.add(new Integer(1));
        btree2.add(new Integer(2));
        btree2.add(new Integer(3));

        System.out.println("btree2.size() = " + btree2.size());
        System.out.println("btree2.maxDepth() = " + btree2.maxDepth());
        System.out.print("[ ");
        for (Object integer: btree2.toArray()) {
            System.out.print(integer + ", ");
        }
        System.out.println("]");
        System.out.println();

        System.out.println("Testando busca.");
        System.out.println("1 em btree1: " + btree1.search(new Integer(1)));
        System.out.println("4 em btree1: " + btree1.search(new Integer(4)));
        System.out.println("3 em btree2: " + btree2.search(new Integer(3)));
        System.out.println("4 em btree2: " + btree2.search(new Integer(4)));
        System.out.println();

        System.out.println("Testando acesso.");
        System.out.println("  " + btree1.getRoot());
        System.out.print(btree1.leftBinTree().getRoot());
        System.out.println("   " + btree1.rightBinTree().getRoot());
        System.out.print("Infix btree1:");
        for (NodeBinTree<Integer> integer: btree1) {
            System.out.print(" " + integer);
        }
        System.out.println();
        btree1.setOrder(BinTreeOrder.PREFIX);
        System.out.print("Prefix btree1:");
        for (NodeBinTree<Integer> integer: btree1) {
            System.out.print(" " + integer);
        }
        System.out.println();
        btree1.setOrder(BinTreeOrder.POSFIX);
        System.out.print("Posfix btree1:");
        for (NodeBinTree<Integer> integer: btree1) {
            System.out.print(" " + integer);
        }
        System.out.println();

        System.out.println(btree2.getRoot());
        System.out.println("  " + btree2.rightBinTree().getRoot());
        System.out.println("    " + btree2.rightBinTree().rightBinTree().getRoot());
        System.out.print("Infix btree2:");
        for (NodeBinTree<Integer> integer: btree2) {
            System.out.print(" " + integer);
        }
        System.out.println();
        btree2.setOrder(BinTreeOrder.PREFIX);
        System.out.print("Prefix btree2:");
        for (NodeBinTree<Integer> integer: btree2) {
            System.out.print(" " + integer);
        }
        System.out.println();
        btree2.setOrder(BinTreeOrder.POSFIX);
        System.out.print("Posfix btree2:");
        for (NodeBinTree<Integer> integer: btree2) {
            System.out.print(" " + integer);
        }
        System.out.println();

    }
}