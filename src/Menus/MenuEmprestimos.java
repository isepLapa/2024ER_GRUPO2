package Menus;

import Gerenciamento.Biblioteca;

import java.util.Scanner;

public class MenuEmprestimos extends Menu {

    private final Biblioteca biblioteca;


    public MenuEmprestimos(Biblioteca biblioteca) {
        super("Emprestimos", new String[]{"Adicionar", "Remover", "Listar", "Editar", "Utentes com atraso"}, biblioteca, true);
        this.biblioteca = biblioteca;
        this.Menu();
    }


    private void Menu() {
        renderMenu();
        switch (validateUserInput()) {
            case 1:
                biblioteca.emprestimos.AdicionarEmprestimo();
                break;
            case 2:
                biblioteca.emprestimos.RemoverEmprestimo();
                break;
            case 3:
                biblioteca.emprestimos.ListarEmprestimos();
                break;
            case 4:
                biblioteca.emprestimos.AlterarEmprestimo();
                break;
            case 5:
                biblioteca.emprestimos.listarUtentesComAtraso();
                break;
            default:
                System.out.println("opção invalida");
                break;
        }

        Menu();
    }
}
