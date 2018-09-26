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
			
			IRemoteScrambble GameServer = (IRemoteScrambble) Naming.lookup("rmi://localhost/GameServer");
			System.out.println("catch the stub.");
			
			int randomInt;
			new Thread(new ClientGamer("client"+ String.valueOf(randomInt =1+(int)(Math.random()*50)), GameServer)).start();
			
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RemoteException | NotBoundException e) {
				System.err.println(e.getMessage());
				//e.printStackTrace();
		}
	}
}
