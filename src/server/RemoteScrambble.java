package server;

import java.awt.Button;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

import client.ClientGamer;
import remote.IRemoteClient;
import remote.IRemoteScrambble;

public class RemoteScrambble extends UnicastRemoteObject implements IRemoteScrambble {

	private static final long serialVersionUID = 1L;
	private ArrayList<IRemoteClient> clientList = new ArrayList();
	HashMap<Integer, Button> buttons;
	HashMap<Integer, Boolean> buttonStatus;
	private enum GameState {Initial, Pending,InGaming, Finished};
	private GameState currentState;
	
	protected RemoteScrambble() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		currentState = GameState.Initial;
	}

	@Override
	public void submit(String character) throws RemoteException {
		
	}

	@Override
	public void regeister(IRemoteClient client) throws RemoteException {
		this.clientList.add(client);
//		if (currentState == GameState.Initial) {
//			buttons = client.getButtons();
//			buttonStatus = client.getStatus();
//			currentState = GameState.Pending;
//		}
		
	}
	
	@Override
	public void broadcast(String msg) throws RemoteException {
		for (int i = 0; i<clientList.size();i++) {
			clientList.get(i).updateInfo(msg);;
		}
	}	
	
}
