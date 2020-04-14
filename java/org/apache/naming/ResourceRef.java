
package org.apache.naming;

import javax.naming.StringRefAddr;

/**
 * Represents a reference address to a resource.
 *
 * @author Remy Maucherat
 */
public class ResourceRef extends AbstractRef {

    private static final long serialVersionUID = 1L;


    /**
     * Default factory for this reference.
     */
    public static final String DEFAULT_FACTORY =
            org.apache.naming.factory.Constants.DEFAULT_RESOURCE_FACTORY;


    /**
     * Description address type.
     */
    public static final String DESCRIPTION = "description";


    /**
     * Scope address type.
     */
    public static final String SCOPE = "scope";


    /**
     * Auth address type.
     */
    public static final String AUTH = "auth";


    /**
     * Is this resource a singleton
     */
    public static final String SINGLETON = "singleton";


    /**
     * Resource Reference.
     *
     * @param resourceClass Resource class
     * @param description Description of the resource
     * @param scope Resource scope
     * @param auth Resource authentication
     * @param singleton Is this resource a singleton (every lookup should return
     *                  the same instance rather than a new instance)?
     */
    public ResourceRef(String resourceClass, String description,
                       String scope, String auth, boolean singleton) {
        this(resourceClass, description, scope, auth, singleton, null, null);
    }


    /**
     * Resource Reference.
     *
     * @param resourceClass Resource class
     * @param description Description of the resource
     * @param scope Resource scope
     * @param auth Resource authentication
     * @param singleton Is this resource a singleton (every lookup should return
     *                  the same instance rather than a new instance)?
     * @param factory The possibly null class name of the object's factory.
     * @param factoryLocation The possibly null location from which to load the
     *                        factory (e.g. URL)
     */
    public ResourceRef(String resourceClass, String description,
                       String scope, String auth, boolean singleton,
                       String factory, String factoryLocation) {
        super(resourceClass, factory, factoryLocation);
        StringRefAddr refAddr = null;
        if (description != null) {
            refAddr = new StringRefAddr(DESCRIPTION, description);
            add(refAddr);
        }
        if (scope != null) {
            refAddr = new StringRefAddr(SCOPE, scope);
            add(refAddr);
        }
        if (auth != null) {
            refAddr = new StringRefAddr(AUTH, auth);
            add(refAddr);
        }
        // singleton is a boolean so slightly different handling
        refAddr = new StringRefAddr(SINGLETON, Boolean.toString(singleton));
        add(refAddr);
    }


    @Override
    protected String getDefaultFactoryClassName() {
        return DEFAULT_FACTORY;
    }
}
