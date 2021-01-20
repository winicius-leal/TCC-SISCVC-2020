package model.dao;

import connection.ConnectionBanco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.bean.Cliente;

public class ClienteDAO {

    public void inserir(Cliente c) {
        ConnectionBanco objBanco = new ConnectionBanco();

        // VARIAVEIS
        int id_cliente = c.getId_cliente();
        String nome = c.getNome();
        String cpf = c.getCpf();
        String telefoneCel = c.getTelefoneCel();
        String telefoneFixo = c.getTelefoneFixo();
        String email = c.getEmail();

        objBanco.conectar();

        try {
            PreparedStatement objPrst = objBanco.conexao.prepareStatement("INSERT INTO cliente(id_cliente,nome,cpf,telefoneCel,TelefoneFixo,email)VALUES(?,?,?,?,?,?)");

            objPrst.setInt(1, id_cliente);
            objPrst.setString(2, nome);
            objPrst.setString(3, cpf);
            objPrst.setString(4, telefoneCel);
            objPrst.setString(5, telefoneFixo);
            objPrst.setString(6, email);

            objPrst.execute();
            JOptionPane.showMessageDialog(null, "INSERIDO COM SUCESSO");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO AO INSERIR" + ex.getMessage());
        }

    }

    public void alterar(Cliente c) {

        ConnectionBanco objBanco = new ConnectionBanco();

        // VARIAVEIS
        int id_cliente = c.getId_cliente();
        String nome = c.getNome();
        String cpf = c.getCpf();
        String telefoneCel = c.getTelefoneCel();
        String telefoneFixo = c.getTelefoneFixo();
        String email = c.getEmail();

        objBanco.conectar();

        try {
            PreparedStatement objPrst = objBanco.conexao.prepareStatement("UPDATE cliente SET descricao=?,qtd=?,preco=? WHERE id=?)");

            objPrst.setInt(1, id_cliente);
            objPrst.setString(2, nome);
            objPrst.setString(3, cpf);
            objPrst.setString(4, telefoneCel);
            objPrst.setString(5, telefoneFixo);
            objPrst.setString(6, email);

            objPrst.execute();
            JOptionPane.showMessageDialog(null, "SELECIONADO COM SUCESSO");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO AO SELECIONAR" + ex.getMessage());
        }
    }

    public void excluir(String excluir) {
        Cliente c = new Cliente();
        ConnectionBanco objBanco = new ConnectionBanco();
        objBanco.conectar();
        
        try {
            PreparedStatement objPrst = objBanco.conexao.prepareStatement("DELETE FROM cliente where nome='"+excluir+"'");
            objPrst.execute();
            JOptionPane.showMessageDialog(null, "EXCLUIDO COM SUCESSO");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO AO EXCLUIR" + ex.getMessage());
        }

    }

    public ResultSet selecionar(String consulta, int opc) {

        ConnectionBanco objBanco = new ConnectionBanco();
        objBanco.conectar();

        try {
            String sql = "";
            if (opc == 1) {
                sql = "SELECT *FROM cliente";
            } else {
                if (opc == 2) {
                    sql = "SELECT *FROM cliente where nome like'" + consulta + "%'";
                } else {
                    if (opc == 3) {
                        sql = "SELECT *FROM cliente where cpf like'" + consulta + "%'";
                    }
                }
            }
            PreparedStatement objPst = objBanco.conexao.prepareStatement(sql);
            ResultSet objRst = objPst.executeQuery();
            //JOptionPane.showMessageDialog(null, "CONSULTA REALIZADA COM SUCESSO");
            return objRst;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO AO CONSULTAR NO BANCO DE DADOS" + ex.getMessage());
        }
        return null;
    }
    
     public ResultSet selecionar_id_fk(int id_cliente) {

        ConnectionBanco objBanco = new ConnectionBanco();
        objBanco.conectar();

        try {
            String sql = "select *from cliente where id_cliente ='"+id_cliente+"'";
        
            
            PreparedStatement objPst = objBanco.conexao.prepareStatement(sql);
            ResultSet objRst = objPst.executeQuery();
            //JOptionPane.showMessageDialog(null, "CONSULTA REALIZADA COM SUCESSO");
            return objRst;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO AO CONSULTAR NO BANCO DE DADOS" + ex.getMessage());
        }
        return null;
    }

}
