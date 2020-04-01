package rmi_iiop;

import rmi_iiop.common.HelloImpl;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Hashtable;

public class IIOPServer {
    public static void main(String[] args) throws Exception {
        //实例化Hello servant
        HelloImpl helloRef = new HelloImpl();
        //使用JNDI在命名服务中发布引用
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.cosnaming.CNCtxFactory");
        env.put(Context.PROVIDER_URL, "iiop://127.0.0.1:1050");
        InitialContext initialContext = new InitialContext(env);
        initialContext.rebind("HelloService", helloRef);
        System.out.println("Hello Server Ready...");
        Thread.currentThread().join();
    }
}
