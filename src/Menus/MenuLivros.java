package Menus;

import java.util.Scanner;

public class MenuLivros {

    public MenuLivros() {
        this.Menu();
    }


    private void Menu() {
        System.out.println("Menu Livros");

        int op = 0;

        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("\nGERINDO LIVROS");
            System.out.println("1 - Adicionar Livro");
            System.out.println("2 - Remover Livro");
            System.out.println("3 - Mostrar Livro");
            System.out.println("4 - Alterar Livro");
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

                    break;
                case 2:

                    break;

                case 3:

                    break;
                case 4:

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
