package Menus;

import Gerenciamento.Biblioteca;
import Gerenciamento.Bibliotecas;
import Gerenciamento.Utentes;
import Storage.Storage;

public class MenuAdmin extends Menu {

    private final Bibliotecas bibliotecas;

    public MenuAdmin(Bibliotecas bibliotecas, Storage storage) {
        super( "Admin", new String[] {"Adicionar Biblioteca", "Remover Biblioteca", "Voltar"}, null, false);
        this.bibliotecas = bibliotecas;
        Menu();
    }

    private void Menu() {
        renderMenu();
        switch (validateUserInput()) {
            case 1:
                bibliotecas.adicionarBiblioteca();
                break;
            case 2:
                bibliotecas.eliminarBiblioteca();
                break;
            case 3:
                new MenuBibliotecas();
                break;
            default:
                System.out.println("opção invalida");
                break;
        }
        Menu();
    }

}
