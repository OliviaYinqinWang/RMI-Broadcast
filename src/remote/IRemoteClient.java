package remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemoteClient extends Remote{
	public void updateInfo(String msg) throws RemoteException;

}
