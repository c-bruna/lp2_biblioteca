package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Operacoes {
    private static Operacoes instance;

    private List<Livro> livros;
    private List<Usuario> usuarios;
    private List<Emprestimo> emprestimos;
    private Bibliotecario loginBibliotecario;

    private Operacoes() {
        livros = new ArrayList<>();
        usuarios = new ArrayList<>();
        emprestimos = new ArrayList<>();
    }

    public static Operacoes getInstance() {
        if (instance == null) {
            instance = new Operacoes();
        }
        return instance;
    }

    public Bibliotecario loginBibliotecario(String login, String senha) throws Exception {
        boolean loginBiblio = false;
        for (Usuario usuario : usuarios) {
            if (usuario instanceof Bibliotecario) {
                Bibliotecario biblio = (Bibliotecario) usuario;
                if (biblio.getLogin().equals(login) && biblio.getSenha().equals(senha)) {
                    loginBibliotecario = biblio;
                    loginBiblio = true;
                    break;
                }
            }
        }

        if (!loginBiblio) {
            throw new Exception("Bibliotecário não encontrado ou senha incorreta.");
        }
        return null;
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

    public Livro buscarLivroPorNomeArquivo(String nomeArquivo, String nomeLivro) {
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.equalsIgnoreCase(nomeLivro)) {
                    return new Livro(line, "", "", 0, 0); // Retornar o livro encontrado (dados básicos)
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return null;
    }

    public Livro buscarLivroPorAutor(String autor) {
        for (Livro livro : livros) {
            if (livro.getAutor().equalsIgnoreCase(autor)) {
                return livro;
            }
        }
        return null;
    }

    public boolean realizarEmprestimo(Livro livro, Usuario usuario) throws Exception{
        if (livro.getLivroEmprestado() != LivroEmprestado.DISPONIVEL) {
            throw new Exception("Livro não disponível para empréstimo.");
        }

        if (usuario.getPerfil() == TipoUsuario.ESTUDANTE && usuario.getEmprestimos().size() >= 3) {
            throw new Exception("Estudante já atingiu o limite de empréstimos.");
        }

        if ((usuario.getPerfil() == TipoUsuario.PROFESSOR || usuario.getPerfil() == TipoUsuario.BIBLIOTECARIO) &&
                usuario.getEmprestimos().size() >= 5) {
            throw new Exception("Professor/Bibliotecário já atingiu o limite de empréstimos.");
        }

        LocalDate dataEmprestimo = LocalDate.now();
        LocalDate dataDevolucaoPrevista;
        if (usuario.getPerfil() == TipoUsuario.ESTUDANTE) {
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

    public List<Emprestimo> listarEmprestimosAtivosUsuario(Usuario usuario) {
        List<Emprestimo> emprestimosAtivos = new ArrayList<>();
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getUsuario().equals(usuario)) {
                emprestimosAtivos.add(emprestimo);
            }
        }
        return emprestimosAtivos;
    }

    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public Usuario buscarUsuarioPorCpf(String cpf) {
        for (Usuario usuario : usuarios) {
            if (usuario.getCpf().equals(cpf)) {
                return usuario;
            }
        }
        return null;
    }

    public void removerUsuario(Usuario usuario) {
        usuarios.remove(usuario);
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
