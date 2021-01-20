package view;

import java.io.File;
import java.io.IOException;
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
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import javax.swing.JOptionPane;
import model.bean.Certificado_usuario;
import model.dao.Certificado_usuarioDAO;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;
import view.Coleta;

public class MVCAutomatic {
    
    public static void main(String[] args) {
    	
    	TrayClass t = new TrayClass();
    	
    	File arquivo = new File("MVCAutomatic.jar");
    	
    	try {
            String value = "MVCAutomatic";
            String dir = arquivo.getAbsolutePath();
            //String dir = System.getProperty("java.class.path");
            String path = "REG ADD \"HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Run\"";
            String command = path+" /V \""+value+"\" /D \""+dir+"\" /f";
            System.out.println(command);
            Runtime.getRuntime().exec(command);
        }catch(Exception e){
        	e.printStackTrace();
        }
    	
		Timer timer = new Timer(); //instanciar o timer thread
		
		TimerTask task = new TimerTask(){
			
			public void run() {
				
				Coleta coleta = new Coleta();
				
				try {
					coleta.coletaDadosDoCertificado();
					
				} catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException e) {
			
					e.printStackTrace();
				}
				
				
			}
					
		};
		
		
		
		timer.scheduleAtFixedRate(task, 0, 50000);
		
		
      
		
	}
}
