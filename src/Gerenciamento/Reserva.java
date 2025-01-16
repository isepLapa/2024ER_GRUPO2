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

    public String getLivros() {
        return tituloLivros;
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

    @Override
    public String toString() {
        return "Reserva N.º " + numero + "\n" +
                "UUtentetente: " + utente + "\n" +
                "Livros: " + tituloLivros + "\n" +
                "Data de Registo: " + dataRegisto + "\n" +
                "Data de Início: " + dataInicio + "\n" +
                "Data de Fim: " + dataFim + "\n";
    }
}
