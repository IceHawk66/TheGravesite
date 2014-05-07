package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.*;
import javax.swing.*;

public class GUI extends JFrame{
	
	public GUI(){
		super();
	}
	
	public GUI(int breite, int hoehe){
		super("The Gravesite");
		super.setSize(breite, hoehe);
		
		// Hintergrundbild
		/*File bgimage = new File("/Users/mariusschulte/git/TheGravesite/TheGravesite/src/bg.png");
		BufferedImage bufimg = null;
		
		try{
			bufimg = ImageIO.read(bgimage);
			
		} catch(IOException e){
			System.err.println("Image not found!");
		}
		System.out.println(this.getClass());
		JPanel imagepanel = new JPanel();
		imagepanel.add(new JLabel(new ImageIcon(bufimg)));
		this.add(imagepanel, BorderLayout.CENTER);*/
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

		ImageIcon image = new ImageIcon("/Users/mariusschulte/git/TheGravesite/TheGravesite/src/bg.png");
		JLabel label = new JLabel();
		JPanel panel = new JPanel();
		label.setIcon(image);
		panel.add(label);
		this.add(panel, BorderLayout.CENTER);
		validate();
		
		// Panels
		JPanel buttons = new JPanel();
		//JPanel leeresPanel = new JPanel();
		
		// Layouts
		this.setLayout(new BorderLayout());
		buttons.setLayout(new GridLayout(4,1));
		//leeresPanel.setSize(300, hoehe);
		
		// Buttons
		JButton starten = new JButton("Start");
		JButton laden = new JButton("Laden");
		JButton optionen = new JButton("Optionen");
		JButton beenden = new JButton("Beenden");
		
		// Buttons dem Panel hinzufügen
		buttons.add(starten);
		buttons.add(laden);
		buttons.add(optionen);
		buttons.add(beenden);
		
		this.add(buttons, BorderLayout.SOUTH);
		//this.add(leeresPanel, BorderLayout.WEST);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		super.setVisible(true);
	}
}