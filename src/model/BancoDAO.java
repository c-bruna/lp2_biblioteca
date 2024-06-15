package model;

import java.io.*;
import java.util.ArrayList;

public class BancoDAO {
    private static BancoDAO instance;
    ArrayList<Usuario> usuarios;
    ArrayList<Usuario> usuariosLidos;
    ArrayList<Livro> livros;
    ArrayList<Emprestimo> emprestimos;

    private BancoDAO() {
        usuarios = new ArrayList<>();
        usuariosLidos = new ArrayList<>();
        livros = new ArrayList<>();
        emprestimos = new ArrayList<>();
    }

    public static BancoDAO getInstance() {
        if (instance == null) {
            instance = new BancoDAO();
        }
        return instance;
    }

    public void salvarArquivo(String nomeArquivo, Object objeto) {
        try (FileOutputStream arquivoSaida = new FileOutputStream(nomeArquivo);
             ObjectOutputStream saida = new ObjectOutputStream(arquivoSaida)) {
            saida.writeObject(objeto);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void carregarUsuario(Usuario usuario){
        usuarios.add(usuario);
    }

    public void salvarArquivo1() {
        try (FileOutputStream arquivoSaida = new FileOutputStream("arquivo.bin");
             ObjectOutputStream saida = new ObjectOutputStream(arquivoSaida)) {
            saida.writeObject(usuarios);
            System.out.println("Arquivo binário gerado com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar arquivo binário: " + e.getMessage());
        }
    }

    public void salvarArquivo(ArrayList<Usuario> p) {
        try {
            FileOutputStream arquivoSaida = new FileOutputStream("arquivo.bin");
            ObjectOutputStream saida = new ObjectOutputStream(arquivoSaida);
            saida.writeObject(p);
            saida.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void lerArquivo() {
        try {
            FileInputStream arquivoLeitura = new FileInputStream("arquivo.bin");
            ObjectInputStream entrada = new ObjectInputStream(arquivoLeitura);
            usuarios = (ArrayList<Usuario>) entrada.readObject();
            entrada.close();
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erro de leitura: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Classe não encontrada: " + e.getMessage());
        }
    }


    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public ArrayList<Livro> getLivros() {
        return livros;
    }

    public ArrayList<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

}
