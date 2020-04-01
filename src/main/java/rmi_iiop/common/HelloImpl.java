package rmi_iiop.common;

import javax.rmi.PortableRemoteObject;
import java.rmi.RemoteException;

public class HelloImpl extends PortableRemoteObject implements HelloInterface {
    public HelloImpl() throws java.rmi.RemoteException {
        super();     // invoke rmi linking and remote object initialization
    }

    public void sayHello(String from) throws RemoteException {
        System.out.println("Hello from " + from + "!!");
        System.out.flush();
    }
}