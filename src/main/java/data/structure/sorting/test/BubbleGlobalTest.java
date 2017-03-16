package data.structure.sorting.test;

import data.structure.sorting.Sort;

import data.structure.list.test.Aluno;

public class BubbleGlobalTest {
    public static void main(String[] args) {
        Aluno[] alunos = new Aluno[10];

        alunos[0] = new Aluno("João",   10.0);
        alunos[1] = new Aluno("Maria",   9.0);
        alunos[2] = new Aluno("Pedro",   8.0);
        alunos[3] = new Aluno("Amanda",  7.0);
        alunos[4] = new Aluno("José",    6.0);
        alunos[5] = new Aluno("Paula",   5.0);
        alunos[6] = new Aluno("Renato",  4.0);
        alunos[7] = new Aluno("Bianca",  3.0);
        alunos[8] = new Aluno("Fabio",   2.0);
        alunos[9] = new Aluno("Joana",   1.0);

        Sort.bubble(alunos);

        for (Aluno aluno: alunos) {
            System.out.println(aluno);
        }
        System.out.println();

        Sort.bubble(alunos, true);

        for (Aluno aluno: alunos) {
            System.out.println(aluno);
        }
        System.out.println();

    }
}