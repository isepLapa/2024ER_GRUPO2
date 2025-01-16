package Gerenciamento;

import Utils.Utils;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reservas {


    Scanner sc = new Scanner(System.in);
    private List<Reserva> listaReservas = new ArrayList<>();
    private Biblioteca biblioteca;
    private Emprestimos emprestimos;
    private Livro livros;

    // Construtor com apenas biblioteca
    public Reservas(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    public Reservas(Biblioteca biblioteca, Emprestimos emprestimos) {
        this.biblioteca = biblioteca;
        this.emprestimos = emprestimos;
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

        String dataInicioInput = Utils.ScanString("Data de inicio (yyyy-MM-dd): ");
        LocalDate dataInicio = validarDataSemTry(dataInicioInput);
        if (dataInicio == null) {
            System.out.println("Erro ao adicionar reserva: Data de início inválida.");
            return false;
        }

        String dataFimInput = Utils.ScanString("Data de Fim (yyyy-MM-dd): ");
        LocalDate dataFim = validarDataSemTry(dataFimInput);
        if (dataFim == null) {
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

    public boolean converterReservaEmEmprestimo() {
        System.out.print("Digite o número da reserva que deseja converter em empréstimo: ");
        int numeroReserva = sc.nextInt();
        Reserva reserva = buscarReserva(numeroReserva);

        if (reserva == null) {
            System.out.println("Erro: Reserva não encontrada.");
            return false;
        }

        if (!Livros.verificarIsbnNaLista(livros.getTitulo())) {
            System.out.println("Erro: Livro não está disponível para empréstimo.");
            return false;
        }

// Criação do empréstimo
        int numeroEmprestimo = emprestimos.getEmprestimos().size() + 1;
        String dataInicio = reserva.getDataInicio().toString();
        String dataPrevistaDevolucao = reserva.getDataFim().toString();
        String utente = reserva.getUtente();
        String tituloLivro = reserva.getUtente();

        Emprestimo novoEmprestimo = new Emprestimo(numeroEmprestimo, dataInicio, utente, dataPrevistaDevolucao, null,tituloLivro);
        emprestimos.getEmprestimos().add(novoEmprestimo);

// Remove a reserva da lista
        listaReservas.remove(reserva);

        System.out.println("Reserva convertida em empréstimo com sucesso!");
        return true;
    }

}


