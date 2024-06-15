import model.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static BancoDAO bd;
    public static void main(String[] args) {
        Operacoes operacoes = Operacoes.getInstance();
        bd = BancoDAO.getInstance();

        bd.lerArquivo();
        Usuario bibliotecario = new Bibliotecario("Cleide", "12345678901", "001", "01/01/1970", "admin", "senha123");
        Usuario estudante1 = new Estudante("João", "111", "002", "02/02/2000");
        Usuario estudante2 = new Estudante("Maria", "222", "003", "03/03/2001");
        Usuario professor3 = new Professor("Pedro", "333", "004", "04/04/2002", "História");
        Usuario professor4 = new Professor("Ana", "444", "005", "05/05/2003", "Artes");
        Usuario estudante5 = new Estudante("Lucas", "555", "006", "06/06/2004");

        bd.carregarUsuario(bibliotecario);
        bd.carregarUsuario(estudante1);
        bd.carregarUsuario(estudante2);
        bd.carregarUsuario(professor3);
        bd.carregarUsuario(professor4);
        bd.carregarUsuario(estudante5);

        operacoes.adicionarUsuario(estudante1);
        operacoes.adicionarUsuario(estudante2);
        operacoes.adicionarUsuario(professor3);
        operacoes.adicionarUsuario(professor4);
        operacoes.adicionarUsuario(estudante5);

        ArrayList<Usuario> usuarios = bd.getUsuarios();

        bd.salvarArquivo(usuarios);

        Livro livro1 = new Livro("Barbie", "Mattel", "Moda e Magia", 2000, 5);
        Livro livro2 = new Livro("Mickey Mouse", "Walt Disney", "Desenho", 2005, 3);
        Livro livro3 = new Livro("Harry Potter", "J.K. Rowling", "Fantasia", 1997, 10);
        Livro livro4 = new Livro("Dom Quixote", "Miguel de Cervantes", "Literatura Clássica", 1605, 8);
        Livro livro5 = new Livro("O Senhor dos Anéis", "J.R.R. Tolkien", "Fantasia Épica", 1954, 7);
        Livro livro6 = new Livro("A Culpa é das Estrelas", "John Green", "Romance", 2012, 6);

        operacoes.adicionarLivro(livro1);
        operacoes.adicionarLivro(livro2);
        operacoes.adicionarLivro(livro3);
        operacoes.adicionarLivro(livro4);


        Scanner scanner = new Scanner(System.in);

        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        try {
            if (operacoes.loginBibliotecario(login, senha)) {
                System.out.println("Login realizado com sucesso!");

                int opcao;
                do {
                    exibirMenu();
                    opcao = scanner.nextInt();
                    scanner.nextLine();
                    bd.salvarArquivo("biblioteca.bin", bd.getUsuarios());
                    bd.salvarArquivo("biblioteca.bin", bd.getLivros());

                    switch (opcao) {
                        case 1:
                            adicionarLivro(operacoes, scanner);
                            break;
                        case 2:
                            realizarEmprestimo(operacoes, scanner);
                            break;
                        case 3:
                            realizarDevolucao(operacoes, scanner);
                            break;
                        case 4:
                            pesquisarLivro(operacoes, scanner);
                            break;
                        case 5:
                            excluirLivro(operacoes, scanner);
                            break;
                        case 6:
                            verificarSituacaoUsuario(operacoes, scanner);
                            break;
                        case 7:
                            listarEmprestimosUsuario(operacoes, scanner);
                            break;
                        case 0:
                            System.out.println("Desligando...");
                            break;
                        default:
                            System.out.println("Opção inexistente. Digite novamente.");
                    }
                } while (opcao != 0);

            } else {
                System.out.println("Login ou senha incorretos.");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            scanner.close();
        }
    }


    private static void exibirMenu() {
        System.out.println("\n### Menu Bibliotecária ###");
        System.out.println("1. Adicionar livro");
        System.out.println("2. Realizar empréstimo");
        System.out.println("3. Realizar devolução");
        System.out.println("4. Pesquisar livro por título");
        System.out.println("5. Excluir livro");
        System.out.println("6. Verificar situação do usuário por CPF");
        System.out.println("7. Listar empréstimos ativos de um usuário por CPF");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void adicionarLivro(Operacoes operacoes, Scanner scanner) {
        System.out.print("Informe o título do livro: ");
        String titulo = scanner.nextLine();
        System.out.print("Informe o autor do livro: ");
        String autor = scanner.nextLine();
        System.out.print("Informe o assunto do livro: ");
        String assunto = scanner.nextLine();
        System.out.print("Informe o ano de publicação do livro: ");
        int anoPublicacao = scanner.nextInt();
        System.out.print("Informe a quantidade de cópias do livro: ");
        int quantidadeCopias = scanner.nextInt();

        Livro novoLivro = new Livro(titulo, autor, assunto, anoPublicacao, quantidadeCopias);
        operacoes.adicionarLivro(novoLivro);
        System.out.println("Livro adicionado com sucesso: " + novoLivro.getTitulo());
    }

    private static void realizarEmprestimo(Operacoes operacoes, Scanner scanner) {
        System.out.print("Informe o título do livro para empréstimo: ");
        String tituloLivro = scanner.nextLine();
        System.out.print("Informe o CPF do usuário para empréstimo: ");
        String cpfUsuario = scanner.nextLine();

        try {
            Livro livro = operacoes.buscarLivroPorTitulo(tituloLivro);
            Usuario usuario = operacoes.buscarUsuarioPorCpf(cpfUsuario);

            if (livro != null && usuario != null) {
                operacoes.realizarEmprestimo(livro, usuario);
                System.out.println("Empréstimo realizado com sucesso: " + livro.getTitulo() + " para " + usuario.getNome());
            } else {
                System.out.println("Livro ou usuário não encontrado.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void realizarDevolucao(Operacoes operacoes, Scanner scanner) {
        System.out.print("Informe o título do livro para devolução: ");
        String tituloLivro = scanner.nextLine();
        System.out.print("Informe o CPF do usuário para devolução: ");
        String cpfUsuario = scanner.nextLine();

        Livro livro = operacoes.buscarLivroPorTitulo(tituloLivro);
        Usuario usuario = operacoes.buscarUsuarioPorCpf(cpfUsuario);

        Emprestimo emprestimo = operacoes.buscarEmprestimo(livro, usuario);
        operacoes.realizarDevolucao(emprestimo);
    }

    private static void pesquisarLivro(Operacoes operacoes, Scanner scanner) {
        System.out.print("Informe o título do livro para pesquisa: ");
        String tituloLivro = scanner.nextLine();

        Livro livro = operacoes.buscarLivroPorTitulo(tituloLivro);

        System.out.println("Livro encontrado: " + livro.getTitulo());

    }

    private static void excluirLivro(Operacoes operacoes, Scanner scanner) {
        System.out.print("Informe o título do livro para exclusão: ");
        String tituloLivro = scanner.nextLine();

        Livro livro = operacoes.buscarLivroPorTitulo(tituloLivro);
        if (livro != null) {
            operacoes.removerLivro(livro);
            System.out.println("Livro removido com sucesso: " + livro.getTitulo());
        } else {
            System.out.println("Livro não encontrado.");
        }
    }

    private static void verificarSituacaoUsuario(Operacoes operacoes, Scanner scanner) {
        System.out.print("Informe o CPF do usuário para verificação: ");
        String cpfUsuario = scanner.nextLine();

        Usuario usuario = operacoes.buscarUsuarioPorCpf(cpfUsuario);

        System.out.println("Usuário encontrado: " + usuario.getNome());

    }

    private static void listarEmprestimosUsuario(Operacoes operacoes, Scanner scanner) {
        System.out.print("Informe o CPF do usuário para listar empréstimos ativos: ");
        String cpfUsuario = scanner.nextLine();

        Usuario usuario = operacoes.buscarUsuarioPorCpf(cpfUsuario);
        if (usuario != null) {
            for (Emprestimo emprestimo : operacoes.listarEmprestimosUsuario(usuario)) {
                System.out.println("Empréstimo ativo: " + emprestimo.getLivro().getTitulo());
            }
        }
        else {
            System.out.println("Usuário não encontrado.");
        }
    }
}
