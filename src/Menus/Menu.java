package Menus;

import Gerenciamento.Biblioteca;
import Storage.Storage;
import Utils.Utils;
import java.util.Scanner;

public class Menu {

    private final String[] options;
    private final String title;
    private final Biblioteca biblioteca;
    private final boolean podeVoltar;

    public Menu(String title, String[] options, Biblioteca biblioteca, boolean podeVoltar) {
        this.options = options;
        this.title = title;
        this.biblioteca = biblioteca;
        this.podeVoltar = podeVoltar;

    }

    //mostra o menu
    public void renderMenu() {
        int x = 1;
        Utils.printTituloPagina(title);
        for (String option : options) {
            System.out.println(x + " - " + option);
            x++;
        }
        if (this.podeVoltar) {
            System.out.println(x + " - Voltar");
        }
    }

    //retorna uma opçao valida introduzida pelo usuario
    public int validateUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduza uma opção: ");

        int op = 0;

        if (scanner.hasNextInt()) {
            op = scanner.nextInt();

            if (this.podeVoltar && op == options.length+1)
                new MenuBiblioteca(biblioteca);
            else if (op <= options.length)
                return op;
        }
        return 0;
    }

}
