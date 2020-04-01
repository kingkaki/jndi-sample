package rmi_iiop.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ObjectParamHelloInterface extends Remote {
    public void sayHello(Object from) throws RemoteException;
}
