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

public class GUI extends JFrame implements ActionListener{
	
	private JButton starten;
	private JButton laden;
	private JButton optionen;
	private JButton beenden;
	
	private String spielername;
	
	public GUI(){
		super();
	}
	
	public GUI(int breite, int hoehe){
		super("The Gravesite");
		super.setSize(breite, hoehe);
		
		// Hintergrundbild
		File bgimage = new File("/Users/mariusschulte/git/TheGravesite/TheGravesite/src/bg.png");
		BufferedImage bufimg = null;
		
		try{
			bufimg = ImageIO.read(bgimage);
		} catch(IOException e){
			System.err.println("Image not found!");
		}
		JPanel imagepanel = new JPanel();
		imagepanel.add(new JLabel(new ImageIcon(bufimg)));
		this.add(imagepanel, BorderLayout.CENTER);
		
		
		/*BufferedImage myPicture;
		try {
			myPicture = ImageIO.read(new File("/Users/mariusschulte/git/TheGravesite/TheGravesite/src/bg.png"));
			JPanel imagepanel = new JPanel();
			imagepanel.add(new JLabel(new ImageIcon(myPicture)));
			this.add(imagepanel, BorderLayout.CENTER);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		// Panels
		JPanel buttons = new JPanel();
		//JPanel leeresPanel = new JPanel();
		
		// Layouts
		this.setLayout(new BorderLayout());
		buttons.setLayout(new GridLayout(4,1));
		//leeresPanel.setSize(300, hoehe);
		
		// Buttons
		starten = new JButton("Start");
		laden = new JButton("Laden");
		optionen = new JButton("Optionen");
		beenden = new JButton("Beenden");
		
		// Buttons dem Panel hinzufügen
		buttons.add(starten);
		buttons.add(laden);
		buttons.add(optionen);
		buttons.add(beenden);
		
		// Listener hinzufügen
		starten.addActionListener(this);
		
		this.add(buttons, BorderLayout.SOUTH);
		//this.add(leeresPanel, BorderLayout.WEST);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		super.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		String befehl = e.getActionCommand();
		if (befehl.equals("Start")) {
			// Spielername muss gespeichert werden und ein neuer Spieler erzeugt werden
			// evtl. Abfrage, ob weiterspielen wenn Spielstand vorhanden
			spielername = JOptionPane.showInputDialog(null,"Geben Sie Ihren Namen ein","Neuen Spieler erstellen",JOptionPane.PLAIN_MESSAGE);
		}	
		
	}
	
}