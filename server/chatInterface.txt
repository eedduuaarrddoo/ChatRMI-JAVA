package tadsrmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatInterface extends Remote {
    String getMessage() throws RemoteException;

    void broadcast(String var1) throws RemoteException;
}