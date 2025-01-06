package Gerenciamento;

import Storage.Storage;

import java.util.List;

public class Biblioteca {
    private String nome;
    private Storage storage;

    public Livros livros;
    public RevistaJornais revistajornais;

    public Biblioteca(String nome, Storage storage) {
        this.nome = nome;
        this.storage = storage;

        this.IniciarBiblioteca();
    }

    private void IniciarBiblioteca() {
        System.out.println("Biblioteca " + this.nome + " iniciada com sucesso!");

        this.livros = new Livros(this.nome, this.storage);
        this.revistajornais = new RevistaJornais(this.nome, this.storage);
    }

}
