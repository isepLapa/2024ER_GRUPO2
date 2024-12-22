import java.util.ArrayList;

    import java.util.ArrayList;
import java.util.Scanner;

public class gerenciamentoUtentes {
    Scanner sc = new Scanner(System.in);

        private ArrayList<utente> utentes = new ArrayList<>();

        public void removerUtente(){
            System.out.println("------------------------------------");
            listarUtentes();
            System.out.printf("Digite o NIF do Utente para deletar : ");
            String nifTemp = sc.next();
            for (utente utenteObj : utentes) {
                if (utenteObj.getNif().equals(nifTemp));
                utentes.remove(utenteObj);
                System.out.println(utenteObj.toString() + "\n" +"Removido com sucesso");
            }
        }

        public void adicionarUtente(utente utente) {
            utentes.add(utente);
        }

//        public void removerUtente(utente utente) {
//            System.out.printf("");
//            utentes.remove(utente);
//        } NÃO APAGAR !!!!!!!

        public void listarUtentes() {

//            for (int i = 0; i < utentes.size(); i++) {
//                System.out.println("Utente " + i++ + utentes.get(i));
//            }

            int x = 1;
            for (utente utente: utentes) {
                System.out.println("Utente " + x + "\n" + utente.toString());
                x++;
            }


        }
    }

