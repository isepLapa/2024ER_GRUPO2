package Storage;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    public List<String> getBibliotecas() {
        return this.GetBibliotecas();
    }

    public List<String> get(Path biblioteca) {
        return this.LerFicheiro("bibliotecas/" + biblioteca);
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
}
