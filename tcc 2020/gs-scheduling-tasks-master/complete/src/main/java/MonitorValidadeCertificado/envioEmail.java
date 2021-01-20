
package MonitorValidadeCertificado;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;


public class envioEmail {
    
    public void envioEmail(String email, int dia){
        
        
        
         Properties props = new Properties();
            /** Parâmetros de conexão com servidor Gmail */
         
	         props.put("mail.smtp.host", "smtp.gmail.com");
	         props.put("mail.smtp.port", "587");
	         props.put("mail.smtp.auth", "true");
	         props.put("mail.smtp.starttls.enable", "true");
         


            Session session = Session.getInstance(props,new javax.mail.Authenticator() {
                             protected PasswordAuthentication getPasswordAuthentication() 
                             {
                                   return new PasswordAuthentication("winicius.leal@soluti.com.br", "sol@2016");
                             }
                        });
            session.setDebug(true);

        try{

            Message message = new MimeMessage(session);
                  message.setFrom(new InternetAddress("winicius.leal@soluti.com.br")); //Remetente

                  Address[] toUser = InternetAddress 
                             .parse(email);  //Destinatário(s)
                  
               if(dia==1){
                   
                   System.out.println("********************************** ENTROU AQUI ***************************************");
                   
                   String msg = "Boa Noite,\n"
                           + "\n"
                           + "Seu Certificado Digtital está previsto para vencer amanhã !"
                           + "\n"
                           + "\n"
                           + "\n"
                           + "**************** MENSAGEM AUTOMATICA NÃO PRECISA SER RESPONDIDA **************"
                           + "\n"
                           + "\n";
                   
                  message.setRecipients(Message.RecipientType.TO, toUser);
                  message.setSubject("SisCVC Informa: Seu certificado está prestes a vencer");//Assunto
                  message.setText(msg);
               }else{
                   if(dia==15){
                        message.setRecipients(Message.RecipientType.TO, toUser);
                  message.setSubject("SisCVC Informa: Seu certificado está prestes a vencer");//Assunto
                  message.setText("BOA NOITE,"
                          + "Seu certificado está previsto para vencer daqui 15 dias ! ");
                   }else{
                       if(dia==30){
                           message.setRecipients(Message.RecipientType.TO, toUser);
			               message.setSubject("SisMVC Informa: Seu certificado está prestes a vencer");//Assunto
			               message.setText("BOA NOITE,"
                          + "Seu certificado está previsto para vencer daqui 30 dias ! ");
                       }
                   }
               }

                  


                  
             Transport.send(message);
                 

        }catch(Exception e) {
            System.out.println( "Erro "+e);
            JOptionPane.showMessageDialog(null, "Falha ao enviar o Email");

        }
        
        
    }
    
}
