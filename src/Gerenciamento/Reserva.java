package Gerenciamento;

import java.time.LocalDate;
import java.util.List;

public class Reserva {
    private int numero;
    private String utente;
    private String isbn;
    private LocalDate dataRegisto;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    public Reserva(int numero, String utente, String isbn, LocalDate dataRegisto, LocalDate dataInicio, LocalDate dataFim) {
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

    public LocalDate getDataRegisto() {
        return dataRegisto;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataFim() {
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

    public void setDataRegisto(LocalDate dataRegisto) {
        this.dataRegisto = dataRegisto;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public void setDataFim(LocalDate dataFim) {
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
