package rmi.common;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ObjectParamServiceImpl extends UnicastRemoteObject implements ObjectParamService {

    public ObjectParamServiceImpl() throws RemoteException {
    }

    @Override
    public Object sayHello(Object str) throws RemoteException {
        return "hello " + str;
    }
}
