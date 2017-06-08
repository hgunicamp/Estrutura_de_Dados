package data.structure.database.tupples;

import java.io.Serializable;
import java.util.ArrayList;

import data.structure.database.tupples.Automovel;

public class Proprietario implements Serializable {

    private String nome;
    private String endereco;
    private String cpf;
    private ArrayList<Integer> autoHashes;

    // Getters and Setters.
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCpf() {
        return this.cpf;
    }

    public ArrayList<Integer> getAutoHashes() {
        return this.autoHashes;
    }

    // Constructor
    public Proprietario(String nome, String endereco, String cpf) {
        this.nome = nome;
        this.endereco = endereco;
        this.cpf = cpf;
        this.autoHashes = new ArrayList<Integer>();
    }

    /**
     * Insere um automóvel à lista de hashes
     */
    public void add(Automovel automovel) {
        this.autoHashes.add(automovel.hashCode());
    }

    /**
     * Remove um automóvel da lista de hashes
     */
    public void remove(Automovel automovel) {
        int index = this.autoHashes.indexOf(automovel.hashCode());
        this.autoHashes.remove(index);
    }

    /**
     * Retorno um código hash baseado no nome
     */
    @Override
    public int hashCode() {
        return cpf.hashCode();
    }

    @Override
    public String toString() {
        return String.format("Nome: %s       cpf: %s\nEndereço: %s", nome, cpf, endereco);
    }

}