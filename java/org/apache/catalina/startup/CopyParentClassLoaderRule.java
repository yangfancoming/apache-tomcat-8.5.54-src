


package org.apache.catalina.startup;


import java.lang.reflect.Method;

import org.apache.catalina.Container;
import org.apache.tomcat.util.digester.Rule;
import org.xml.sax.Attributes;


/**
 * <p>Rule that copies the <code>parentClassLoader</code> property from the
 * next-to-top item on the stack (which must be a <code>Container</code>)
 * to the top item on the stack (which must also be a
 * <code>Container</code>).</p>
 */
public class CopyParentClassLoaderRule extends Rule {

    // ----------------------------------------------------------- Constructors

    /**
     * Construct a new instance of this Rule.
     */
    public CopyParentClassLoaderRule() {
    }

    // --------------------------------------------------------- Public Methods

    /**
     * Handle the beginning of an XML element.
     * @param attributes The attributes of this element
     * @exception Exception if a processing error occurs
     */
    @Override
    public void begin(String namespace, String name, Attributes attributes)  throws Exception {
        if (digester.getLogger().isDebugEnabled()) digester.getLogger().debug("Copying parent class loader");
        Container child = (Container) digester.peek(0);
        Object parent = digester.peek(1);
        Method method = parent.getClass().getMethod("getParentClassLoader", new Class[0]);
        ClassLoader classLoader = (ClassLoader) method.invoke(parent, new Object[0]);
        child.setParentClassLoader(classLoader);
    }

}
