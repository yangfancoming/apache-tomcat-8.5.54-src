
package org.apache.naming.factory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.RefAddr;
import javax.naming.Reference;
import javax.naming.spi.ObjectFactory;

import org.apache.naming.EjbRef;

/**
 * Object factory for EJBs.
 *
 * @author Remy Maucherat
 */
public class EjbFactory extends FactoryBase {

    @Override
    protected boolean isReferenceTypeSupported(Object obj) {
        return obj instanceof EjbRef;
    }

    @Override
    protected ObjectFactory getDefaultFactory(Reference ref) throws NamingException {

        ObjectFactory factory;
        String javaxEjbFactoryClassName = System.getProperty(
                "javax.ejb.Factory", Constants.OPENEJB_EJB_FACTORY);
        try {
            factory = (ObjectFactory)
                Class.forName(javaxEjbFactoryClassName).getConstructor().newInstance();
        } catch(Throwable t) {
            if (t instanceof NamingException) {
                throw (NamingException) t;
            }
            if (t instanceof ThreadDeath) {
                throw (ThreadDeath) t;
            }
            if (t instanceof VirtualMachineError) {
                throw (VirtualMachineError) t;
            }
            NamingException ex = new NamingException
                ("Could not create resource factory instance");
            ex.initCause(t);
            throw ex;
        }
        return factory;
    }

    @Override
    protected Object getLinked(Reference ref) throws NamingException {
        // If ejb-link has been specified, resolving the link using JNDI
        RefAddr linkRefAddr = ref.get(EjbRef.LINK);
        if (linkRefAddr != null) {
            // Retrieving the EJB link
            String ejbLink = linkRefAddr.getContent().toString();
            Object beanObj = (new InitialContext()).lookup(ejbLink);
            return beanObj;
        }
        return null;
    }
}
