package Gerenciamento;

import Utils.Utils;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reservas {
    Scanner sc = new Scanner(System.in);
    private List<Reserva> listaReservas = new ArrayList<>();

    // Method to add a new reservation
    public boolean addReserva() {
        try {
            // Collect input from the user
            String utente = Utils.ScanString("Utente: ");
            String tituloLivro = Utils.ScanString("Titulo do Livro: ");
            LocalDate dataInicio = LocalDate.parse(Utils.ScanString("Data de inicio (yyyy-MM-dd): "));
            LocalDate dataFim = LocalDate.parse(Utils.ScanString("Data de Fim (yyyy-MM-dd): "));

            // Validate dates
            if (dataInicio.isAfter(dataFim)) {
                System.out.println("Erro: A data de início deve ser anterior ou igual à data de fim.");
                return false;
            }

            // Create and add a reservation
            int numero = listaReservas.size() + 1; // Auto-increment reservation number
            LocalDate dataRegisto = LocalDate.now(); // Current date
            Reserva novaReserva = new Reserva(numero, utente, tituloLivro, dataRegisto, dataInicio, dataFim);
            listaReservas.add(novaReserva);

            System.out.println("Reserva adicionada com sucesso!");
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao adicionar reserva: " + e.getMessage());
            return false;
        }
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


}


