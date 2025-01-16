package Gerenciamento;

import Storage.Storage;

public class Biblioteca {
    private String nome;
    private Storage storage;

    public Livros livros;
    public Utentes utentes;
    public RevistaJornais revistajornais;
    public Emprestimos emprestimos;

    public Biblioteca(String nome, Storage storage) {
        this.nome = nome;
        this.storage = storage;

        this.IniciarBiblioteca();
    }

    private void IniciarBiblioteca() {

        this.livros = new Livros(this.nome, this.storage);
        this.revistajornais = new RevistaJornais(this.nome, this.storage);

        this.utentes = new Utentes(this, this.nome, this.storage);
        this.emprestimos = new Emprestimos(this, this.nome, this.storage);

    }

}
