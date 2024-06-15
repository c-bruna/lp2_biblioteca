package model;

import java.io.Serializable;

public class Estudante extends Usuario implements Serializable {
    private String curso;

    public Estudante(String nome, String cpf, String matricula, String dataNascimento) {
        super(nome, cpf, matricula, dataNascimento);
        this.curso = String.valueOf(curso);
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}
