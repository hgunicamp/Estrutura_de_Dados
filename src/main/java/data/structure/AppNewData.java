package data.structure;

import java.io.IOException;
import java.util.Scanner;

import data.structure.database.files.FileService;
import data.structure.database.indexes.Index;
import data.structure.database.indexes.IndexElement;
import data.structure.database.tupples.*;

public class AppNewData 
{
    private static final String  propDataFile = FileService.DATADIR  + "proprietario.data";
    private static final String propIndexFile = FileService.INDEXDIR + "proprietario.index";
    private static final String  autoDataFile = FileService.DATADIR  + "automovel.data";
    private static final String autoIndexFile = FileService.INDEXDIR + "automovel.index";
    private static final String  acesDataFile = FileService.DATADIR  + "acessorio.data";
    private static final String acesIndexFile = FileService.INDEXDIR + "acessorio.index";

    private static Automovel newAutomovel(Scanner scanner, String placa,
                                          int propHashCode, Index acessorios) throws IOException {
        System.out.println("Modelo: ");
        String modelo = scanner.nextLine();
        System.out.println("Fabricante: ");
        String fabricante = scanner.nextLine();
        System.out.println("Ano: ");
        int ano = Integer.parseInt(scanner.nextLine());
        Automovel automovel = new Automovel(modelo, fabricante, ano);
        automovel.setPlaca(placa);
        automovel.setDonoHashCode(propHashCode);
        while (true) {
            System.out.println("Novo acessorio (s/n):");
            if (scanner.nextLine().equals("n")) break;
            System.out.println("Acessorio:");
            automovel.add(newAcessorio(
                scanner.nextLine(),
                acessorios
            ));
        }
        return automovel;
    }

    private static Acessorio newAcessorio(String acessorio, Index acessorios) throws IOException {
        Acessorio newAces = new Acessorio(acessorio);
        IndexElement index =  acessorios.search(acessorio.hashCode());
        if (null == index) {
            acessorios.setData(newAces);
        }
        return newAces;
    }

    private static void usedAutomovel(Automovel automovel, Index automoveis,
            Proprietario proprietario, Index proprietarios) throws IOException,
                                                                   ClassNotFoundException {
        Proprietario oldProprietario = (Proprietario) proprietarios.getData(automovel.getDonoHashCode());
        oldProprietario.remove(automovel);
        proprietario.add(automovel);
        automovel.setDonoHashCode(proprietario.hashCode());
        automoveis.setData(automovel);
        proprietarios.setData(oldProprietario);
        proprietarios.setData(proprietario);
    }

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        // Loadind the database indexes
        Index propIndex = new Index(propIndexFile, propDataFile);
        Index autoIndex = new Index(autoIndexFile, autoDataFile);
        Index acesIndex = new Index(acesIndexFile, acesDataFile);

        try {
            while (true) {
                System.out.println("Novos dados (s/n)");
                if (scanner.nextLine().equals("n")) break;
                System.out.println("Digite o CPF:");
                String cpf = scanner.nextLine();
                int propHashCode = cpf.hashCode();
                Proprietario proprietario = (Proprietario) propIndex.getData(propHashCode);
                if (null == proprietario) {
                    System.out.println("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.println("Endereço: ");
                    String endereco = scanner.nextLine();
                    proprietario = new Proprietario(nome, endereco, cpf);
                } else { System.out.println(proprietario + "\n"); }

                do {
                    System.out.println("Digite a Placa do Automóvel:");
                    String placa = scanner.nextLine();
                    int autoHashCode = placa.hashCode();
                    Automovel automovel = (Automovel) autoIndex.getData(autoHashCode);
                    if (null == automovel) {
                        automovel = newAutomovel(scanner, placa, propHashCode, acesIndex);
                        automovel.setDonoHashCode(propHashCode);
                        autoIndex.setData(automovel);
                        proprietario.add(automovel);
                    } else {
                        usedAutomovel(automovel, autoIndex, proprietario, propIndex);
                    }
                    System.out.println("Novo automóvel (s/n)?:");
                } while (!scanner.nextLine().equals("n"));
                propIndex.setData(proprietario);

            }
            propIndex.updateIndexFile();
            autoIndex.updateIndexFile();
            acesIndex.updateIndexFile();
        } catch (Exception e) { e.printStackTrace(); }
    }
}
