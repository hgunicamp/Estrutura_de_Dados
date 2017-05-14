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

        // Testando ordem de acesso.
        System.out.println("Testando acesso em ordem Infixa.");
        System.out.print("btree1:");
        for (Object integer: btree1) {
            System.out.print(" " + integer);
        }
        System.out.println();
        System.out.print("btree2:");
        for (Object integer: btree2) {
            System.out.print(" " + integer);
        }
        System.out.println();

        System.out.println("Testando acesso em ordem Prefixa.");
        btree1.setOrder(BinTreeOrder.PREFIX);
        btree2.setOrder(BinTreeOrder.PREFIX);
        System.out.print("btree1:");
        for (Object integer: btree1) {
            System.out.print(" " + integer);
        }
        System.out.println();
        System.out.print("btree2:");
        for (Object integer: btree2) {
            System.out.print(" " + integer);
        }
        System.out.println();

        System.out.println("Testando acesso em ordem Posfixa.");
        btree1.setOrder(BinTreeOrder.POSFIX);
        btree2.setOrder(BinTreeOrder.POSFIX);
        System.out.print("btree1:");
        for (Object integer: btree1) {
            System.out.print(" " + integer);
        }
        System.out.println();
        System.out.print("btree2:");
        for (Object integer: btree2) {
            System.out.print(" " + integer);
        }
        System.out.println();
        System.out.println();

        // Testando a busca
        System.out.println("Testando busca.");
        System.out.println(" 1 em btree1: " + btree1.search(new Integer(1)));
        System.out.println("10 em btree1: " + btree1.search(new Integer(10)));
        System.out.println(" 3 em btree2: " + btree2.search(new Integer(3)));
        System.out.println("11 em btree2: " + btree2.search(new Integer(11)));
        System.out.println();

        // Testando remoção
        System.out.println("Testando Remoção");
        // Removendo 6 de btree1
        NodeBinTree<Integer> pivot = btree1.search(new Integer(6));
        System.out.println("Removendo \"" + pivot + "\" de btree1");
        System.out.println("Antes");
        btree1.printArray();
        btree1.remove(pivot);
        System.out.println("Depois");
        btree1.printArray();
        // Removendo 8 de btree1
        pivot = btree1.search(new Integer(8));
        System.out.println("Removendo \"" + pivot + "\" de btree1;");
        System.out.println("Antes");
        btree1.printArray();
        btree1.remove(pivot);
        System.out.println("Depois");
        btree1.printArray();
        // Removendo 4 de btree1
        pivot = btree1.search(new Integer(4));
        System.out.println("Removendo \"" + pivot + "\" de btree1;");
        System.out.println("Antes");
        btree1.printArray();
        btree1.remove(pivot);
        System.out.println("Depois");
        btree1.printArray();
        // Removendo 3 de btree2
        pivot = btree2.search(new Integer(3));
        System.out.println("Removendo \"" + pivot + "\" de btree2;");
        System.out.println("Antes");
        btree2.printArray();
        btree2.remove(pivot);
        System.out.println("Depois");
        btree2.printArray();

        // Testando rotação interna.
        System.out.println("Testando rotação interna.");
        btree1.add(new Integer(0));
        btree1.add(new Integer(1));
        System.out.println("Rotacionando btree1 para direita em torno de 2");
        System.out.println("Antes");
        btree1.printArray();
        btree1.search(new Integer(2)).rightInternalRotation(null);
        System.out.println("Depois");
        btree1.printArray();
        System.out.println("Rotacionando btree2 para esquerda em torno de 2");
        System.out.println("Antes");
        btree2.printArray();
        btree2.search(new Integer(2)).leftInternalRotation(null);
        System.out.println("Depois");
        btree2.printArray();


    }
}