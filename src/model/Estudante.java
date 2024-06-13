package model;

public class Estudante extends Usuario {
    private String curso;

    public Estudante(String nome, String cpf, String matricula, String dataNascimento, TipoUsuario curso) {
        super(nome, cpf, matricula, dataNascimento, TipoUsuario.ESTUDANTE);
        this.curso = String.valueOf(curso);
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}
