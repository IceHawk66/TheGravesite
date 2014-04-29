package main;

import java.rmi.*;
import java.rmi.registry.*;

public class main {

	public static void main(String[] args) throws RemoteException, NotBoundException {
		Registry registry = LocateRegistry.getRegistry();
		InterfaceRawData rawdata = (InterfaceRawData) registry.lookup("RawData");
		
		//Adder adder = (Adder) registry.lookup("Adder");
		String[][] spielerdaten;
		String[][] map;
		
		spielerdaten = rawdata.getSpielerdaten();
		map = rawdata.getMap();
		
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[0].length; j++){
				System.out.println(map[i][j]);
			}
		}
	}
}
