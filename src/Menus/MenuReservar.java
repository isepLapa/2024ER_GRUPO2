package Menus;

import Gerenciamento.Biblioteca;
import Gerenciamento.Reserva;
import Gerenciamento.Reservas;
import Utils.Utils;

import java.util.Scanner;

public class MenuReservar {
    private final Biblioteca biblioteca;

    public MenuReservar(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
        this.Menu();
    }


    private void Menu() {
        Reservas Reservas = new Reservas();
        System.out.println("Menu Reservar");

        int op = 0;

        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("\nGERINDO RESERVAR");
            System.out.println("1 - Adicionar Reserva");
            System.out.println("2 - Remover Reserva");
            System.out.println("3 - Mostrar Reserva");
            System.out.println("4 - Alterar Reserva");
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
                    Utils.printTituloPagina("Adicionar Reserva");
                    Reservas.addReserva();
                    break;
                case 2:
                    Utils.printTituloPagina("Remover Reserva");
                    Reservas.removerReserva();
                    break;

                case 3:
                    Utils.printTituloPagina("Mostrar Reserva");
                    Reservas.listarReservas();
                    break;
                case 4:
                    Utils.printTituloPagina("Alterar Reserva");
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
