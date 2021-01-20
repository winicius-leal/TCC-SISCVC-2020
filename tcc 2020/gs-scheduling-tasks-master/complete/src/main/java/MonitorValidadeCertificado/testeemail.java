
package MonitorValidadeCertificado;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import model.dao.UsuarioDAO;


public class testeemail {
     public static void main(String[] args) throws SQLException{
         
    	 String destinatario = "winicius.leal@soluti.com.br";
    	 String remetente = "winicius.leal@soluti.com.br";
    	 String senha = "sol@2016";
    	 
         
         Properties props = new Properties();
            /** Parâmetros de conexão com servidor Gmail */
            //props.put("mail.smtp.user", remetente);
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");//587
            //props.put("mail.debug", "true");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            //props.put("mail.smtp.EnableSSL.enable", "true");
            
            
            //props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            //props.setProperty("mail.smtp.socketFactory.fallback", "false");
            
            //props.setProperty("mail.smtp.port", "465");
            //props.setProperty("mail.smtp.socketFactory.port", "465");//587
            
            
        
             
           


            Session session = Session.getInstance(props,new javax.mail.Authenticator() {
                             protected PasswordAuthentication getPasswordAuthentication() 
                             {
                                   return new PasswordAuthentication(remetente,senha);
                             }
                        });

            /** Ativa Debug para sessão */
            session.setDebug(true);

        try{

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remetente)); //Remetente

                  Address[] toUser = InternetAddress 
                             .parse(destinatario);  //Destinatário(s)
                  
              
                   
                   System.out.println("********************************** ENTROU AQUI ***************************************");
                   
//                   String msg = "Boa Noite,\n"
//                           + "\n"
//                           + "Seu Certificado Digtital está previsto para vencer amanhã !"
//                           + "\n"
//                           + "\n"
//                           + "\n"
//                           + "**************** MENSAGEM AUTOMATICA NÃO PRECISA SER RESPONDIDA **************"
//                           + "\n"
//                           + "\n";
                   
                  message.setRecipients(Message.RecipientType.TO, toUser);
                  message.setSubject("SisMVC Informa: Seu certificado está prestes a vencer testando 123");//Assunto
                  message.setText("testando envio de email");
              
                  
                   
               

                  


                  /**Método para enviar a mensagem criada*/
                  Transport.send(message);
                  
                  System.out.println("ENTROU AQUI ***************************************");
                  
                 

        }catch(Exception e) {
            System.out.println( "Deu ruim "+e);
            JOptionPane.showMessageDialog(null, "foi nao");

        }
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
//         UsuarioDAO usuario = new UsuarioDAO();
//          ResultSet objRs = null;
//          
//         objRs = usuario.consultarEmailUsuarios();
//       
//        
//        String grupoEmail="";
//        
//        
//        while (objRs.next()) {
//            
//                String email = objRs.getObject("email").toString();
//                
//                grupoEmail = grupoEmail+","+email;
//                            
//        }
//        
//        System.out.println(grupoEmail);
       
      //  System.out.println(grupoEmail);
        
//        String unidade = "fsf65sfs66666";
//         String unidade1 = unidade.replaceAll("[^0-9]", "");
//         System.out.println(unidade1);
    }
}
