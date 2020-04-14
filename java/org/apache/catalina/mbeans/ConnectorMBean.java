
package org.apache.catalina.mbeans;

import javax.management.Attribute;
import javax.management.AttributeNotFoundException;
import javax.management.MBeanException;
import javax.management.ReflectionException;
import javax.management.RuntimeOperationsException;

import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.IntrospectionUtils;

/**
 * <p>A <strong>ModelMBean</strong> implementation for the
 * <code>org.apache.coyote.tomcat5.CoyoteConnector</code> component.</p>
 *
 * @author Amy Roh
 */
public class ConnectorMBean extends ClassNameMBean<Connector> {

    /**
     * Obtain and return the value of a specific attribute of this MBean.
     *
     * @param name Name of the requested attribute
     *
     * @exception AttributeNotFoundException if this attribute is not
     *  supported by this MBean
     * @exception MBeanException if the initializer of an object
     *  throws an exception
     * @exception ReflectionException if a Java reflection exception
     *  occurs when invoking the getter
     */
    @Override
    public Object getAttribute(String name) throws AttributeNotFoundException, MBeanException,
            ReflectionException {

        // Validate the input parameters
        if (name == null) {
            throw new RuntimeOperationsException(
                    new IllegalArgumentException("Attribute name is null"),
                    "Attribute name is null");
        }

        Connector connector = doGetManagedResource();
        return IntrospectionUtils.getProperty(connector, name);
    }


    /**
     * Set the value of a specific attribute of this MBean.
     *
     * @param attribute The identification of the attribute to be set
     *  and the new value
     *
     * @exception AttributeNotFoundException if this attribute is not
     *  supported by this MBean
     * @exception MBeanException if the initializer of an object
     *  throws an exception
     * @exception ReflectionException if a Java reflection exception
     *  occurs when invoking the getter
     */
     @Override
    public void setAttribute(Attribute attribute) throws AttributeNotFoundException, MBeanException,
            ReflectionException {

        // Validate the input parameters
        if (attribute == null) {
            throw new RuntimeOperationsException(new IllegalArgumentException(
                    "Attribute is null"), "Attribute is null");
        }
        String name = attribute.getName();
        Object value = attribute.getValue();
        if (name == null) {
            throw new RuntimeOperationsException(
                    new IllegalArgumentException("Attribute name is null"),
                    "Attribute name is null");
        }

        Connector connector = doGetManagedResource();
        IntrospectionUtils.setProperty(connector, name, String.valueOf(value));
    }
}
