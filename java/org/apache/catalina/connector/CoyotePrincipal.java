
package org.apache.catalina.connector;

import java.io.Serializable;
import java.security.Principal;

/**
 * Generic implementation of <strong>java.security.Principal</strong> that
 * is used to represent principals authenticated at the protocol handler level.
 *
 * @author Remy Maucherat
 */
public class CoyotePrincipal implements Principal, Serializable {

    private static final long serialVersionUID = 1L;


    // ----------------------------------------------------------- Constructors

    public CoyotePrincipal(String name) {

        this.name = name;

    }


    // ------------------------------------------------------------- Properties


    /**
     * The username of the user represented by this Principal.
     */
    protected final String name;

    @Override
    public String getName() {
        return this.name;
    }


    // --------------------------------------------------------- Public Methods


    /**
     * Return a String representation of this object, which exposes only
     * information that should be public.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("CoyotePrincipal[");
        sb.append(this.name);
        sb.append("]");
        return sb.toString();
    }


}
