package Menus;

import Gerenciamento.Biblioteca;
import Gerenciamento.Bibliotecas;
import Storage.Storage;

public class MenuBibliotecas extends Menu{

    private final Storage storage = new Storage();;
    private final Bibliotecas bibliotecas;

    public MenuBibliotecas() {
        super("Bibliotecas", new String[]{"Mostrar Bibliotecas", "Gerir"}, null, false);
        this.bibliotecas = new Bibliotecas(storage);

        this.Menu();
    }

    private void Menu() {
        renderMenu();
        switch (validateUserInput()) {
            case 1:
                bibliotecas.selecionarBiblioteca();
                new MenuBiblioteca(new Biblioteca(bibliotecas.listaBibliotecas().get(bibliotecas.getBibliotecaEscolhida()), storage));
                break;
            case 2:
                new MenuAdmin(bibliotecas, storage);
                break;
            case 3:

                break;
            case 4:
                break;
            default:
                break;
        }
        Menu();
    }
}
