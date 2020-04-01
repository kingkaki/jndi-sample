package corba_iiop;

import corba_iiop.HelloApp.HelloPOA;
import org.omg.CORBA.ORB;

public class HelloImpl extends HelloPOA {
    private ORB orb;

    public ORB getOrb() {
        return orb;
    }

    public void setOrb(ORB orb) {
        this.orb = orb;
    }

    @Override
    public String sayHello() {
        return "Hello world.(from server)";
    }
}
