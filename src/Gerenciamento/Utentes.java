package Gerenciamento;

import Storage.Storage;
import Utils.Utils;

import java.nio.file.Path;
import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;

public class Utentes {
    private final Biblioteca biblioteca;
    private final Storage storage;

    private final Path utentesPath;

    Scanner sc = new Scanner(System.in);
    private List<Utente> utentes = new ArrayList<>();

    public Utentes(Biblioteca biblioteca, String nomeBiblioteca, Storage storage) {
        this.biblioteca = biblioteca;
        this.storage = storage;

        this.utentesPath = Path.of(nomeBiblioteca + "/utentes.txt");
        this.utentes = this.getUtentes();
    }

    public void removerUtente(){
        System.out.println("------------------------------------");
        listarUtentes();
        System.out.println("Digite o NIF do Gerenciamento.Utente para deletar : ");
        String nifTemp = sc.next();
        for (Utente utenteObj : utentes) {
            if (utenteObj.getNif().equals(nifTemp)) {
                utentes.remove(utenteObj);
                this.storage.save(this.utentesPath, utentes);
                System.out.println(utenteObj.toString() + "\n" +"Removido com sucesso");
                break;
            }
            else {
                System.out.println("O utilizador não existe");
            }
        }
    }

    public void adicionarUtente() {
        String nif = Utils.validaNif("Introduza o nif: ");
        String name = Utils.ScanString("Introduza o nome: ");
        String gender = Utils.ScanString("Introduza o genero: ");
        String contact = Utils.ScanString("Introduza o Contacto: ");
        Utente utente = new Utente(nif, name, gender, contact);
        utentes.add(utente);

        this.storage.save(this.utentesPath, utentes);
    }

    public void listarUtentes() {
        int x = 1;
        for (Utente utente: utentes) {
            System.out.println("Gerenciamento.Utente " + x + "\n" + utente.toString());
            x++;
        }
    }

public boolean verificarNifUtentesNaLista(String nif) {
    if (utentes == null || utentes.isEmpty()) {
        System.out.println("A lista de utentes está vazia ou não inicializada.");
        return false;
    }

    // Percorre a lista de utentes
    for (Utente utente : utentes) {
        System.out.println("Comparando NIF: " + utente.getNif() + " com " + nif);
        if (utente.getNif().equals(nif)) {
            return true; // Retorna true assim que encontra um NIF correspondente
        }
    }

    return false;
}

    public List<Utente> getUtentes() {
        if(utentes.isEmpty()){
            return this.convertToUtenteList(storage.get(this.utentesPath));
        }

        return utentes;
    }

    /**
     * Converte uma lista de strings em uma lista de objetos Livro.
     *
     * @param utentesStr a lista de strings dos utentes
     * @return uma lista de objetos Livro
     */
    public List<Utente> convertToUtenteList(List<String> utentesStr) {
        List<Utente> utentes = new ArrayList<>();
        for (String livroStr : utentesStr) {
            utentes.add(parseUtente(livroStr));
        }
        return utentes;
    }

    private Utente parseUtente(String utente) {
        String[] partesUtente = utente.split(" ");
        String nif = partesUtente[0].split("=")[1];
        String nome = partesUtente[1].split("=")[1];
        String genero = partesUtente[2].split("=")[1];
        String contacto = partesUtente[3].split("=")[1];
        return new Utente(nif, nome, genero, contacto);
    }

}

