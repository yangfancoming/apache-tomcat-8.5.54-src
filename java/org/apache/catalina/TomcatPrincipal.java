
package org.apache.catalina;

import java.security.Principal;

import org.ietf.jgss.GSSCredential;

/**
 * Defines additional methods implemented by {@link Principal}s created by
 * Tomcat's standard {@link Realm} implementations.
 */
public interface TomcatPrincipal extends Principal {

    /**
     * @return The authenticated Principal to be exposed to applications.
     */
    Principal getUserPrincipal();

    /**
     * @return The user's delegated credentials.
     */
    GSSCredential getGssCredential();

    /**
     * Calls logout, if necessary, on any associated JAASLoginContext. May in
     * the future be extended to cover other logout requirements.
     *
     * @throws Exception If something goes wrong with the logout. Uses Exception
     *                   to allow for future expansion of this method to cover
     *                   other logout mechanisms that might throw a different
     *                   exception to LoginContext
     *
     */
    void logout() throws Exception;
}
