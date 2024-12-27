package Gerenciamento;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Livros {
    Scanner sc = new Scanner(System.in);

    private ArrayList<Livro> livros = new ArrayList<>();

//    public void removerLivro() {
//        System.out.println("------------------------------------");
//        listarLivro();
//        System.out.printf("Digite o ISBN do Livro para deletar : ");
//        String isbnTemp = sc.next();
//        for (Livro livroObj : livros) {
//            if (livroObj.getIsbn().equals(isbnTemp)) {
//                livros.remove(livroObj);
//            }
//            System.out.println(livroObj.toString() + "\n" + "Removido com sucesso");
//            break;
//        }
//    }

    public void removerLivro() {
        System.out.println("------------------------------------");
        listarLivro(); // Listar todos os livros disponíveis
        System.out.printf("Digite o ISBN do Livro para deletar: ");
        String isbnTemp = sc.next(); // Ler o ISBN fornecido pelo usuário

        // Flag para rastrear se o livro foi removido
        boolean livroRemovido = false;

        // Usar Iterator para evitar problemas de modificação da coleção
        Iterator<Livro> iterator = livros.iterator();
        while (iterator.hasNext()) {
            Livro livroObj = iterator.next();
            if (livroObj.getIsbn().equals(isbnTemp)) {
                iterator.remove(); // Remover o livro de forma segura
                System.out.println(livroObj.toString() + "\n" + "Removido com sucesso");
                livroRemovido = true;
                break; // Parar o loop após encontrar e remover o livro
            }
        }

        // Caso nenhum livro tenha sido removido
        if (!livroRemovido) {
            System.out.println("Nenhum livro encontrado com o ISBN fornecido.");
        }
    }

    public void adicionarLivro(Livro livro) {
        livros.add(livro);
    }


    public void listarLivro() {
        int x = 1;
        for (Livro livro : livros) {
            System.out.println("Livro Nº " + x + "\n" + livro.toString());
            x++;
        }


    }
}
