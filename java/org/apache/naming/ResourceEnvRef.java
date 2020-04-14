
package org.apache.naming;

/**
 * Represents a reference address to a resource environment.
 *
 * @author Remy Maucherat
 */
public class ResourceEnvRef extends AbstractRef {

    private static final long serialVersionUID = 1L;


    /**
     * Default factory for this reference.
     */
    public static final String DEFAULT_FACTORY =
            org.apache.naming.factory.Constants.DEFAULT_RESOURCE_ENV_FACTORY;


    /**
     * Resource env reference.
     *
     * @param resourceType Type
     */
    public ResourceEnvRef(String resourceType) {
        super(resourceType);
    }


    @Override
    protected String getDefaultFactoryClassName() {
        return DEFAULT_FACTORY;
    }
}
