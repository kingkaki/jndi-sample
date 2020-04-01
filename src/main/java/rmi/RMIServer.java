package rmi;

import rmi.common.ServiceImpl;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.bind("hello", new ServiceImpl());

        System.out.println("RMI Server start...");
        Thread.currentThread().join();
    }
}
