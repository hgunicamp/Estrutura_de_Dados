package data.structure.database.tupples;

import java.io.Serializable;
import java.util.ArrayList;

import data.structure.database.tupples.Automovel;

public class Proprietario implements Serializable {

    private String nome;
    private String endereco;
    private int cnpj;
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

    public int getCnpj() {
        return this.cnpj;
    }

    public void setCnpj(int cnpj) {
        this.cnpj = cnpj;
    }

    public ArrayList<Integer> getAutoHashes() {
        return this.autoHashes;
    }

    // Constructor
    public Proprietario() {
        this.autoHashes = new ArrayList<Integer>();
    }

    public Proprietario(String nome, int cnpj) {
        this.nome = nome;
        this.cnpj = cnpj;
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
        String hashGen = nome + cnpj;
        return hashGen.hashCode();
    }

    @Override
    public String toString() {
        return "[nome: " + nome + " cnpj: " + cnpj + "]";
    }

}