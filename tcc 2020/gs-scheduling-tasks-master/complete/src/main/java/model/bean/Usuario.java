package model.bean;

public class Usuario {
    
    private int id;
    private String usuario;
    private String senha;
    private String senhaConfirmar;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenhaConfirmar() {
        return senhaConfirmar;
    }

    public void setSenhaConfirmar(String senhaConfirmar) {
        this.senhaConfirmar = senhaConfirmar;
    }
}
