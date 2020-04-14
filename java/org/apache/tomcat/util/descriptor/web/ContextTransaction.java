


package org.apache.tomcat.util.descriptor.web;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;


/**
 * Representation of an application resource reference, as represented in
 * an <code>&lt;res-env-refy&gt;</code> element in the deployment descriptor.
 *
 * @author Craig R. McClanahan
 */
public class ContextTransaction implements Serializable {

    private static final long serialVersionUID = 1L;

    // ------------------------------------------------------------- Properties


    /**
     * Holder for our configured properties.
     */
    private final HashMap<String, Object> properties = new HashMap<>();

    /**
     * @param name The property name
     * @return a configured property.
     */
    public Object getProperty(String name) {
        return properties.get(name);
    }

    /**
     * Set a configured property.
     * @param name The property name
     * @param value The property value
     */
    public void setProperty(String name, Object value) {
        properties.put(name, value);
    }

    /**
     * Remove a configured property.
     * @param name The property name
     */
    public void removeProperty(String name) {
        properties.remove(name);
    }

    /**
     * List properties.
     * @return the property names iterator
     */
    public Iterator<String> listProperties() {
        return properties.keySet().iterator();
    }


    // --------------------------------------------------------- Public Methods


    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Transaction[");
        sb.append("]");
        return sb.toString();
    }
}
