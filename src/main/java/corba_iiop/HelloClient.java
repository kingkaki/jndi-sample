package corba_iiop;

import corba_iiop.HelloApp.Hello;
import corba_iiop.HelloApp.HelloHelper;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Hashtable;
import java.util.Properties;

public class HelloClient {
    public static void main(String[] args) throws Exception {
        Properties prop = new Properties();
        prop.put("org.omg.CORBA.ORBInitialHost", "127.0.0.1");
        prop.put("org.omg.CORBA.ORBInitialPort", "1050");
        ORB orb = ORB.init(args, null);

        Hello helloImpl = getStubFromORB1(orb);

        System.out.println(helloImpl.sayHello());
    }

    public static Hello getStubFromNameService(ORB orb) throws Exception {

        org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
        NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

        String name = "Hello";
        // helloImpl的类型为_HelloStub，而不是真正的helloImpl
        return HelloHelper.narrow(ncRef.resolve_str(name));
    }

    public static Hello getStubFromORB1(ORB orb) {
        org.omg.CORBA.Object obj = orb.string_to_object("corbaname::127.0.0.1:1050#Hello");
        return HelloHelper.narrow(obj);
    }

    public static Hello getStubFromORB2(ORB orb) throws Exception {
        org.omg.CORBA.Object obj = orb.string_to_object("corbaloc::127.0.0.1:1050");
        NamingContextExt ncRef = NamingContextExtHelper.narrow(obj);
        return HelloHelper.narrow(ncRef.resolve_str("Hello"));
    }

    public static Hello getStubFromJNDI(ORB orb) throws Exception {
        Hashtable env = new Hashtable();
        env.put("java.naming.corba.orb", orb);
        Context ic = new InitialContext(env);
        return HelloHelper.narrow((org.omg.CORBA.Object) ic.lookup("corbaname::127.0.0.1:1050#Hello"));
    }


}
