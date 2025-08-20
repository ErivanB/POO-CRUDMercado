package model;

public class CaixaEletronico {

    private String nome;
    private String login;
    private String senha;
    private String email;
    private String nisPis;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNisPis() {
        return nisPis;
    }

    public void setNisPis(String nisPis) {
        this.nisPis = nisPis;
    }

    public CaixaEletronico(String nome, String login, String senha, String email, String nisPis) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.email = email;
        this.nisPis = nisPis;
    }

    public CaixaEletronico(){

    }

}

