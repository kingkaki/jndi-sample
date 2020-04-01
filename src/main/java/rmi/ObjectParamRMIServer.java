package rmi;

import rmi.common.ObjectParamServiceImpl;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ObjectParamRMIServer {
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.bind("hello", new ObjectParamServiceImpl());

        System.out.println("Object Param RMI Server start...");
        Thread.currentThread().join();
    }
}
