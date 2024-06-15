package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Operacoes {
    private static Operacoes instance;
    private BancoDAO bancoDAO;

    private List<Livro> livros;
    private List<Usuario> usuarios;
    private List<Emprestimo> emprestimos;
    private Bibliotecario loginBibliotecario;

    private Operacoes() {
        livros = new ArrayList<>();
        usuarios = new ArrayList<>();
        emprestimos = new ArrayList<>();
        bancoDAO = BancoDAO.getInstance();
    }

    public static Operacoes getInstance() {
        if (instance == null) {
            instance = new Operacoes();
        }
        return instance;
    }

    public boolean loginBibliotecario(String login, String senha) throws Exception {
        System.out.println("Login recebido: " + login);
        System.out.println("Senha recebida: " + senha);
        for (Usuario usuario : bancoDAO.getUsuarios()) {
            if (usuario instanceof Bibliotecario) {
                Bibliotecario bibliotecario = (Bibliotecario) usuario;
                System.out.println(bibliotecario.getLogin());
                if (bibliotecario.getLogin().equals(login) && bibliotecario.getSenha().equals(senha)) {
                    return true;
                }
            }
        }

        throw new Exception("Login ou senha incorretos.");
    }

    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void adicionarLivro(Livro livro) {
        livros.add(livro);
    }


    public void removerLivro(Livro livro) {
        livros.remove(livro);
    }

    public Livro buscarLivroPorTitulo(String titulo) {
        for (Livro livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                return livro;
            }
        }
        return null;
    }

    public void listarUsuarios() {
        System.out.println("Lista de usuários:");
        for (Usuario usuario : bancoDAO.getUsuarios()) {
            System.out.println(usuario.getNome() + " " + usuario.getCpf());
        }
    }

    public boolean realizarEmprestimo(Livro livro, Usuario usuario) throws Exception {
        if (livro.getLivroEmprestado() != LivroEmprestado.DISPONIVEL) {
            throw new Exception("Livro não disponível para empréstimo.");
        }

        if (usuario instanceof Estudante && usuario.getEmprestimos().size() >= 3) {
            throw new Exception("Estudante já atingiu o limite de empréstimos.");
        }

        if ((usuario instanceof Professor || usuario instanceof Bibliotecario) && usuario.getEmprestimos().size() >= 5) {
            throw new Exception("Já atingiu o limite de empréstimos.");
        }

        LocalDate dataEmprestimo = LocalDate.now();
        LocalDate dataDevolucaoPrevista;
        if (usuario instanceof Estudante) {
            dataDevolucaoPrevista = dataEmprestimo.plusDays(15);
        } else {
            dataDevolucaoPrevista = dataEmprestimo.plusDays(30);
        }

        Emprestimo emprestimo = new Emprestimo(livro, usuario, dataEmprestimo, dataDevolucaoPrevista);
        usuario.adicionarEmprestimo(emprestimo);
        livro.setLivroEmprestado(LivroEmprestado.INDISPONIVEL);
        emprestimos.add(emprestimo);

        return true;
    }


    public void realizarDevolucao(Emprestimo emprestimo) {
        Livro livroDevolvido = emprestimo.getLivro();
        Usuario usuario = emprestimo.getUsuario();
        usuario.removerEmprestimo(emprestimo);
        livroDevolvido.setLivroEmprestado(LivroEmprestado.DISPONIVEL);
        emprestimos.remove(emprestimo);
    }

    public Emprestimo buscarEmprestimo(Livro livro, Usuario usuario) {
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getLivro().equals(livro) && emprestimo.getUsuario().equals(usuario)) {
                return emprestimo;
            }
        }
        return null;
    }

    public List<Emprestimo> listarEmprestimosUsuario(Usuario usuario) {
        List<Emprestimo> emprestimosAtivos = new ArrayList<>();
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getUsuario().equals(usuario)) {
                emprestimosAtivos.add(emprestimo);
            }
        }
        return emprestimosAtivos;
    }


    public Usuario buscarUsuarioPorCpf(String cpf) {
        for (Usuario usuario : usuarios) {
            if (usuario.getCpf().equals(cpf)) {
                return usuario;
            }
        }
        return null;
    }


    public List<Livro> getLivros() {
        return livros;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

}
