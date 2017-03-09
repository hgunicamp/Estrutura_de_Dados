package data.structure.list.test;

public class Aluno {
    protected String nome;
    protected double nota;

    public Aluno(String nome, double nota) {
        this.nome = nome;
        this.nota = nota;
    }

    public Aluno(String nome) {
        this.nome = nome;
        this.nota = 0.0;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getNota() {
        return this.nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    @Override
    public Aluno clone() {
        return new Aluno(this.nome, this.nota);
    }

    @Override
    public String toString() {
        return "Aluno - [nome: " + nome + ", nota: " + nota + "]"; 
    }
}
