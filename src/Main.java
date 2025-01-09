import Gerenciamento.Biblioteca;
import Menus.*;
import Storage.Storage;
import Utils.Utils;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Storage storage = new Storage();
        Menu menu = new Menu(storage);

        List<String> bibliotecas = storage.getBibliotecas();

        if (bibliotecas.isEmpty()) {
            System.out.println("Nenhuma Biblioteca encontrada, deseja criar uma nova? (S/N)");
            String resposta = sc.nextLine();
            if (resposta.equalsIgnoreCase("S")) {
                System.out.print("Escreve o nome da biblioteca: ");
                storage.createBiblioteca(sc.nextLine());
                bibliotecas = storage.getBibliotecas(); // Atualizar lista de bibliotecas
            }
        }

        System.out.println("Selecione uma biblioteca:");

        int escolha = Utils.TransformarListaEmEscolha("Escolha uma biblioteca: ", bibliotecas);

        menu.inicio(bibliotecas.get(escolha));
    }
}