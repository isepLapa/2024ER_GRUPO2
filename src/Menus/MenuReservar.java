package Menus;

import Gerenciamento.Biblioteca;

import java.util.Scanner;

public class MenuReservar extends Menu {

    private final Biblioteca biblioteca;

    public MenuReservar(Biblioteca biblioteca) {
        super("Reservas" ,new String[]{"Reservar"}, biblioteca, true);
        this.biblioteca = biblioteca;
        this.Menu();
    }


    private void Menu() {
        renderMenu();
        switch (validateUserInput()) {
            case 1:
                System.out.println("reservar jdnajds");
                break;

            default:
                System.out.println("Opção invalida");
                break;
        }
    }
}
