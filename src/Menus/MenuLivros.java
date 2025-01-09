package Menus;

import Gerenciamento.Biblioteca;
import Utils.Utils;

import java.util.Scanner;

public class MenuLivros extends Menu {

    private final Biblioteca biblioteca;

    public MenuLivros(Biblioteca biblioteca) {
        super("Livros", new String[]{"Adicionar Livros", "Remover Livros", "Mostrar Livros", "Alterar Livros"});
        this.biblioteca = biblioteca;
        this.Menu();
    }


    private void Menu() {
        renderMenu();
        switch (validateUserInput()) {
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            default:
                System.out.println("opção invalida");
                break;
        }

        Menu();
    }
}
