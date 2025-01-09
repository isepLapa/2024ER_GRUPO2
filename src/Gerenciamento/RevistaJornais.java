package Gerenciamento;
import Storage.Storage;
import Utils.Utils;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RevistaJornais {
    private final String biblioteca;
    private final Storage storage;

    private final Path revistasPath;
    private List<String> revistas;

    public RevistaJornais(String biblioteca, Storage storage) {
        this.biblioteca = biblioteca;
        this.storage = storage;
        this.revistasPath = Path.of(this.biblioteca + "/jornal-revista.txt");
        this.revistas = this.getRevistas();
    }

    public void listarRevistas() {
        if (revistas.isEmpty()) {
            System.out.println("Não existem revistas ou jornais na biblioteca " + this.biblioteca);
            return;
        }

        System.out.println("Lista de Revistas e Jornais:");
        revistas.forEach(System.out::println);
    }

    public void adicionarRevista() {

        String titulo = Utils.validarVazio("Título do Jornal/Revista: ");
        String editora = Utils.validarVazio("Editora do Jornal/Revista: ");
        String categoria = Utils.validarVazio("Categoria do Jornal/Revista: ");
        String issn = Utils.validarIssn("Introduza o ISSN: ");
        String dataPublicacao = Utils.validarData("Data de Publicação (dd/MM/yyyy): ");



        RevistaJornal revista = new RevistaJornal(titulo, editora, categoria, dataPublicacao, issn);
        revistas.add(revista.toString());
        this.storage.save(this.revistasPath, revistas);
        System.out.println("Revista ou Jornal adicionado com sucesso!");
    }

    public void removerRevista() {
        String titulo = Utils.ScanString("Título da Revista/Jornal: ");
        revistas = revistas.stream()
                .filter(revista -> !revista.contains("titulo=" + titulo))
                .collect(Collectors.toList());
        this.storage.save(this.revistasPath, revistas);
        System.out.println("Revista ou Jornal removido com sucesso!");
    }

    public void alterarRevista() {
        listarRevistas();
        int escolha = Utils.TransformarListaEmEscolha("Escolha o índice da Revista/Jornal que deseja alterar: ", revistas);

        if (escolha < 0 || escolha >= revistas.size()) {
            System.out.println("Opção inválida.");
            return;
        }

        String revistaSelecionada = revistas.get(escolha);
        System.out.println("Revista/Jornal selecionado: " + revistaSelecionada);

        RevistaJornal revista = this.parseRevista(revistaSelecionada);

        int opcao = Utils.ScanInt("""
                Escolha o campo a alterar:
                1 - Título
                2 - Editora
                3 - Categoria
                4 - Data de Publicação
                5 - ISSN""");
        switch (opcao) {
            case 1 -> revista.setTitulo(Utils.ScanString("Novo título: "));
            case 2 -> revista.setEditora(Utils.ScanString("Nova editora: "));
            case 3 -> revista.setCategoria(Utils.ScanString("Nova categoria: "));
            case 4 -> revista.setDatapub(Utils.ScanString("Nova data de publicação (dd/MM/yyyy): "));
            case 5 -> revista.setIsnn(Utils.ScanString("Novo ISSN: "));
            default -> System.out.println("Opção inválida!");
        }

        revistas.set(escolha, revista.toString());
        this.storage.save(this.revistasPath, revistas);
        System.out.println("Revista/Jornal alterado com sucesso!");
    }

    /**
     * Analisa uma representação em string de uma revista/jornal e retorna um objeto RevistaJornal.
     * @param revista a representação em string no formato "titulo=... editora=... categoria=... issn=... datapub=..."
     * @return um objeto RevistaJornal com os detalhes analisados
     */
    private RevistaJornal parseRevista(String revista) {
        String[] partesRevista = revista.split(" ");
        String titulo = partesRevista[0].split("=")[1];
        String editora = partesRevista[1].split("=")[1];
        String categoria = partesRevista[2].split("=")[1];
        String issn = partesRevista[3].split("=")[1];
        String datapub = partesRevista[4].split("=")[1];

        return new RevistaJornal(titulo, editora, categoria, datapub, issn);
    }

    private List<String> getRevistas() {
        List<String> revistasSalvas = storage.get(this.revistasPath);
        return revistasSalvas != null ? revistasSalvas : new ArrayList<>();
    }
}
