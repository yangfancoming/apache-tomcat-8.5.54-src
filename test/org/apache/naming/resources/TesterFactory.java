
package org.apache.naming.resources;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.Reference;
import javax.naming.spi.ObjectFactory;

public class TesterFactory implements ObjectFactory {

    @Override
    public Object getObjectInstance(Object obj, Name name, Context nameCtx,
            Hashtable<?,?> environment) throws Exception {

        if (obj instanceof Reference) {
            Reference ref = (Reference)obj;
            String className = ref.getClassName();

            if (className == null) {
                throw new RuntimeException();
            }

            if (className.equals("org.apache.naming.resources.TesterObject")) {
                ClassLoader cl = Thread.currentThread().getContextClassLoader();
                Class<?> clazz =
                    cl.loadClass("org.apache.naming.resources.TesterObject");
                return clazz.getConstructor().newInstance();
            }
        }
        return null;
    }
}
