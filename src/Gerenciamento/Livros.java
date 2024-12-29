package Gerenciamento;

import Storage.Storage;

import java.nio.file.Path;
import java.util.List;

public class Livros{
    private String nome;
    private Storage storage;

    public Livros(String nome, Storage storage) {
        this.nome = nome;
        this.storage = storage;
    }

    public List<String> getLivros() {
        return storage.get(Path.of(this.nome + "/livros.txt"));
    }
}
