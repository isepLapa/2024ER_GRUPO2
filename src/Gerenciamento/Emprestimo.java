package Gerenciamento;

public class Emprestimo {
    private int numero;
    private String dataInicio;
    private String utente;
    private String dataPrevistaDevolucao;
    private String dataEfetivaDevolucao;
    private String livros;

    public Emprestimo(int numero, String dataInicio, String utente, String dataPrevistaDevolucao, String dataEfetivaDevolucao, String livros) {
        this.numero = numero;
        this.dataInicio = dataInicio;
        this.utente = utente;
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
        this.dataEfetivaDevolucao = dataEfetivaDevolucao;
        this.livros = livros;
    }

    public Emprestimo() {
    }

    public String getNumeroString() {
        return String.valueOf(numero);
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getUtente() {
        return utente;
    }

    public void setUtente(String utente) {
        this.utente = utente;
    }

    public String getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }

    public void setDataPrevistaDevolucao(String dataPrevistaDevolucao) {
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
    }

    public String getDataEfetivaDevolucao() {
        return dataEfetivaDevolucao;
    }

    public void setDataEfetivaDevolucao(String dataEfetivaDevolucao) {
        this.dataEfetivaDevolucao = dataEfetivaDevolucao;
    }

    @Override
    public String toString() {
        return "numero=" + numero + " " +
                "dataInicio=" + dataInicio + " " +
                "utente=" + utente + " " +
                "dataPrevistaDevolucao=" + dataPrevistaDevolucao + " " +
                "dataEfetivaDevolucao=" + dataEfetivaDevolucao + " " +
                "livros=" + livros;
    }
}