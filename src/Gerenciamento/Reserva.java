package Gerenciamento;

import java.time.LocalDate;
import java.util.List;

public class Reserva {
    private int numero;
    private String utente;
    private String isbn;
    private String dataRegisto;
    private String dataInicio;
    private String dataFim;

    public Reserva(int numero, String utente, String isbn, String dataRegisto, String dataInicio, String dataFim) {
        this.numero = numero;
        this.utente = utente;
        this.isbn = isbn;
        this.dataRegisto = dataRegisto;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getNumero() {
        return numero;
    }

    public String getUtente() {
        return utente;
    }

    public String getDataRegisto() {
        return dataRegisto;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setUtente(String utente) {
        this.utente = utente;
    }

    public void setIsbn(String tituloLivros) {
        this.isbn = tituloLivros;
    }

    public void setDataRegisto(String dataRegisto) {
        this.dataRegisto = dataRegisto;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    @Override
    public String toString() {
        return "Reserva N.º " + numero + "\n" +
                "Utente: " + utente + "\n" +
                "Livros: " + isbn + "\n" +
                "Data de Registo: " + dataRegisto + "\n" +
                "Data de Início: " + dataInicio + "\n" +
                "Data de Fim: " + dataFim + "\n";
    }
}
