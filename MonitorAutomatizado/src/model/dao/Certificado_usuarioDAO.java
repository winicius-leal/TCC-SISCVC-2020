
package model.dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import connection.ConnectionBanco;
import model.bean.Certificado_usuario;



public class Certificado_usuarioDAO {
    
    public void inserir(Certificado_usuario d) {
       
        ConnectionBanco objBanco = new ConnectionBanco();

        // VARIAVEIS
      
    int id_certificado = d.getId_certificado();
    
    String email = d.getEmail();
    
    String dataValidade = d.getDataValidade();


        objBanco.conectar();

        try {
            PreparedStatement objPrst = objBanco.conexao.prepareStatement("INSERT INTO certificado_usuario(id_certificado,email,dataValidade)VALUES(?,?,?)");

            objPrst.setInt(1, id_certificado);
            
            objPrst.setString(2, email);
            
            objPrst.setString(3, dataValidade);
           

            objPrst.execute();
            
            //JOptionPane.showMessageDialog(null, "CERTIFICADO DO USUARIO INSERIDO COM SUCESSO");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO AO INSERIR: " + ex.getMessage());
        }
    }
     
    public ResultSet selecionar() {

        ConnectionBanco objBanco = new ConnectionBanco();
        objBanco.conectar();

        try {
            String sql = "SELECT *FROM certificado";

            PreparedStatement objPst = objBanco.conexao.prepareStatement(sql);
            ResultSet objRst = objPst.executeQuery();
            //JOptionPane.showMessageDialog(null, "CONSULTA REALIZADA COM SUCESSO");
            return objRst;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO AO CONSULTAR NO BANCO DE DADOS" + ex.getMessage());
        }
        return null;
    }
     
    public ResultSet selecionarCertificadoVinculado(int id_usuario) {

        ConnectionBanco objBanco = new ConnectionBanco();
        objBanco.conectar();

        try {
            String sql = "SELECT *FROM certificado_usuario where id_fk_usuario ='"+id_usuario+"'";

            PreparedStatement objPst = objBanco.conexao.prepareStatement(sql);
            ResultSet objRst = objPst.executeQuery();
            
            return objRst;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO AO CONSULTAR NO BANCO DE DADOS" + ex.getMessage());
        }
        return null;
    }
      public ResultSet selecionarSerialNumberExistente(String serialNumber) {

        ConnectionBanco objBanco = new ConnectionBanco();
        objBanco.conectar();

        try {
            String sql = "SELECT *FROM certificado_usuario where serialNumber ='"+serialNumber+"'";

            PreparedStatement objPst = objBanco.conexao.prepareStatement(sql);
            ResultSet objRst = objPst.executeQuery();
            
            return objRst;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO AO CONSULTAR NO BANCO DE DADOS" + ex.getMessage());
        }
        return null;
    }
      
      public void excluir(int id_certificado_usuario) {
          
        
        
        ConnectionBanco objBanco = new ConnectionBanco();
        objBanco.conectar();
        
        try {
            PreparedStatement objPrst = objBanco.conexao.prepareStatement("DELETE FROM certificado_usuario where id_certificado='"+id_certificado_usuario+"'");
            objPrst.execute();
            JOptionPane.showMessageDialog(null, "EXCLUIDO COM SUCESSO");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO AO EXCLUIR" + ex.getMessage());
        }

    }
      
      public ResultSet selecionarEmailExistente(String email) {

          ConnectionBanco objBanco = new ConnectionBanco();
          objBanco.conectar();

          try {
              String sql = "SELECT *FROM certificado_usuario where email ='"+email+"'";

              PreparedStatement objPst = objBanco.conexao.prepareStatement(sql);
              ResultSet objRst = objPst.executeQuery();
              
              return objRst;
          } catch (SQLException ex) {
              JOptionPane.showMessageDialog(null, "ERRO AO CONSULTAR NO BANCO DE DADOS" + ex.getMessage());
          }
          return null;
      }

}

