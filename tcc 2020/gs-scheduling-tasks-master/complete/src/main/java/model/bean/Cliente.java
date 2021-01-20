
package model.bean;

public class Cliente {

    // criei de acordo com os nomes do banco de dados
    private int id_cliente;
    private String nome;
    private String cpf;
    private String telefoneCel;
    private String telefoneFixo;
    private String email;
    private String consultaCliente;

    public String getConsultaCliente() {
        return consultaCliente;
    }

    public void setConsultaCliente(String consultaCliente) {
        this.consultaCliente = consultaCliente;
    }
   
    public int getId_cliente() {
        return id_cliente;
    }

    public void setId(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefoneCel() {
        return telefoneCel;
    }

    public void setTelefoneCel(String telefoneCel) {
        this.telefoneCel = telefoneCel;
    }

    public String getTelefoneFixo() {
        return telefoneFixo;
    }

    public void setTelefoneFixo(String telefoneFixo) {
        this.telefoneFixo = telefoneFixo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
   
}
