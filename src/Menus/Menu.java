package Menus;

import Gerenciamento.Biblioteca;

import java.util.Scanner;

public class Menu {

    private final String[] options;
    private final String title;
    private Biblioteca biblioteca;

    public Menu(String title, String[] options, Biblioteca biblioteca) {
        this.options = options;
        this.title = title;
        this.biblioteca = biblioteca;
    }

    //mostra o menu
    public void renderMenu() {
        int x = 1;
        Utils.Utils.printTituloPagina(title);
        for (String option : options) {
            System.out.println(x + " - " + option);
            x++;
        }
        System.out.println(x + " - Voltar");
    }

    //retorna uma opçao valida introduzida pelo usuario
    public int validateUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduza uma opção: ");

        int op = 0;

        if (scanner.hasNextInt()) {
            op = scanner.nextInt();

            if (op == options.length+1)
                new MenuBiblioteca(biblioteca);
            else if (op <= options.length)
                return op;
        }
        return 0;
    }

}
