package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;

import javax.imageio.*;
import javax.swing.*;

import main.ServerToClient;

public class GUI extends JFrame implements ActionListener, KeyListener{
	private JLabel picLabel;
	private JPanel oberflaeche;
	private String spielername;
	private String spielid;
	private int[][] map;
	private String[][] spielerdaten;
	private ServerToClient stc;
	private int hoehe;
	private int breite;
	private Map welt;
	private Spielerdaten sd;
	private MoeglicheZuege mz;
	
	/*
	 * Bild von /src anstatt /bin
	 * menubar graphics
	 * größe von jpanel
	 */
	
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
		this.breite = breite;
		this.hoehe = hoehe;
		this.map = map;
		this.spielerdaten = spielerdaten;
		this.stc = stc;

		welt = new Map(stc, this);
		sd = new Spielerdaten(stc, this);
		mz = new MoeglicheZuege(stc, this);
		
		
		erstelleMenuBar();
		registriereListener();
		ladeOberflaeche();
		
		// Für paint()
		JPanel spieldaten = new JPanel();
		spieldaten.setPreferredSize(new Dimension(breite/3,hoehe));
		JPanel spielzuege = new JPanel();
		spielzuege.setPreferredSize(new Dimension(breite/3,hoehe/4));
		this.pack();

		this.addKeyListener(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		super.setVisible(true);
	}
	
	private void registriereListener() {
		neuesSpiel.addActionListener(this);
		ladeSpiel.addActionListener(this);
		optionen.addActionListener(this);
		speichern.addActionListener(this);
		beendeSpiel.addActionListener(this);
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

	private void ladeOberflaeche() {
		oberflaeche = new JPanel();
		oberflaeche.setLayout(new BorderLayout());
		oberflaeche.add(ladeBild(), BorderLayout.CENTER);
		this.add(oberflaeche);
	}
	
	private JLabel ladeBild(){
		
		BufferedImage hintergrund = null;
 		try{
			hintergrund = ImageIO.read(this.getClass().getResource("/resources/bg.png"));
 			picLabel = new JLabel(new ImageIcon(hintergrund));
 			this.add(picLabel);
 			return picLabel;
 		} catch (IOException e) {
 			
 		}
 		return new JLabel(); 
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
        	picLabel.setVisible(false);	// oberflaeche.remove(picLabel) wäre mir lieber, aber funktioniert nicht *argh*
        	try {
				stc.erstelleNeueMap();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	speichern.setEnabled(true);
        	oberflaeche.add(welt, BorderLayout.CENTER);
        	oberflaeche.add(sd, BorderLayout.EAST);
        	oberflaeche.add(mz, BorderLayout.SOUTH);
        	mz.repaint();
        	sd.repaint();
			welt.repaint();
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


	@Override
	public void keyTyped(KeyEvent e) {
		// nothing here
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if ((e.getKeyCode() == KeyEvent.VK_UP) || (e.getKeyCode() == 87))
			try {
				stc.bewegeMap(1);
				map = stc.getSichtfeld();
				welt.repaint();
				sd.repaint();
				mz.repaint();
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
    	if ((e.getKeyCode() == KeyEvent.VK_DOWN) || (e.getKeyCode() == 83))
			try {
				stc.bewegeMap(2);
				map = stc.getSichtfeld();
				welt.repaint();
				sd.repaint();
				mz.repaint();
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
    	if ((e.getKeyCode() == KeyEvent.VK_LEFT) || (e.getKeyCode() == 65))
			try {
				stc.bewegeMap(3);
				map = stc.getSichtfeld();
				welt.repaint();
				sd.repaint();
				mz.repaint();
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
    	if ((e.getKeyCode() == KeyEvent.VK_RIGHT) || (e.getKeyCode() == 68))
			try {
				stc.bewegeMap(4);
				map = stc.getSichtfeld();
				welt.repaint();
				sd.repaint();
				mz.repaint();
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// nothing here
	}

	public int getBreite() {
		return breite;
	}

	public int getHoehe() {
		return hoehe;
	}
}