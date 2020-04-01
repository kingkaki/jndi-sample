package rmi.common;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServiceImpl extends UnicastRemoteObject implements Service {

    public ServiceImpl() throws RemoteException {
    }

    @Override
    public Object sayHello(String str) throws RemoteException {
        return "hello " + str;
    }
}
