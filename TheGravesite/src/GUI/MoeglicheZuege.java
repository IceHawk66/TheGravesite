package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
	private int[][] aktivemap;
	
	public MoeglicheZuege(ServerToClient stc, JFrame owner){
		this.stc = stc;
		this.owner = owner;
		this.setPreferredSize(new Dimension((int)(owner.getWidth()*0.66),(int)(owner.getHeight()*0.25)));
		this.setVisible(true);
	}
	
	public void paint(Graphics g){
		int spielerX = -1;
		int spielerY = -1;
		int counter = 1;
		List<String> spielzuege = new LinkedList<String>();

		try {
			aktivemap = stc.getAktiveMap();
			spielerX = stc.getSpielerX();
			spielerY = stc.getSpielerY();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}

		g.setColor(Color.black);
		g.fillRect(0, 0, 10000,10000);
		g.setColor(Color.white);
		
		if(spielerX == -1 || spielerY == -1){
			g.setColor(Color.red);
			g.drawString("ERROR", (int)(this.getWidth()*0.1), (int)(this.getHeight()*0.1));
			System.err.println("Die aktuellen Spielerkoordinaten konnten nicht abgerufen werden.");
		}
		else{
			if(aktivemap[spielerY][spielerX-1] == 2){
				spielzuege.add("Schritt nach links");
			}
			if(aktivemap[spielerY][spielerX+1] == 2){
				spielzuege.add("Schritt nach rechts");
			}
			if(aktivemap[spielerY-1][spielerX] == 2){
				spielzuege.add("Schritt nach oben");
			}
			if(aktivemap[spielerY+1][spielerX] == 2){
				spielzuege.add("Schritt nach unten");
			}
			if(aktivemap[spielerY][spielerX-1] == 6){
				spielzuege.add("Aktion ausführen");
			}
			if(aktivemap[spielerY][spielerX+1] == 6){
				spielzuege.add("Aktion ausführen");
			}
			if(aktivemap[spielerY-1][spielerX] == 6){
				spielzuege.add("Aktion ausführen");
			}
			if(aktivemap[spielerY+1][spielerX] == 6){
				spielzuege.add("Aktion ausführen");
			}

			Iterator<String> it = spielzuege.iterator();
			g.setFont(new Font("Arial", Font.PLAIN, 18));
			g.drawString("Mögliche Operationen:", (int)(this.getWidth()*0.1), (int)(this.getHeight()*0.2));
			while(it.hasNext()){
				String s = it.next();
				g.drawString(s, (int)(this.getWidth()*0.1), (int)((this.getHeight()*0.2) + (counter++ * g.getFont().getSize())));
			}
			counter = 1;
		}
	}
}
