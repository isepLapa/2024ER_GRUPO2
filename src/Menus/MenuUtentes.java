package Menus;

import Gerenciamento.Biblioteca;
import Gerenciamento.Utentes;

public class MenuUtentes extends Menu {

    private final Biblioteca biblioteca;
    private Utentes utentes;

    public MenuUtentes(Biblioteca biblioteca) {
        super( "Utentes", new String[] {"Adicionar Utente", "Remover Utente", "Alterar Utente", "Listar Utentes"});
        this.biblioteca = biblioteca;
        utentes = new Utentes();
        Menu();
    }

    private void Menu() {
        renderMenu();
        switch (validateUserInput()) {
            case 1:
                utentes.adicionarUtente();
                break;
            case 2:
                utentes.removerUtente();
                break;
            case 3:

                break;
            case 4:
                utentes.listarUtentes();
                break;
            default:
                System.out.println("opção invalida");
                Menu();
                break;
        }

        Menu();
    }

}
