
package org.apache.catalina.realm;

import java.security.cert.X509Certificate;

/**
 * Provides an interface for retrieving a user name from an X509Certificate.
 */
public interface X509UsernameRetriever {
    /**
     * Gets a user name from an X509Certificate.
     *
     * @param cert The certificate containing the user name.
     * @return An appropriate user name obtained from one or more fields
     *         in the certificate.
     */
    public String getUsername(X509Certificate cert);
}
