
package org.apache.catalina.realm;

import java.security.cert.X509Certificate;

/**
 * An X509UsernameRetriever that returns a certificate's entire
 * SubjectDN as the username.
 */
public class X509SubjectDnRetriever implements X509UsernameRetriever {

    @Override
    public String getUsername(X509Certificate clientCert) {
        return clientCert.getSubjectDN().getName();
    }
}
