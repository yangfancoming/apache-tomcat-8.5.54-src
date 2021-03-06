
package org.apache.catalina.mbeans;

import javax.management.Attribute;
import javax.management.AttributeNotFoundException;
import javax.management.MBeanException;
import javax.management.ReflectionException;
import javax.management.RuntimeOperationsException;

import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.apache.tomcat.util.descriptor.web.NamingResources;

/**
 * <p>A <strong>ModelMBean</strong> implementation for the
 * <code>org.apache.tomcat.util.descriptor.web.ContextResource</code> component.</p>
 *
 * @author Amy Roh
 */
public class ContextResourceMBean extends BaseCatalinaMBean<ContextResource> {

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

        ContextResource cr = doGetManagedResource();

        String value = null;
        if ("auth".equals(name)) {
            return cr.getAuth();
        } else if ("description".equals(name)) {
            return cr.getDescription();
        } else if ("name".equals(name)) {
            return cr.getName();
        } else if ("scope".equals(name)) {
            return cr.getScope();
        } else if ("type".equals(name)) {
            return cr.getType();
        } else {
            value = (String) cr.getProperty(name);
            if (value == null) {
                throw new AttributeNotFoundException
                    ("Cannot find attribute [" + name + "]");
            }
        }

        return value;
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
            throw new RuntimeOperationsException(
                    new IllegalArgumentException("Attribute is null"),
                    "Attribute is null");
        }
        String name = attribute.getName();
        Object value = attribute.getValue();
        if (name == null) {
            throw new RuntimeOperationsException(
                    new IllegalArgumentException("Attribute name is null"),
                    "Attribute name is null");
        }

        ContextResource cr = doGetManagedResource();

        if ("auth".equals(name)) {
            cr.setAuth((String)value);
        } else if ("description".equals(name)) {
            cr.setDescription((String)value);
        } else if ("name".equals(name)) {
            cr.setName((String)value);
        } else if ("scope".equals(name)) {
            cr.setScope((String)value);
        } else if ("type".equals(name)) {
            cr.setType((String)value);
        } else {
            cr.setProperty(name, "" + value);
        }

        // cannot use side-effects.  It's removed and added back each time
        // there is a modification in a resource.
        NamingResources nr = cr.getNamingResources();
        nr.removeResource(cr.getName());
        nr.addResource(cr);
    }
}
