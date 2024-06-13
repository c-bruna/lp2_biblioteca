import model.*;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.List;

public class Main {
    private static TipoUsuario tipoUsuario;

    public static void main(String[] args) throws Exception {
        Operacoes operacoes = Operacoes.getInstance();
        Bibliotecario bibliotecario = new Bibliotecario("Ana", "11223344556", "B789", "1990-12-25", "ana_biblio", "senha123");
        operacoes.adicionarUsuario(bibliotecario);
        Bibliotecario loginBibliotecario = operacoes.loginBibliotecario("ana_biblio", "senha123");
        Scanner scanner = new Scanner(System.in);

        //if (loginBibliotecario == null) {
        //    System.out.println("Falha na autenticação. Encerrando o programa.");
        //    return;
        //}

        while (true) {
            exibirMenu();

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha pendente

            switch (opcao) {
                case 1:
                    adicionarLivro(operacoes, scanner);
                    break;
                case 2:
                    removerLivro(operacoes, scanner);
                    break;
                case 3:
                    realizarEmprestimo(scanner, operacoes);
                    break;
                case 4:
                    realizarDevolucao(operacoes, scanner);
                    break;
                case 5:
                    buscarLivro(operacoes, scanner);
                    break;
                case 6:
                    listarEmprestimosAtivos(operacoes, scanner);
                    break;
                case 7:
                    cadastrarUsuario(operacoes, scanner);
                    break;
                case 8:
                    System.out.println("Saindo do sistema.");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }


    private static void cadastrarUsuario(Operacoes operacoes, Scanner scanner) {
        System.out.println("\nCadastro de Usuário");
        System.out.println("Selecione o tipo de usuário:");
        System.out.println("1. Estudante");
        System.out.println("2. Professor");
        System.out.println("3. Bibliotecário");
        System.out.print("Escolha o tipo de usuário a ser cadastrado: ");

        int tipoUsuario = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha pendente

        switch (tipoUsuario) {
            case 1:
                cadastrarEstudante(operacoes, scanner);
                break;
            case 2:
                cadastrarProfessor(operacoes, scanner);
                break;
            case 3:
                cadastrarBibliotecario(operacoes, scanner);
                break;
            default:
                System.out.println("Tipo de usuário inválido.");
                break;
        }
    }

    private static void cadastrarEstudante(Operacoes operacoes, Scanner scanner) {
        System.out.print("Nome do Estudante: ");
        String nome = scanner.nextLine().trim();

        System.out.print("CPF do Estudante: ");
        String cpf = scanner.nextLine().trim();

        System.out.print("Matrícula do Estudante: ");
        String matricula = scanner.nextLine().trim();

        System.out.print("Data de Nascimento do Estudante: ");
        String dataNascimento = scanner.nextLine().trim();

        // Criar o objeto Estudante
        Usuario estudante = new Estudante(nome, cpf, matricula, dataNascimento, TipoUsuario.ESTUDANTE);

        // Adicionar o estudante através das operações
        operacoes.adicionarUsuario(estudante);

        System.out.println("Estudante cadastrado com sucesso!");
    }

    private static void cadastrarProfessor(Operacoes operacoes, Scanner scanner) {
        System.out.print("Nome do Professor: ");
        String nome = scanner.nextLine().trim();

        System.out.print("CPF do Professor: ");
        String cpf = scanner.nextLine().trim();

        System.out.print("Matrícula do Professor: ");
        String matricula = scanner.nextLine().trim();

        System.out.print("Data de Nascimento do Professor: ");
        String dataNascimento = scanner.nextLine().trim();

        System.out.print("Departamento do Professor: ");
        String departamento = scanner.nextLine().trim();

        // Criar o objeto Professor
        Usuario professor = new Professor(nome, cpf, matricula, dataNascimento, departamento);

        // Adicionar o professor através das operações
        operacoes.adicionarUsuario(professor);

        System.out.println("Professor cadastrado com sucesso!");
    }

    private static void cadastrarBibliotecario(Operacoes operacoes, Scanner scanner) {
        System.out.print("Nome do Bibliotecário: ");
        String nome = scanner.nextLine().trim();

        System.out.print("CPF do Bibliotecário: ");
        String cpf = scanner.nextLine().trim();

        System.out.print("Matrícula do Bibliotecário: ");
        String matricula = scanner.nextLine().trim();

        System.out.print("Data de Nascimento do Bibliotecário: ");
        String dataNascimento = scanner.nextLine().trim();

        System.out.print("Login do Bibliotecário: ");
        String login = scanner.nextLine().trim();

        System.out.print("Senha do Bibliotecário: ");
        String senha = scanner.nextLine().trim();

        // Criar o objeto Bibliotecário
        Bibliotecario bibliotecario = new Bibliotecario(nome, cpf, matricula, dataNascimento, login, senha);

        // Adicionar o bibliotecário através das operações
        operacoes.adicionarUsuario(bibliotecario);

        System.out.println("Bibliotecário cadastrado com sucesso!");
    }

    private static Bibliotecario autenticarBibliotecario(Operacoes operacoes) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Autenticação de Bibliotecário");
        System.out.print("Login: ");
        String login = scanner.nextLine().trim();

        System.out.print("Senha: ");
        String senha = scanner.nextLine().trim();

        return operacoes.loginBibliotecario(login, senha);
    }

    private static void exibirMenu() {
        System.out.println("\nSistema de Biblioteca");
        System.out.println("1. Adicionar Livro");
        System.out.println("2. Remover Livro");
        System.out.println("3. Realizar Empréstimo");
        System.out.println("4. Realizar Devolução");
        System.out.println("5. Buscar Livro");
        System.out.println("6. Listar Empréstimos Ativos de um Usuário");
        System.out.println("7. Cadastrar Usuário");
        System.out.println("8. Sair");
        System.out.print("Escolha uma opção: ");
    }



    private static void adicionarLivro(Operacoes operacoes, Scanner scanner) {
        System.out.println("\nAdicionar Livro");

        System.out.print("Digite o título do livro: ");
        String titulo = scanner.nextLine();

        System.out.print("Digite o autor do livro: ");
        String autor = scanner.nextLine();

        System.out.print("Digite o ano de lançamento do livro: ");
        int anoLancamento = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer do scanner

        System.out.print("Digite o assunto do livro: ");
        String assunto = scanner.nextLine();

        System.out.print("Digite a quantidade em estoque do livro: ");
        int qtdEstoque = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer do scanner

        Livro livro = new Livro(titulo, autor, assunto, anoLancamento, qtdEstoque);
        operacoes.adicionarLivro(livro);

        System.out.println("Livro adicionado com sucesso!");
    }

    private static void removerLivro(Operacoes operacoes, Scanner scanner) {
        System.out.println("\nRemover Livro");

        System.out.print("Digite o título do livro a ser removido: ");
        String titulo = scanner.nextLine();

        Livro livroEncontrado = operacoes.buscarLivroPorTitulo(titulo);

        if (livroEncontrado == null) {
            System.out.println("Livro não encontrado.");
        } else {
            operacoes.removerLivro(livroEncontrado);
            System.out.println("Livro removido com sucesso!");
        }
    }

    private static void realizarEmprestimo(Scanner scanner, Operacoes operacoes) {
        System.out.println("\nRealizar Empréstimo");

        System.out.print("Informe o CPF do usuário: ");
        String cpf = scanner.nextLine();

        Usuario usuario = operacoes.buscarUsuarioPorCpf(cpf);
        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        System.out.print("Informe o título do livro que deseja emprestar: ");
        String tituloLivro = scanner.nextLine();

        Livro livro = operacoes.buscarLivroPorTitulo(tituloLivro);
        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        try {
            operacoes.realizarEmprestimo(livro, usuario);
        } catch (Exception e) {
            System.out.println("Erro ao realizar empréstimo: " + e.getMessage());
        }
    }

    private static void realizarDevolucao(Operacoes operacoes, Scanner scanner) {
        System.out.println("\nRealizar Devolução");

        System.out.print("Digite o título do livro a ser devolvido: ");
        String tituloLivro = scanner.nextLine();

        Livro livro = operacoes.buscarLivroPorTitulo(tituloLivro);

        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        Emprestimo emprestimo = null;
        for (Emprestimo emp : operacoes.getEmprestimos()) {
            if (emp.getLivro().equals(livro)) {
                emprestimo = emp;
                break;
            }
        }

        if (emprestimo == null) {
            System.out.println("Livro não está emprestado.");
            return;
        }

        operacoes.realizarDevolucao(emprestimo);
        System.out.println("Devolução realizada com sucesso!");
    }

    private static void buscarLivro(Operacoes operacoes, Scanner scanner) {
        System.out.println("\nBuscar Livro");

        System.out.print("Digite o nome do arquivo (.txt) com os títulos de livros: ");
        String nomeArquivo = scanner.nextLine();

        System.out.print("Digite o título do livro que deseja buscar: ");
        String tituloLivro = scanner.nextLine();

        Livro livroEncontrado = operacoes.buscarLivroPorNomeArquivo(nomeArquivo, tituloLivro);

        if (livroEncontrado == null) {
            System.out.println("Livro não encontrado no arquivo.");
        } else {
            System.out.println("Livro encontrado:");
            System.out.println(livroEncontrado);
        }
    }


    private static void listarEmprestimosAtivos(Operacoes operacoes, Scanner scanner) {
        System.out.println("\nListar Empréstimos Ativos de um Usuário");

        System.out.print("Digite o CPF do usuário: ");
        String cpf = scanner.nextLine();

        Usuario usuario = operacoes.buscarUsuarioPorCpf(cpf);

        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        List<Emprestimo> emprestimosAtivos = operacoes.listarEmprestimosAtivosUsuario(usuario);

        if (emprestimosAtivos.isEmpty()) {
            System.out.println("Usuário não possui empréstimos ativos.");
        } else {
            System.out.println("Empréstimos ativos do usuário:");
            for (Emprestimo emp : emprestimosAtivos) {
                System.out.println(emp);
            }
        }
    }
}
