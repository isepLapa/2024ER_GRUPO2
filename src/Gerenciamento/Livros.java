package Gerenciamento;

import Storage.Storage;
import Utils.Utils;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class Livros{
    private final String biblioteca;
    private final Storage storage;

    private final Path livrosPath;
    private static List<Livro> livros;

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

        for (Livro livro : livros) {
            System.out.println(livro);
        }
    }

    public void AdicionarLivro() {
        String nome = Utils.verificarSeItemExiste("Nome do livro: ", livros, Livro::getTitulo);
        String editora = Utils.ScanString("Editora: ");
        String categoria = Utils.ScanString("Categoria: ");
        String ano = Utils.validarData("Ano de edição: ");
        String isbn = validarISBN(Utils.ScanString("ISBN: "));
        String autor = Utils.ScanString("Autor: ");

        Livro livro = new Livro(nome, editora, categoria, ano, isbn, autor);
        livros.add(livro);

        this.storage.save(this.livrosPath, livros);
    }

    public void RemoverLivro() {
        String nome = Utils.ScanString("Nome do livro: ");

        livros.removeIf(livro -> livro.getTitulo().equals(nome));

        this.storage.save(this.livrosPath, livros);
    }

    public void AlterarLivro() {
        int escolha = Utils.TransformarListaEmEscolha("Escolha o índice do livro que deseja alterar: ", livros);

        Scanner sc = new Scanner(System.in);
        System.out.println("Escolha o campo que deseja alterar:");
        System.out.println("1 - Título");
        System.out.println("2 - Editora");
        System.out.println("3 - Categoria");
        System.out.println("4 - Ano de Edição");
        System.out.println("5 - ISBN");
        System.out.println("6 - Autor");

        int op = sc.nextInt();
        sc.nextLine();

        switch (op) {
            case 1:
                livros.get(escolha).setTitulo(Utils.ScanString("Novo título: "));
                break;
            case 2:
                livros.get(escolha).setEditora(Utils.ScanString("Nova editora: "));
                break;
            case 3:
                livros.get(escolha).setCategoria(Utils.ScanString("Nova categoria: "));
                break;
            case 4:
                livros.get(escolha).setAnoDeEdicao(Utils.validarData("Novo ano de edição: "));
                break;
            case 5:
                livros.get(escolha).setIsbn(validarISBN(Utils.ScanString("Novo ISBN: ")));
                break;
            case 6:
                livros.get(escolha).setAutor(Utils.ScanString("Novo autor: "));
                break;
            default:
                System.out.println("Opção inválida! Por favor, escolha entre 1 e 6.");
        }

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

    public List<Livro> getLivros() {
        return this.convertToLivroList(storage.get(this.livrosPath));
    }

    /**
     * Converte uma lista de strings em uma lista de objetos Livro.
     *
     * @param livrosStr a lista de strings dos livros
     * @return uma lista de objetos Livro
     */
    public List<Livro> convertToLivroList(List<String> livrosStr) {
        List<Livro> livros = new ArrayList<>();
        for (String livroStr : livrosStr) {
            livros.add(parseLivro(livroStr));
        }
        return livros;
    }

    public static String validarISBN(String isbn) {
        do {
            isbn = Utils.verificarSeItemExiste("Digite um ISBN válido: " , livros, Livro::getIsbn);
        } while (!isISBNValido(isbn));
        return isbn;
    }

    public static boolean isISBNValido(String isbn) {
        if (isbn == null) {
            return false;
        }

        // Primeiro da-mos strip aos hifens para ser mais facil fazer a validação
        isbn = isbn.replace("-", "");

        if (isbn.matches("\\d{9}[\\dXx]")) {
            return isISBN10Valido(isbn);
        } else if (isbn.matches("\\d{13}")) {
            return isISBN13Valido(isbn);
        }

        return false;
    }

    /**
     * Verifica se um ISBN-10 é válido.
     *
     * @param isbn o ISBN a ser verificado
     * @return true se o ISBN é válido, false caso contrário
     *
     * @see <a href="https://en.wikipedia.org/wiki/ISBN#ISBN-10_check_digit_calculation">Wikipedia: ISBN-10 check digit calculation</a>
     */
    private static boolean isISBN10Valido(String isbn) {
        try {
            int[] digits = new int[10];
            for (int i = 0; i < 9; i++) {
                // convertemos cada digito para um inteiro
                digits[i] = Integer.parseInt(isbn.substring(i, i + 1));
            }

            // o ultimo digito pode ser um numero ou um X
            String checkDigit = isbn.substring(9);
            // se for um X convertemos para 10
            digits[9] = "X".equalsIgnoreCase(checkDigit) ? 10 : Integer.parseInt(checkDigit);

            int soma = 0;
            for (int i = 0; i < 10; i++) {
                // somamos todos menos o ultimo
                soma += digits[i] * (10 - i);
            }

            // se a soma for divisivel por 11 é valido
            return soma % 11 == 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Verifica se um ISBN-13 é válido.
     *
     * @param isbn o ISBN a ser verificado
     * @return true se o ISBN é válido, false caso contrário
     *
     * @see <a href="https://pt.wikipedia.org/wiki/ISBN#ISBN-13_check_digit_calculation">Wikipedia: ISBN-13 check digit calculation</a>
     */
    private static boolean isISBN13Valido(String isbn) {
        try {
            int[] digits = new int[13];
            for (int i = 0; i < 13; i++) {
                digits[i] = Integer.parseInt(isbn.substring(i, i + 1));
            }

            int soma = 0;
            for (int i = 0; i < 12; i++) {
                // se o indice for par multiplicamos por 1, se for impar por 3
                soma += (i % 2 == 0) ? digits[i] : digits[i] * 3;
            }

            int digitoVerificador = 10 - (soma % 10);
            if (digitoVerificador == 10) {
                digitoVerificador = 0;
            }

            return digitoVerificador == digits[12];
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
