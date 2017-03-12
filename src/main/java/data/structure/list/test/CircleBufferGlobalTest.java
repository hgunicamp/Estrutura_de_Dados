package data.structure.list.test;

import data.structure.list.CircleBuffer;

public class CircleBufferGlobalTest {
    public static void main(String[] args) {
        CircleBuffer<Aluno> buffer = new CircleBuffer<Aluno>(5);

        /* Teste de Inserção */
        System.out.println("Teste de Inserção.");
        buffer.put(new Aluno("João",  10.0));
        buffer.put(new Aluno("Maria",  9.0));
        buffer.put(new Aluno("Pedro",  8.0));
        buffer.put(new Aluno("Sarah",  7.0));
        System.out.println("Inseridos: " + buffer.numElements());
        System.out.println();

        /* Teste de Remoção */
        System.out.println("Teste de Remoção.");
        System.out.println(buffer.get());
        System.out.println(buffer.get());
        System.out.println(buffer.get());
        System.out.println(buffer.get());
        System.out.println();

        /* Teste Misto */
        System.out.println("Teste Misto.");
        System.out.println("Inseridos: " + buffer.numElements());
        buffer.put(new Aluno("João",  10.0));
        buffer.put(new Aluno("Maria",  9.0));
        System.out.println("Inseridos: " + buffer.numElements());
        System.out.println(buffer.get());
        System.out.println("Inseridos: " + buffer.numElements());
        buffer.put(new Aluno("Pedro",  8.0));
        System.out.println(buffer.get());
        buffer.put(new Aluno("Sarah",  7.0));
        System.out.println(buffer.get());
        System.out.println("Inseridos: " + buffer.numElements());
        System.out.println(buffer.get());
        System.out.println("Inseridos: " + buffer.numElements());
        System.out.println();

    }
}