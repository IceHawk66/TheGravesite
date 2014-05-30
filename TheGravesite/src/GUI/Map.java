package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import main.ServerToClient;

public class Map extends JPanel{
	
	private ServerToClient stc;
	private JFrame owner;
	private int[][] map;
	
	public Map(ServerToClient stc, JFrame owner){
		this.stc = stc;
		this.owner = owner;
		this.setPreferredSize(new Dimension((int)(owner.getContentPane().getWidth()*0.66),(int)(owner.getContentPane().getHeight()*0.75)));
		this.setVisible(true);
	}
	
	public void paint(Graphics g){

		// Map
		try {
			map = stc.getSichtfeld();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 100000,100000);
		g.setColor(Color.white);
		//g.drawString("" + this.getContentPane().getWidth(), 40, 40);
		//g.drawString("" + map.length, 40, 70);
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[0].length; j++){
				switch(map[i][j]){
					case 0: 
					case 1:
					case 2:
						g.setColor(Color.WHITE);break;
					case 3:
					case 4: 
						g.setColor(Color.GREEN);break;
					case 5:
						g.setColor(Color.GREEN);break;
					default:
						g.setColor(Color.WHITE);break;
				}
				g.drawString(intToString(map[i][j]), (berechnePosition((int)(owner.getContentPane().getHeight() * 0.66), map.length, j)), berechnePosition((int)(owner.getContentPane().getWidth()*0.75), map[0].length, i));
				
			}
		}
	}
	
	
	public String intToString(int n){
		switch(n){
			case 0: return " ";
			case 1: return "#";
			case 2: return ".";
			case 3: return "0";
			case 4: return "0";
			case 5: return "X";
			default: return " ";
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
