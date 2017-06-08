package data.structure;

import java.util.ArrayList;
import java.util.Scanner;

import data.structure.database.files.FileService;
import data.structure.database.indexes.Index;
import data.structure.database.tupples.*;

public class App 
{
    private static final String  propDataFile = FileService.DATADIR  + "proprietario.data";
    private static final String propIndexFile = FileService.INDEXDIR + "proprietario.index";
    private static final String  autoDataFile = FileService.DATADIR  + "automovel.data";
    private static final String autoIndexFile = FileService.INDEXDIR + "automovel.index";
    private static final String  acesDataFile = FileService.DATADIR  + "acessorio.data";
    private static final String acesIndexFile = FileService.INDEXDIR + "acessorio.index";

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        // Loadind the database indexes
        Index propIndex = new Index(propIndexFile, propDataFile);
        Index autoIndex = new Index(autoIndexFile, autoDataFile);
        Index acesIndex = new Index(acesIndexFile, acesDataFile);

        try {
            while (true) {
                System.out.println("Nova busca (s/n)");
                if (scanner.nextLine().equals("n")) break;
                System.out.println("Digite uma placa:");
                int autoHashCode = scanner.nextLine().hashCode();
                Automovel automovel = (Automovel) autoIndex.getData(autoHashCode);
                if (null == automovel) {
                    System.out.println("Não encontrado");
                    continue;
                }
                Proprietario proprietario = (Proprietario) propIndex.getData(automovel.getDonoHashCode());
                ArrayList<Acessorio> acessorios = new ArrayList<Acessorio>();
                for (Integer acesHashCode: automovel.getAcessoriosHashes()) {
                    acessorios.add( (Acessorio) acesIndex.getData(acesHashCode));
                }
                System.out.println("Proprietário");
                System.out.println("\t" + proprietario);
                System.out.println("Automóvel");
                System.out.println("\t" + automovel);
                System.out.println("Acessórios:");
                for (Acessorio acessorio: acessorios) {
                    System.out.println("\t" + acessorio);
                }

            }
        } catch (Exception e) { e.printStackTrace(); }
    }
}
