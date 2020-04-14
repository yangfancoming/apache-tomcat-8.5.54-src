
package org.apache.naming;

/**
 * Represents a reference address to a transaction.
 *
 * @author Remy Maucherat
 */
public class TransactionRef extends AbstractRef {

    private static final long serialVersionUID = 1L;


    /**
     * Default factory for this reference.
     */
    public static final String DEFAULT_FACTORY =
            org.apache.naming.factory.Constants.DEFAULT_TRANSACTION_FACTORY;


    /**
     * Resource Reference.
     */
    public TransactionRef() {
        this(null, null);
    }


    /**
     * Resource Reference.
     *
     * @param factory The factory class
     * @param factoryLocation The factory location
     */
    public TransactionRef(String factory, String factoryLocation) {
        super("javax.transaction.UserTransaction", factory, factoryLocation);
    }


    @Override
    protected String getDefaultFactoryClassName() {
        return DEFAULT_FACTORY;
    }
}
