package main;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerToClient extends Remote{
	
	public String[][] getSpielerdaten() throws RemoteException;
	public int[][] getAktiveMap() throws RemoteException;
	public void addSpieler(String name) throws RemoteException;
	public void bewegeMap(int richtung) throws RemoteException;
	public void erstelleNeueMap() throws RemoteException;
	public int getSpielerX() throws RemoteException;
	public int getSpielerY() throws RemoteException;
}