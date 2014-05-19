package GUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
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

public class GUI extends JFrame implements ActionListener{
	private JLabel picLabel;
	private String spielername;
	private int[][] map;
	
	// Menuebar
	JMenuBar menuBar;
    JMenu fileMenu;
	        
	// Dropdown Eintraege
	JMenuItem neuesSpiel;
	JMenuItem ladeSpiel;
	JMenuItem optionen;
	JMenuItem speichern;
	JMenuItem beendeSpiel;
	
	
	public GUI(int breite, int hoehe, int[][] map){
		super("The Gravesite");
		super.setSize(breite, hoehe);
		this.map = map;
		
		erstelleMenuBar();
		ladeBild();
		registriereListener();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		super.setVisible(true);
	}
	
	private void registriereListener() {
		neuesSpiel.addActionListener(this);
		ladeSpiel.addActionListener(this);
		optionen.addActionListener(this);
		speichern.addActionListener(this);
		beendeSpiel.addActionListener(this);
		
	}

	private void ladeBild() {
		// Bild im Hauptmenue
		BufferedImage hintergrund = null;
		try {
			// TODO evtl. anderen Pfad benutzen / Bild woanders ablegen
			hintergrund = ImageIO.read(this.getClass().getResource("bg.png"));
			picLabel = new JLabel(new ImageIcon(hintergrund));
			this.add(picLabel);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void erstelleMenuBar() {
		// Menue hinzufuegen
		menuBar = new JMenuBar();
				
		// Menues erstellen
        fileMenu = new JMenu("Datei");
        menuBar.add(fileMenu);
		        
		// Dropdown Eintraege
		neuesSpiel = new JMenuItem("Neues Spiel");
		ladeSpiel = new JMenuItem("Spiel laden");
		optionen = new JMenuItem("Optionen");
		speichern = new JMenuItem("Speichern");
		beendeSpiel = new JMenuItem("Beenden");
		        
		// Eintraege dem Menue hinzufuegen
		fileMenu.add(neuesSpiel);
		fileMenu.add(ladeSpiel);
		fileMenu.add(optionen);
		fileMenu.add(speichern);
		fileMenu.add(beendeSpiel);
		        
		speichern.setEnabled(false);
		        
		// MenuBar setzen
		this.setJMenuBar(menuBar);
	}

	// Map abbilden
	public void ladeMap(){
	    JPanel grid = new JPanel();
	    grid.setLayout(new GridLayout(map.length, map[0].length));
	    for (int i = 0; i < map.length; i++) {
	        for (int j = 0; j < map[0].length; j++) {
	        	if (map[i][j] == 2)
                    grid.add(new JLabel("'")); // Boden
                if (map[i][j] == 1)
                    grid.add(new JLabel("#")); // Wand
                if (map[i][j] == 0)
                    grid.add(new JLabel(" ")); // Ausserhalb der Map
	        }
	    }
	    this.add(grid);
	    grid.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String befehl = e.getActionCommand();

		if (befehl.equals("Neues Spiel")) {
			spielername = JOptionPane.showInputDialog(null,"Geben Sie Ihren Namen ein","Neuen Spieler erstellen",JOptionPane.PLAIN_MESSAGE);
        	picLabel.setVisible(false);
        	ladeMap();
		}
		
		if(befehl.equals("Spiel laden")){
			// ladeSpiel Dialog aufrufen
		}
		
		if(befehl.equals("Optionen")){
			// Optionen Menue
		}
		
		if(befehl.equals("Spiel speichern")){
			// Spiel speichern
		}
		
		if(befehl.equals("Beenden")){
			System.exit(0);
		}
        
	}
	
}