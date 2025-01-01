// Importa tudo do package Menus
import Gerenciamento.Biblioteca;
import Menus.*;
import Storage.Storage;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Storage storage = new Storage();
        Menu menu = new Menu(storage);

        List<String> bibliotecas = storage.getBibliotecas();

        if(bibliotecas.isEmpty()) {
            System.out.println("Nenhuma Biblioteca encontrada, deseja criar uma nova? (S/N)");

            String resposta = sc.nextLine();

            if(resposta.equalsIgnoreCase("S")) {
                System.out.print("Escreve o nome da biblioteca: ");
                storage.createBiblioteca(sc.nextLine());
                sc.close();
            }
            return;
        }


        System.out.println("Selecione uma biblioteca:");
        for (int i = 0; i < bibliotecas.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + bibliotecas.get(i));
        }

        int escolha = 0;
        do {
            System.out.print("\n\nDigite o número da biblioteca que quer gerir: ");
            if (sc.hasNextInt()) {
                escolha = sc.nextInt();
                sc.nextLine(); // Consumir a quebra de linha
                if (escolha < 1 || escolha > bibliotecas.size()) {
                    System.out.println("Opção inválida! Por favor, escolha um número entre 1 e " + bibliotecas.size() + ".");
                }
            } else {
                System.out.println("Entrada inválida! Por favor, insira um número.");
                sc.next(); // Limpar entrada inválida
            }
        } while (escolha < 1 || escolha > bibliotecas.size());

        System.out.println("Biblioteca selecionada: " + bibliotecas.get(escolha - 1));

        menu.inicio(bibliotecas.get(escolha - 1));
    }
}