
package org.apache.naming;

import javax.naming.StringRefAddr;

/**
 * Represents a reference address to a resource.
 *
 * @author Remy Maucherat
 */
public class ResourceLinkRef extends AbstractRef {

    private static final long serialVersionUID = 1L;


    /**
     * Default factory for this reference.
     */
    public static final String DEFAULT_FACTORY =
            org.apache.naming.factory.Constants.DEFAULT_RESOURCE_LINK_FACTORY;


    /**
     * Description address type.
     */
    public static final String GLOBALNAME = "globalName";


    /**
     * ResourceLink Reference.
     *
     * @param resourceClass Resource class
     * @param globalName Global name
     * @param factory The possibly null class name of the object's factory.
     * @param factoryLocation The possibly null location from which to load the
     *                        factory (e.g. URL)
     */
    public ResourceLinkRef(String resourceClass, String globalName,
            String factory, String factoryLocation) {
        super(resourceClass, factory, factoryLocation);
        StringRefAddr refAddr = null;
        if (globalName != null) {
            refAddr = new StringRefAddr(GLOBALNAME, globalName);
            add(refAddr);
        }
    }


    @Override
    protected String getDefaultFactoryClassName() {
        return DEFAULT_FACTORY;
    }
}
