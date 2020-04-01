package corba_iiop;

import corba_iiop.HelloApp.Hello;
import corba_iiop.HelloApp.HelloHelper;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import java.util.Properties;

public class HelloServer  {
    public static void main(String[] args) throws Exception {
        Properties prop = new Properties();
        prop.put("org.omg.CORBA.ORBInitialHost", "127.0.0.1");
        prop.put("org.omg.CORBA.ORBInitialPort", "1050");

        ORB orb = ORB.init(args, prop);
        POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
        rootpoa.the_POAManager().activate();
        HelloImpl helloImpl = new HelloImpl();
        helloImpl.setOrb(orb);

        org.omg.CORBA.Object ref = rootpoa.servant_to_reference(helloImpl);
        Hello href = HelloHelper.narrow(ref);

        org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
        NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

        String name = "Hello";
        NameComponent path[] = ncRef.to_name(name);
        ncRef.rebind(path, href);
        System.out.println("Server start...");
        orb.run();

    }
}
