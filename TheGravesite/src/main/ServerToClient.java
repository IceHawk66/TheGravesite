package main;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerToClient extends Remote{
	
	public String[][] getSpielerdaten() throws RemoteException;
	public int[][] getMap() throws RemoteException;
	public void addSpieler(String name) throws RemoteException;
}