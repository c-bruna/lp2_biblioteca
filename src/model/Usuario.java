package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Usuario extends Pessoa implements Serializable {
    public List<Emprestimo> emprestimos;

    public Usuario(String nome, String cpf, String matricula, String dataNascimento) {
        super(nome, cpf, matricula, dataNascimento);
        this.emprestimos = new ArrayList<>();
    }

    public Usuario() {
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
