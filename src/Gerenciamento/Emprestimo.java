package Gerenciamento;

public class Emprestimo {
    private int numero;
    private String dataInicio;
    private String nif;
    private String dataPrevistaDevolucao;
    private String dataEfetivaDevolucao;
    private String livros;

    public Emprestimo(int numero, String dataInicio, String nif, String dataPrevistaDevolucao, String dataEfetivaDevolucao, String livros) {
        this.numero = numero;
        this.dataInicio = dataInicio;
        this.nif = nif;
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

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
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
                "nif=" + nif + " " +
                "dataPrevistaDevolucao=" + dataPrevistaDevolucao + " " +
                "dataEfetivaDevolucao=" + dataEfetivaDevolucao + " " +
                "livros=" + livros;
    }
}