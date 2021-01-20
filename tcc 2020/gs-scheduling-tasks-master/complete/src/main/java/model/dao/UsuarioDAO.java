package model.dao;

import connection.ConnectionBanco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.bean.Usuario;

public class UsuarioDAO {

    public void inserir(Usuario u) {
        ConnectionBanco objBanco = new ConnectionBanco();

        // VARIAVEIS
        int id = u.getId();
        String usuario = u.getUsuario();
        String senha = u.getSenha();
        String senhaConfirmar = u.getSenhaConfirmar();

        objBanco.conectar();

        try {
            PreparedStatement objPrst = objBanco.conexao.prepareStatement("INSERT INTO usuario(id,usuario,senha,senhaConfirmar)VALUES(?,?,?,?)");

            objPrst.setInt(1, id);
            objPrst.setString(2, usuario);
            objPrst.setString(3, senha);
            objPrst.setString(4, senhaConfirmar);

            objPrst.execute();
            JOptionPane.showMessageDialog(null, "CADASTRADO COM SUCESSO");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO AO CADASTRAR" + ex.getMessage());
        }

    }

    public ResultSet consultarUsuario(String consultaUsuario) {

        ConnectionBanco objBanco = new ConnectionBanco();
        objBanco.conectar();

        try {
            String sql = "";

            sql = "SELECT *FROM usuario where usuario like'" + consultaUsuario + "%'";

            PreparedStatement objPst = objBanco.conexao.prepareStatement(sql);
            ResultSet objRst = objPst.executeQuery();
            
            JOptionPane.showMessageDialog(null, "CONSULTA REALIZADA COM SUCESSO");
            return objRst;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO AO CONSULTAR NO BANCO DE DADOS" + ex.getMessage());
        }
        return null;
    }
    
    public ResultSet consultarEmailUsuarios() {

        ConnectionBanco objBanco = new ConnectionBanco();
        objBanco.conectar();

        try {
            String sql = "";

            sql = "SELECT email FROM usuario";

            PreparedStatement objPst = objBanco.conexao.prepareStatement(sql);
            ResultSet objRst = objPst.executeQuery();
            
            
            return objRst;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO AO CONSULTAR NO BANCO DE DADOS" + ex.getMessage());
        }
        return null;
    }

 public void excluir(String excluir) {
        Usuario u=new Usuario();
        ConnectionBanco objBanco = new ConnectionBanco();
        objBanco.conectar();
        
        try {
            PreparedStatement objPrst = objBanco.conexao.prepareStatement("DELETE FROM usuario where usuario='"+excluir+"'");
            objPrst.execute();
            JOptionPane.showMessageDialog(null, "EXCLUIDO COM SUCESSO");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO AO EXCLUIR" + ex.getMessage());
        }

    }

}
