package Gerenciamento;

import Storage.Storage;
import Utils.Utils;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reservas {
    Scanner sc = new Scanner(System.in);
    private final Storage storage;
    private final Path reservasPath;
    private List<Reserva> reservas = new ArrayList<>();
    private Biblioteca biblioteca;


    public Reservas(Biblioteca biblioteca, String nomeBiblioteca, Storage storage) {
        this.biblioteca = biblioteca;
        this.storage = storage;

        this.reservasPath = Path.of(nomeBiblioteca + "/reservas.txt");
        this.reservas = this.getReservas();
    }

    public boolean addReserva() {
        String utente = Utils.ScanString("Nif do Utente: ");
        if (!biblioteca.utentes.verificarNifUtentesNaLista(utente)) {
            System.out.println("Erro ao adicionar reserva: Nif não encontrado.");
            return false;
        }

        String tituloLivro = Utils.ScanString("Isbn: ");
        if (!Livros.verificarIsbnNaLista(tituloLivro)) {
            System.out.println("Erro ao adicionar reserva: Isbn não encontrado.");
            return false;
        }

        String dataInicioInput = Utils.validarData("Data de inicio (dd/mm/yyyy): ");

        String dataFimInput = Utils.validarData("Data de Fim (dd/mm/yyyy): ");

        // Validar as datas
        if (LocalDate.parse(dataInicioInput).isAfter(LocalDate.parse(dataFimInput))) {
            System.out.println("Erro: A data de início deve ser anterior ou igual à data de fim.");
            return false;
        }

        // Criar e adicionar a reserva
        int numero = reservas.size() + 1; // Número auto-incrementado da reserva
        LocalDate dataRegisto = LocalDate.now(); // Data atual
        Reserva novaReserva = new Reserva(numero, utente, tituloLivro, dataRegisto.toString(), dataInicioInput, dataFimInput);
        reservas.add(novaReserva);
        System.out.println("Reserva adicionada com sucesso!");

        this.storage.save(this.reservasPath, reservas);
        return true;
    }

    private LocalDate validarDataSemTry(String dataInput) {
        if (dataInput == null || dataInput.isEmpty()) {
            return null;
        }
        if (!dataInput.matches("\\d{4}-\\d{2}-\\d{2}")) {
            return null;
        }
        return LocalDate.parse(dataInput);
    }

    // Method to list all reservations
    public void listarReservas() {
        if (reservas.isEmpty()) {
            System.out.println("Não há reservas registradas.");
            return;
        }

        for (Reserva reserva : reservas) {
            System.out.println(reserva);
        }
    }

    // Method to find a reservation by its number
    public Reserva buscarReserva(int numero) {
        for (Reserva reserva : reservas) {
            if (reserva.getNumero() == numero) {
                return reserva;
            }
        }
        System.out.println("Reserva com número " + numero + " não encontrada.");
        return null;
    }

    // Method to remove a reservation by its number
    public boolean removerReserva() {
        if (reservas.isEmpty()) {
            System.out.println("Não há reservas registradas.");
            return false;
        }

        int numero = Utils.ScanInt("Digite o número da reserva que deseja remover: ");
        Reserva reserva = buscarReserva(numero);
        if (reserva != null) {
            reservas.remove(reserva);
            System.out.println("Reserva N.º " + numero + " removida com sucesso.");
            this.storage.save(this.reservasPath, reservas);
            return true;
        }
        System.out.println("Não foi possível remover a reserva. Número não encontrado.");
        return false;
    }

    public boolean alterarReserva() {
        if (reservas.isEmpty()) {
            System.out.println("Não há reservas registradas.");
            return false;
        }

        int numero = Utils.ScanInt("Digite o número da reserva que deseja alterar: ");
        Reserva reserva = buscarReserva(numero);
        if (reserva == null) {
            System.out.println("Reserva com número " + numero + " não encontrada.");
            return false;
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("Escolha o campo que deseja alterar:");
        System.out.println("1 - Nif do Utente");
        System.out.println("2 - ISBN do Livro");
        System.out.println("3 - Data de Início");
        System.out.println("4 - Data de Fim");

        int op = sc.nextInt();
        sc.nextLine();

        switch (op) {
            case 1:
                reserva.setUtente(Utils.ScanString("Novo Nif do Utente: "));
                break;
            case 2:
                reserva.setIsbn(Utils.ScanString("Novo ISBN do Livro: "));
                break;
            case 3:
                reserva.setDataInicio(Utils.validarData("Nova data Inicio"));
                break;
            case 4:
                reserva.setDataFim(Utils.validarData("Nova data Inicio"));
                break;
            default:
                System.out.println("Opção inválida! Por favor, escolha entre 1 e 4.");
                return false;
        }

        this.storage.save(this.reservasPath, reservas);
        System.out.println("Reserva alterada com sucesso!");
        return true;
    }

    public List<Reserva> getReservas() {
        if(reservas.isEmpty()) {
            return this.convertToReservasList(storage.get(this.reservasPath));
        }

        return reservas;
    }

    /**
     * Converte uma lista de strings em uma lista de objetos Reservas.
     *
     * @param reservasStr a lista de strings dos livros
     * @return uma lista de objetos Reservas
     */
    public List<Reserva> convertToReservasList(List<String> reservasStr) {
        List<Reserva> reservas = new ArrayList<>();
        for (String reservaStr : reservasStr) {
            reservas.add(parseReserva(reservaStr));
        }
        return reservas;
    }

    private Reserva parseReserva(String reserva) {
        String[] partesReserva = reserva.split(" ");
        int numero = Integer.parseInt(partesReserva[0].split("=")[1]);
        String utente = partesReserva[1].split("=")[1];
        String tituloLivro = partesReserva[2].split("=")[1];
        String dataRegisto = partesReserva[3].split("=")[1];
        String dataInicio = partesReserva[4].split("=")[1];
        String dataFim = partesReserva[5].split("=")[1];

        return new Reserva(numero, utente, tituloLivro, dataRegisto, dataInicio, dataFim);
    }

    public Boolean reservaExiste(String isbn) {
        for (Reserva reserva : reservas) {
            if (reserva.getIsbn().contains(isbn)) {
                return true;
            }
        }
        System.out.println("Reserva com ISBN " + isbn + " não encontrada.");
        return false;
    }

}


