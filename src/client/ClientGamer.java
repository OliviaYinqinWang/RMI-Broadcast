package client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import remote.IRemoteClient;
import remote.IRemoteScrambble;

public class ClientGamer extends UnicastRemoteObject implements IRemoteClient,Runnable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private IRemoteScrambble server;
	
	
	protected ClientGamer(String name, IRemoteScrambble server) throws RemoteException {
		this.name = name;
		this.server = server;
		server.regeister(this);
	}
	
	@Override
	public void updateInfo(String msg) throws RemoteException {
		System.out.println(msg);
	}
	
	@Override
	public void run() {
		Scanner keyboard = new Scanner(System.in);
		String msg = null;
		while (true) {
			System.out.println("please enter message: ");
			msg = keyboard.nextLine();
			try {
				server.broadcast(name+": " + msg);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}




}
