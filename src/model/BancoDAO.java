package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BancoDAO {
    private static BancoDAO instance;
    private List<Usuario> usuarios;
    private List<Livro> livros;
    private List<Emprestimo> emprestimos;

    private BancoDAO() {
        usuarios = new ArrayList<>();
        livros = new ArrayList<>();
        emprestimos = new ArrayList<>();
        carregarDados();
    }

    public static synchronized BancoDAO getInstance() {
        if (instance == null) {
            instance = new BancoDAO();
        }
        return instance;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void salvarDados() throws IOException {
        try (ObjectOutputStream oosUsuarios = new ObjectOutputStream(new FileOutputStream("usuarios.dat"));
             ObjectOutputStream oosLivros = new ObjectOutputStream(new FileOutputStream("livros.dat"));
             ObjectOutputStream oosEmprestimos = new ObjectOutputStream(new FileOutputStream("emprestimos.dat"))) {

            oosUsuarios.writeObject(usuarios);
            oosLivros.writeObject(livros);
            oosEmprestimos.writeObject(emprestimos);
        }
    }

    public void carregarDados() {
        try (ObjectInputStream oisUsuarios = new ObjectInputStream(new FileInputStream("usuarios.dat"));
             ObjectInputStream oisLivros = new ObjectInputStream(new FileInputStream("livros.dat"));
             ObjectInputStream oisEmprestimos = new ObjectInputStream(new FileInputStream("emprestimos.dat"))) {

            usuarios = (List<Usuario>) oisUsuarios.readObject();
            livros = (List<Livro>) oisLivros.readObject();
            emprestimos = (List<Emprestimo>) oisEmprestimos.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar dados: " + e.getMessage());
        }
    }
}
