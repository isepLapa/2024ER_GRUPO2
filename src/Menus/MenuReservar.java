package Menus;

import Gerenciamento.Biblioteca;
import Utils.Utils;

import java.util.Scanner;

public class MenuReservar extends Menu {

    private final Biblioteca biblioteca;

    public MenuReservar(Biblioteca biblioteca) {
        super("Reservas", new String[]{"Adicionar Reserva", "Remover Reserva", "Mostrar Reservas", "Alterar Reserva"}, biblioteca, true);
        this.biblioteca = biblioteca;
        this.Menu();
    }

    private void Menu() {
        renderMenu();
        switch (validateUserInput()) {
            case 1:
                biblioteca.reservas.addReserva();
                break;
            case 2:
                biblioteca.reservas.removerReserva();
                break;
            case 3:
                biblioteca.reservas.listarReservas();
                break;
            case 4:
                //biblioteca.reservas.();
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }

        Menu();
    }
}