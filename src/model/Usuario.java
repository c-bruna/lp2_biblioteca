package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Usuario extends Pessoa implements Serializable {
    private TipoUsuario perfil;
    private List<Emprestimo> emprestimos;

    public Usuario(String nome, String cpf, String matricula, String dataNascimento, TipoUsuario perfil) {
        super(nome, cpf, matricula, dataNascimento);
        this.perfil = perfil;
        this.emprestimos = new ArrayList<>();
    }

    public TipoUsuario getPerfil() {
        return perfil;
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void adicionarEmprestimo(Emprestimo emprestimo) {
        this.emprestimos.add(emprestimo);
    }

    public void removerEmprestimo(Emprestimo emprestimo) {
        this.emprestimos.remove(emprestimo);
    }
}
