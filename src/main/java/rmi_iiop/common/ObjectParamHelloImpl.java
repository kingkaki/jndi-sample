package rmi_iiop.common;

import javax.rmi.PortableRemoteObject;
import java.rmi.RemoteException;

public class ObjectParamHelloImpl extends PortableRemoteObject implements ObjectParamHelloInterface  {
    public ObjectParamHelloImpl() throws java.rmi.RemoteException {
        super();     // invoke rmi linking and remote object initialization
    }

    public void sayHello(Object from) throws RemoteException {
        System.out.println("Hello from " + from + "!!");
        System.out.flush();
    }
}