package MonitorValidadeCertificado;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.dao.Certificado_clienteDAO;
import model.dao.ClienteDAO;
import model.dao.UsuarioDAO;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MonitorValidadeCertificado {
    
  static int id_cliente = 0;
  static String emailCliente = null;
  String horaAtual = null;
  
  DateTime dataAtual = new DateTime();
  
  envioEmail mail = new envioEmail(); //objeto da classe envio de email
  
   Certificado_clienteDAO cdao = new Certificado_clienteDAO();
   ClienteDAO cliente = new ClienteDAO();
   UsuarioDAO usuario = new UsuarioDAO();
 
    @Component
    public class Agendamento {  //@Scheduled significa que esse metodo será agendado ou programado
        
        
        @Scheduled(cron = "0 49 17 * * *")//MiliSegundos, minutos, hora, dia, mes, ano
        
        public void executarTarefa() throws SQLException {

            System.out.println("\t\t MONITOR VALIDADE DE CERTIFICADO DIGITAL");
            ResultSet objRs = null;
            ResultSet objRsCliente = null;
            ResultSet objRsUsuario =null;

            objRs = cdao.selecionar(); //SELECT *FROM certificado_cliente

    
            org.joda.time.format.DateTimeFormatter dtfPadrao = org.joda.time.format.DateTimeFormat.forPattern("dd/MM/yyyy HH:mm");
            //    org.joda.time.format.DateTimeFormatter dtfPadrao = DateTimeFormat.forPattern("YYYY/MM/dd");

            dataAtual.toString(dtfPadrao); //coloca no formato a data atual
            System.out.println("data atual:" + dataAtual.toString(dtfPadrao));

            horaAtual = dataAtual.toString().substring(11, 16);
            System.out.println("hora atual: " + horaAtual);
           
            dataAtual.toString(dtfPadrao);
            
            System.out.println("\t\t MONITOR VALIDADE DE CERTIFICADO DIGITAL");
            while (objRs.next()) {

                String dataBanco = objRs.getObject("dataValidade").toString();
                String notifica1 = objRs.getObject("notifica1").toString();
                String notifica15 = objRs.getObject("notifica15").toString();
                String notifica30 = objRs.getObject("notifica30").toString();

                System.out.println("id cliente: " + objRs.getObject("id_fk_cliente"));

                id_cliente = Integer.parseInt(objRs.getObject("id_fk_cliente").toString());

                objRsCliente = cliente.selecionar_id_fk(id_cliente); // consulto o cliente para pegar o e-mail

                if (objRsCliente.next()) {
                    
                    System.out.println("Email: " + objRsCliente.getObject("email"));
                    emailCliente = objRsCliente.getObject("email").toString();
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Email do cliente não encontrado");
                }

                // PEGO OS EMAIL DOS USUARIOS CADASTRADOS PAR NOTIFICAR
                
                objRsUsuario = usuario.consultarEmailUsuarios(); //SELECT email FROM usuario
                String grupoEmail="";
                
                while (objRsUsuario.next()) {
                    
                     String emailUsuarios = objRsUsuario.getObject("email").toString();
                     grupoEmail = grupoEmail+","+emailUsuarios;//forma uma string de emails separados por ,
                }
                
                
                int anoBanco = Integer.parseInt(dataBanco.substring(0, 4));
                int mesBanco = Integer.parseInt(dataBanco.substring(5, 7));
                int diaBanco = Integer.parseInt(dataBanco.substring(8, 10));

                DateTime databanco = new DateTime(anoBanco,mesBanco,diaBanco, 22, 00);

                databanco.toString(dtfPadrao);
                
                System.out.println("data banco:" + databanco.toString(dtfPadrao));

                Days d = Days.daysBetween(dataAtual, databanco);//diferença de dias entre a data atual e data de validade no banco

                System.out.println("Diferença dias:" + d.getDays());

                int dia = d.getDays();
                
                System.out.println("resultado dias: "+dia);

                if (dia == 1 && notifica1.equals("1")) {
                    
                    System.out.println("teste emails: "+emailCliente+","+grupoEmail);
  
                  mail.envioEmail(emailCliente+","+grupoEmail,1);
      
                } else {
                    if (dia == 15 && notifica15.equals("15")) {
                        System.out.println("notifica 15 dias antes");
                    } else {
                        if (dia == 30 && notifica30.equals("30")) {
                            System.out.println("notifica 30 dias antes");
                        } else {
                            System.out.println("----NAO NOTIFICA -----");
                        }
                    }
                }

            }
        }
    }
}
