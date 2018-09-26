package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import client.ClientGamer;
import remote.IRemoteClient;
import remote.IRemoteScrambble;

public class RemoteScrambble extends UnicastRemoteObject implements IRemoteScrambble {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<IRemoteClient> clientList = new ArrayList();
	
	protected RemoteScrambble() throws RemoteException {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void submit(String character) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void regeister(IRemoteClient client) throws RemoteException {
		// TODO Auto-generated method stub
		this.clientList.add(client);
	}
	
	@Override
	public void broadcast(String msg) throws RemoteException {
		// TODO Auto-generated method stub
		for (int i = 0; i<clientList.size();i++) {
			clientList.get(i).updateInfo(msg);;
		}
	}	
	
	public ArrayList<IRemoteClient> getClientList(){
		return clientList;
	}
}
