package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class Server {
	
	public static void main(String[] args) {
		try {
			
			ArrayList<String> player = new ArrayList<String>();
			player.add("Luke");
			
//			RemoteScrambble server = new RemoteScrambble();
//			Registry registry = LocateRegistry.getRegistry();
//			registry.bind("GameServer", server);
			Registry registry = LocateRegistry.createRegistry(8888);
			
			RemoteScrambble server = new RemoteScrambble(player);
			
			registry.rebind("GameServer", server);
			System.out.println("server ready.");
			
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
