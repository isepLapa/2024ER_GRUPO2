package Gerenciamento;

import Utils.Utils;

import java.util.ArrayList;

import java.util.Scanner;

public class Utentes {
    Scanner sc = new Scanner(System.in);

    private ArrayList<Utente> utentes = new ArrayList<>();

    public void removerUtente(){
        System.out.println("------------------------------------");
        listarUtentes();
        System.out.println("Digite o NIF do Gerenciamento.Utente para deletar : ");
        String nifTemp = sc.next();
        for (Utente utenteObj : utentes) {
            if (utenteObj.getNif().equals(nifTemp)) {
                utentes.remove(utenteObj);
                System.out.println(utenteObj.toString() + "\n" +"Removido com sucesso");
                break;
            }
            else {
                System.out.println("O utilizador não existe");
            }
        }
    }

    public void adicionarUtente() {
        String nif = Utils.ScanString("introduza o nif");
        String name = Utils.ScanString("introduza o nome");
        String gender = Utils.ScanString("introduza o genero");
        String contact = Utils.ScanString("introduza o Contacto");
        Utente utente = new Utente(nif, name, gender, contact);
        utentes.add(utente);
    }

    public void listarUtentes() {
        int x = 1;
        for (Utente utente: utentes) {
            System.out.println("Gerenciamento.Utente " + x + "\n" + utente.toString());
            x++;
        }
    }
}

