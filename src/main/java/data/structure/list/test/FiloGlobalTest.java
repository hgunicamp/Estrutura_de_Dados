package data.structure.list.test;

import data.structure.list.Filo;

public class FiloGlobalTest {
    public static void main(String[] args) {
        Filo<Aluno> filo = new Filo<Aluno>();

        /* Teste de Inserção. */
        System.out.println("Teste de Inserção.");
        filo.push(new Aluno("João", 10.0));
        filo.push(new Aluno("Maria", 9.0));
        filo.push(new Aluno("Pedro", 8.0));
        filo.push(new Aluno("Sarah", 7.0));
        System.out.println();

        /* Teste de Remoção. */
        System.out.println("Teste de Remoção.");
        System.out.println(filo.pop());
        System.out.println(filo.pop());
        System.out.println(filo.pop());
        System.out.println(filo.pop());
        System.out.println();

        /* Teste de Misto. */
        System.out.println("Teste de Misto.");
        filo.push(new Aluno("João", 10.0));
        filo.push(new Aluno("Maria", 9.0));
        System.out.println("Size: " + filo.size());
        System.out.println(filo.pop());
        System.out.println("Size: " + filo.size());
        filo.push(new Aluno("Pedro", 8.0));
        System.out.println("Size: " + filo.size());
        System.out.println(filo.pop());
        System.out.println(filo.pop());
        System.out.println("Size: " + filo.size());
        filo.push(new Aluno("Sarah", 7.0));
        System.out.println("Size: " + filo.size());
        System.out.println(filo.pop());
        System.out.println("Size: " + filo.size());
        System.out.println();

    }
}