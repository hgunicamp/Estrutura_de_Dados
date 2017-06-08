package data.structure.database.tupples;

import java.io.Serializable;
import java.util.ArrayList;

public class Automovel implements Serializable {

    private String modelo;
    private String fabricante;
    private String placa;
    private int ano;
    private int donoHashCode;
    private ArrayList<Integer> acessoriosHashes;

    // Getters and Setters
    public String getModelo() {
        return this.modelo;
    }

    public String getFabricante() {
        return this.fabricante;
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

    public int getDonoHashCode() {
        return this.donoHashCode;
    }

    public void setDonoHashCode(int donoHashCode) {
        this.donoHashCode = donoHashCode;
    }

    public ArrayList<Integer> getAcessoriosHashes() {
        return this.acessoriosHashes;
    }

    // Contructor
    public Automovel(String modelo, String fabricante, int ano) {
        this.modelo = modelo;
        this.fabricante = fabricante;
        this.ano = ano;
        this.acessoriosHashes = new ArrayList<Integer>();
    }

    @Override
    public int hashCode() {
        return placa.hashCode();
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

    @Override
    public String toString() {
        return "modelo: " + modelo + " fabricante: " + fabricante + " ano: " + ano;
    }


}