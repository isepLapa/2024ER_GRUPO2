package Gerenciamento;
import java.time.format.DateTimeFormatter;

import Utils.Utils;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reservas {
    Scanner sc = new Scanner(System.in);
    private List<Reserva> listaReservas = new ArrayList<>();
    private Biblioteca biblioteca;

    public Reservas(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;

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

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    String dataInicioInput = Utils.ScanString("Data de início (dd/MM/yyyy): ");
    LocalDate dataInicio;
    try {
        dataInicio = LocalDate.parse(dataInicioInput, formatter);
    } catch (Exception e) {
        System.out.println("Erro ao adicionar reserva: Data de início inválida.");
        return false;
    }

    String dataFimInput = Utils.ScanString("Data de fim (dd/MM/yyyy): ");
    LocalDate dataFim;
    try {
        dataFim = LocalDate.parse(dataFimInput, formatter);
    } catch (Exception e) {
        System.out.println("Erro ao adicionar reserva: Data de fim inválida.");
        return false;
    }

    // Validar as datas
    if (dataInicio.isAfter(dataFim)) {
        System.out.println("Erro: A data de início deve ser anterior ou igual à data de fim.");
        return false;
    }

    // Criar e adicionar a reserva
    int numero = listaReservas.size() + 1; // Número auto-incrementado da reserva
    LocalDate dataRegisto = LocalDate.now(); // Data atual
    Reserva novaReserva = new Reserva(numero, utente, tituloLivro, dataRegisto, dataInicio, dataFim);
    listaReservas.add(novaReserva);
    System.out.println("Reserva adicionada com sucesso!");

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
        if (listaReservas.isEmpty()) {
            System.out.println("Não há reservas registradas.");
            return;
        }

        for (Reserva reserva : listaReservas) {
            System.out.println(reserva);
        }
    }

    // Method to find a reservation by its number
    public Reserva buscarReserva(int numero) {
        for (Reserva reserva : listaReservas) {
            if (reserva.getNumero() == numero) {
                return reserva;
            }
        }
        System.out.println("Reserva com número " + numero + " não encontrada.");
        return null;
    }

    // Method to remove a reservation by its number
    public boolean removerReserva() {
        // Solicitar ao usuário que insira o número da reserva
        System.out.print("Digite o número da reserva que deseja remover: ");
        int numero = sc.nextInt();
        // Buscar e remover a reserva
        Reserva reserva = buscarReserva(numero);
        if (reserva != null) {
            listaReservas.remove(reserva);
            System.out.println("Reserva N.º " + numero + " removida com sucesso.");
            return true;
        }
        System.out.println("Não foi possível remover a reserva. Número não encontrado.");
        return false;
    }

    public void alterarReserva() {
        // Listar reservas e permitir ao usuário escolher qual alterar
        listarReservas();
        int escolha = Utils.ScanInt("Escolha o número da reserva que deseja alterar: ");

        // Buscar a reserva escolhida
        Reserva reserva = buscarReserva(escolha);
        if (reserva == null) {
            System.out.println("Reserva não encontrada!");
            return;
        }

        // Menu para alterar campos
        System.out.println("Escolha o campo que deseja alterar:");
        System.out.println("1 - NIF");
        System.out.println("2 - ISBN");
        System.out.println("3 - Data de início (yyyy-MM-dd)");
        System.out.println("4 - Data de fim (yyyy-MM-dd)");

        int opcao = sc.nextInt();
        sc.nextLine(); // Consumir a quebra de linha

        switch (opcao) {
            case 1: // Alterar NIF
                String novoNif = Utils.ScanString("Novo NIF: ");
                if (!biblioteca.utentes.verificarNifUtentesNaLista(novoNif)) {
                    System.out.println("Erro: NIF não encontrado.");
                } else {
                    reserva.setUtente(novoNif);
                    System.out.println("NIF alterado com sucesso!");
                }
                break;
            case 2: // Alterar ISBN
                String novoIsbn = Utils.ScanString("Novo ISBN: ");
                if (!Livros.verificarIsbnNaLista(novoIsbn)) {
                    System.out.println("Erro: ISBN não encontrado.");
                } else {
                    reserva.setIsbn(novoIsbn);
                    System.out.println("ISBN alterado com sucesso!");
                }
                break;
            case 3: // Alterar data de início
                String novaDataInicioStr = Utils.ScanString("Nova data de início (yyyy-MM-dd): ");
                LocalDate novaDataInicio = validarDataSemTry(novaDataInicioStr);
                if (novaDataInicio == null) {
                    System.out.println("Erro: Data de início inválida.");
                } else if (novaDataInicio.isAfter(reserva.getDataFim())) {
                    System.out.println("Erro: A data de início não pode ser posterior à data de fim.");
                } else {
                    reserva.setDataInicio(novaDataInicio);
                    System.out.println("Data de início alterada com sucesso!");
                }
                break;
            case 4: // Alterar data de fim
                String novaDataFimStr = Utils.ScanString("Nova data de fim (yyyy-MM-dd): ");
                LocalDate novaDataFim = validarDataSemTry(novaDataFimStr);
                if (novaDataFim == null) {
                    System.out.println("Erro: Data de fim inválida.");
                } else if (novaDataFim.isBefore(reserva.getDataInicio())) {
                    System.out.println("Erro: A data de fim não pode ser anterior à data de início.");
                } else {
                    reserva.setDataFim(novaDataFim);
                    System.out.println("Data de fim alterada com sucesso!");
                }
                break;
            default:
                System.out.println("Opção inválida! Por favor, escolha entre 1 e 4.");
        }
    }
}


