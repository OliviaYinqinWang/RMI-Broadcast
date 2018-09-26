package remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

import client.ClientGamer;

public interface IRemoteScrambble extends Remote {
	public void submit(String character) throws RemoteException;
	public void regeister(IRemoteClient client) throws RemoteException;
	
	public void broadcast(String msg) throws RemoteException;
}
