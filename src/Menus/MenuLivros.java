package Menus;

import Gerenciamento.Biblioteca;
import Utils.Utils;

import java.util.Scanner;

public class MenuLivros extends Menu {

    private final Biblioteca biblioteca;

    public MenuLivros(Biblioteca biblioteca) {
        super("Livros", new String[]{"Adicionar Livros", "Remover Livros", "Mostrar Livros", "Alterar Livros"}, biblioteca);
        this.biblioteca = biblioteca;
        this.Menu();
    }


    private void Menu() {
        renderMenu();
        switch (validateUserInput()) {
            case 1:
                biblioteca.livros.AdicionarLivro();
                break;
            case 2:
                biblioteca.livros.RemoverLivro();
                break;
            case 3:
                biblioteca.livros.ListarLivros();
                break;
            case 4:
                biblioteca.livros.AlterarLivro();
                break;
            default:
                System.out.println("opção invalida");
                break;
        }

        Menu();
    }
}
