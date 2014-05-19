package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
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

public class GUI extends JFrame{
	private JLabel picLabel;
	private String spielername;
	private int[][] map;
	
	public GUI(){
		super();
	}
	
	public GUI(int breite, int hoehe, int[][] map){
		super("The Gravesite");
		super.setSize(breite, hoehe);
		map = this.map;
		
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
		
		// Menue hinzufuegen
		JMenuBar menuBar = new JMenuBar();
		
		// Menues erstellen
        JMenu fileMenu = new JMenu("Datei");
        menuBar.add(fileMenu);
        
        // Dropdown Eintraege
        JMenuItem neuesSpiel = new JMenuItem("Neues Spiel");
        JMenuItem ladeSpiel = new JMenuItem("Spiel laden");
        JMenuItem optionen = new JMenuItem("Optionen");
        JMenuItem speichern = new JMenuItem("Speichern");
        JMenuItem beendeSpiel = new JMenuItem("Beenden");
        
        // Eintraege dem Menue hinzufuegen
        fileMenu.add(neuesSpiel);
        fileMenu.add(ladeSpiel);
        fileMenu.add(optionen);
        fileMenu.add(speichern);
        fileMenu.add(beendeSpiel);
        
        speichern.setEnabled(false);
        
        // MenuBar setzen
        this.setJMenuBar(menuBar);
        
        // ActionListener fuer das Menue
        neuesSpiel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	spielername = JOptionPane.showInputDialog(null,"Geben Sie Ihren Namen ein","Neuen Spieler erstellen",JOptionPane.PLAIN_MESSAGE);
            	picLabel.setVisible(false);
            	zeichneOberflaeche();
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
        
        speichern.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	// Spiel speichern
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
	
	// TODO Map abbilden
	public void zeichneOberflaeche(){
		/*ImageIcon raute = new ImageIcon(this.getClass().getResource("raute.png"));
	    JPanel grid = new JPanel();
	    grid.setLayout(new GridLayout(50, 50));
	    for (int i = 0; i < map.length; i++) {
	        for (int j = 0; j < map[0].length; j++) {
	        	if (map[i][j] == 2)
                    new JLabel(raute);
                if (map[i][j] == 1)
                    new JLabel(raute);
                if (map[i][j] == 0)
                    new JLabel(raute);
	        }
	    }
	    this.add(grid);
	    grid.setVisible(true);
	    */
		
		JTextArea tamap = new JTextArea();
		JPanel pmap = new JPanel();
		pmap.add(tamap);
		for (int i = 0; i < map.length; i++) {
	        for (int j = 0; j < map[0].length; j++) {
	        	if (map[i][j] == 2)
                    tamap.append("'");
                if (map[i][j] == 1)
                	tamap.append("#");
                if (map[i][j] == 0)
                	tamap.append(" ");
	        }
	        tamap.append("\n");
	    }
	}
	
}