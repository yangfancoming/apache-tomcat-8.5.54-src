
package org.apache.naming.factory;

import javax.naming.NamingException;
import javax.naming.Reference;
import javax.naming.spi.ObjectFactory;

import org.apache.naming.ResourceRef;
import org.apache.naming.StringManager;

/**
 * Object factory for Resources.
 *
 * @author Remy Maucherat
 */
public class ResourceFactory extends FactoryBase {

    private static final StringManager sm = StringManager.getManager(ResourceFactory.class);

    @Override
    protected boolean isReferenceTypeSupported(Object obj) {
        return obj instanceof ResourceRef;
    }

    @Override
    protected ObjectFactory getDefaultFactory(Reference ref) throws NamingException {

        ObjectFactory factory = null;

        if (ref.getClassName().equals("javax.sql.DataSource")) {
            String javaxSqlDataSourceFactoryClassName =
                System.getProperty("javax.sql.DataSource.Factory",
                        Constants.DBCP_DATASOURCE_FACTORY);
            try {
                factory = (ObjectFactory) Class.forName(
                        javaxSqlDataSourceFactoryClassName).getConstructor().newInstance();
            } catch (Exception e) {
                NamingException ex = new NamingException(sm.getString("resourceFactory.factoryCreationError"));
                ex.initCause(e);
                throw ex;
            }
        } else if (ref.getClassName().equals("javax.mail.Session")) {
            String javaxMailSessionFactoryClassName =
                System.getProperty("javax.mail.Session.Factory",
                        "org.apache.naming.factory.MailSessionFactory");
            try {
                factory = (ObjectFactory) Class.forName(
                        javaxMailSessionFactoryClassName).getConstructor().newInstance();
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
                NamingException ex = new NamingException(sm.getString("resourceFactory.factoryCreationError"));
                ex.initCause(t);
                throw ex;
            }
        }

        return factory;
    }

    @Override
    protected Object getLinked(Reference ref) {
        // Not supported
        return null;
    }
}
