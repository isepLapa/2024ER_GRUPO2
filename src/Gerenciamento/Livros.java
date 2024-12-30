package Gerenciamento;

import Storage.Storage;

import java.nio.file.Path;
import java.util.List;

public class Livros{
    private String biblioteca;
    private Storage storage;

    public Livros(String biblioteca, Storage storage) {
        this.biblioteca = biblioteca;
        this.storage = storage;
    }

    public void ListarLivros() {
        List<String> livros = this.getLivros();

        if (livros.isEmpty()) {
            System.out.println("Não existem livros na biblioteca " + this.biblioteca);
            return;
        }

        for (String livro : livros) {
            System.out.println(livro);
        }
    }

    private List<String> getLivros() {
        return storage.get(Path.of(this.biblioteca + "/livros.txt"));
    }
}
