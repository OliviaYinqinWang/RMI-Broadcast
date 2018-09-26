package server;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
	
	public static void main(String[] args) {
		try {
//			RemoteScrambble server = new RemoteScrambble();
//			Registry registry = LocateRegistry.getRegistry();
//			registry.bind("GameServer", server);
			
			RemoteScrambble server = new RemoteScrambble();
			Naming.rebind("GameServer", server);

			System.out.println("server ready.");
			
			while(true) {
				for(int i =0; i< server.getClientList().size();i++) {

				}
			}
			
//		} catch (AlreadyBoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		} catch (RemoteException | MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
