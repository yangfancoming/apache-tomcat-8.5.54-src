
package org.apache.catalina.mbeans;

/**
 * <p>A convenience base class for <strong>ModelMBean</strong> implementations
 * where the underlying base class (and therefore the set of supported
 * properties) is different for varying implementations of a standard
 * interface.  For Catalina, that includes at least the following:
 * Connector, Logger, Realm, and Valve.  This class creates an artificial
 * MBean attribute named <code>className</code>, which reports the fully
 * qualified class name of the managed object as its value.</p>
 *
 * @param <T> The type that this bean represents.
 *
 * @author Craig R. McClanahan
 */
public class ClassNameMBean<T> extends BaseCatalinaMBean<T> {

    /**
     * Return the fully qualified Java class name of the managed object
     * for this MBean.
     */
    @Override
    public String getClassName() {
        return this.resource.getClass().getName();
    }
}
