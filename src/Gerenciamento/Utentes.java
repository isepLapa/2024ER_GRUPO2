package Gerenciamento;

import java.util.ArrayList;

import java.util.Scanner;

public class Utentes {
    Scanner sc = new Scanner(System.in);

    private ArrayList<Utente> utentes = new ArrayList<>();

    public void removerUtente(){
        System.out.println("------------------------------------");
        listarUtentes();
        System.out.printf("Digite o NIF do Gerenciamento.Utente para deletar : ");
        String nifTemp = sc.next();
        for (Utente utenteObj : utentes) {
            if (utenteObj.getNif().equals(nifTemp))
                utentes.remove(utenteObj);
            System.out.println(utenteObj.toString() + "\n" +"Removido com sucesso");
        }
    }

    public void adicionarUtente(Utente utente) {
        utentes.add(utente);
    }
//        public void removerUtente(utente utente) {
//            System.out.printf("");
//            utentes.remove(utente);
//        } NÃO APAGAR !!!!!!!

    public void listarUtentes() {

//            for (int i = 0; i < utentes.size(); i++) {
//                System.out.println("Gerenciamento.Utente " + i++ + utentes.get(i));
//            }

        int x = 1;
        for (Utente utente: utentes) {
            System.out.println("Gerenciamento.Utente " + x + "\n" + utente.toString());
            x++;
        }
    }
}

