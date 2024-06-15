package model;

import java.io.Serializable;

public class Professor extends Usuario implements Serializable {
    private String departamento;

    public Professor(String nome, String cpf, String matricula, String dataNascimento, String departamento) {
        super(nome, cpf, matricula, dataNascimento);
        this.departamento = departamento;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
}
