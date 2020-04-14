


package org.apache.tomcat.util.digester;


import java.util.List;


/**
 * Public interface defining a collection of Rule instances (and corresponding
 * matching patterns) plus an implementation of a matching policy that selects
 * the rules that match a particular pattern of nested elements discovered
 * during parsing.
 */

public interface Rules {


    // ------------------------------------------------------------- Properties


    /**
     * @return the Digester instance with which this Rules instance is
     * associated.
     */
    public Digester getDigester();


    /**
     * Set the Digester instance with which this Rules instance is associated.
     *
     * @param digester The newly associated Digester instance
     */
    public void setDigester(Digester digester);


    /**
     * @return the namespace URI that will be applied to all subsequently
     * added <code>Rule</code> objects.
     *
     * @deprecated Unused. Will be removed in Tomcat 9
     */
    @Deprecated
    public String getNamespaceURI();


    /**
     * Set the namespace URI that will be applied to all subsequently
     * added <code>Rule</code> objects.
     *
     * @param namespaceURI Namespace URI that must match on all
     *  subsequently added rules, or <code>null</code> for matching
     *  regardless of the current namespace URI
     *
     * @deprecated Unused. Will be removed in Tomcat 9
     */
    @Deprecated
    public void setNamespaceURI(String namespaceURI);


    // --------------------------------------------------------- Public Methods


    /**
     * Register a new Rule instance matching the specified pattern.
     *
     * @param pattern Nesting pattern to be matched for this Rule
     * @param rule Rule instance to be registered
     */
    public void add(String pattern, Rule rule);


    /**
     * Clear all existing Rule instance registrations.
     */
    public void clear();


    /**
     * Return a List of all registered Rule instances that match the specified
     * nesting pattern, or a zero-length List if there are no matches.  If more
     * than one Rule instance matches, they <strong>must</strong> be returned
     * in the order originally registered through the <code>add()</code>
     * method.
     *
     * @param namespaceURI Namespace URI for which to select matching rules,
     *  or <code>null</code> to match regardless of namespace URI
     * @param pattern Nesting pattern to be matched
     * @return a rules list
     */
    public List<Rule> match(String namespaceURI, String pattern);


    /**
     * Return a List of all registered Rule instances, or a zero-length List
     * if there are no registered Rule instances.  If more than one Rule
     * instance has been registered, they <strong>must</strong> be returned
     * in the order originally registered through the <code>add()</code>
     * method.
     * @return a rules list
     */
    public List<Rule> rules();


}
