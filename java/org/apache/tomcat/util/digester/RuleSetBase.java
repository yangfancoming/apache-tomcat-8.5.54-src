
package org.apache.tomcat.util.digester;

/**
 * <p>Convenience base class that implements the {@link RuleSet} interface.
 * Concrete implementations should list all of their actual rule creation
 * logic in the <code>addRuleSet()</code> implementation.</p>
 *
 * @deprecated Unnecessary once deprecated methods are removed. Will be removed
 *             in Tomcat 9.
 */
@Deprecated
public abstract class RuleSetBase implements RuleSet {

    // ----------------------------------------------------- Instance Variables

    /**
     * The namespace URI that all Rule instances created by this RuleSet
     * will be associated with.
     *
     * @deprecated Unused. This will be removed in Tomcat 9.
     */
    @Deprecated
    protected String namespaceURI = null;


    // ------------------------------------------------------------- Properties

    /**
     * Return the namespace URI that will be applied to all Rule instances
     * created from this RuleSet.
     *
     * @deprecated Unused. This will be removed in Tomcat 9.
     */
    @Deprecated
    @Override
    public String getNamespaceURI() {
        return this.namespaceURI;
    }


    // --------------------------------------------------------- Public Methods

    /**
     * Add the set of Rule instances defined in this RuleSet to the
     * specified <code>Digester</code> instance, associating them with
     * our namespace URI (if any).  This method should only be called
     * by a Digester instance.
     *
     * @param digester Digester instance to which the new Rule instances
     *  should be added.
     */
    @Override
    public abstract void addRuleInstances(Digester digester);
}
