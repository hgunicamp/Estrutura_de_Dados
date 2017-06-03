package data.structure.database.tupples;

import java.io.Serializable;
import java.util.ArrayList;

public class Automovel implements Serializable {

    private String nome;
    private String fabricante;
    private String placa;
    private int ano;
    private ArrayList<Integer> acessoriosHashes;

    // Getters and Setters
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFabricante() {
        return this.fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getPlaca() {
        return this.placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getAno() {
        return this.ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public ArrayList<Integer> getAcessoriosHashes() {
        return this.acessoriosHashes;
    }

    // Contructor
    public Automovel() {
        this.acessoriosHashes = new ArrayList<Integer>();
    }

    @Override
    public int hashCode() {
        String hashGen = nome + fabricante + ano;
        return hashGen.hashCode();
    }

    /**
     * Insere um acessorio à lista de hashes
     */
    public void add(Acessorio acessorio) {
        this.acessoriosHashes.add(acessorio.hashCode());
    }

    /**
     * Remove um acessório da lista de hashes
     */
    public void remove(Acessorio acessorio) {
        int index = this.acessoriosHashes.indexOf(acessorio.hashCode());
        this.acessoriosHashes.remove(index);
    }


}