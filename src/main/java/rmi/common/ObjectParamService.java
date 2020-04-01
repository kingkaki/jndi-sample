package rmi.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ObjectParamService extends Remote {
    Object sayHello(Object str) throws RemoteException;
}
