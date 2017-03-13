package data.structure.list.test;

import data.structure.list.Filo;

public class FiloGlobalTest {
    public static void main(String[] args) {
        Filo<Aluno> fifo = new Filo<Aluno>();

        /* Teste de Inserção. */
        System.out.println("Teste de Inserção.");
        fifo.push(new Aluno("João", 10.0));
        fifo.push(new Aluno("Maria", 9.0));
        fifo.push(new Aluno("Pedro", 8.0));
        fifo.push(new Aluno("Sarah", 7.0));
        System.out.println();

        /* Teste de Remoção. */
        System.out.println("Teste de Remoção.");
        System.out.println(fifo.pop());
        System.out.println(fifo.pop());
        System.out.println(fifo.pop());
        System.out.println(fifo.pop());
        System.out.println();

        /* Teste de Misto. */
        System.out.println("Teste de Misto.");
        fifo.push(new Aluno("João", 10.0));
        fifo.push(new Aluno("Maria", 9.0));
        System.out.println("Size: " + fifo.size());
        System.out.println(fifo.pop());
        System.out.println("Size: " + fifo.size());
        fifo.push(new Aluno("Pedro", 8.0));
        System.out.println("Size: " + fifo.size());
        System.out.println(fifo.pop());
        System.out.println(fifo.pop());
        System.out.println("Size: " + fifo.size());
        fifo.push(new Aluno("Sarah", 7.0));
        System.out.println("Size: " + fifo.size());
        System.out.println(fifo.pop());
        System.out.println("Size: " + fifo.size());
        System.out.println();

    }
}