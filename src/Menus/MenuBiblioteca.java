package Menus;


import Gerenciamento.Biblioteca;
import Storage.Storage;


public class MenuBiblioteca extends Menu{

    //Gerenciamento.Utentes gerenciamentoUtentes = new Gerenciamento.Utentes();
    //private Storage storage;

    private Biblioteca biblioteca;

    public MenuBiblioteca(Biblioteca biblioteca) {
        super("Biblioteca", new String[]{"Livros", "Jornais/Revistas", "Utentes", "Emprestimos", "Reservar"}, biblioteca);
        this.biblioteca = biblioteca;
        Menu();
    }

    public void Menu() {
        renderMenu();
        switch (validateUserInput()) {
            case 1:
                new MenuLivros(biblioteca);
                break;
            case 2:
                new MenuJornaisRevistas(biblioteca);
                break;
            case 3:
                new MenuUtentes(biblioteca);
                break;
            case 4:
                new MenuEmprestimos(biblioteca);
                break;
            case 5:
                new MenuReservar(biblioteca);
                break;
            default:
                System.out.printf("opção invalida");
                break;
        }
        Menu();
    }
}
