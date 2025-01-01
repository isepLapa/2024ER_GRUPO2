package Utils;

import Gerenciamento.Livro;

import java.util.List;
import java.util.Scanner;

public class Utils {

    /**
     * Transforma uma lista de strings em uma escolha de índice.
     *
     * @param printMensagem a mensagem a ser impressa antes da escolha para o utilizador
     * @param lista a lista de strings a ser transformada em escolha
     * @return o índice escolhido pelo utilizador, ou 0 se a lista estiver vazia ou a escolha for inválida
     *
     * @Example Validaçao em caso de null:
     * <br>
     * <code>if (escolha returns 0) <br>
     *      System.out.println("Não existe informação para mostrar.");</code>
     */
    public static int TransformarListaEmEscolha(String printMensagem, List<String> lista) {

        if (lista.isEmpty()) {
            System.out.println("Não existe informação para mostrar.");
            return 0;
        }

        for (int i = 0; i < lista.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + lista.get(i));
        }

        Scanner sc = new Scanner(System.in);
        System.out.println(printMensagem);
        int escolha = sc.nextInt();
        sc.nextLine(); // Consumir a quebra de linha

        do {
            if (escolha < 1 || escolha > lista.size()) {
                System.out.println("Escolha inválida! Por favor, escolha um número entre 1 e " + lista.size() + ".");
                escolha = sc.nextInt();
                sc.nextLine(); // Consumir a quebra de linha
            }
        } while (escolha < 1 || escolha > lista.size());

        return (escolha - 1);
    }

    public static void printTituloPagina(String titulo) {
        System.out.println("-".repeat(26 + titulo.length()));
        System.out.println("|".repeat(2) + " ".repeat(22 + titulo.length()) + "|".repeat(2));
        System.out.println("|".repeat(2) + " ".repeat(11) + titulo + " ".repeat(11) + "|".repeat(2));
        System.out.println("|".repeat(2) + " ".repeat(22 + titulo.length()) + "|".repeat(2));
        System.out.println("-".repeat(26 + titulo.length()));
    }

    public static String ScanString(String mensagem) {
        Scanner sc = new Scanner(System.in);
        System.out.println(mensagem);
        return sc.nextLine();
    }
}
