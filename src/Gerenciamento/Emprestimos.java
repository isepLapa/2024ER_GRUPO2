package Gerenciamento;

import Storage.Storage;
import Utils.Utils;

import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Emprestimos {
    private final Biblioteca biblioteca;
    private final Storage storage;

    private final Path emprestimosPath;
    private static List<Emprestimo> emprestimos;

    public Emprestimos(Biblioteca biblioteca, String nomeBiblioteca, Storage storage) {
        this.biblioteca = biblioteca;
        this.storage = storage;

        this.emprestimosPath = Path.of(nomeBiblioteca + "/emprestimos.txt");
        this.emprestimos = this.getEmprestimos();
    }

    public void ListarEmprestimos() {
        if (emprestimos.isEmpty()) {
            System.out.println("Não existem emprestimos na biblioteca " + this.biblioteca);
            return;
        }

        for (Emprestimo emprestimo : emprestimos) {
            System.out.println(emprestimo);
        }
    }

    public void AdicionarEmprestimo() {
        boolean flag = false;
        int numero = emprestimos.isEmpty() ? 1 : emprestimos.stream().mapToInt(Emprestimo::getNumero).max().getAsInt() + 1;
        System.out.println("Número do empréstimo que irá ser criado: " + numero + "\n -------------------------------");
        String dataInicio = Utils.validarData("Data de início (dd/MM/yyyy): ");
        String nif;

        do {
            nif = Utils.ScanString("NIF do Utente: ");
            if (!Utils.validarNifSemLoop(nif)) {
                System.out.println("NIF inválido.");
                continue;
            }
            if (Utils.verificarSeItemNaoExiste("NIF do Utente: ", biblioteca.utentes.getUtentes(), Utente::getNif).equals(nif)) {
                System.out.println("Utente não encontrado.");
                continue;
            }
            break;
        } while (true);

        String dataPrevistaDevolucao = Utils.validarData("Data prevista de devolução: ");
        String dataEfetivaDevolucao = Utils.validarData("Data efetiva de devolução: ");
        int numLivros = Utils.ScanInt("Número de livros a reservar: ");
        StringBuilder livros = new StringBuilder();

        for (int i = 0; i < numLivros; i++) {
            while (true) {
                String isbn = Utils.ScanString("ISBN do livro " + (i + 1) + ": ");
                if (!Livros.verificarIsbnNaLista(isbn)) {
                    System.out.println("Erro ao adicionar empréstimo: ISBN " + isbn + " não encontrado.");
                    continue;
                }

                if (emprestimos.stream().anyMatch(emprestimo -> emprestimo.getLivros().contains(isbn)) ||
                        biblioteca.reservas.reservaExiste(isbn)) {
                    System.out.println("Erro ao adicionar empréstimo: O livro com ISBN " + isbn + " já foi emprestado ou reservado.");
                    continue;
                }

                livros.append(isbn).append(" ");
                break;
            }
        }




        Emprestimo emprestimo = new Emprestimo(numero, dataInicio, nif, dataPrevistaDevolucao, dataEfetivaDevolucao, livros.toString());
        emprestimos.add(emprestimo);

        this.storage.save(this.emprestimosPath, emprestimos);
    }

    public void RemoverEmprestimo() {

        String numeroReserva = Utils.ScanString("Numero do emprestimo: ");

        emprestimos.removeIf(emprestimo -> emprestimo.getNumeroString().equals(numeroReserva));

        this.storage.save(this.emprestimosPath, emprestimos);
    }

    public void AlterarEmprestimo() {
        int escolha = Utils.TransformarListaEmEscolha("Escolha o índice do empréstimo que deseja alterar: ", emprestimos);

        Scanner sc = new Scanner(System.in);
        System.out.println("Escolha o campo que deseja alterar:");
        System.out.println("1 - Data de Início");
        System.out.println("2 - Utente");
        System.out.println("3 - Data Prevista de Devolução");
        System.out.println("4 - Data Efetiva de Devolução");

        int op = sc.nextInt();
        sc.nextLine();

        switch (op) {
            case 1:
                emprestimos.get(escolha).setDataInicio(Utils.validarData("Nova data de início: "));
                break;
            case 2:
                while (true) {
                    String nif = Utils.verificarSeItemExiste("Novo NIF do Utente: ", biblioteca.utentes.getUtentes(), Utente::getNif);

                    if(Utils.validarNifSemLoop(nif))
                    {
                        emprestimos.get(escolha).setNif(nif);
                        break;
                    }
                }

                break;
            case 3:
                emprestimos.get(escolha).setDataPrevistaDevolucao(Utils.validarData("Nova data prevista de devolução: "));
                break;
            case 4:
                emprestimos.get(escolha).setDataEfetivaDevolucao(Utils.validarData("Nova data efetiva de devolução: "));
                break;
            default:
                System.out.println("Opção inválida! Por favor, escolha entre 1 e 5.");
        }

        this.storage.save(this.emprestimosPath, emprestimos);
    }


    public void PesquisarEmprestimosPorUtenteEIntervaloDeDatas() {
        String utenteNIF = Utils.ScanString("Digite o NIF do utente: ");
        String dataInicioStr = Utils.validarData("Digite a data de início (dd/MM/yyyy): ");
        String dataFimStr = Utils.validarData("Digite a data de fim (dd/MM/yyyy): ");

        String nomeUtente = "";


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataInicio = LocalDate.parse(dataInicioStr, formatter);
        LocalDate dataFim = LocalDate.parse(dataFimStr, formatter);

        List<Emprestimo> emprestimosFiltrados = new ArrayList<>();
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getNif().equals(utenteNIF)) {
                nomeUtente = emprestimo.getNif();

                LocalDate dataEmprestimo = LocalDate.parse(emprestimo.getDataInicio(), formatter);
                if ((dataEmprestimo.isEqual(dataInicio) || dataEmprestimo.isAfter(dataInicio)) &&
                        (dataEmprestimo.isEqual(dataFim) || dataEmprestimo.isBefore(dataFim))) {
                    emprestimosFiltrados.add(emprestimo);
                }
            }
        }

        if (emprestimosFiltrados.isEmpty()) {
            System.out.println("Nenhum empréstimo encontrado para o utente " + nomeUtente + " no intervalo de datas especificado.");
        } else {
            System.out.println("Empréstimos encontrados para o utente " + nomeUtente + " no intervalo de datas especificado:");
            emprestimosFiltrados.forEach(System.out::println);
        }
    }

    /**
     * Analisa uma representação em string de um emprestimo e retorna um objeto Emprestimo.
     *
     * @param emprestimo a representação em string do emprestimo no formato "titulo=... editora=... categoria=... isbn=... autor=... anoDeEdicao=..."
     * @return um objeto Emprestimo com os detalhes analisados
     */
    private Emprestimo parseEmprestimo(String emprestimo) {
        String[] partesEmprestimo = emprestimo.split(" ");
        int numero = Integer.parseInt(partesEmprestimo[0].split("=")[1]);
        String dataInicio = partesEmprestimo[1].split("=")[1];
        String utente = partesEmprestimo[2].split("=")[1];
        String dataPrevistaDevolucao = partesEmprestimo[3].split("=")[1];
        String dataEfetivaDevolucao = partesEmprestimo[4].split("=")[1];
        String livros = partesEmprestimo[5].split("=")[1];

        return new Emprestimo(numero, dataInicio, utente, dataPrevistaDevolucao, dataEfetivaDevolucao, livros);
    }

    public List<Emprestimo> getEmprestimos() {
        return this.convertToEmprestimoList(storage.get(this.emprestimosPath));
    }


    public void listarUtentesComAtraso() {
        int diasAtraso = Utils.ScanInt("Digite o número de dias de atraso: ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        List<String> utentesComAtraso = new ArrayList<>();
        for (Emprestimo emprestimo : emprestimos) {
            LocalDate dataPrevistaDevolucao = LocalDate.parse(emprestimo.getDataPrevistaDevolucao(), formatter);
            LocalDate dataEfetivaDevolucao = LocalDate.parse(emprestimo.getDataEfetivaDevolucao(), formatter);

            long diasDeAtraso = ChronoUnit.DAYS.between(dataPrevistaDevolucao, dataEfetivaDevolucao);
            if (diasDeAtraso > diasAtraso) {
                utentesComAtraso.add(biblioteca.utentes.getUtenteByNif(emprestimo.getNif()));
            }
        }

        if (utentesComAtraso.isEmpty()) {
            System.out.println("Nenhum utente tem devoluçoes com atraso superior a " + diasAtraso + " dias.");
        } else {
            System.out.println("Utentes com devoluções com atraso superior a " + diasAtraso + " dias:");
            utentesComAtraso.forEach(System.out::println);
        }
    }


    /**
     * Converte uma lista de strings em uma lista de objetos Emprestimo.
     *
     * @param emprestimosStr a lista de strings dos emprestimos
     * @return uma lista de objetos Emprestimo
     */
    public List<Emprestimo> convertToEmprestimoList(List<String> emprestimosStr) {
        List<Emprestimo> emprestimos = new ArrayList<>();
        for (String livroStr : emprestimosStr) {
            emprestimos.add(parseEmprestimo(livroStr));
        }
        return emprestimos;
    }
}
