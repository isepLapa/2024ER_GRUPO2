package Menus;
import Gerenciamento.Biblioteca;

import java.util.Scanner;

public class MenuJornaisRevistas extends Menu {

    private final Biblioteca biblioteca;

    public MenuJornaisRevistas(Biblioteca biblioteca) {
        super("Jornais/Revistas", new String[]{"Adicionar", "Editar", "Eliminar", "Listar"}, biblioteca);
        this.biblioteca = biblioteca;
        this.Menu();
    }

    private void Menu() {
        renderMenu();
        switch (validateUserInput()) {
            case 1:
                biblioteca.revistajornais.adicionarRevista();
                break;
            case 2:
                biblioteca.revistajornais.alterarRevista();
                break;
            case 3:
                biblioteca.revistajornais.removerRevista();
                break;
            case 4:
                biblioteca.revistajornais.listarRevistas();
                break;
            default:
                System.out.println("opção invalida");
                break;
        }

        Menu();
    }
}
