package Gerenciamento;

import java.time.LocalDate;
import java.util.List;

public class Reserva {
    private int numero;
    private String utente;
    private String tituloLivros;
    private LocalDate dataRegisto;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String Isbn;

    public Reserva(int numero, String utente, String tituloLivro, LocalDate dataRegisto, LocalDate dataInicio, LocalDate dataFim) {
        this.numero = numero;
        this.utente = utente;
        this.tituloLivros = tituloLivro;
        this.dataRegisto = dataRegisto;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public int getNumero() {
        return numero;
    }

    public String getUtente() {
        return utente;
    }

//    public List<String> getLivros() {
//        return livros;
//    }
    public String getIsbn() {
        return Isbn;
    }
    public LocalDate getDataRegisto() {
        return dataRegisto;
    }

    public String getTituloLivros() { return tituloLivros; }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    @Override
    public String toString() {
        return "Reserva N.º " + numero + "\n" +
                "Utente: " + utente + "\n" +
                "Livros: " + tituloLivros + "\n" +
                "Data de Registo: " + dataRegisto + "\n" +
                "Data de Início: " + dataInicio + "\n" +
                "Data de Fim: " + dataFim + "\n";
    }
}
