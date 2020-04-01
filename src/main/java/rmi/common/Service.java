package rmi.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Service extends Remote {
    Object sayHello(String str) throws RemoteException;
}