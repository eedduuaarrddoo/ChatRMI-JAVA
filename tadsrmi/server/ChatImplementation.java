package tadsrmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChatImplementation extends UnicastRemoteObject implements ChatInterface {
    String lastMessage = null;

    public ChatImplementation() throws RemoteException {
    }

    public String getMessage() throws RemoteException {
        return this.lastMessage;
    }

    public void broadcast(String message) {
        this.lastMessage = message;
    }
}