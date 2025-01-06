package Gerenciamento;

import java.util.Scanner;

public class RevistaJornal {
    private String titulo;
    private String editora;
    private String categoria;
    private String isnn;
    private String datapub;

    public RevistaJornal(String titulo, String editora, String categoria,
                 String datapub, String isnn) {
        this.titulo = titulo;
        this.editora = editora;
        this.categoria = categoria;
        this.datapub = datapub;
        this.isnn = isnn;
    }

    public RevistaJornal() {

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

    public String getDatapub() {
        return datapub;
    }

    public void setDatapub(String Datapub) {
        this.datapub = datapub;
    }

    public String getIsnn() {
        return isnn;
    }

    public void setIsnn(String isnn) {
        this.isnn = isnn;
    }


    @Override
    public String toString() {
        return
                "titulo=" + titulo + " " +
                        "editora=" + editora + " " +
                        "categoria=" + categoria + " " +
                        "isbn=" + isnn + " " +
                        "anoDeEdicao=" + datapub;
    }
}
