package Menus;

import Gerenciamento.Biblioteca;

import java.util.Scanner;

public class MenuEmprestimos {

    private final Biblioteca biblioteca;

    public MenuEmprestimos(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
        this.Menu();
    }


    private void Menu() {
        System.out.println("Menu Emprestimos");

        int op = 0;

        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("\nGERINDO EMPRESTIMOS");
            System.out.println("1 - Adicionar Emprestimos");
            System.out.println("2 - Remover Emprestimos");
            System.out.println("3 - Mostrar Emprestimos");
            System.out.println("4 - Alterar Emprestimos");
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
