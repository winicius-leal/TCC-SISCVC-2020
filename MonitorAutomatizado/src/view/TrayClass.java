package view;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import java.net.URL;

public class TrayClass {
	
	static TrayIcon trayIcon;
	
	public TrayClass() {
		show();
	}

	public static void show() {
		if(!SystemTray.isSupported()) {
			System.exit(0);
		}
		trayIcon = new TrayIcon(createIcon("/view/icon.png", "Icon"));
		trayIcon.setToolTip("MVC Automatic v1.0");
		final SystemTray tray = SystemTray.getSystemTray();
		final PopupMenu menu = new PopupMenu();
		MenuItem about = new MenuItem("About");
		MenuItem exit = new MenuItem("Exit");
		menu.add(about);
		menu.addSeparator();
		menu.add(exit);
		
		about.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "O MVC Automatic v1.0 está monitorando seus certificados");
				
			}
		});
        exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
				
			}
		});
		
		trayIcon.setPopupMenu(menu);
		
		
		try {
			tray.add(trayIcon);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	protected static Image createIcon(String path, String desc) {
		URL imageURL = TrayClass.class.getResource(path);
		return (new ImageIcon(imageURL, desc)).getImage();
	}
	
	
}


