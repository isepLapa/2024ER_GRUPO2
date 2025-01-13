package Gerenciamento;

public class Livro {
    private String titulo;
    private String editora;
    private String categoria;
    private String anoDeEdicao;
    private String isbn;
    private String autor;

    public Livro(String titulo, String editora, String categoria,
                 String anoDeEdicao, String isbn, String autor) {
        this.titulo = titulo;
        this.editora = editora;
        this.categoria = categoria;
        this.anoDeEdicao = anoDeEdicao;
        this.isbn = isbn;
        this.autor = autor;
    }

    public Livro() {

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getAnoDeEdicao() {
        return anoDeEdicao;
    }

    public void setAnoDeEdicao(String anoDeEdicao) {
        this.anoDeEdicao = anoDeEdicao;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return
                "titulo=" + titulo + " " +
                        "editora=" + editora + " " +
                        "categoria=" + categoria + " " +
                        "isbn=" + isbn + " " +
                        "autor=" + autor + " " +
                        "anoDeEdicao=" + anoDeEdicao;
    }
}
