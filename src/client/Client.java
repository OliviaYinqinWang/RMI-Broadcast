package client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.security.auth.x500.X500Principal;
import javax.xml.stream.events.StartDocument;

import remote.IRemoteScrambble;

public class Client {
	
	public static void main(String[] args) {
		try {
//			Registry registry = LocateRegistry.getRegistry("localhost");
//			IRemoteScrambble server = (IRemoteScrambble) registry.lookup("GameServer");
			
			Registry registry = LocateRegistry.getRegistry("175.33.221.72", 7777);
			IRemoteScrambble GameServer = (IRemoteScrambble) registry.lookup("GameServer");
			System.out.println("catch the stub.");
			
			int randomInt;
			new Thread(new ClientGamer("client"+ String.valueOf(randomInt =1+(int)(Math.random()*50)), GameServer)).start();
			
			} catch (RemoteException | NotBoundException e) {
				System.err.println(e.getMessage());
				//e.printStackTrace();
		}
	}
}
