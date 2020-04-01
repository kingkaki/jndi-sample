package rmi;

import rmi.common.Service;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Hashtable;

public class RMIClient {
    public static void main(String[] args) throws Exception {
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
        env.put(Context.PROVIDER_URL, "rmi://localhost:1099");
        Context ctx = new InitialContext(env);
        Service service = (Service) ctx.lookup("hello");
        System.out.println(service.sayHello("client"));

    }
}
