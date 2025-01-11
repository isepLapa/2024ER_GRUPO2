package Gerenciamento;

import Storage.Storage;
import Utils.Utils;

import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Livros{
    private final String biblioteca;
    private final Storage storage;

    private final Path livrosPath;
    private List<String> livros;

    public Livros(String biblioteca, Storage storage) {
        this.biblioteca = biblioteca;
        this.storage = storage;

        this.livrosPath = Path.of(this.biblioteca + "/livros.txt");
        this.livros = this.getLivros();
    }

    public void ListarLivros() {
        if (livros.isEmpty()) {
            System.out.println("Não existem livros na biblioteca " + this.biblioteca);
            return;
        }

        for (String livro : livros) {
            System.out.println(livro);
        }
    }

    public void AdicionarLivro() {
        String nome = Utils.ScanString("Nome do livro: ");
        String editora = Utils.ScanString("Editora: ");
        String categoria = Utils.ScanString("Categoria: ");
        String ano = Utils.ScanString("Ano de edição: ");
        String isbn = Utils.ScanString("ISBN: ");
        String autor = Utils.ScanString("Autor: ");

        Livro livro = new Livro(nome, editora, categoria, ano, isbn, autor);
        livros.add(livro.toString());
        this.storage.save(this.livrosPath, livros);
    }

    public void RemoverLivro() {
        String nome = Utils.ScanString("Nome do livro: ");

        this.storage.remove(this.livrosPath, livros, "titulo=" + nome);
    }

    public void AlterarLivro() {
        int escolha = Utils.TransformarListaEmEscolha("Escolha 0 livro que quer alterar: ", livros);

        if(escolha == 0) {
            return;
        }

        String livroSelecionado = livros.get(escolha);
        System.out.println("Livro selecionado: " + livroSelecionado);

        Livro livro = this.parseLivro(livroSelecionado);

        Scanner sc = new Scanner(System.in);
        System.out.println("Escolha o campo que deseja alterar:");
        System.out.println("1 - Título");
        System.out.println("2 - Editora");
        System.out.println("3 - Categoria");
        System.out.println("4 - Ano de Edição");
        System.out.println("5 - ISBN");
        System.out.println("6 - Autor");

        int op = sc.nextInt();
        sc.nextLine(); // Consumir a quebra de linha

        switch (op) {
            case 1:
                System.out.println("Novo título: ");
                livro.setTitulo(sc.nextLine());
                break;
            case 2:
                System.out.println("Nova editora: ");
                livro.setEditora(sc.nextLine());
                break;
            case 3:
                System.out.println("Nova categoria: ");
                livro.setCategoria(sc.nextLine());
                break;
            case 4:
                System.out.println("Novo ano de edição: ");
                livro.setAnoDeEdicao(sc.nextLine());
                break;
            case 5:
                System.out.println("Novo ISBN: ");
                livro.setIsbn(sc.nextLine());
                break;
            case 6:
                System.out.println("Novo autor: ");
                livro.setAutor(sc.nextLine());
                break;
            default:
                System.out.println("Opção inválida! Por favor, escolha entre 1 e 6.");
        }

        livros.set(escolha, livro.toString());
        this.storage.save(this.livrosPath, livros);
    }

    /**
     * Analisa uma representação em string de um livro e retorna um objeto Livro.
     *
     * @param livro a representação em string do livro no formato "titulo=... editora=... categoria=... isbn=... autor=... anoDeEdicao=..."
     * @return um objeto Livro com os detalhes analisados
     */
    private Livro parseLivro(String livro) {
        String[] partesLivro = livro.split(" ");
        String titulo = partesLivro[0].split("=")[1];
        String editora = partesLivro[1].split("=")[1];
        String categoria = partesLivro[2].split("=")[1];
        String isbn = partesLivro[3].split("=")[1];
        String autor = partesLivro[4].split("=")[1];
        String anoDeEdicao = partesLivro[5].split("=")[1];

        return new Livro(titulo, editora, categoria, anoDeEdicao, isbn, autor);
    }

    public List<String> getLivros() {
        return storage.get(this.livrosPath);
    }

}
