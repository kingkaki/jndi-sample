package rmi_iiop;

import rmi_iiop.common.ObjectParamHelloImpl;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Hashtable;

public class ObjectParamIIOPServer {
    public static void main(String[] args) throws Exception {
        //实例化Hello servant
        ObjectParamHelloImpl helloRef = new ObjectParamHelloImpl();
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
