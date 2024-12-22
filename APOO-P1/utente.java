import java.util.Scanner;

public class utente {
    private String nif;
    private String nome;
    private String genero;
    private String contacto;
// RESOLVER ISSO !!!!!!!
    public utente(String nome, String nif, String genero, String contacto) {
        if (!nifValido(nif)) {
            System.out.println("NIF inválido!");
            nifTemp1.getNif()

        }
        this.nome = nome;
        this.nif = nif;
        this.genero = genero;
        this.contacto = contacto;
    }

    public utente() {
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        if (!nifValido(nif)) {
            throw new IllegalArgumentException("NIF inválido!");
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
                "nif='" + nif + '\n' +
                "nome='" + nome + '\n' +
                "genero='" + genero + '\n' +
                "contacto='" + contacto;
    }


}
