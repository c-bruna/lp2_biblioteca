package model;

public class Professor extends Usuario {
    private String departamento;

    public Professor(String nome, String cpf, String matricula, String dataNascimento, String departamento) {
        super(nome, cpf, matricula, dataNascimento, TipoUsuario.PROFESSOR);
        this.departamento = departamento;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
}
