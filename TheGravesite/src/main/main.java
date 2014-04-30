package main;

import java.rmi.*;
import GUI.*;
import java.rmi.registry.*;

public class main {

	public static void main(String[] args) throws RemoteException, NotBoundException {
		Registry registry = LocateRegistry.getRegistry();
		InterfaceRawData rawdata = (InterfaceRawData) registry.lookup("InterfaceRawData");
		
		//Adder adder = (Adder) registry.lookup("Adder");
		String[][] spielerdaten;
		String[][] map;
		
		// Arrays initialisieren mit Daten vom Server
		spielerdaten = rawdata.getSpielerdaten();
		map = rawdata.getMap();
		
		GUI hauptfenster = new GUI(1280, 860);
		
		// Testausgabe
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[0].length; j++){
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
}
