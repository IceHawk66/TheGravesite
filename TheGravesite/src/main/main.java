package main;

import java.rmi.*;
import GUI.*;
import java.rmi.registry.*;

public class main {

	public static void main(String[] args) throws RemoteException, NotBoundException {
		try{
			Registry registry = LocateRegistry.getRegistry();
			InterfaceRawData rawdata = (InterfaceRawData) registry.lookup("InterfaceRawData");
			
			String[][] spielerdaten;
			String[][] map;
			
			// Arrays initialisieren mit Daten vom Server
			spielerdaten = rawdata.getSpielerdaten();
			map = rawdata.getMap();
			
			GUI hauptfenster = new GUI(1280, 720);
			
			// Testausgabe
			/*for(int i = 0; i < map.length; i++){
				for(int j = 0; j < map[0].length; j++){
					System.out.print(map[i][j]);
				}
				System.out.println();
			}*/
		} catch (ConnectException e){
			System.err.println("Die Verbindung zum Server schlug fehl. Ist der Server gestartet?");
		}
		
	}
}
