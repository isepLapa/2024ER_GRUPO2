package Gerenciamento;

import java.util.Scanner;

public class Utente {
    private String nif;
    private String nome;
    private String genero;
    private String contacto;

    Scanner sc = new Scanner(System.in);

    public Utente(String nome, String nif,
                  String genero, String contacto) {

        this.nome = nome;
        this.nif = nif;
        this.genero = genero;
        this.contacto = contacto;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        if (!nifValido(nif)) {
            System.out.println("NIF inválido!");
        }
        this.nif = nif;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public boolean nifValido(String nif) {
        return nif != null && nif.matches("\\d{9}");
    }

    @Override
    public String toString() {
        return
                "nome=" + nome + " " +
                "nif=" + nif + " " +
                        "genero=" + genero + " " +
                        "contacto=" + contacto;

    }


}
