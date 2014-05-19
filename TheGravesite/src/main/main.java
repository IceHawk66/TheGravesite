package main;

import java.rmi.*;

import GUI.*;

import java.rmi.registry.*;

public class main {
	
	private static int[][] map;
	private static String[][] spielerdaten;

	public static void main(String[] args) throws RemoteException, NotBoundException {
		try{
			// Registry vom Server abrufen
			Registry registry = LocateRegistry.getRegistry();
			InterfaceRawData rawdata = (InterfaceRawData) registry.lookup("InterfaceRawData");

			// Arrays initialisieren mit Daten vom Server
			spielerdaten = rawdata.getSpielerdaten();
			map = rawdata.getMap();
			
			GUI hauptfenster = new GUI(1280, 720, map);

		} catch (ConnectException e){
			System.err.println("Die Verbindung zum Server schlug fehl. Ist der Server gestartet?");
		}
	}

    public static int[][] getMap(){
    	return map;
    }
    
}
