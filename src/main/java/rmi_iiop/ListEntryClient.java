package rmi_iiop;

import javax.naming.*;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class ListEntryClient {
    public static void main(String[] args) throws Exception {
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.cosnaming.CNCtxFactory");
        env.put(Context.PROVIDER_URL, "iiop://127.0.0.1:1050");
        Context ctx = new InitialContext(env);
        Map map = listAllEntries(ctx);
    }

    private static Map listAllEntries(Context initialContext) throws NamingException {
        String namespace = initialContext instanceof InitialContext ? initialContext.getNameInNamespace() : "";
        HashMap<String, Object> map = new HashMap<String, Object>();
        System.out.println("> Listing namespace: " + namespace);
        NamingEnumeration<NameClassPair> list = initialContext.list(namespace);
        while (list.hasMoreElements()) {
            NameClassPair next = list.next();
            String name = next.getName();
            String jndiPath = namespace + name;
            HashMap<String, Object> lookup = new HashMap<String, Object>();
            try {
                System.out.println("> Looking up name: " + jndiPath);
                Object tmp = initialContext.lookup(jndiPath);
                if (tmp instanceof Context) {
                    lookup.put("class", tmp.getClass());
                    lookup.put("interfaces", tmp.getClass().getInterfaces());
                    Map<String, Object> entries = listAllEntries((Context) tmp);
                    for (Map.Entry<String, Object> entry : entries.entrySet()) {
                        String key = entry.getKey();
                        if (key != null) {
                            lookup.put(key, entries.get(key));
                            break;
                        }
                    }
                } else {
                    lookup.put("class", tmp.getClass());
                    lookup.put("interfaces", tmp.getClass().getInterfaces());
                }
            } catch (Throwable t) {
                lookup.put("error msg", t.toString());
                Object tmp = initialContext.lookup(jndiPath);
                lookup.put("class", tmp.getClass());
                lookup.put("interfaces", tmp.getClass().getInterfaces());
            }
            map.put(name, lookup);

        }
        return map;
    }
}
