package Menus;

import Gerenciamento.Biblioteca;
import Gerenciamento.Utentes;

public class MenuUtentes extends Menu {

    private final Biblioteca biblioteca;

    public MenuUtentes(Biblioteca biblioteca) {
        super( "Utentes", new String[] {"Adicionar Utente", "Remover Utente", "Alterar Utente", "Listar Utentes"});
        this.biblioteca = biblioteca;
        Menu();
    }

    private void Menu() {
        renderMenu();
        switch (validateUserInput()) {
            case 1:
                biblioteca.utentes.adicionarUtente();
                break;
            case 2:
                biblioteca.utentes.removerUtente();
                break;
            case 3:

                break;
            case 4:
                biblioteca.utentes.listarUtentes();
                break;
            default:
                System.out.println("opção invalida");
                break;
        }
        Menu();
    }

}
