package data.structure.list.test;

import data.structure.list.Fifo;

public class FifoGlobalTest {
    public static void main(String[] args) {
        Fifo<Aluno> fifo = new Fifo<Aluno>();

        /* Teste de Inserção. */
        System.out.println("Teste de Inserção.");
        fifo.put(new Aluno("João", 10.0));
        fifo.put(new Aluno("Maria", 9.0));
        fifo.put(new Aluno("Pedro", 8.0));
        fifo.put(new Aluno("Sarah", 7.0));
        System.out.println();

        /* Teste de Remoção. */
        System.out.println("Teste de Remoção.");
        System.out.println(fifo.get());
        System.out.println(fifo.get());
        System.out.println(fifo.get());
        System.out.println(fifo.get());
        System.out.println();

        /* Teste de Misto. */
        System.out.println("Teste de Misto.");
        fifo.put(new Aluno("João", 10.0));
        fifo.put(new Aluno("Maria", 9.0));
        System.out.println("Size: " + fifo.size());
        System.out.println(fifo.get());
        System.out.println("Size: " + fifo.size());
        fifo.put(new Aluno("Pedro", 8.0));
        System.out.println("Size: " + fifo.size());
        System.out.println(fifo.get());
        System.out.println(fifo.get());
        System.out.println("Size: " + fifo.size());
        fifo.put(new Aluno("Sarah", 7.0));
        System.out.println("Size: " + fifo.size());
        System.out.println(fifo.get());
        System.out.println("Size: " + fifo.size());
        System.out.println();

    }
}