package data.structure;

import java.util.ArrayList;
import java.util.Scanner;

import data.structure.database.files.FileService;
import data.structure.database.tupples.*;
import data.structure.tree.RBTree;
import data.structure.database.indexes.*;

public class AppLoad {
    private static String  propDataFile = "proprietario.data";
    private static String propIndexFile = "proprietario.index";
    private static String  autoDataFile = "automovel.data";
    private static String autoIndexFile = "automovel.index";
    private static String  acesDataFile = "acessorio.data";
    private static String acesIndexFile = "acessorio.index";

    private static Proprietario newProprietario(ArrayList<Proprietario> propList, Index propIndex, Scanner scanner) {
        System.out.println("Nome: ");
        String nome = scanner.nextLine();
        System.out.println("Endereço: ");
        String endereco = scanner.nextLine();
        System.out.println("CPF: ");
        String cpf = scanner.nextLine();
        Proprietario newProp = new Proprietario(nome, endereco, cpf);
        propList.add(newProp);
        propIndex.addIndex(newProp.hashCode(), 0, 0);
        return newProp;
    }

    private static Automovel newAutomovel(ArrayList<Automovel> autoList, Index autoIndex, int donoHashCode,Scanner scanner) {
        System.out.println("Modelo: ");
        String modelo = scanner.nextLine();
        System.out.println("Fabricante: ");
        String fabricante = scanner.nextLine();
        System.out.println("Placa: ");
        String placa = scanner.nextLine();
        System.out.println("Ano: ");
        int ano = Integer.parseInt(scanner.nextLine());
        Automovel carro = new Automovel(modelo, fabricante, ano);
        carro.setPlaca(placa);
        carro.setDonoHashCode(donoHashCode);
        autoList.add(carro);
        autoIndex.addIndex(carro.hashCode(), 0, 0);
        return carro;
    }

    private static Acessorio newAcessorio(ArrayList<Acessorio> acesList, Index acesIndex, Scanner scanner) {
        System.out.println("Nome: ");
        String nome = scanner.nextLine();
        Acessorio newAces = new Acessorio(nome);
        acesList.add(newAces);
        if (null == acesIndex.search(newAces.hashCode())) {
            acesIndex.addIndex(newAces.hashCode(), 0, 0);
        }
        return newAces;
    }

    public static void main(String[] args) {
        String strPropFile  = FileService.DATADIR  + propDataFile;
        String strAutoFile  = FileService.DATADIR  + autoDataFile;
        String strAcesFile  = FileService.DATADIR  + acesDataFile;
        String strPropIndex = FileService.INDEXDIR + propIndexFile;
        String strAutoIndex = FileService.INDEXDIR + autoIndexFile;
        String strAcesIndex = FileService.INDEXDIR + acesIndexFile;

        Scanner scanner = new Scanner(System.in);
        ArrayList<Proprietario> propList = new ArrayList<Proprietario>();
        ArrayList<Automovel> autoList = new ArrayList<Automovel>();
        ArrayList<Acessorio> acesList = new ArrayList<Acessorio>();

        Index propIndex = new Index(strPropIndex, strPropFile, true);
        Index autoIndex = new Index(strAutoIndex, strAutoFile, true);
        Index acesIndex = new Index(strAcesIndex, strAcesFile, true);

        // Gereando dados.
        boolean novoProp;
        do {
            System.out.println("Novo proprietário:");
            Proprietario prop = newProprietario(propList, propIndex, scanner);
            boolean novoCarro;
            do {
                System.out.println("Novo carro:");
                Automovel carro = newAutomovel(autoList, autoIndex, prop.hashCode(), scanner);
                prop.add(carro);
                boolean novoAcessorio;
                do {
                    System.out.println("Novo Acessório:");
                    Acessorio acessorio = newAcessorio(acesList, acesIndex, scanner);
                    carro.add(acessorio);
                    System.out.println("Novo acessorio? (s/n): ");
                    novoAcessorio = scanner.nextLine().equals("s");
                } while (novoAcessorio);
                System.out.println("Novo carro? (s/n): ");
                novoCarro = scanner.nextLine().equals("s");
            } while (novoCarro);
            System.out.println("Novo proprietário? (s/n): ");
            novoProp = scanner.nextLine().equals("s");
        } while(novoProp);

        scanner.close();

        try {
            int initPos = 0;
            // Gravando os dados.
            for (Proprietario prop: propList) {
                byte[] binProp = FileService.convertToBytes(prop);
                IndexElement pIndex = propIndex.search(prop.hashCode());
                int sizeProp = binProp.length;
                FileService.writeDataFile(strPropFile, initPos, sizeProp, binProp);
                pIndex.setInitPosition(initPos);
                pIndex.setSize(sizeProp);
                initPos += sizeProp;
            }

            initPos = 0;
            for (Automovel carro: autoList) {
                byte[] binAuto = FileService.convertToBytes(carro);
                IndexElement aIndex = autoIndex.search(carro.hashCode());
                int sizeAuto = binAuto.length;
                FileService.writeDataFile(strAutoFile, initPos, sizeAuto, binAuto);
                aIndex.setInitPosition(initPos);
                aIndex.setSize(sizeAuto);
                initPos += sizeAuto;
            }

            initPos = 0;
            for (Acessorio aces: acesList) {
                byte[] binAces = FileService.convertToBytes(aces);
                IndexElement aIndex = acesIndex.search(aces.hashCode());
                int sizeAces = binAces.length;
                FileService.writeDataFile(strAcesFile, initPos, sizeAces, binAces);
                aIndex.setInitPosition(initPos);
                aIndex.setSize(sizeAces);
                initPos += sizeAces;
            }

            FileService.writeIndexFile(strPropIndex, propIndex.getIndexTree());
            FileService.writeIndexFile(strAutoIndex, autoIndex.getIndexTree());
            FileService.writeIndexFile(strAcesIndex, acesIndex.getIndexTree());

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }


    }

}