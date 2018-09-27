package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import sun.security.krb5.internal.HostAddress;
import sun.security.util.HostnameChecker;

public class Server {
	
	public static void main(String[] args) {
		try {
			
			
			Registry registry = LocateRegistry.createRegistry(7777);
			RemoteScrambble server = new RemoteScrambble();
			registry.rebind("GameServer", server);

			System.out.println("server ready.");
			
			/* begin the game automatically when the online client number >2 */
//			if (clientManager.getOnlineNum() == 2) {
//			}
			
			
			
			
			
//		} catch (AlreadyBoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
