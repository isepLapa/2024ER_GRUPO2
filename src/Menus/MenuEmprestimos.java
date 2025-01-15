package Menus;

import Gerenciamento.Biblioteca;

import java.util.Scanner;

public class MenuEmprestimos extends Menu {

    private final Biblioteca biblioteca;


    public MenuEmprestimos(Biblioteca biblioteca) {
        super("Emprestimo", new String[]{"Adicionar", "Remover", "Listar", "Editar"}, biblioteca, true);
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
