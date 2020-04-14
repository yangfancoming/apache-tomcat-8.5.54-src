

package org.apache.tomcat.dbcp.dbcp2.datasources;

import javax.naming.RefAddr;
import javax.naming.Reference;

/**
 * A JNDI ObjectFactory which creates <code>SharedPoolDataSource</code>s
 *
 * @since 2.0
 */
public class SharedPoolDataSourceFactory extends InstanceKeyDataSourceFactory {
    private static final String SHARED_POOL_CLASSNAME = SharedPoolDataSource.class.getName();

    @Override
    protected boolean isCorrectClass(final String className) {
        return SHARED_POOL_CLASSNAME.equals(className);
    }

    @Override
    protected InstanceKeyDataSource getNewInstance(final Reference ref) {
        final SharedPoolDataSource spds = new SharedPoolDataSource();
        final RefAddr ra = ref.get("maxTotal");
        if (ra != null && ra.getContent() != null) {
            spds.setMaxTotal(Integer.parseInt(ra.getContent().toString()));
        }
        return spds;
    }
}
