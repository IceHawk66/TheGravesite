package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.*;
import javax.swing.*;

public class GUI extends JFrame{

	private String spielername;
	
	public GUI(){
		super();
	}
	
	public GUI(int breite, int hoehe){
		super("The Gravesite");
		super.setSize(breite, hoehe);
		
		System.out.println(System.getProperty("user.dir" + "bg.png"));
		
		BufferedImage hintergrund;
		try {
			hintergrund = ImageIO.read(new File(System.getProperty("user.dir" + "/bg.jpg")));
			JLabel picLabel = new JLabel(new ImageIcon(hintergrund));
			this.add(picLabel);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Menue hinzufuegen
		JMenuBar menuBar = new JMenuBar();
		
		// Menues erstellen
        JMenu fileMenu = new JMenu("Datei");
        menuBar.add(fileMenu);
        
        // Dropdown Eintraege
        JMenuItem neuesSpiel = new JMenuItem("Neues Spiel");
        JMenuItem ladeSpiel = new JMenuItem("Spiel laden");
        JMenuItem optionen = new JMenuItem("Optionen");
        JMenuItem beendeSpiel = new JMenuItem("Beenden");
        
        // Eintraege dem Menue hinzufuegen
        fileMenu.add(neuesSpiel);
        fileMenu.add(ladeSpiel);
        fileMenu.add(optionen);
        fileMenu.add(beendeSpiel);
        
        // MenuBar setzen
        this.setJMenuBar(menuBar);
        
        // ActionListener fuer das Menue
        neuesSpiel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	spielername = JOptionPane.showInputDialog(null,"Geben Sie Ihren Namen ein","Neuen Spieler erstellen",JOptionPane.PLAIN_MESSAGE);
            }
        });
        
        ladeSpiel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	// ladeSpiel Dialog aufrufen
            }
        });
        
        optionen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	// Optionen Menue
            }
        });
        
        beendeSpiel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	System.exit(0);
            }
        });
        

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		super.setVisible(true);
	}
	
}