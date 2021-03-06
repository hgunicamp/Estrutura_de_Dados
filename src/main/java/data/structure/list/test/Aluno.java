package data.structure.list.test;

public class Aluno implements Comparable<Aluno> {
    protected String nome;
    protected double nota;

    public int compareTo(Aluno other) {
        double otherNota = other.getNota();
        if (this.nota < otherNota) return -1;
        else if (this.nota > otherNota) return 1;
        else return 0;
    }

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

    @Override
    public boolean equals(Object other) {
        Aluno otherCast = (Aluno) other;
        return this.nome.equals(otherCast.getNome());
    }

}
