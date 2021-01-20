package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class ConnectionBanco {

    String DRIVER = "com.mysql.cj.jdbc.Driver";
    String URL = "jdbc:mysql://162.241.2.249:3306/wmdark63_teste?useSSL=false";
    String USER = "wmdark63_teste";
    String PASS = "240897";
    public Connection conexao = null;

    public void conectar() {
        try {
            Class.forName(DRIVER);
            this.conexao = DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Problema ao carregar o drive");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problema ao Conectar/Autenticar ao banco");
        }
    }

    public void desconectar() {
        try {
            this.conexao.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problema ao fechar a conexao");
        }
    }

    public void executarComando(String sql) {
        try {
            Statement objstm = conexao.createStatement();
            objstm.execute(sql);
            JOptionPane.showMessageDialog(null, "comando executado com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "erro ao executar o comando" + ex.getMessage());
        }
    }

    public ResultSet executarConsulta(String sql) {
        try {
            Statement objstm = conexao.createStatement();
            
            ResultSet objRs = objstm.executeQuery(sql);
            JOptionPane.showMessageDialog(null, "comando executado com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "erro ao executar o comando" + ex.getMessage());
        }
        return null;
    }
}
