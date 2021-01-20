
package model.dao;

import connection.ConnectionBanco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.bean.Certificado;



public class Certificado_clienteDAO {
    
    public void inserir(Certificado d) {
        ConnectionBanco objBanco = new ConnectionBanco();

        // VARIAVEIS
      
    int id_certificado = d.getId_certificado();
    String nomeTitular = d.getNomeTitular();
    String cpfTitular = d.getCpfTitular();
    String nomeCertificado= d.getNomeCertificado();
    String cpf_cnpj= d.getCpf_cnpj();
    String tipoTitular = d.getTipoTitular();
    String tipoCertificado = d.getTipoCertificado();
    String email = d.getEmail();
    String midia = d.getMidia();
    String acCertificado = d.getAcCertificado();
    String dataEmissao = d.getDataEmissao();
    String dataValidade = d.getDataValidade();
    String notifica1 = d.getNotifica1();
    String notifica15 = d.getNotifica15();
    String notifica30 = d.getNotifica30();
    int id_fk_cliente = d.getId_fk_cliente();


        objBanco.conectar();

        try {
            PreparedStatement objPrst = objBanco.conexao.prepareStatement("INSERT INTO certificado_cliente(id_certificado,nomeTitular,cpfTitular,nomeCertificado,cpf_cnpj,tipoTitular,tipoCertificado,email,midia,acCertificado,dataEmissao,dataValidade,notifica1,notifica15,notifica30,id_fk_cliente)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            objPrst.setInt(1, id_certificado);
            objPrst.setString(2, nomeTitular);
            objPrst.setString(3, cpfTitular);
            objPrst.setString(4, nomeCertificado);
            objPrst.setString(5, cpf_cnpj);
            objPrst.setString(6, tipoTitular);
            objPrst.setString(7, tipoCertificado);
            objPrst.setString(8, email);
            objPrst.setString(9, midia);
            objPrst.setString(10, acCertificado);
            objPrst.setString(11, dataEmissao);
            objPrst.setString(12, dataValidade);
            objPrst.setString(13, notifica1);
            objPrst.setString(14, notifica15);
            objPrst.setString(15, notifica30);
            objPrst.setInt(16, id_fk_cliente);

            objPrst.execute();
            JOptionPane.showMessageDialog(null, "INSERIDO COM SUCESSO");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO AO INSERIR" + ex.getMessage());
        }
    }
     public ResultSet selecionar() {

        ConnectionBanco objBanco = new ConnectionBanco();
        objBanco.conectar();

        try {
            String sql = "SELECT *FROM certificado_cliente";

            PreparedStatement objPst = objBanco.conexao.prepareStatement(sql);
            ResultSet objRst = objPst.executeQuery();
        
            return objRst;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO AO CONSULTAR NO BANCO DE DADOS" + ex.getMessage());
        }
        return null;
    }
     
    public ResultSet selecionarCertificadoVinculado(int id_cliente) {

        ConnectionBanco objBanco = new ConnectionBanco();
        objBanco.conectar();

        try {
            String sql = "SELECT *FROM certificado_cliente where id_fk_cliente ='"+id_cliente+"'";

            PreparedStatement objPst = objBanco.conexao.prepareStatement(sql);
            ResultSet objRst = objPst.executeQuery();
            
            return objRst;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO AO CONSULTAR NO BANCO DE DADOS" + ex.getMessage());
        }
        return null;
    }
    
     public ResultSet selecionarCertificadoParaCampos(int id_certificado) {

        ConnectionBanco objBanco = new ConnectionBanco();
        objBanco.conectar();

        try {
            String sql = "SELECT *FROM certificado_cliente where id_certificado ='"+id_certificado+"'";

            PreparedStatement objPst = objBanco.conexao.prepareStatement(sql);
            ResultSet objRst = objPst.executeQuery();
            
            return objRst;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO AO CONSULTAR NO BANCO DE DADOS" + ex.getMessage());
        }
        return null;
    }


}

