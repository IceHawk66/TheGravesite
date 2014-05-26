package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.rmi.RemoteException;

import javax.imageio.*;
import javax.swing.*;

import main.ServerToClient;

public class GUI extends JFrame implements ActionListener, KeyListener{
	private JLabel picLabel;
	private JPanel grid;
	private JPanel oberflaeche;
	private String spielername;
	private String spielid;
	private int[][] map;
	private String[][] spielerdaten;
	private ServerToClient stc;
	private int hoehe;
	private int breite;
	
	// Menuebar
	JMenuBar menuBar;
    JMenu fileMenu;
	        
	// Dropdown Eintraege
	JMenuItem neuesSpiel;
	JMenuItem ladeSpiel;
	JMenuItem optionen;
	JMenuItem speichern;
	JMenuItem beendeSpiel;
	
	
	public GUI(int breite, int hoehe, int[][] map, String[][] spielerdaten, ServerToClient stc){
		super("The Gravesite");
		super.setSize(breite, hoehe);
		this.map = map;
		this.spielerdaten = spielerdaten;
		this.stc = stc;
		this.hoehe = hoehe;
		this.breite = breite;
		
		erstelleMenuBar();
		ladeBild();
		registriereListener();
		
		// Für paint()
		grid = new JPanel();
		JPanel spieldaten = new JPanel();
		spieldaten.setPreferredSize(new Dimension(breite/3,hoehe));
		JPanel spielzuege = new JPanel();
		spielzuege.setPreferredSize(new Dimension(breite/3,hoehe/4));
		
		this.addKeyListener(this);
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
	public void aktualisiereMap(){
	    grid = new JPanel();
	    grid.setBackground(Color.BLACK);
	    grid.setLayout(new GridLayout(map.length, map[0].length));
	    for (int i = 0; i < map.length; i++) {
	        for (int j = 0; j < map[0].length; j++) {
	        	if (map[i][j] == 5)
                    grid.add(new JLabel("<html><font color='green'>X</font></html>")); // Spieler
	        	if (map[i][j] == 4)
                    grid.add(new JLabel("<html><font color='red'>E</font></html>")); // Eingang
	        	if (map[i][j] == 3)
                    grid.add(new JLabel("<html><font color='blue'>O</font></html>")); // Ausgang
	        	if (map[i][j] == 2)
                    grid.add(new JLabel("<html><font color='white'>.</font></html>")); // Boden
                if (map[i][j] == 1)
                    grid.add(new JLabel("<html><font color='white'>#</font></html>")); // Wand
                if (map[i][j] == 0)
                    grid.add(new JLabel("<html><font color='white'>#</font></html>")); // Ausserhalb der Map
	        }
	    }
	    //grid.setVisible(true);
	}
	
	public JPanel ladeSpielerdaten(){
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(breite/3,hoehe));
		panel.setBackground(Color.BLACK);
		panel.setLayout(new GridLayout(spielerdaten.length, spielerdaten[0].length));
		for(int i = 0; i < spielerdaten.length; i++){
			for(int j = 0; j < spielerdaten[0].length; j++){
				panel.add(new JLabel("<html><font color='white'>" + spielerdaten[i][j] + "</font></html>"));
			}
		}
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String befehl = e.getActionCommand();

		if (befehl.equals("Neues Spiel")) {
			// TODO Spielername setzen
			spielername = JOptionPane.showInputDialog(null,"Geben Sie Ihren Namen ein","Neuen Spieler erstellen",JOptionPane.PLAIN_MESSAGE);
        	try {
				stc.addSpieler(spielername);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			picLabel.setVisible(false);
			//repaint();
        	//ladeOberflaeche();
		}
		
		if(befehl.equals("Spiel laden")){
			// TODO Spiel speichern
			spielid = JOptionPane.showInputDialog(null,"Geben Sie Ihre Spiel-ID ein","Spiel laden",JOptionPane.PLAIN_MESSAGE);
		}
		
		if(befehl.equals("Optionen")){
			// TODO Optionen Menue
		}
		
		if(befehl.equals("Spiel speichern")){
			// TODO Spiel speichern
		}
		
		if(befehl.equals("Beenden")){
			System.exit(0);
		}
        
	}

	private void ladeOberflaeche() {
		oberflaeche = new JPanel();
		oberflaeche.setLayout(new BorderLayout());
		aktualisiereMap();
		oberflaeche.add(grid, BorderLayout.CENTER);
		oberflaeche.add(ladeSpielerdaten(), BorderLayout.EAST);
		oberflaeche.add(ladeMoeglicheZuege(), BorderLayout.SOUTH);
		this.add(oberflaeche);
	}

	private JPanel ladeMoeglicheZuege() {
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(breite/3,hoehe/4));
		panel.setBackground(Color.BLACK);
		panel.setLayout(new GridLayout(spielerdaten.length, spielerdaten[0].length));
		for(int i = 0; i < spielerdaten.length; i++){
			for(int j = 0; j < spielerdaten[0].length; j++){
				panel.add(new JLabel("<html><font color='white'>" + spielerdaten[i][j] + "</font></html>"));
			}
		}
		return panel;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// nothing here
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyChar() == KeyEvent.VK_LEFT){ // map nach rechts verschieben
			map[1][1] = 5;
			aktualisiereMap();
			oberflaeche.remove(grid);
			//oberflaeche.add(grid);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// nothing here
	}
	
	public void paint(Graphics g){
		// Map
		g.setColor(Color.black);
		g.fillRect(0, 0, breite, hoehe);
		g.setColor(Color.white);
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[0].length; j++){
				g.drawString(String.valueOf(map[i][j]), berechnePosition(grid.getWidth(), map.length, i), (berechnePosition(grid.getHeight(), map[0].length, j)) + 40);
			}
		}
	}
	
	/**
	 * Variablen werden erst in Double gecastet, damit bei grid.getWidth() / map.length nicht 0 herauskommt
	 * Wenn hinterher benötigt dann über Server, Berechnung nicht auf Client
	 * @param a Länge des JPanels
	 * @param b Länge des Arrays
	 * @param c Multiplikator
	 * @return X bzw Y Position
	 */
	public int berechnePosition(int a, int b, int c){
		double d = (double)a;
		double e = (double)b;
		double f = (double)c;
		double g = d / e * f;
		return (int)g;
	}
}