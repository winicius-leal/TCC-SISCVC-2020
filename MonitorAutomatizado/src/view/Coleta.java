package view;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.Signature;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Enumeration;

import javax.print.DocFlavor.URL;
import javax.swing.JOptionPane;

import junit.framework.Test;
import model.bean.Certificado_usuario;
import model.dao.Certificado_usuarioDAO;

public class Coleta {
	
	public static void coletaDadosDoCertificado () throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		
		
		String aliasDoWindowsMY = null;
		String emailCert = null;
		String dataValidade = null;

	    KeyStore keystore = KeyStore.getInstance("Windows-MY");
	    
	    
	    
	    keystore.load(null, null);

	    Enumeration<String> al = keystore.aliases(); //cria um enumeration para listar todos os aliases dos certificados encontrados
	    
	    
	    
	           
	    while (al.hasMoreElements()) {//enquanto houver mais elementos
	    	
	        aliasDoWindowsMY = al.nextElement(); //pega o primeiro elemento do enumeration
	        
	        System.out.println(aliasDoWindowsMY); //imprimi o nome do alias no Windows MY
	        
	        X509Certificate cert = (X509Certificate) keystore.getCertificate(aliasDoWindowsMY); //obj das informa��es do certificado X509
	 
	        
	        int indiceTipoCertificado1;
			int indiceTipoCertificado2;
			if(cert.getSubjectDN().toString().indexOf("PF")!=-1){ // se o �ndice da primeira ocorr�ncia da string PF for ( !=-1) ou (existir).
	            indiceTipoCertificado1 = cert.getSubjectDN().toString().indexOf("PF"); 
	            indiceTipoCertificado2=indiceTipoCertificado1+5;
	            String tipoCertificado = cert.getSubjectDN().toString().substring(indiceTipoCertificado1,indiceTipoCertificado2);//forma uma substring das 5 posi��es sequentes ap�s o P (PJ A1)
	            System.out.println("TIPO CERTIFICADO: "+tipoCertificado);//imprime o tipo do certificado 
	            emailCert = cert.getSubjectAlternativeNames().iterator().next().get(1).toString(); //captura o email
	            System.out.println("EMAIL: "+cert.getSubjectAlternativeNames().iterator().next().get(1)); //imprime o email
	            dataValidade = new SimpleDateFormat("dd/MM/yyyy").format(cert.getNotAfter());
	            System.out.println("DATA DE VALIDADE: "+dataValidade);
	            verificarEmail (emailCert, dataValidade);
	        }
			
			
	        
			if(cert.getSubjectDN().toString().indexOf("PJ")!=-1){ // se o �ndice da primeira ocorr�ncia da string PF for ( !=-1) ou (existir).
				indiceTipoCertificado1 = cert.getSubjectDN().toString().indexOf("PJ"); //numero do indice do PJ
	            indiceTipoCertificado2=indiceTipoCertificado1+5; //numero do indice do PJ + 5
	            String tipoCertificado = cert.getSubjectDN().toString().substring(indiceTipoCertificado1,indiceTipoCertificado2);//forma uma substring das 5 posi��es sequentes ap�s o P (PJ A1)
	            System.out.println("TIPO CERTIFICADO: "+tipoCertificado);//imprime o tipo do certificado 
	            emailCert = cert.getSubjectAlternativeNames().iterator().next().get(1).toString(); //captura o email          
	            System.out.println("EMAIL: "+cert.getSubjectAlternativeNames().iterator().next().get(1)); //imprime o email
	            dataValidade = new SimpleDateFormat("dd/MM/yyyy").format(cert.getNotAfter());
	            System.out.println("DATA DE VALIDADE: "+dataValidade);
	            verificarEmail (emailCert,dataValidade);
	           
	            
	        }
	            
	        System.out.println("------------------------------------------------------------------------------------------------------");

	       
	    } 
		
		
	}
	

    static ResultSet objRs = null;
    
    static Certificado_usuario d = new Certificado_usuario();
    static Certificado_usuarioDAO certdao = new Certificado_usuarioDAO();
    
    
    
    private static void verificarEmail(String emailCert, String dataValidade) {
    	
   	 objRs = certdao.selecionarEmailExistente(emailCert);
   	 
   	try {
		if (objRs.next()) {
			
			System.out.println("Ja existe no banco de dados");
				
			
		}else {
			
			incluir(emailCert, dataValidade);
			
			System.out.println("N�o existe no banco de dados");
			
			//JOptionPane.showMessageDialog(null, "Um novo certificado foi encontrado !");
			
		}
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
   	
		
	}
    
    
   

	private static void incluir(String emailCert, String dataValidade) {
		
		 try {
	            
	            d.setEmail(emailCert);            

	            String prazoValidade = dataValidade;
	            String dia1 = prazoValidade.substring(0, 2);
	            String mes1 = prazoValidade.substring(3, 5);
	            String ano1 = prazoValidade.substring(6, 10);
	            String prazoValidadeConvertida = ano1 + "-" + mes1 + "-" + dia1;
	            
	            
	            d.setDataValidade(prazoValidadeConvertida);
	            
	            certdao.inserir(d);
	           System.out.println(prazoValidadeConvertida+emailCert);
	           
	           
			 
			 System.out.println("INCLUIDO NO BANCO DE DADOS");
			 JOptionPane.showMessageDialog(null, "Novo certificado encontrado, voc� receber� notifica��o de vencimento !");
	            
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(null, "ERRO NO CODIGO PARA INCLUSÃO");
	        }
		
	}

	
	
	
	
	
  
    
}


