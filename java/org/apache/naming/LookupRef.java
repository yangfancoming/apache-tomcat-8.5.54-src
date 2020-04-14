
package org.apache.naming;

import javax.naming.RefAddr;
import javax.naming.StringRefAddr;

/**
 * Represents a reference to lookup.
 */
public class LookupRef extends AbstractRef {

    private static final long serialVersionUID = 1L;

    /**
     * JNDI name for the lookup
     */
    public static final String LOOKUP_NAME = "lookup-name";


    public LookupRef(String resourceType, String lookupName) {
        this(resourceType, null, null, lookupName);
    }


    public LookupRef(String resourceType, String factory, String factoryLocation, String lookupName) {
        super(resourceType, factory, factoryLocation);
        if (lookupName != null && !lookupName.equals("")) {
            RefAddr ref = new StringRefAddr(LOOKUP_NAME, lookupName);
            add(ref);
        }
    }


    @Override
    protected String getDefaultFactoryClassName() {
        return org.apache.naming.factory.Constants.DEFAULT_LOOKUP_JNDI_FACTORY;
    }
}
