package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;

public class GUI extends JFrame{
	
	public GUI(){
		super();
	}
	
	public GUI(int breite, int hoehe){
		super("The Gravesite");
		super.setSize(breite, hoehe);
		
		// Panels
		JPanel buttons = new JPanel();
		Dimension groessebuttons = new Dimension(breite / 2, hoehe / 2);
		buttons.setPreferredSize(groessebuttons);
		buttons.setMaximumSize(new Dimension(100, 100));
		buttons.setMinimumSize(new Dimension(1,1));
		
		// Layouts
		this.setLayout(new BorderLayout());
		buttons.setLayout(new GridLayout(4,1));
		
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
		
		this.add(buttons, BorderLayout.CENTER);
		
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setVisible(true);
	}
}
