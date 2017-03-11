package data.structure.list.test;

import data.structure.list.LinkedList;

public class LinkedListGlobalTest {
    public static void main(String[] args) {
        LinkedList<Aluno> list = new LinkedList<Aluno>();

        /* Testando inserção serial. */
        System.out.println("Testando inserção serial.");
        list.add(new Aluno("João",   10.0));
        list.add(new Aluno("Maria",   9.0));
        list.add(new Aluno("Pedro",   8.0));
        list.add(new Aluno("Amanda",  7.0));
        list.add(new Aluno("José",    6.0));
        list.add(new Aluno("Paula",   5.0));
        list.add(new Aluno("Renato",  4.0));
        list.add(new Aluno("Bianca",  3.0));
        list.add(new Aluno("Fabio",   2.0));
        list.add(new Aluno("Joana",   1.0));

        list.print();
        System.out.println();

        /* Testando  remoção */
        System.out.println("Testando  remoção.");
        System.out.println(list.removeFirst());
        System.out.println(list.remove(8));
        System.out.println();

        list.print();
        System.out.println();

        /* Testando inserção */
        System.out.println("Testando inserção.");
        list.add(0, new Aluno("Joana", 1.0));
        list.add(5, new Aluno("João", 10.0));

        list.print();
        System.out.println();

        /* Testando clone */
        System.out.println("Testando clone.");
        LinkedList<Aluno> list2 = list.clone();

        list2.print();
        System.out.println();

        System.out.println(list2.getFirst());
        System.out.println(list2.getLast());
        System.out.println();

        /* Testando inserção de lista */
        System.out.println("Testando inserção de lista.");
        list2 = new LinkedList<Aluno>();
        list2.add(new Aluno("Tiago", 7.5));
        list2.add(new Aluno("Silvia", 8.5));

        list.add(8, list2);

        list.print();
        System.out.println();

        list2 = new LinkedList<Aluno>();
        list2.add(new Aluno("Lucas",  9.5));
        list2.add(new Aluno("Lilian", 6.5));

        list.add(0, list2);

        list.print();
        System.out.println();

        /* Teste set */
        System.out.println("Teste set.");
        list.set(0, new Aluno("Mario", 6.5));
        list.set(11, new Aluno("Roberta", 5.5));

        list.print();
        System.out.println();

        /* Teste find */
        System.out.println("Teste find.");
        System.out.println("find(0,\"Mario\"): " + list.find(0, new Aluno("Mario")));
        System.out.println("find(8,\"Fabio\"): " + list.find(8, new Aluno("Fabio")));
        System.out.println("find(8,\"Mario\"): " + list.find(8, new Aluno("Mario")));
        System.out.println();

    }
}
