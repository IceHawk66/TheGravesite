package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.rmi.RemoteException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.ServerToClient;

public class Spielerdaten extends JPanel{
	
	private ServerToClient stc;
	private JFrame owner;
	private String[][] spielerdaten;

	public Spielerdaten(ServerToClient stc, JFrame owner){
		this.stc = stc;
		this.owner = owner;
		this.setPreferredSize(new Dimension(312,owner.getContentPane().getHeight()));
		this.setVisible(true);
	}
	
	public void paint(Graphics g){
		g.setColor(Color.black);
		g.fillRect(0, 0, 10000,10000);
		g.setColor(Color.white);
		
		try {
			spielerdaten = stc.getSpielerdaten();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for(int i = 0; i < spielerdaten.length; i++){
			for(int j = 0; j < spielerdaten[0].length; j++){
				g.drawString(spielerdaten[i][j], (berechnePosition(this.getHeight(), spielerdaten.length, j)), 
						berechnePosition((int)(this.getWidth()*0.25), spielerdaten[0].length, i));
				
			}
		}
	}
	
	public int berechnePosition(int a, int b, int c){
		double d = (double)a;
		double e = (double)b;
		double f = (double)c;
		double g = d / e * f;
		return (int)g;
	}	
}
