import java.util.Scanner;

// Classe Menu corrigida e funcional
public class Menu {
    gerenciamentoUtentes gerenciamentoUtentes = new gerenciamentoUtentes();
    public void inicio() {
        int op = 0;
        Scanner sc = new Scanner(System.in);

        do {
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
                    submenu("Livros");
                    break;
                case 2:
                    submenu("Jornais/Revistas");
                    break;
                case 3:
                    submenu("Utentes");
                    break;
                case 4:
                    submenu("Empréstimo");
                    break;
                case 5:
                    submenu("Reservar");
                    break;
                case 6:
                    System.out.println("Saindo do programa. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida! Por favor, escolha entre 1 e 6.");
            }
        } while (op != 6);

        sc.close();
    }

    public void submenu(String titulo) {
        int op = 0;
        utente utenteObj = null; // Inicialmente nenhum utente é definido

        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("\nSubmenu - " + titulo + ":");
            System.out.println("1 - Adicionar " + titulo);
            System.out.println("2 - Remover " + titulo);
            System.out.println("3 - Mostrar " + titulo);
            System.out.println("4 - Alterar " + titulo);
            System.out.println("5 - Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

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
                    System.out.println("Adicionar " + titulo + " selecionado.");

                    if ("Utentes".equalsIgnoreCase(titulo)) {
                        System.out.print("Introduza o nome: ");
                        String nomeTemp = sc.nextLine();
                        System.out.print("Introduza o NIF: ");
                        String nifTemp = sc.nextLine();
                        System.out.print("Introduza o género: ");
                        String generoTemp = sc.nextLine();
                        System.out.print("Introduza o contacto: ");
                        String contactoTemp = sc.nextLine();

                        utenteObj = new utente(nomeTemp, nifTemp, generoTemp, contactoTemp);
                        gerenciamentoUtentes.adicionarUtente(utenteObj);
                    } else {
                        System.out.println("Função não implementada para " + titulo + ".");
                    }
                    break;

                case 2:
                    gerenciamentoUtentes.removerUtente();

                    break;

                case 3:
                    System.out.println("Mostrar " + titulo + " selecionado.");
                    gerenciamentoUtentes.listarUtentes();

                    /*if (utenteObj != null && "Utentes".equalsIgnoreCase(titulo)) {
                        System.out.println("Detalhes do Utente:");
                        System.out.println("Nome: " + utenteObj.getNome());
                        System.out.println("NIF: " + utenteObj.getNif());
                        System.out.println("Gênero: " + utenteObj.getGenero());
                        System.out.println("Contacto: " + utenteObj.getContacto());
                    } else {
                        System.out.println("Nenhum " + titulo + " foi adicionado ainda.");
                    }*/
                    break;
                case 4:
                    System.out.println("Alterar " + titulo + " selecionado.");

                    if (utenteObj != null && "Utentes".equalsIgnoreCase(titulo)) {
                        System.out.print("Introduza o novo nome: ");
                        utenteObj.setNome(sc.nextLine());
                        System.out.print("Introduza o novo NIF: ");
                        utenteObj.setNif(sc.nextLine());
                        System.out.print("Introduza o novo género: ");
                        utenteObj.setGenero(sc.nextLine());
                        System.out.print("Introduza o novo contacto: ");
                        utenteObj.setContacto(sc.nextLine());
                        System.out.println("Utente alterado com sucesso!");
                    } else {
                        System.out.println("Nenhum " + titulo + " foi adicionado ainda.");
                    }
                    break;

                case 5:
                    System.out.println("Voltando ao Menu Principal...");
                    break;

                default:
                    System.out.println("Opção inválida! Por favor, escolha entre 1 e 5.");
            }
        } while (op != 5);
    }
}