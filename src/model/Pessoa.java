package model;
// Classe criada porque há atributos em comum entre as classes Estudante, Professor
// e Bibliotecário
public class Pessoa {

    public String cpf;
    public String matricula;
    public String nome;
    public String dataNascimento;

    // Construtor
    public Pessoa(String nome, String cpf, String matricula, String dataNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.matricula = matricula;
        this.dataNascimento = dataNascimento;
    }

    public Pessoa() {

    }

    // Getters e Setters
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
