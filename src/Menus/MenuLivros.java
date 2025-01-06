package Menus;

import Gerenciamento.Biblioteca;
import Utils.Utils;

import java.util.Scanner;

public class MenuLivros {

    private final Biblioteca biblioteca;

    public MenuLivros(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
        this.Menu();
    }


    private void Menu() {
        System.out.println("Menu Livros");

        int op = 0;

        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("\nGERINDO LIVROS");
            System.out.println("1 - Adicionar Livros");
            System.out.println("2 - Remover Livros");
            System.out.println("3 - Mostrar Livros");
            System.out.println("4 - Alterar Livros");
            System.out.println("5 - Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            if (sc.hasNextInt()) {
                op = sc.nextInt();
                sc.nextLine(); // Consumir a quebra de linha
            } else {
                System.out.println("Entrada inválida! Por favor, insira um número.");
                sc.next(); // Limpar entrada inválida
                continue;
            }

            switch (op) {
                case 1:
                    Utils.printTituloPagina("Adicionar Livro");
                    biblioteca.livros.AdicionarLivro();
                    break;
                case 2:
                    Utils.printTituloPagina("Remover Livro");
                    biblioteca.livros.RemoverLivro();
                    break;

                case 3:
                    Utils.printTituloPagina("Mostrar Livros");
                    biblioteca.livros.ListarLivros();
                    break;
                case 4:
                    Utils.printTituloPagina("Alterar Livro");
                    biblioteca.livros.AlterarLivro();
                    break;

                case 5:
                    System.out.println("Voltando ao Menu Principal...");
                    break;

                default:
                    System.out.println("Opção inválida! Por favor, escolha entre 1 e 5.");
            }
        } while (op != 5);
    }
}
