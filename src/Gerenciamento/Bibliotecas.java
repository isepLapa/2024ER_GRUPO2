package Gerenciamento;

import Storage.Storage;
import Utils.Utils;

import java.nio.file.Path;
import java.util.List;

public class Bibliotecas {
    private Storage storage;
    private int bibliotecaEscolhida;

    public Bibliotecas(Storage storage) {
        this.storage = storage;
    }

    public void adicionarBiblioteca() {
        String nomeBiblioteca = Utils.ScanString("Escreve o nome da biblioteca: ");
        storage.createBiblioteca(nomeBiblioteca);
    }

    public void eliminarBiblioteca() {
        List<String> bibliotecas = storage.getBibliotecas();
        if (bibliotecas.isEmpty()) {
            System.out.println("Nenhuma biblioteca encontrada.");
            return;
        }
        System.out.println("Selecione uma biblioteca para eliminar:");
        int escolha = Utils.TransformarListaEmEscolha("Escolha uma biblioteca: ", bibliotecas);
        String nomeBiblioteca = bibliotecas.get(escolha);

        if (Utils.ScanString("Tem a certeza que deseja eliminar a biblioteca " + bibliotecas.get(escolha) + "? (S/N)").equalsIgnoreCase("S")) {

            if (this.isBibliotecaVazia(nomeBiblioteca)) {
                storage.remove(Path.of("bibliotecas.txt"), bibliotecas, nomeBiblioteca);
                storage.removeFile(nomeBiblioteca);
            }
             else {
                System.out.println("A biblioteca não está vazia, não é possível eliminar.");
            }
        }
    }

    public int getBibliotecaEscolhida() {
        return this.bibliotecaEscolhida;
    }

    public List<String> listaBibliotecas() {
        return storage.getBibliotecas();
    }

    public void selecionarBiblioteca() {
        List<String> bibliotecas = storage.getBibliotecas();
        if (bibliotecas.isEmpty()) {
            if (Utils.ScanString("Nenhuma Biblioteca encontrada, deseja criar uma nova? (S/N)").equalsIgnoreCase("S")) {
                storage.createBiblioteca(Utils.ScanString("Escreve o nome da biblioteca: "));
                bibliotecas = storage.getBibliotecas();
            }
        }
        System.out.println("Selecione uma biblioteca:");
        this.bibliotecaEscolhida = Utils.TransformarListaEmEscolha("Escolha uma biblioteca: ", bibliotecas);
    }

    private boolean isBibliotecaVazia(String nomeBiblioteca) {
        Biblioteca biblioteca = new Biblioteca(nomeBiblioteca, storage);

        return biblioteca.livros.getLivros().isEmpty()
                && biblioteca.revistajornais.getRevistas().isEmpty()
                && biblioteca.utentes.getUtentes().isEmpty()
                && biblioteca.emprestimos.getEmprestimos().isEmpty();
    }
}