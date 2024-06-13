package model;

public class Bibliotecario extends Usuario {
    private String login;
    private String senha;

    public Bibliotecario(String nome, String cpf, String matricula, String dataNascimento, String login, String senha) {
        super(nome, cpf, matricula, dataNascimento, TipoUsuario.BIBLIOTECARIO);
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean verificarSenha(String senha) {
        return this.senha.equals(senha);
    }
}
