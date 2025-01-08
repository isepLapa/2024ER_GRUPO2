package Menus;


import Gerenciamento.Biblioteca;
import Storage.Storage;


public class MenuBiblioteca extends Menu{

    Gerenciamento.Utentes gerenciamentoUtentes = new Gerenciamento.Utentes();
    private Storage storage;

    public MenuBiblioteca(Storage storage) {
        super("Bibliotecas", new String[]{"Livros", "Jornais/Revistas", "Utentes", "Emprestimos", "Reservar"});
        this.storage = storage;
    }

    public void inicio(String nomeBiblioteca) {
        Biblioteca biblioteca = new Biblioteca(nomeBiblioteca, storage);
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
                inicio(nomeBiblioteca);
                break;
        }



        /*Biblioteca biblioteca = new Biblioteca(nomeBiblioteca, storage);
        int op = 0;
        Scanner sc = new Scanner(System.in);

        System.out.print("\033[H\033[2J");
        System.out.flush();

        do {
            System.out.println("-------------------------------------");
            System.out.println("      GERINDO BIBLIOTECA: " + nomeBiblioteca);
            System.out.println("-------------------------------------");


            System.out.println("\nEscolha uma opção:");
            System.out.println("1 - Livros");
            System.out.println("2 - Jornais/Revistas");
            System.out.println("3 - Utentes");
            System.out.println("4 - Empréstimo");
            System.out.println("5 - Reservar");
            System.out.println("6 - Sair");
            System.out.print("Insira a opção que deseja realizar: ");

            if (sc.hasNextInt()) {
                op = sc.nextInt();
                sc.nextLine(); // Consumir a quebra de linha
            } else {
                System.out.println("Entrada inválida! Por favor, insira um número.");
                sc.next(); // Limpar entrada inválida
                continue;
            }

            switch (op) {
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
                case 6:
                    System.out.println("Saindo do programa. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida! Por favor, escolha entre 1 e 6.");
            }
        } while (op != 6);

        sc.close();*/
    }
}
