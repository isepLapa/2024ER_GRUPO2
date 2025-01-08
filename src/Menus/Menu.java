package Menus;

import java.util.Scanner;

public class Menu {

    private final String[] options;
    private String title;

    public Menu(String title,String[] options) {
        this.options = options;
        this.title = title;
    }

    //mostra o menu
    public void renderMenu() {
        int x = 1;
        Utils.Utils.printTituloPagina(title);
        for (String option : options) {
            System.out.println(x + " - " + option);
            x++;
        }
    }

    //retorna uma opçao valida introduzida pelo usuario
    public int validateUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduza uma opção: ");

        if (scanner.hasNextLine() || scanner.nextInt() <= options.length)
            return scanner.nextInt();
        return 0;
    }

}
