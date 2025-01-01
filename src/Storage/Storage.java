package Storage;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    public List<String> getBibliotecas() {
        return this.GetBibliotecas();
    }

    /**
     * Retorna os dados do arquivo especificado pelo caminho da biblioteca.
     *
     * @param biblioteca Caminho onde os dados vão ser lidos.
     * @return A lista de dados lidos.
     */
    public List<String> get(Path biblioteca) {
        return this.LerFicheiro("bibliotecas/" + biblioteca);
    }

    /**
     * Salva os dados fornecidos no arquivo especificado pelo caminho da biblioteca.
     *
     * @param biblioteca Caminho onde os dados vão ser salvos.
     * @param data A lista de dados a serem salvos.
     */
    public void save(Path biblioteca, List<String> data) {
        this.EscreverEmFicheiro("bibliotecas/" + biblioteca, data);
    }

    /**
     * Remove um item da lista de livros da biblioteca.
     *
     * @param biblioteca Caminho onde os dados vão ser salvos.
     * @param lista A lista para fazer a iteração de remoçao.
     * @param query O critério de busca para encontrar o item a ser removido.
     */
    public void remove(Path biblioteca, List<String> lista, String query) {
        boolean itemRemovido = lista.removeIf(item -> item.contains(query + " "));

        if(!itemRemovido) {
            System.out.println("Livro não encontrado!");
            return;
        }

        this.EscreverEmFicheiro("bibliotecas/" + biblioteca, lista);
        System.out.println("Livro removido com sucesso!");
    }

    public void createBiblioteca(String biblioteca) {
        // Implementar a criação de uma nova biblioteca
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("bibliotecas.txt", true))) {
            bw.write(biblioteca);
            bw.newLine();
            System.out.println("Biblioteca adicionada com sucesso!");

            // Criar diretório da biblioteca
            File dir = new File("bibliotecas/" + biblioteca);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // Criar ficheiros dentro do diretório da biblioteca
            new File(dir, "livros.txt").createNewFile();
            new File(dir, "jornal-revista.txt").createNewFile();
            new File(dir, "utentes.txt").createNewFile();
            new File(dir, "emprestimos.txt").createNewFile();
            new File(dir, "reservas.txt").createNewFile();

        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo bibliotecas.txt");
        }
    }

    private List<String> GetBibliotecas() {
        List<String> bibliotecas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("bibliotecas.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                bibliotecas.add(line);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo bibliotecas.txt");
        }

        return bibliotecas;

    }

    private List<String> LerFicheiro(String path) {
        List<String> bibliotecas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                bibliotecas.add(line);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo bibliotecas.txt");
        }

        return bibliotecas;
    }

    private void EscreverEmFicheiro(String path, List<String> data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (String line : data) {
                bw.write(line);
                bw.newLine();

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
