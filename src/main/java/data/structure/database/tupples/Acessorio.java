package data.structure.database.tupples;

import java.io.Serializable;

public class Acessorio implements Serializable {
    private String nome;

    // Getters and Setters
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Constructor
    public Acessorio(String nome) {
        this.nome = nome;
    }

    @Override
    public int hashCode() {
       return nome.hashCode();
    }

    @Override
    public String toString() {
        return "Acessório: " + nome;
    }
}