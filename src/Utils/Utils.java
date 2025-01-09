package Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

    public static int ScanInt(String mensagem) {
        Scanner sc = new Scanner(System.in);
        System.out.println(mensagem);
        return sc.nextInt();
    }
    public static String validarVazio(String mensagem) {
        String var;
        do {
            var = Utils.ScanString(mensagem);
            if (var.isBlank()) {
                System.out.println("Este campo é obrigatório, por favor introduza um valor válido.");
            }
        } while (var.isBlank());
        return var;
    }
    public static String validarIssn(String mensagem) {
        String var;
        do {
            var = Utils.ScanString(mensagem);
            if (var.isBlank()) {
                System.out.println("Este campo é obrigatório, por favor introduza um valor válido.");
            } else if (!var.matches("\\d{4}-\\d{3}[\\dXx]")) {
                System.out.println("Por favor, insira o ISSN no formato 0000-000X.");
            } else {
                return var;
            }
        } while (true);
    }

    public static String validarData(String mensagem) {
        String data;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        do {
            data = Utils.ScanString(mensagem);
            if (data.isBlank()) {
                System.out.println("Este campo é obrigatório, por favor introduza uma data válida.");
            } else {
                try {
                    LocalDate.parse(data, formatter);
                    return data; // Retorna a data válida
                } catch (DateTimeParseException e) {
                    System.out.println("Por favor, insira a data no formato dd/MM/yyyy.");
                }
            }
        } while (true);
    }
}
