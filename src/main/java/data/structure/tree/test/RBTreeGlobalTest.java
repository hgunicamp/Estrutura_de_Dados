package data.structure.tree.test;

import data.structure.tree.RBTree;
import data.structure.tree.NodeRBTree;

public class RBTreeGlobalTest {
    public static void main(String[] args) {
        // Testando inserção e profundidade.
        System.out.println("Testando inserção.");
        RBTree<Integer> btree1 = new RBTree<Integer>();
        btree1.add(new Integer(10));
        btree1.add(new Integer(20));
        btree1.add(new Integer(30));
        btree1.printArray();

        btree1 = new RBTree<Integer>();
        btree1.add(new Integer(30));
        btree1.add(new Integer(20));
        btree1.add(new Integer(10));
        btree1.printArray();

        btree1 = new RBTree<Integer>();
        btree1.add(new Integer(10));
        btree1.add(new Integer(30));
        btree1.add(new Integer(20));
        btree1.printArray();

        btree1 = new RBTree<Integer>();
        btree1.add(new Integer(30));
        btree1.add(new Integer(10));
        btree1.add(new Integer(20));
        btree1.printArray();

        btree1.add(new Integer(5));
        btree1.add(new Integer(15));
        btree1.add(new Integer(25));
        btree1.add(new Integer(35));
        btree1.printArray();

        btree1.add(new Integer(40));
        btree1.printArray();

        btree1.add(new Integer(37));
        btree1.printArray();

        btree1.add(new Integer(13));
        btree1.printArray();

        btree1.add(new Integer(17));
        btree1.printArray();

        btree1.add(new Integer(15));
        btree1.printArray();

        btree1.add(new Integer(16));
        btree1.printArray();

        btree1.add(new Integer(14));
        btree1.printArray();

        btree1.add(new Integer(18));
        btree1.printArray();
        System.out.println();

        // Teste de deleção
        System.out.println("Teste de deleção");
        btree1.remove((NodeRBTree<Integer>) btree1.search(new Integer(14)));
        btree1.remove((NodeRBTree<Integer>) btree1.search(new Integer(18)));
        btree1.remove((NodeRBTree<Integer>) btree1.search(new Integer(35)));
        btree1.remove((NodeRBTree<Integer>) btree1.search(new Integer(40)));
        btree1.printArray();
        System.out.println("Removendo 13.");
        btree1.remove((NodeRBTree<Integer>) btree1.search(new Integer(13)));
        btree1.printArray();
        System.out.println("Removendo 30.");
        btree1.remove((NodeRBTree<Integer>) btree1.search(new Integer(30)));
        btree1.printArray();

        System.out.println("Inserindo 7, 14, 18, 19.");
        btree1.add(new Integer(7));
        btree1.add(new Integer(14));
        btree1.add(new Integer(18));
        btree1.add(new Integer(19));
        btree1.add(new Integer(16));
        btree1.printArray();

        System.out.println("Removendo 37.");
        btree1.remove((NodeRBTree<Integer>) btree1.search(new Integer(37)));
        btree1.printArray();

        System.out.println("Removendo 15.");
        btree1.remove((NodeRBTree<Integer>) btree1.search(new Integer(15)));
        btree1.printArray();

        System.out.println("Removendo 15.");
        btree1.remove((NodeRBTree<Integer>) btree1.search(new Integer(15)));
        btree1.printArray();

        System.out.println("Removendo 10.");
        btree1.remove((NodeRBTree<Integer>) btree1.search(new Integer(10)));
        btree1.printArray();
    }
}