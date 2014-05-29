package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.ServerToClient;

public class MoeglicheZuege extends JPanel
{
	private ServerToClient stc;
	private JFrame owner;
	private int[][] map;
	
	public MoeglicheZuege(ServerToClient stc, JFrame owner){
		this.stc = stc;
		this.owner = owner;
		this.setPreferredSize(new Dimension((int)(owner.getContentPane().getWidth()*0.66),(int)(owner.getContentPane().getHeight()*0.25)));
		this.setVisible(true);
	}
	
	public void paint(Graphics g){
		int spielerX = -1;
		int spielerY = -1;
		int counter = 0;
		List<String> spielzuege = new LinkedList<String>();

		try {
			map = stc.getAktiveMap();
			spielerX = stc.getSpielerX();
			spielerY = stc.getSpielerY();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}

		g.setColor(Color.black);
		g.fillRect(0, 0, 10000,10000);
		g.setColor(Color.white);
		
		if(spielerX == -1 || spielerY == -1){
			g.drawString("ERROR", (int)(this.getWidth()*0.1), (int)(this.getHeight()*0.1));
			System.err.println("Die aktuellen Spielerkoordinaten konnten nicht abgerufen werden.");
		}
		else{
			if(map[spielerX-1][spielerY] != 1){
				spielzuege.add("Schritt nach links");
			}
			if(map[spielerX+1][spielerY] != 1){
				spielzuege.add("Schritt nach rechts");
			}
			if(map[spielerX][spielerY-1] != 1){
				spielzuege.add("Schritt nach oben");
			}
			if(map[spielerX][spielerY+1] != 1){
				spielzuege.add("Schritt nach unten");
			}
			if(map[spielerX-1][spielerY] != 6){
				spielzuege.add("Aktion ausführen");
			}
			if(map[spielerX+1][spielerY] != 6){
				spielzuege.add("Aktion ausführen");
			}
			if(map[spielerX][spielerY-1] != 6){
				spielzuege.add("Aktion ausführen");
			}
			if(map[spielerX][spielerY+1] != 6){
				spielzuege.add("Aktion ausführen");
			}
		}
		this.setLayout(new GridLayout(1, spielzuege.size()));
		Iterator<String> it = spielzuege.iterator();
		g.drawString("Mögliche Operationen:", (int)(this.getWidth()*0.1), (int)(this.getHeight()*0.1));
		while(it.hasNext()){
			
			//g.drawString(it.next(), x, y);
		}
		counter = 0;
	}
}
