package ldap;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Hashtable;

public class LdapClient {
    public static void main(String[] args) throws Exception {
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://127.0.0.1:1389");
        Context ctx = new InitialContext(env);
        Object object = ctx.lookup("uid=kingkk,dc=example,dc=com");
        System.out.println(object.getClass());
    }
}
